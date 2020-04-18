package com.noto0648.stations.tile;

import com.noto0648.stations.common.Utils;
import com.noto0648.stations.packet.IPacketReceiver;
import com.noto0648.stations.packet.IPacketSender;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityNumberPlate extends TileBase implements IPacketReceiver, IPacketSender {
  private String drawStr = "";
  
  public void readFromNBT(NBTTagCompound p_145839_1_) {
    super.readFromNBT(p_145839_1_);
    this.drawStr = p_145839_1_.getString("number");
  }
  
  public void writeToNBT(NBTTagCompound p_145841_1_) {
    super.writeToNBT(p_145841_1_);
    p_145841_1_.setString("number", this.drawStr);
  }
  
  public String getDrawStr() {
    return this.drawStr;
  }
  
  public void setDrawStr(String par1) {
    this.drawStr = par1;
  }
  
  public void receive(List<Object> data) {
    if (((Byte)data.get(0)).byteValue() == 3) {
      this.drawStr = (String)data.get(1);
      this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
      if (!this.worldObj.isRemote)
        Utils.INSTANCE.sendToPlayers(this); 
    } 
  }
  
  public TileEntity getTile() {
    return this;
  }
  
  public void setSendData(List<Object> list) {
    list.add(Byte.valueOf((byte)3));
    list.add(this.drawStr);
  }
}
