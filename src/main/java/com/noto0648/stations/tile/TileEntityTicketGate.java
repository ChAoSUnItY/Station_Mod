package com.noto0648.stations.tile;

import com.noto0648.stations.Stations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityTicketGate extends TileBase {
  public static final int OPEN_INTERVAL = 60;
  
  private int openInterval = -1;
  
  private int lastEntityId;
  
  public void updateEntity() {
    if (this.openInterval >= 0) {
      this.openInterval++;
      if (this.openInterval == 60) {
        this.openInterval = -1;
        Entity e = this.worldObj.getEntityByID(this.lastEntityId);
        if (e != null && e instanceof EntityPlayer)
          this.worldObj.playAuxSFXAtEntity((EntityPlayer)e, 1003, this.xCoord, this.yCoord, this.zCoord, 0); 
      } 
    } 
  }
  
  public void readFromNBT(NBTTagCompound p_145839_1_) {
    super.readFromNBT(p_145839_1_);
    this.openInterval = p_145839_1_.getInteger("interval");
    this.lastEntityId = p_145839_1_.getInteger("playerId");
  }
  
  public void writeToNBT(NBTTagCompound p_145841_1_) {
    super.writeToNBT(p_145841_1_);
    p_145841_1_.setInteger("interval", this.openInterval);
    p_145841_1_.setInteger("playerId", this.lastEntityId);
  }
  
  public void openGate(int playerId) {
    this.openInterval = 0;
    this.lastEntityId = playerId;
  }
  
  public boolean isGateOpen() {
    return (this.openInterval != -1);
  }
  
  public static ItemStack cutTicket(ItemStack ticket) {
    if (ticket == null || ticket.getItem() != Stations.instance.ticket || ticket.stackSize != 1)
      return null; 
    if (ticket.getItemDamage() == 0) {
      ItemStack result = new ItemStack(ticket.getItem(), 1, 1);
      result.setTagCompound(ticket.getTagCompound());
      return result;
    } 
    if (ticket.getItemDamage() == 2)
      return ticket; 
    return null;
  }
}
