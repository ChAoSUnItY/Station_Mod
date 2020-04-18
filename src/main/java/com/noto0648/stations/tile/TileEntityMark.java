package com.noto0648.stations.tile;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityMark extends TileBase {
  private boolean parentRegistered;
  
  private int parentX;
  
  private int parentY;
  
  private int parentZ;
  
  public void readFromNBT(NBTTagCompound p_145839_1_) {
    super.readFromNBT(p_145839_1_);
    this.parentX = p_145839_1_.getInteger("parentX");
    this.parentY = p_145839_1_.getInteger("parentY");
    this.parentZ = p_145839_1_.getInteger("parentZ");
    this.parentRegistered = p_145839_1_.getBoolean("parReg");
  }
  
  public void writeToNBT(NBTTagCompound p_145841_1_) {
    super.writeToNBT(p_145841_1_);
    p_145841_1_.setInteger("parentX", this.parentX);
    p_145841_1_.setInteger("parentY", this.parentY);
    p_145841_1_.setInteger("parentZ", this.parentZ);
    p_145841_1_.setBoolean("parReg", this.parentRegistered);
  }
  
  public int getParentX() {
    return this.parentX;
  }
  
  public int getParentY() {
    return this.parentY;
  }
  
  public int getParentZ() {
    return this.parentZ;
  }
  
  public boolean isRegistered() {
    return this.parentRegistered;
  }
  
  public boolean setParent(int x, int y, int z) {
    if (getBlockMetadata() >= 14) {
      this.parentX = x;
      this.parentY = y;
      this.parentZ = z;
      this.parentRegistered = true;
      return true;
    } 
    return false;
  }
}
