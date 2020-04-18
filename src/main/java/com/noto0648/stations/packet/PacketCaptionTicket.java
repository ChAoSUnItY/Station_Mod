package com.noto0648.stations.packet;

import com.noto0648.stations.Stations;
import com.noto0648.stations.common.Utils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PacketCaptionTicket implements IMessageHandler<PacketSendTicket, IMessage> {
  public IMessage onMessage(PacketSendTicket message, MessageContext ctx) {
    int ticketOutPutId = message.getIDValue();
    EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).playerEntity;
    if (entityPlayerMP != null) {
      if (ticketOutPutId == 2) {
        int emptySlot = ((EntityPlayer)entityPlayerMP).inventory.getFirstEmptyStack();
        ItemStack ticket = new ItemStack(Stations.instance.ticketCase, 1);
        if (emptySlot != -1) {
          ((EntityPlayer)entityPlayerMP).inventory.setInventorySlotContents(emptySlot, ticket);
        } else {
          Utils.INSTANCE.dropItemStack(((EntityPlayer)entityPlayerMP).worldObj, ticket, message.x, message.y, message.z);
        } 
      } 
      int loopMax = (ticketOutPutId == 0) ? 1 : ((ticketOutPutId == 1) ? 2 : ((ticketOutPutId == 2) ? 11 : ((ticketOutPutId == 3) ? 1 : 0)));
      for (int i = 0; i < loopMax; i++) {
        int emptySlot = ((EntityPlayer)entityPlayerMP).inventory.getFirstEmptyStack();
        ItemStack ticket = new ItemStack(Stations.instance.ticket, 1, 0);
        ticket.setTagCompound(new NBTTagCompound());
        ticket.getTagCompound().setLong("issue", ((EntityPlayer)entityPlayerMP).worldObj.getWorldTime());
        if (emptySlot != -1) {
          ((EntityPlayer)entityPlayerMP).inventory.setInventorySlotContents(emptySlot, ticket);
        } else {
          Utils.INSTANCE.dropItemStack(((EntityPlayer)entityPlayerMP).worldObj, ticket, message.x, message.y, message.z);
        } 
      } 
    } 
    return null;
  }
}
