package com.noto0648.stations.tile;

import com.noto0648.stations.common.MarkData;
import com.noto0648.stations.common.MinecraftDate;
import com.noto0648.stations.common.Utils;
import com.noto0648.stations.packet.PacketSendMarkData;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityMarkMachine extends TileBase {
  public List<MarkData> markDataList = new ArrayList<MarkData>();
  
  public void readFromNBT(NBTTagCompound p_145839_1_) {
    super.readFromNBT(p_145839_1_);
    this.markDataList = new ArrayList<MarkData>();
    this.markDataList.clear();
    NBTTagList tags = (NBTTagList)p_145839_1_.getTag("marks");
    for (int i = 0; i < tags.tagCount(); i++) {
      NBTTagCompound compound = tags.getCompoundTagAt(i);
      MarkData mkd = new MarkData();
      mkd.readFromNBTTag(compound);
      this.markDataList.add(mkd);
    } 
  }
  
  public void writeToNBT(NBTTagCompound p_145841_1_) {
    super.writeToNBT(p_145841_1_);
    NBTTagList tags = new NBTTagList();
    for (int i = 0; i < this.markDataList.size(); i++) {
      NBTTagCompound compound = new NBTTagCompound();
      ((MarkData)this.markDataList.get(i)).writeToNBTTag(compound);
      tags.appendTag((NBTBase)compound);
    } 
    p_145841_1_.setTag("marks", (NBTBase)tags);
  }
  
  public void setMarkDataList(List<MarkData> list) {
    this.markDataList.clear();
    for (int i = 0; i < list.size(); i++)
      this.markDataList.add(list.get(i)); 
    if (!this.worldObj.isRemote)
      Utils.INSTANCE.sendToPlayers((IMessage)new PacketSendMarkData(this.markDataList, this.xCoord, this.yCoord, this.zCoord), this); 
  }
  
  public List<MarkData> getMarkDataList() {
    return this.markDataList;
  }
  
  public MarkData[] getStringIndex() {
    MinecraftDate md = new MinecraftDate(this.worldObj.getWorldTime());
    MarkData[] result = new MarkData[2];
    int resultIndex = 0;
    for (int i = 0; i < this.markDataList.size(); i++) {
      MarkData mkd = this.markDataList.get(i);
      if (mkd.hours >= md.getHours())
        if (mkd.hours > md.getHours() || (mkd.minutes >= md.getMinutes() && mkd.hours >= md.getHours())) {
          result[resultIndex] = mkd;
          resultIndex++;
        }  
      if (resultIndex >= 2)
        break; 
    } 
    return result;
  }
}
