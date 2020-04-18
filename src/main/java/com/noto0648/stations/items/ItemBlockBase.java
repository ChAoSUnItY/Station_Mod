package com.noto0648.stations.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBase extends ItemBlock {
  public ItemBlockBase(Block p_i45328_1_) {
    super(p_i45328_1_);
    setHasSubtypes(true);
    setMaxDamage(0);
  }
  
  public String getUnlocalizedName(ItemStack p_77667_1_) {
    return this.field_150939_a.getUnlocalizedName() + p_77667_1_.getItemDamage();
  }
  
  public int getMetadata(int p_77647_1_) {
    return p_77647_1_;
  }
}
