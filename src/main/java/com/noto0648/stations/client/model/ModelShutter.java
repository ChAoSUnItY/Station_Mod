package com.noto0648.stations.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShutter extends ModelBase {
  private ModelRenderer shape;
  
  public ModelShutter() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.shape = new ModelRenderer(this, 0, 0);
    this.shape.addBox(0.0F, 0.0F, 0.0F, 16, 16, 1);
    this.shape.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape.setTextureSize(64, 32);
    this.shape.mirror = true;
    setRotation(this.shape, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.shape.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }
  
  public void renderAll(float f5) {
    this.shape.render(f5);
  }
}
