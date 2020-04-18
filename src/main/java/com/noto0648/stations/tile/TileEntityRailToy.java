package com.noto0648.stations.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRailToy extends TileBase {
  private static ForgeDirection[][] normalConnection = new ForgeDirection[][] { { ForgeDirection.NORTH, ForgeDirection.SOUTH }, { ForgeDirection.EAST, ForgeDirection.WEST } };
  
  protected int rotate;
  
  public void readFromNBT(NBTTagCompound p_145839_1_) {
    super.readFromNBT(p_145839_1_);
    this.rotate = p_145839_1_.getInteger("rotate");
  }
  
  public void writeToNBT(NBTTagCompound p_145841_1_) {
    super.writeToNBT(p_145841_1_);
    p_145841_1_.setInteger("rotate", this.rotate);
  }
  
  public int getRotate() {
    return this.rotate;
  }
  
  public void setRotate(int par1) {
    this.rotate = par1;
  }
  
  public ForgeDirection[] getConnectDirection() {
    if (this.rotate == 0 || this.rotate == 2)
      return normalConnection[0]; 
    if (this.rotate == 1 || this.rotate == 3)
      return normalConnection[1]; 
    return null;
  }
}
