package com.noto0648.stations.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFence extends ModelBase {
  ModelRenderer box1;
  
  ModelRenderer box2;
  
  ModelRenderer box3;
  
  ModelRenderer box4;
  
  ModelRenderer box5;
  
  ModelRenderer box6;
  
  ModelRenderer box7;
  
  ModelRenderer box8;
  
  ModelRenderer box9;
  
  ModelRenderer box10;
  
  ModelRenderer box11;
  
  ModelRenderer box12;
  
  ModelRenderer box13;
  
  public ModelFence() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.box1 = new ModelRenderer(this, 0, 0);
    this.box1.addBox(0.0F, -16.0F, 0.0F, 1, 16, 2);
    this.box1.setRotationPoint(-8.0F, 24.0F, 0.0F);
    this.box1.setTextureSize(64, 32);
    this.box1.mirror = true;
    setRotation(this.box1, 0.0F, 0.0F, 0.0F);
    this.box2 = new ModelRenderer(this, 0, 0);
    this.box2.addBox(0.0F, -16.0F, 0.0F, 1, 16, 2);
    this.box2.setRotationPoint(7.0F, 24.0F, 0.0F);
    this.box2.setTextureSize(64, 32);
    this.box2.mirror = true;
    setRotation(this.box2, 0.0F, 0.0F, 0.0F);
    this.box3 = new ModelRenderer(this, 8, 0);
    this.box3.addBox(0.0F, 0.0F, 0.0F, 14, 1, 1);
    this.box3.setRotationPoint(-7.0F, 22.0F, 0.0F);
    this.box3.setTextureSize(64, 32);
    this.box3.mirror = true;
    setRotation(this.box3, 0.0F, 0.0F, 0.0F);
    this.box4 = new ModelRenderer(this, 8, 0);
    this.box4.addBox(0.0F, 0.0F, 0.0F, 14, 1, 1);
    this.box4.setRotationPoint(-7.0F, 9.0F, 0.0F);
    this.box4.setTextureSize(64, 32);
    this.box4.mirror = true;
    setRotation(this.box4, 0.0F, 0.0F, 0.0F);
    this.box5 = new ModelRenderer(this, 12, 19);
    this.box5.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1);
    this.box5.setRotationPoint(5.0F, 10.0F, 0.0F);
    this.box5.setTextureSize(64, 32);
    this.box5.mirror = true;
    setRotation(this.box5, 0.0F, 0.0F, 0.0F);
    this.box6 = new ModelRenderer(this, 12, 19);
    this.box6.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1);
    this.box6.setRotationPoint(3.0F, 10.0F, 0.0F);
    this.box6.setTextureSize(64, 32);
    this.box6.mirror = true;
    setRotation(this.box6, 0.0F, 0.0F, 0.0F);
    this.box7 = new ModelRenderer(this, 12, 19);
    this.box7.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1);
    this.box7.setRotationPoint(1.0F, 10.0F, 0.0F);
    this.box7.setTextureSize(64, 32);
    this.box7.mirror = true;
    setRotation(this.box7, 0.0F, 0.0F, 0.0F);
    this.box8 = new ModelRenderer(this, 12, 19);
    this.box8.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1);
    this.box8.setRotationPoint(-2.0F, 10.0F, 0.0F);
    this.box8.setTextureSize(64, 32);
    this.box8.mirror = true;
    setRotation(this.box8, 0.0F, 0.0F, 0.0F);
    this.box9 = new ModelRenderer(this, 12, 19);
    this.box9.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1);
    this.box9.setRotationPoint(-4.0F, 10.0F, 0.0F);
    this.box9.setTextureSize(64, 32);
    this.box9.mirror = true;
    setRotation(this.box9, 0.0F, 0.0F, 0.0F);
    this.box10 = new ModelRenderer(this, 12, 19);
    this.box10.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1);
    this.box10.setRotationPoint(-6.0F, 10.0F, 0.0F);
    this.box10.setTextureSize(64, 32);
    this.box10.mirror = true;
    setRotation(this.box10, 0.0F, 0.0F, 0.0F);
    this.box11 = new ModelRenderer(this, 8, 4);
    this.box11.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1);
    this.box11.setRotationPoint(-8.0F, 5.0F, 1.0F);
    this.box11.setTextureSize(64, 32);
    this.box11.mirror = true;
    setRotation(this.box11, 0.0F, 0.0F, 0.0F);
    this.box12 = new ModelRenderer(this, 0, 20);
    this.box12.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
    this.box12.setRotationPoint(7.0F, 5.0F, 0.0F);
    this.box12.setTextureSize(64, 32);
    this.box12.mirror = true;
    setRotation(this.box12, 0.0F, 0.0F, 0.0F);
    this.box13 = new ModelRenderer(this, 0, 20);
    this.box13.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
    this.box13.setRotationPoint(-8.0F, 5.0F, 0.0F);
    this.box13.setTextureSize(64, 32);
    this.box13.mirror = true;
    setRotation(this.box13, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.box1.render(f5);
    this.box2.render(f5);
    this.box3.render(f5);
    this.box4.render(f5);
    this.box5.render(f5);
    this.box6.render(f5);
    this.box7.render(f5);
    this.box8.render(f5);
    this.box9.render(f5);
    this.box10.render(f5);
    this.box11.render(f5);
    this.box12.render(f5);
    this.box13.render(f5);
  }
  
  public void allRender(float f5) {
    this.box1.render(f5);
    this.box2.render(f5);
    this.box3.render(f5);
    this.box4.render(f5);
    this.box5.render(f5);
    this.box6.render(f5);
    this.box7.render(f5);
    this.box8.render(f5);
    this.box9.render(f5);
    this.box10.render(f5);
    this.box11.render(f5);
    this.box12.render(f5);
    this.box13.render(f5);
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
