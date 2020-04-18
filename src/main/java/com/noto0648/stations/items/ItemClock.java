package com.noto0648.stations.items;

import com.noto0648.stations.Stations;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemClock extends Item {
  public ItemClock() {
    setMaxStackSize(1);
    setTextureName("notomod:digital_clock");
    setCreativeTab((CreativeTabs)Stations.tab);
    setHasSubtypes(true);
    setMaxDamage(0);
  }
  
  public String getUnlocalizedName(ItemStack p_77667_1_) {
    return "item." + ((p_77667_1_.getItemDamage() == 1) ? "NotoMod.analogClock" : "NotoMod.digitalClock");
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
  }
}
