package com.noto0648.stations.client.gui.control;

import com.noto0648.stations.client.gui.IGui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.opengl.Display;

public class ControlTextBox extends Control {
  private String text = "";
  
  private int cursorCounter;
  
  private int cursorPosition;
  
  private int selectStart = -1;
  
  private int selectEnd = -1;
  
  private ControlButton buttonEdit;
  
  private ControlContextMenu menu;
  
  private Frame inputWindow;
  
  private String inputLimit = "";
  
  public ControlTextBox(IGui gui) {
    super(gui);
  }
  
  public ControlTextBox(IGui gui, int x, int y, int w, int h) {
    super(gui);
    this.locationX = x;
    this.locationY = y;
    this.width = w;
    this.height = h;
    this.buttonEdit = new ControlButton(getGui(), this.locationX + this.width - this.height, this.locationY, this.height, this.height, "E") {
        public void onButtonClick(int button) {
          if (button == 0) {
            playClickSound();
            ControlTextBox.this.showEditWindow();
          } 
        }
      };
    this.menu = new ControlContextMenu(getGui(), new String[] { "Cut", "Copy", "Paste", "Select All" }) {
        public void dataClick(int id) {
          if (id == 1) {
            if (ControlTextBox.this.getSelectText() != null)
              GuiScreen.setClipboardString(ControlTextBox.this.getSelectText()); 
          } else if (id == 2) {
            ControlTextBox.this.setText(GuiScreen.getClipboardString());
          } else if (id == 0) {
            if (ControlTextBox.this.getSelectText() != null) {
              GuiScreen.setClipboardString(ControlTextBox.this.getSelectText());
              ControlTextBox.this.setText("");
            } 
          } else if (id == 3) {
            ControlTextBox.this.selectStart = 0;
            ControlTextBox.this.selectEnd = ControlTextBox.this.text.length();
            ControlTextBox.this.cursorPositionCheck();
          } 
        }
      };
  }
  
  public void initGui() {
    this.buttonEdit.initGui();
    this.menu.initGui();
    this.buttonEdit.setLocation(this.locationX + this.width - this.height, this.locationY);
    this.buttonEdit.setSize(this.height, this.height);
  }
  
  public void update() {
    if (this.isFocus && this.isEnable) {
      this.cursorCounter++;
      if (this.cursorCounter == 20)
        this.cursorCounter = 0; 
    } else {
      closeWindow();
    } 
    this.buttonEdit.update();
    this.menu.update();
  }
  
  public void draw(int mouseX, int mouseY) {
    drawRect(this.locationX, this.locationY, this.locationX + this.width, this.locationY + this.height, -16777216);
    drawRect(this.locationX, this.locationY, this.locationX + this.width, this.locationY + 1, -4144960);
    drawRect(this.locationX, this.locationY, this.locationX + 1, this.locationY + this.height, -4144960);
    drawRect(this.locationX, this.locationY + this.height - 1, this.locationX + this.width, this.locationY + this.height, -4144960);
    drawRect(this.locationX + this.width - 1, this.locationY, this.locationX + this.width, this.locationY + this.height, -4144960);
    if (this.selectStart != -1 || this.selectEnd != -1) {
      int txtWidth1 = getFont().getStringWidth(this.text.substring(0, this.selectStart));
      int txtWidth = getFont().getStringWidth(this.text.substring(this.selectStart, this.selectEnd));
      drawRect(this.locationX + 5 + txtWidth1, this.locationY + 5, this.locationX + 5 + txtWidth1 + txtWidth, this.locationY + 16, -16739073);
    } 
    getFont().drawStringWithShadow(this.text, this.locationX + 5, this.locationY + 6, 14737632);
    if (this.isFocus && this.cursorCounter < 10) {
      int txtWidth = getFont().getStringWidth(this.text.substring(0, this.cursorPosition));
      drawRect(this.locationX + 5 + txtWidth, this.locationY + 5, this.locationX + 6 + txtWidth, this.locationY + 16, -2039584);
    } 
    this.buttonEdit.draw(mouseX, mouseY);
    this.menu.draw(mouseX, mouseY);
  }
  
