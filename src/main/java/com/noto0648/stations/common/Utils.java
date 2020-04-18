package com.noto0648.stations.common;

import com.noto0648.stations.Stations;
import com.noto0648.stations.packet.IPacketSender;
import com.noto0648.stations.packet.PacketSendTile;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Utils {
  public static Utils INSTANCE = new Utils();
  
  private final Random rand = new Random();
  
  public boolean haveWrench(EntityPlayer ep) {
    return (ep.getCurrentEquippedItem() != null && ep.getCurrentEquippedItem().getItem() == Stations.instance.torqueWrench);
  }
  
  public boolean haveTicket(EntityPlayer ep) {
    return (ep.getCurrentEquippedItem() != null && ep.getCurrentEquippedItem().getItem() == Stations.instance.ticket);
  }
  
  public void sendPacket(IPacketSender packet) {
    List<Object> result = new ArrayList();
    packet.setSendData(result);
    TileEntity te = packet.getTile();
    Stations.packetDispatcher.sendToServer((IMessage)new PacketSendTile(result, te.xCoord, te.yCoord, te.zCoord));
  }
  
  public void dropItemStack(World world, ItemStack stack, int x, int y, int z) {
    ItemStack itemstack = stack;
    if (itemstack != null) {
      float f = this.rand.nextFloat() * 0.8F + 0.1F;
      float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
      for (float f2 = this.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld((Entity)entityitem)) {
        int j1 = this.rand.nextInt(21) + 10;
        if (j1 > itemstack.stackSize)
          j1 = itemstack.stackSize; 
        itemstack.stackSize -= j1;
        EntityItem entityitem = new EntityItem(world, (x + f), (y + f1), (z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
        float f3 = 0.05F;
        entityitem.motionX = ((float)this.rand.nextGaussian() * f3);
        entityitem.motionY = ((float)this.rand.nextGaussian() * f3 + 0.2F);
        entityitem.motionZ = ((float)this.rand.nextGaussian() * f3);
        if (itemstack.hasTagCompound())
          entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy()); 
      } 
    } 
  }
  
  public void sendToPlayers(IPacketSender packetSender) {
    List<Object> result = new ArrayList();
    packetSender.setSendData(result);
    TileEntity te = packetSender.getTile();
    sendToPlayers((IMessage)new PacketSendTile(result, te.xCoord, te.yCoord, te.zCoord), te);
  }
  
  public void sendToPlayers(IMessage mes, TileEntity te) {
    sendToPlayers(mes, te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, 192);
  }
  
  public void sendToPlayers(IMessage mes, World world, int x, int y, int z, int maxDistance) {
    if (!world.isRemote && mes != null)
      for (int j = 0; j < world.playerEntities.size(); j++) {
        EntityPlayerMP player = world.playerEntities.get(j);
        if (Math.abs(player.posX - x) <= maxDistance && Math.abs(player.posY - y) <= maxDistance && Math.abs(player.posZ - z) <= maxDistance)
          player.playerNetServerHandler.sendPacket(Stations.packetDispatcher.getPacketFrom(mes)); 
      }  
  }
}
