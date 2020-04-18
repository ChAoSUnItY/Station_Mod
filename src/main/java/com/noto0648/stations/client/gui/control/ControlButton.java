package com.noto0648.stations.client.gui.control;

import com.noto0648.stations.client.gui.IGui;

public class ControlButton extends Control {
  private String text;
  
  public ControlButton(IGui gui) {
    super(gui);
  }
  
  public ControlButton(IGui gui, int x, int y, int w, int h, String tx) {
    super(gui);
    this.locationX = x;
    this.locationY = y;
    this.width = w;
    this.height = h;
    this.text = tx;
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
    getFont().drawStringWithShadow(this.text, this.width / 2 - getFont().getStringWidth(this.text) / 2 + this.locationX, this.height / 2 - 4 + this.locationY, fontColor);
  }
  
  public void mouseClicked(int mouseX, int mouseY, int button) {
    if (onTheMouse(mouseX, mouseY) && this.isEnable)
      onButtonClick(button); 
  }
  
  public void onButtonClick(int button) {}
  
  public void setText(String par1) {
    this.text = par1;
  }
}
