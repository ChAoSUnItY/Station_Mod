package com.noto0648.stations.client.gui.control;

import com.noto0648.stations.client.gui.IGui;
import java.util.ArrayList;
import java.util.List;

public class ControlComboBox extends Control {
  private ControlListBox listBox;
  
  public List<String> items = new ArrayList<String>();
  
  public int selectedIndex = -1;
  
  private boolean showListBox;
  
  public ControlComboBox(IGui gui) {
    super(gui);
  }
  
  public ControlComboBox(IGui gui, int x, int y, int w, int h) {
    super(gui);
    this.locationX = x;
    this.locationY = y;
    this.width = w;
    this.height = h;
    this.listBox = new ControlListBox(gui, x, y + h, w, h * 4) {
        public void selectChanged() {
          ControlComboBox.this.showListBox = false;
          ControlComboBox.this.selectedIndex = this.selectedIndex;
        }
      };
    this.listBox.isEnable = false;
  }
  
  public void initGui() {
    this.listBox.setLocation(this.locationX, this.locationY + this.height);
    this.listBox.setSize(this.width, this.height * 4);
  }
  
  public void draw(int mouseX, int mouseY) {
    int centerColor = -9474193;
    int topColor = -5592406;
    int bottomColor = -11119018;
    int fontColor = 14737632;
    if (onTheMouse(mouseX, mouseY)) {
      centerColor = -8484673;
      bottomColor = -10787428;
      topColor = -4274177;
      fontColor = 16777120;
    } 
    drawRect(this.locationX, this.locationY, this.locationX + this.width, this.locationY + this.height, centerColor);
    drawRect(this.locationX, this.locationY, this.locationX + this.width, this.locationY + 1, topColor);
    drawRect(this.locationX, this.locationY, this.locationX + 1, this.locationY + this.height, topColor);
    drawRect(this.locationX + 1, this.locationY + this.height - 1, this.locationX + this.width, this.locationY + this.height, bottomColor);
    drawRect(this.locationX + this.width - 1, this.locationY, this.locationX + this.width, this.locationY + this.height, bottomColor);
    if (this.items.get(this.selectedIndex) != null)
      getFont().drawStringWithShadow(this.items.get(this.selectedIndex), this.width / 2 - getFont().getStringWidth(this.items.get(this.selectedIndex)) / 2 + this.locationX, this.height / 2 - 4 + this.locationY, fontColor); 
    if (this.showListBox)
      this.listBox.draw(mouseX, mouseY); 
  }
  
  public void mouseClicked(int mouseX, int mouseY, int button) {
    if (onTheMouse(mouseX, mouseY) && this.isEnable) {
      this.showListBox = !this.showListBox;
      this.listBox.isEnable = this.showListBox;
      if (this.showListBox) {
        this.listBox.items.clear();
        this.listBox.items.addAll(this.items);
        this.listBox.selectedIndex = this.selectedIndex;
      } 
    } 
    if (!this.listBox.onTheMouse(mouseX, mouseY) && !onTheMouse(mouseX, mouseY)) {
      this.showListBox = false;
      this.listBox.isEnable = this.showListBox;
    } 
    if (this.showListBox)
      this.listBox.mouseClicked(mouseX, mouseY, button); 
  }
  
  public void mouseClickMove(int mouseX, int mouseY, int button, long time) {
    if (this.showListBox)
      this.listBox.mouseClickMove(mouseX, mouseY, button, time); 
  }
  
  public void mouseMovedOrUp(int mouseX, int mouseY, int mode) {
    if (this.showListBox)
      this.listBox.mouseMovedOrUp(mouseX, mouseY, mode); 
  }
  
  public void setSelectedIndex(int par1) {
    this.selectedIndex = par1;
  }
}
