package com.noto0648.stations.client.gui;

import com.noto0648.stations.Stations;
import com.noto0648.stations.packet.PacketSendTicket;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;

public class GuiTicketMachine extends GuiScreen implements IGui {
  String[] oneLine = new String[20];
  
  int[] twoLine = new int[20];
  
  private int posX;
  
  private int posY;
  
  private int posZ;
  
  public GuiTicketMachine(int x, int y, int z) {
    this.oneLine[0] = "片道きっぷ";
    this.twoLine[0] = 160;
    this.oneLine[1] = "往復券";
    this.twoLine[1] = 320;
    this.oneLine[2] = "回数券";
    this.twoLine[2] = 1600;
    this.oneLine[3] = "入場券";
    this.twoLine[3] = 140;
    this.posX = x;
    this.posY = y;
    this.posZ = z;
  }
  
  public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
    drawDefaultBackground();
    drawRect(50, 10, this.width - 50, this.height - 50, -16777216, -16777216);
    for (int x = 0; x < 5; x++) {
      if (this.oneLine[x] != null) {
        GL11.glPushMatrix();
        drawRect(60 + x * 60, 15, 60 + x * 60 + 50, 45, -4144960, -4144960);
        GL11.glTexCoord2d((60 + x * 60), 15.0D);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        getFontRenderer().drawString(this.oneLine[x], 85 + x * 60 - getFontRenderer().getStringWidth(this.oneLine[x]) / 2, 19, 0);
        getFontRenderer().drawString(this.twoLine[x] + "", 85 + x * 60 - getFontRenderer().getStringWidth(this.twoLine[x] + "") / 2, 31, 0);
        GL11.glPopMatrix();
      } 
    } 
    super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
  }
  
  protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
    for (int x = 0; x < 5; x++) {
      if (this.oneLine[x] != null)
        if (60 + x * 60 <= p_73864_1_ && 15 <= p_73864_2_ && 60 + x * 60 + 50 >= p_73864_1_ && 45 >= p_73864_2_) {
          Stations.packetDispatcher.sendToServer((IMessage)new PacketSendTicket(x, this.posX, this.posY, this.posZ));
          Minecraft.getMinecraft().displayGuiScreen(null);
          break;
        }  
    } 
    super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
  }
  
  public boolean doesGuiPauseGame() {
    return false;
  }
  
  public void drawRect(int x, int y, int x2, int y2, int color, int color2) {
    drawGradientRect(x, y, x2, y2, color, color2);
  }
  
  public FontRenderer getFontRenderer() {
    return this.fontRendererObj;
  }
}
