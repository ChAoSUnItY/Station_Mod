package com.noto0648.stations.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class InventoryTicketCase implements IInventory {
  private ItemStack[] items;
  
  private World worldObj;
  
  private ItemStack ticketCase;
  
  private InventoryPlayer player;
  
  public InventoryTicketCase(ItemStack item, InventoryPlayer inventoryPlayer, World world) {
    this.items = new ItemStack[11];
    this.worldObj = world;
    this.ticketCase = item;
    this.player = inventoryPlayer;
  }
  
  public int getSizeInventory() {
    return this.items.length;
  }
  
  public ItemStack getStackInSlot(int p_70301_1_) {
    return this.items[p_70301_1_];
  }
  
  public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
    if (this.items[p_70298_1_] != null) {
      if ((this.items[p_70298_1_]).stackSize <= p_70298_2_) {
        ItemStack itemStack = this.items[p_70298_1_];
        this.items[p_70298_1_] = null;
        markDirty();
        return itemStack;
      } 
      ItemStack itemstack = this.items[p_70298_1_].splitStack(p_70298_2_);
      if ((this.items[p_70298_1_]).stackSize == 0)
        this.items[p_70298_1_] = null; 
      markDirty();
      return itemstack;
    } 
    return null;
  }
  
  public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
    if (this.items[p_70304_1_] != null) {
      ItemStack itemstack = this.items[p_70304_1_];
      this.items[p_70304_1_] = null;
      return itemstack;
    } 
    return null;
  }
  
  public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
    this.items[p_70299_1_] = p_70299_2_;
    if (p_70299_2_ != null && p_70299_2_.stackSize > getInventoryStackLimit())
      p_70299_2_.stackSize = getInventoryStackLimit(); 
    markDirty();
  }
  
  public String getInventoryName() {
    return "TicketCase";
  }
  
  public boolean hasCustomInventoryName() {
    return false;
  }
  
  public int getInventoryStackLimit() {
    return 1;
  }
  
  public void markDirty() {}
  
  public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
    return true;
  }
  
  public void openInventory() {
    if (!this.ticketCase.hasTagCompound()) {
      this.ticketCase.setTagCompound(new NBTTagCompound());
      this.ticketCase.getTagCompound().setTag("Items", (NBTBase)new NBTTagList());
    } 
    NBTTagList tags = (NBTTagList)this.ticketCase.getTagCompound().getTag("Items");
    for (int i = 0; i < tags.tagCount(); i++) {
      NBTTagCompound tagCompound = tags.getCompoundTagAt(i);
      int slot = tagCompound.getByte("Slot");
      if (slot >= 0 && slot < this.items.length)
        this.items[slot] = ItemStack.loadItemStackFromNBT(tagCompound); 
    } 
  }
  
  public void closeInventory() {
    saveItems();
  }
  
  public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
    return true;
  }
  
  private void saveItems() {
    NBTTagList tagList = new NBTTagList();
    for (int i = 0; i < this.items.length; i++) {
      if (this.items[i] != null) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setByte("Slot", (byte)i);
        this.items[i].writeToNBT(compound);
        tagList.appendTag((NBTBase)compound);
      } 
    } 
    ItemStack result = new ItemStack(this.ticketCase.getItem(), 1);
    result.setTagCompound(new NBTTagCompound());
    result.getTagCompound().setTag("Items", (NBTBase)tagList);
    this.player.setInventorySlotContents(this.player.currentItem, result);
  }
}
