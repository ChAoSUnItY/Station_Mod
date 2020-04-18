package com.noto0648.stations.items;

import com.noto0648.stations.Stations;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemTicketCase extends Item {
  public ItemTicketCase() {
    setCreativeTab((CreativeTabs)Stations.tab);
    setUnlocalizedName("NotoMod.ticketCase");
    setMaxStackSize(1);
    setTextureName("notomod:ticket_case");
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
    if (player.isSneaking()) {
      boolean[] stacks = new boolean[11];
      if (!itemStack.hasTagCompound()) {
        itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setTag("Items", (NBTBase)new NBTTagList());
      } 
      NBTTagList tags = (NBTTagList)itemStack.getTagCompound().getTag("Items");
      for (int j = 0; j < tags.tagCount(); j++) {
        NBTTagCompound tagCompound = tags.getCompoundTagAt(j);
        int slot = tagCompound.getByte("Slot");
        if (slot >= 0 && slot < stacks.length)
          stacks[slot] = true; 
      } 
      for (int i = 0; i < player.inventory.mainInventory.length; i++) {
        ItemStack stack = player.inventory.mainInventory[i];
        if (stack != null && stack.getItem() == Stations.instance.ticket && stack.getItemDamage() < 2)
          for (int k = 0; k < stacks.length; k++) {
            if (!stacks[k]) {
              NBTTagCompound compound = new NBTTagCompound();
              compound.setInteger("Slot", (byte)k);
              stack.writeToNBT(compound);
              tags.appendTag((NBTBase)compound);
              stacks[k] = true;
              player.inventory.mainInventory[i] = null;
              break;
            } 
          }  
      } 
    } else {
      player.openGui(Stations.instance, 30, world, (int)player.posX, (int)player.posY, (int)player.posZ);
    } 
    return itemStack;
  }
}
