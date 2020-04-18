package com.noto0648.stations.client.gui;

import com.noto0648.stations.client.gui.control.Control;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public abstract class GuiScreenBase extends GuiScreen implements IGui {
  protected List<Control> controlList = new ArrayList<Control>();
  
  private boolean isShowContextMenu = false;
  
  private int contextMenuId = -1;
  
  protected abstract void paint(int paramInt1, int paramInt2);
  
  protected abstract void resize();
  
  public void initGui() {
    Keyboard.enableRepeatEvents(true);
    super.initGui();
    int i;
    for (i = 0; i < this.controlList.size(); i++)
      ((Control)this.controlList.get(i)).controlId = i; 
    resize();
    for (i = 0; i < this.controlList.size(); i++)
      ((Control)this.controlList.get(i)).initGui(); 
  }
  
  public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
    super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    if (doesDrawDarkScreen())
      drawDefaultBackground(); 
    int i;
    for (i = 0; i < this.controlList.size(); i++)
      ((Control)this.controlList.get(i)).draw(p_73863_1_, p_73863_2_); 
    paint(p_73863_1_, p_73863_2_);
    for (i = 0; i < this.controlList.size(); i++)
      ((Control)this.controlList.get(i)).drawTopLayer(p_73863_1_, p_73863_2_); 
  }
  
  protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
    super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    if (!this.isShowContextMenu) {
      for (int i = 0; i < this.controlList.size(); i++) {
        ((Control)this.controlList.get(i)).focusCheck(p_73864_1_, p_73864_2_, p_73864_3_);
        ((Control)this.controlList.get(i)).mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
      } 
    } else {
      ((Control)this.controlList.get(this.contextMenuId)).focusCheck(p_73864_1_, p_73864_2_, p_73864_3_);
      ((Control)this.controlList.get(this.contextMenuId)).mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    } 
  }
  
  protected void keyTyped(char p_73869_1_, int p_73869_2_) {
    super.keyTyped(p_73869_1_, p_73869_2_);
    if (!this.isShowContextMenu) {
      for (int i = 0; i < this.controlList.size(); i++)
        ((Control)this.controlList.get(i)).keyTyped(p_73869_1_, p_73869_2_); 
    } else {
      ((Control)this.controlList.get(this.contextMenuId)).keyTyped(p_73869_1_, p_73869_2_);
    } 
  }
  
  protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
    super.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_);
    if (!this.isShowContextMenu) {
      for (int i = 0; i < this.controlList.size(); i++)
        ((Control)this.controlList.get(i)).mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_); 
    } else {
      ((Control)this.controlList.get(this.contextMenuId)).mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_);
    } 
  }
  
  protected void mouseClickMove(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_) {
    super.mouseClickMove(p_146273_1_, p_146273_2_, p_146273_3_, p_146273_4_);
    if (!this.isShowContextMenu) {
      for (int i = 0; i < this.controlList.size(); i++)
        ((Control)this.controlList.get(i)).mouseClickMove(p_146273_1_, p_146273_2_, p_146273_3_, p_146273_4_); 
    } else {
      ((Control)this.controlList.get(this.contextMenuId)).mouseClickMove(p_146273_1_, p_146273_2_, p_146273_3_, p_146273_4_);
    } 
  }
  
  public void updateScreen() {
    super.updateScreen();
    if (!this.isShowContextMenu) {
      for (int i = 0; i < this.controlList.size(); i++)
        ((Control)this.controlList.get(i)).update(); 
    } else {
      ((Control)this.controlList.get(this.contextMenuId)).update();
    } 
  }
  
  public Control getFocusControl() {
    for (int i = 0; i < this.controlList.size(); i++) {
      boolean focus = ((Control)this.controlList.get(i)).isFocus;
      if (focus)
        return this.controlList.get(i); 
    } 
    return null;
  }
  
  public void onGuiClosed() {
    Keyboard.enableRepeatEvents(false);
    for (int i = 0; i < this.controlList.size(); i++)
      ((Control)this.controlList.get(i)).onGuiClosed(); 
  }
  
  public boolean doesDrawDarkScreen() {
    return true;
  }
  
  public boolean doesGuiPauseGame() {
    return false;
  }
  
  @Deprecated
  protected void actionPerformed(GuiButton button) {}
  
  public void drawRect(int x, int y, int x2, int y2, int color, int color2) {
    drawGradientRect(x, y, x2, y2, color, color2);
  }
  
  public FontRenderer getFontRenderer() {
    return this.fontRendererObj;
  }
  
  public void openRightClickMenu(boolean par1, int par2) {
    this.isShowContextMenu = par1;
    this.contextMenuId = par2;
  }
}
