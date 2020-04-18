package com.noto0648.stations.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityTicketMachineRender extends TileEntitySpecialRenderer {
  public static IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("notomod", "objs/ticket_machine.obj"));
  
  public static ResourceLocation texture = new ResourceLocation("notomod", "textures/models/ticket_machine.png");
  
  public void renderTileEntityAt(TileEntity tile, double par2, double par3, double par4, float p_147500_8_) {
    GL11.glPushMatrix();
    GL11.glTranslated(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D);
    GL11.glScalef(0.5F, 0.5F, 0.5F);
    if (tile.getBlockMetadata() == 1)
      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); 
    if (tile.getBlockMetadata() == 2)
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); 
    if (tile.getBlockMetadata() == 3)
      GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); 
    bindTexture(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}
