package com.noto0648.stations.container;

import com.noto0648.stations.Stations;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerTicketCase extends Container {
  private InventoryTicketCase inventory;
  
  private ItemStack ticketCase;
  
  public ContainerTicketCase(InventoryPlayer inv, ItemStack itemStack, World world) {
    this.ticketCase = itemStack;
    this.inventory = new InventoryTicketCase(this.ticketCase, inv, world);
    this.inventory.openInventory();
    int i;
    for (i = 0; i < 11; i++) {
      addSlotToContainer(new Slot(this.inventory, i, i * 18 + 8, 20) {
            public boolean isItemValid(ItemStack p_75214_1_) {
              return (p_75214_1_.getItem() == Stations.instance.ticket && p_75214_1_.getItemDamage() < 2);
            }
          });
    } 
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++) {
        addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 26 + j * 18, 51 + i * 18) {
              public boolean canTakeStack(EntityPlayer p_82869_1_) {
                return !getHasStack() ? true : ((getStack().getItem() != Stations.instance.ticketCase));
              }
            });
      } 
    } 
    for (i = 0; i < 9; i++) {
      addSlotToContainer(new Slot((IInventory)inv, i, 26 + i * 18, 109) {
            public boolean canTakeStack(EntityPlayer p_82869_1_) {
              return !getHasStack() ? true : ((getStack().getItem() != Stations.instance.ticketCase));
            }
          });
    } 
  }
  
  public boolean canInteractWith(EntityPlayer p_75145_1_) {
    return true;
  }
  
  public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
    ItemStack itemstack = null;
    Slot slot = this.inventorySlots.get(p_82846_2_);
    if (slot != null && slot.getHasStack()) {
      ItemStack itemstack1 = slot.getStack();
      itemstack = itemstack1.copy();
      if (p_82846_2_ < this.inventory.getSizeInventory()) {
        if (!mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true))
          return null; 
      } else {
        if (!isTicketItem(slot.getStack()))
          return null; 
        if (!mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false))
          return null; 
      } 
      if (itemstack1.stackSize == 0) {
        slot.putStack((ItemStack)null);
      } else {
        slot.onSlotChanged();
      } 
    } 
    return itemstack;
  }
  
  public void onContainerClosed(EntityPlayer p_75134_1_) {
    super.onContainerClosed(p_75134_1_);
    this.inventory.closeInventory();
  }
  
  private boolean isTicketItem(ItemStack p_75214_1_) {
    return (p_75214_1_.getItem() == Stations.instance.ticket && p_75214_1_.getItemDamage() < 2);
  }
}
