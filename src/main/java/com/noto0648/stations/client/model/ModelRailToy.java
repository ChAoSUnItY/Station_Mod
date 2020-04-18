package com.noto0648.stations.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRailToy extends ModelBase {
  ModelRenderer shape1;
  
  ModelRenderer shape2;
  
  ModelRenderer shape3;
  
  ModelRenderer shape4;
  
  ModelRenderer shape5;
  
  public ModelRailToy() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.shape1 = new ModelRenderer(this, 0, 7);
    this.shape1.addBox(0.0F, 0.0F, 0.0F, 16, 1, 4);
    this.shape1.setRotationPoint(-8.0F, 23.0F, -2.0F);
    this.shape1.setTextureSize(64, 32);
    this.shape1.mirror = true;
    setRotation(this.shape1, 0.0F, 0.0F, 0.0F);
    this.shape2 = new ModelRenderer(this, 0, 0);
    this.shape2.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1);
    this.shape2.setRotationPoint(-8.0F, 23.0F, 3.0F);
    this.shape2.setTextureSize(64, 32);
    this.shape2.mirror = true;
    setRotation(this.shape2, 0.0F, 0.0F, 0.0F);
    this.shape3 = new ModelRenderer(this, 0, 0);
    this.shape3.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1);
    this.shape3.setRotationPoint(-8.0F, 23.0F, -4.0F);
    this.shape3.setTextureSize(64, 32);
    this.shape3.mirror = true;
    setRotation(this.shape3, 0.0F, 0.0F, 0.0F);
    this.shape4 = new ModelRenderer(this, 0, 4);
    this.shape4.addBox(0.0F, -0.5F, 0.0F, 16, 0, 1);
    this.shape4.setRotationPoint(-8.0F, 24.0F, -3.0F);
    this.shape4.setTextureSize(64, 32);
    this.shape4.mirror = true;
    setRotation(this.shape4, 0.0F, 0.0F, 0.0F);
    this.shape5 = new ModelRenderer(this, 0, 4);
    this.shape5.addBox(0.0F, -0.5F, 0.0F, 16, 0, 1);
    this.shape5.setRotationPoint(-8.0F, 24.0F, 2.0F);
    this.shape5.setTextureSize(64, 32);
    this.shape5.mirror = true;
    setRotation(this.shape5, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.shape1.render(f5);
    this.shape2.render(f5);
    this.shape3.render(f5);
    this.shape4.render(f5);
    this.shape5.render(f5);
  }
  
  public void allRender(float f5) {
    this.shape1.render(f5);
    this.shape2.render(f5);
    this.shape3.render(f5);
    this.shape4.render(f5);
    this.shape5.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }
}
