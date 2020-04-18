package com.noto0648.stations.client.render;

import com.noto0648.stations.Stations;
import com.noto0648.stations.client.model.ModelShutter;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityShutterRender extends TileEntitySpecialRenderer {
  public static ModelShutter model = new ModelShutter();
  
  public static ResourceLocation texture = new ResourceLocation("notomod", "textures/models/shutter_texture.png");
  
  public void renderTileEntityAt(TileEntity tile, double par1, double par2, double par3, float p_147500_8_) {
    GL11.glPushMatrix();
    GL11.glTranslated(par1, par2, par3 + 0.475D);
    if (tile.getBlockMetadata() >= 8) {
      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
      GL11.glTranslated(0.0D, 0.0D, -0.475D);
      GL11.glTranslated(-0.525D, 0.0D, 0.975D);
    } 
    if (tile.getBlockMetadata() % 8 == 0) {
      int i = 1;
      while (true) {
        Block bottom = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord - i, tile.zCoord);
        if (bottom == Stations.instance.shutter) {
          int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord - i, tile.zCoord);
          if (meta % 8 != 0) {
            GL11.glTranslatef(0.0F, 0.125F * (meta % 8), 0.0F);
            break;
          } 
          i++;
        } 
        break;
      } 
    } else {
      GL11.glTranslatef(0.0F, 0.125F * (tile.getBlockMetadata() % 8), 0.0F);
    } 
    bindTexture(texture);
    model.renderAll(0.0625F);
    GL11.glPopMatrix();
  }
}
