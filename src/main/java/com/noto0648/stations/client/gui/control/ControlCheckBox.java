package com.noto0648.stations.client.gui.control;

import com.noto0648.stations.client.gui.IGui;

public class ControlCheckBox extends Control {
  public String text;
  
  private boolean check;
  
  public ControlCheckBox(IGui gui) {
    super(gui);
  }
  
  public ControlCheckBox(IGui gui, int x, int y, String str) {
    super(gui);
    this.locationX = x;
    this.locationY = y;
    this.height = 16;
    this.text = str;
  }
  
  public void initGui() {
    this.width = 17 + getFont().getStringWidth(this.text);
  }
  
  public void draw(int mouseX, int mouseY) {
    drawRect(this.locationX, this.locationY, this.locationX + this.height, this.locationY + this.height, -16777216);
    drawRect(this.locationX, this.locationY, this.locationX + this.height, this.locationY + 1, -4144960);
    drawRect(this.locationX, this.locationY, this.locationX + 1, this.locationY + this.height, -4144960);
    drawRect(this.locationX + this.height - 1, this.locationY, this.locationX + this.height, this.locationY + this.height, -4144960);
    drawRect(this.locationX, this.locationY + this.height - 1, this.locationX + this.height, this.locationY + this.height, -4144960);
    if (this.check)
      getFont().drawString("✓", this.locationX + 4, this.locationY + 4, -1); 
    if (onTheMouse(mouseX, mouseY)) {
      getFont().drawStringWithShadow(this.text, this.locationX + 18, this.locationY + 4, 16777120);
    } else {
      getFont().drawStringWithShadow(this.text, this.locationX + 18, this.locationY + 4, 14737632);
    } 
  }
  
  public void mouseClicked(int mouseX, int mouseY, int button) {
    if (onTheMouse(mouseX, mouseY) && button == 0) {
      playClickSound();
      this.check = !this.check;
    } 
  }
  
  public boolean getCheck() {
    return this.check;
  }
  
  public void setCheck(boolean par1) {
    this.check = par1;
  }
}
