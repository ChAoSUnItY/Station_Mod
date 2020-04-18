package com.noto0648.stations.client.render;

import com.noto0648.stations.client.texture.NewFontRenderer;
import com.noto0648.stations.tile.TileEntityNumberPlate;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class TileEntityNumberPlateRender extends TileEntitySpecialRenderer {
  public static IModelCustom plate = AdvancedModelLoader.loadModel(new ResourceLocation("notomod", "objs/number_plate.obj"));
  
  public static ResourceLocation newTexture = new ResourceLocation("notomod", "textures/models/number_plate.png");
  
  public void renderTileEntityAt(TileEntity tileEntity, double par2, double par3, double par4, float p_147500_8_) {
    int value = tileEntity.getBlockMetadata() / 2;
    GL11.glPushMatrix();
    GL11.glTranslated(par2 + 0.5D, par3 + 0.65D, par4 + 0.5D);
    GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
    if (tileEntity.getBlockMetadata() % 2 == 1)
      GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D); 
    bindTexture(newTexture);
    plate.renderAll();
    for (int i = 0; i < 2; i++) {
      GL11.glPushMatrix();
      GL11.glColor3f(0.0F, 0.0F, 0.0F);
      if (i == 0) {
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
      } else {
        GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
      } 
      GL11.glTranslated(0.0D, -0.455D, 0.15D);
      GL11.glScalef(0.03F, 0.03F, 0.03F);
      GL11.glTranslated((-NewFontRenderer.INSTANCE.drawString(((TileEntityNumberPlate)tileEntity).getDrawStr(), false) / 2), 0.0D, 0.0D);
      NewFontRenderer.INSTANCE.drawString(((TileEntityNumberPlate)tileEntity).getDrawStr());
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
    } 
    GL11.glPopMatrix();
  }
}
