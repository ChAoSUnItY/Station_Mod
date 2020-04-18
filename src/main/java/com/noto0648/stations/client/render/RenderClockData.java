package com.noto0648.stations.client.render;

import com.noto0648.stations.Stations;
import com.noto0648.stations.common.MinecraftDate;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class RenderClockData {
  private static Minecraft mc = FMLClientHandler.instance().getClient();
  
  @SubscribeEvent
  public void renderScreen(RenderGameOverlayEvent event) {
    if (event.type != RenderGameOverlayEvent.ElementType.CROSSHAIRS)
      return; 
    if (mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() == Stations.instance.clock) {
      GL11.glPushMatrix();
      if (mc.thePlayer.getCurrentEquippedItem().getItemDamage() == 0) {
        mc.fontRenderer.drawStringWithShadow((new MinecraftDate(mc.theWorld.getWorldTime())).toString(), 1, 1, 16777215);
      } else if (mc.thePlayer.getCurrentEquippedItem().getItemDamage() == 1) {
        GL11.glLineWidth(5.0F);
        MinecraftDate md = new MinecraftDate((World)mc.theWorld);
        Tessellator tes = Tessellator.instance;
        GL11.glDisable(3553);
        GL11.glColor3f(0.0F, 1.0F, 0.0F);
        tes.startDrawing(9);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
        fillCircle(tes, 20.0F, 22, 22);
        tes.draw();
        GL11.glPointSize(4.0F);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        tes.startDrawing(0);
        for (int i = 0; i < 12; i++) {
          float f = (float)((30 * i) * Math.PI / 180.0D);
          tes.addVertex((22.0F + MathHelper.sin(f) * 18.0F), 22.0D + Math.cos(f) * 18.0D, 0.0D);
        } 
        tes.draw();
        GL11.glLineWidth(3.0F);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        tes.startDrawing(1);
        tes.addVertex(22.0D, 22.0D, 0.0D);
        float minute = (float)((6.0F * (60 - md.getMinutes()) - 180.0F) * Math.PI / 180.0D);
        tes.addVertex((22.0F + MathHelper.sin(minute) * 18.0F), 22.0D + Math.cos(minute) * 18.0D, 0.0D);
        tes.draw();
        GL11.glLineWidth(5.0F);
        tes.startDrawing(1);
        tes.addVertex(22.0D, 22.0D, 0.0D);
        float hours = (float)((0.5F * (720 - md.getHours() % 12 * 60 + md.getMinutes()) - 180.0F) * Math.PI / 180.0D);
        tes.addVertex((22.0F + MathHelper.sin(hours) * 15.0F), 22.0D + Math.cos(hours) * 15.0D, 0.0D);
        tes.draw();
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glLineWidth(1.0F);
        GL11.glPointSize(1.0F);
        GL11.glEnable(3553);
      } 
      GL11.glPopMatrix();
    } 
  }
  
  private void fillCircle(Tessellator tes, float r, int x, int y) {
    float th1;
    for (th1 = 0.0F; th1 <= 360.0F; th1++) {
      float th2 = th1 + 10.0F;
      float th1_rad = th1 / 180.0F * 3.1415927F;
      float th2_rad = th2 / 180.0F * 3.1415927F;
      float x1 = r * MathHelper.cos(th1_rad);
      float y1 = r * MathHelper.sin(th1_rad);
      float x2 = r * MathHelper.cos(th2_rad);
      float y2 = r * MathHelper.sin(th2_rad);
      tes.addVertex((x1 + x), (y1 + y), 0.0D);
      tes.addVertex((x2 + x), (y2 + y), 0.0D);
    } 
  }
}