  public void drawTopLayer(int mouseX, int mouseY) {
    this.menu.drawTopLayer(mouseX, mouseY);
  }
  
  public void keyTyped(char par1, int par2) {
    if (this.isFocus && this.isEnable) {
      if (par1 == '\b') {
        if (this.selectStart != -1 && this.selectEnd != -1) {
          if (this.selectStart == 0 && this.selectEnd == this.text.length()) {
            this.text = "";
            textChanged();
          } else {
            this.text = this.text.substring(0, this.selectStart) + this.text.substring(this.selectEnd, this.text.length());
            textChanged();
          } 
          this.cursorPosition = this.selectStart;
          this.selectEnd = this.selectStart = -1;
        } else if (this.text.length() == 1 || this.text.length() == 0) {
          this.text = "";
          this.cursorPosition--;
          textChanged();
        } else if (this.cursorPosition == this.text.length()) {
          this.text = this.text.substring(0, this.text.length() - 1);
          this.cursorPosition--;
          textChanged();
        } else if (this.cursorPosition != 0) {
          this.text = this.text.substring(0, this.cursorPosition - 1) + this.text.substring(this.cursorPosition, this.text.length());
          this.cursorPosition--;
          textChanged();
        } 
        cursorPositionCheck();
      } else if (par2 == 211) {
        if (this.cursorPosition != this.text.length()) {
          this.text = this.text.substring(0, this.cursorPosition) + this.text.substring(this.cursorPosition + 1, this.text.length());
          textChanged();
        } 
        cursorPositionCheck();
      } else if (par2 == 205 || par2 == 203 || par2 == 200 || par2 == 208 || par2 == 199 || par2 == 207) {
        if (GuiScreen.isShiftKeyDown()) {
          if (par2 == 205) {
            if (this.selectStart == -1)
              this.selectStart = this.cursorPosition; 
            if (this.selectEnd == -1) {
              this.selectEnd = Math.min(this.cursorPosition + 1, this.text.length());
            } else {
              this.selectEnd = Math.min(this.cursorPosition + 1, this.text.length());
            } 
            this.cursorPosition++;
          } else if (par2 == 203) {
            if (this.selectEnd == -1)
              this.selectEnd = this.cursorPosition; 
            if (this.selectStart == -1) {
              this.selectStart = Math.max(this.cursorPosition - 1, 0);
            } else {
              this.selectStart = Math.max(this.cursorPosition - 1, 0);
            } 
            this.cursorPosition--;
          } 
        } else {
          if (par2 == 205) {
            this.cursorPosition++;
            this.selectEnd = this.selectStart = -1;
          } 
          if (par2 == 203) {
            this.cursorPosition--;
            this.selectEnd = this.selectStart = -1;
          } 
          if (par2 == 199) {
            this.cursorPosition = 0;
            this.selectEnd = this.selectStart = -1;
          } 
          if (par2 == 207) {
            this.cursorPosition = this.text.length();
            this.selectEnd = this.selectStart = -1;
          } 
        } 
        cursorPositionCheck();
      } 
      if (GuiScreen.isCtrlKeyDown()) {
        if (par1 == 'C') {
          if (getSelectText() != null)
            GuiScreen.setClipboardString(getSelectText()); 
        } else if (par1 == 'V') {
          setText(GuiScreen.getClipboardString());
        } else if (par1 == 'X') {
          if (getSelectText() != null) {
            GuiScreen.setClipboardString(getSelectText());
            setText("");
          } 
        } else if (par1 == 'A') {
          this.selectStart = 0;
          this.selectEnd = this.text.length();
          cursorPositionCheck();
        } 
        return;
      } 
      if (!ChatAllowedCharacters.isAllowedCharacter(par1) || (par2 >= 59 && par2 <= 68) || par2 == 88 || par2 == 87 || par2 == 210)
        return; 
      if (this.inputLimit != null && this.inputLimit.length() != 0) {
        boolean canInput = false;
        for (int i = 0; i < this.inputLimit.length(); i++) {
          if (par1 == this.inputLimit.charAt(i)) {
            canInput = true;
            break;
          } 
        } 
        if (!canInput)
          return; 
      } 
      if (this.cursorPosition == this.text.length()) {
        StringBuilder sb = new StringBuilder(this.text);
        sb.append(par1);
        this.text = sb.toString();
        textChanged();
      } else {
        this.text = this.text.substring(0, this.cursorPosition) + String.valueOf(par1) + this.text.substring(this.cursorPosition, this.text.length());
        textChanged();
      } 
      this.cursorPosition++;
      cursorPositionCheck();
    } 
  }
  
