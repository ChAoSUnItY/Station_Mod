package com.noto0648.stations.client.gui.control;

import com.noto0648.stations.client.gui.IGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public abstract class Control {
  private IGui gui;
  
  public int locationX;
  
  public int locationY;
  
  public int width;
  
  public int height;
  
  public boolean isFocus;
  
  protected boolean isEnable = true;
  
  public int controlId;
  
  public Control(IGui gui) {
    this.gui = gui;
  }
  
  public abstract void draw(int paramInt1, int paramInt2);
  
  public void drawTopLayer(int mouseX, int mouseY) {}
  
  public abstract void mouseClicked(int paramInt1, int paramInt2, int paramInt3);
  
  public void update() {}
  
  public void initGui() {}
  
  protected void drawRect(int x, int y, int x2, int y2, int color) {
    this.gui.drawRect(x, y, x2, y2, color, color);
  }
  
  protected void drawRect(int x, int y, int x2, int y2, int color, int color2) {
    this.gui.drawRect(x, y, x2, y2, color, color2);
  }
  
  protected FontRenderer getFont() {
    return this.gui.getFontRenderer();
  }
  
  public void mouseClickMove(int mouseX, int mouseY, int button, long time) {}
  
  public void mouseMovedOrUp(int mouseX, int mouseY, int mode) {}
  
  public void keyTyped(char par1, int par2) {}
  
  public void onGuiClosed() {}
  
  public void focusCheck(int mouseX, int mouseY, int button) {
    if (onTheMouse(mouseX, mouseY)) {
      this.isFocus = true;
    } else {
      this.isFocus = false;
    } 
  }
  
  public void setSize(int w, int h) {
    this.width = w;
    this.height = h;
    initGui();
  }
  
  public void setLocation(int x, int y) {
    this.locationX = x;
    this.locationY = y;
    initGui();
  }
  
  public boolean onTheMouse(int mouseX, int mouseY) {
    return (mouseX >= this.locationX && mouseY >= this.locationY && this.locationX + this.width >= mouseX && this.locationY + this.height >= mouseY);
  }
  
  protected IGui getGui() {
    return this.gui;
  }
  
  public void setEnabled(boolean par1) {
    this.isEnable = par1;
  }
  
  public void playClickSound() {
    Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
  }
}
