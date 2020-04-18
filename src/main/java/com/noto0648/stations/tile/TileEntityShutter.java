package com.noto0648.stations.tile;

import com.noto0648.stations.Stations;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityShutter extends TileBase {
  private int count;
  
  private boolean undoFlag;
  
  private boolean finish;
  
  public void updateEntity() {
    this.count++;
    if (this.count == 5) {
      this.count = 0;
      if (!this.undoFlag && !this.finish) {
        if (getBlockMetadata() % 8 == 0) {
          Block bottomBlock = getWorldObj().getBlock(this.xCoord, this.yCoord - 1, this.zCoord);
          this.finish = true;
          if (bottomBlock == Blocks.air) {
            if (getBlockMetadata() < 8) {
              getWorldObj().setBlock(this.xCoord, this.yCoord - 1, this.zCoord, Stations.instance.shutter, 7, 2);
            } else {
              getWorldObj().setBlock(this.xCoord, this.yCoord - 1, this.zCoord, Stations.instance.shutter, 15, 2);
            } 
            return;
          } 
        } else {
          getWorldObj().setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, getBlockMetadata() - 1, 2);
        } 
      } else if (this.undoFlag) {
        if (getBlockMetadata() % 8 == 7) {
          Block bottomBlock = getWorldObj().getBlock(this.xCoord, this.yCoord + 1, this.zCoord);
          TileEntity te = getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
          if (bottomBlock == Stations.instance.shutter && te != null && te instanceof TileEntityShutter)
            ((TileEntityShutter)te).setUndoFlag(true); 
          getWorldObj().setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
          getWorldObj().removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
        } else {
          getWorldObj().setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, getBlockMetadata() + 1, 2);
        } 
      } 
    } 
  }
  
  public void readFromNBT(NBTTagCompound p_145839_1_) {
    super.readFromNBT(p_145839_1_);
    this.count = p_145839_1_.getInteger("counter");
    this.undoFlag = p_145839_1_.getBoolean("undo");
    this.finish = p_145839_1_.getBoolean("fin");
  }
  
  public void writeToNBT(NBTTagCompound p_145841_1_) {
    super.writeToNBT(p_145841_1_);
    p_145841_1_.setInteger("counter", this.count);
    p_145841_1_.setBoolean("undo", this.undoFlag);
    p_145841_1_.setBoolean("fin", this.finish);
  }
  
  public void setUndoFlag(boolean par1) {
    this.undoFlag = par1;
    if (!this.undoFlag)
      this.finish = false; 
  }
}