  private void cursorPositionCheck() {
    this.cursorPosition = Math.max(0, this.cursorPosition);
    this.cursorPosition = Math.min(this.text.length(), this.cursorPosition);
  }
  
  public void mouseClicked(int mouseX, int mouseY, int button) {
    if (this.isEnable)
      this.menu.mouseClicked(mouseX, mouseY, button); 
    if (onTheMouse(mouseX, mouseY) && this.isEnable) {
      this.buttonEdit.mouseClicked(mouseX, mouseY, button);
      if (this.buttonEdit.onTheMouse(mouseX, mouseY))
        return; 
      if (button == 1)
        this.menu.show(mouseX, mouseY, this.controlId); 
      if (button == 0) {
        int mx = mouseX - this.locationX - 5;
        int wSize = 0;
        int strWidth = getFont().getStringWidth(this.text);
        if (mx >= strWidth) {
          this.cursorPosition = this.text.length();
          return;
        } 
        for (int i = 0; i < this.text.length(); i++) {
          int cWidth = getFont().getCharWidth(this.text.charAt(i));
          if (mx <= wSize + cWidth) {
            if (mx <= wSize + cWidth / 2) {
              this.cursorPosition = Math.max(0, i);
            } else {
              this.cursorPosition = Math.min(this.text.length(), i + 1);
            } 
            return;
          } 
          wSize += cWidth;
        } 
      } 
    } 
  }
  
  public void showEditWindow() {
    if (this.inputWindow == null) {
      this.inputWindow = new Frame("Minecraft Text Box Control");
      this.inputWindow.setLayout(new BorderLayout());
      this.inputWindow.setResizable(false);
      JTextField tf = new JTextField(getText());
      tf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              Component c = ControlTextBox.this.inputWindow.getComponent(0);
              if (c instanceof JTextField) {
                String str = ((JTextField)c).getText();
                ControlTextBox.this.setText(str);
                ControlTextBox.this.closeWindow();
              } 
            }
          });
      tf.setFont(new Font("SansSerif", 0, 16));
      this.inputWindow.add(tf, "Center");
    } 
    this.inputWindow.setSize(Display.getWidth(), 70);
    this.inputWindow.setLocation(Display.getX(), Display.getY() + Display.getHeight());
    this.inputWindow.setVisible(true);
    this.inputWindow.toFront();
    ((JTextField)this.inputWindow.getComponent(0)).selectAll();
    this.inputWindow.getComponent(0).requestFocusInWindow();
  }
  
  public void closeWindow() {
    if (this.inputWindow != null) {
      this.inputWindow.setVisible(false);
      this.inputWindow.dispose();
      this.inputWindow = null;
    } 
  }
  
  public void onGuiClosed() {
    closeWindow();
  }
  
  public void setText(String par1) {
    if (par1 == null)
      par1 = ""; 
    this.text = par1;
    if (this.inputLimit != null && this.inputLimit.length() != 0) {
      String result = "";
      for (int j = 0; j < this.text.length(); j++) {
        char textChar = this.text.charAt(j);
        boolean addFlag = false;
        for (int i = 0; i < this.inputLimit.length(); i++) {
          if (textChar == this.inputLimit.charAt(i)) {
            addFlag = true;
            break;
          } 
        } 
        if (addFlag)
          result = result + String.valueOf(textChar); 
      } 
      this.text = result;
    } 
    this.cursorPosition = 0;
    textChanged();
  }
  
  public String getText() {
    return this.text;
  }
  
  public String getSelectText() {
    if (this.text.length() == 0)
      return null; 
    if (this.selectStart != -1 && this.selectEnd != -1)
      return this.text.substring(this.selectStart, this.selectEnd); 
    return this.text;
  }
  
  public void setInputLimit(String par1) {
    this.inputLimit = par1;
  }
  
  public void textChanged() {}
}
