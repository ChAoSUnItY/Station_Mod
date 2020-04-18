package com.noto0648.stations.items;

import com.noto0648.stations.Stations;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemStaffArmor extends ItemArmor {
  public ItemStaffArmor(ItemArmor.ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
    super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
    setCreativeTab((CreativeTabs)Stations.tab);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    if (slot == 0 || slot == 1 || slot == 3)
      return "notomod:textures/models/armor/station_staff_layer_1.png"; 
    return "notomod:textures/models/armor/station_staff_layer_2.png";
  }
}
