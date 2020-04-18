package com.noto0648.stations.client.render;

import com.noto0648.stations.tile.TileEntityTicketGate;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class TileEntityTicketGateRender extends TileEntitySpecialRenderer {
  public static IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("notomod", "objs/ticket_gate.obj"));
  
  public static ResourceLocation texture = new ResourceLocation("notomod", "textures/models/ticket_gate_main.png");
  
  public static ResourceLocation textureDoor = new ResourceLocation("notomod", "textures/models/ticket_gate_door.png");
  
  public void renderTileEntityAt(TileEntity tile, double par2, double par3, double par4, float p_147500_8_) {
    GL11.glPushMatrix();
    GL11.glTranslated(par2 + 0.5D, par3 + 0.68D, par4 + 0.5D);
    GL11.glScalef(0.5F, 0.6F, 0.5F);
    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
    if (tile.getBlockMetadata() == 5)
      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); 
    if (tile.getBlockMetadata() == 6)
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); 
    if (tile.getBlockMetadata() == 7)
      GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); 
    bindTexture(texture);
    model.renderPart("machine_machine_cube");
    if (!((TileEntityTicketGate)tile).isGateOpen()) {
      bindTexture(textureDoor);
      model.renderPart("door_right_door_right_cube");
      model.renderPart("door_left_door_left_cube");
    } 
    GL11.glPopMatrix();
  }
}
