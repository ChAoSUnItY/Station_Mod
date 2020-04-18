package com.noto0648.stations.items;

import com.noto0648.stations.Stations;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemTicket extends Item {
  private IIcon usingTicket;
  
  private IIcon normalTicket;
  
  private IIcon icTicket;
  
  public ItemTicket() {
    setCreativeTab((CreativeTabs)Stations.tab);
    setUnlocalizedName("NotoMod.ticket");
    setMaxStackSize(1);
    setHasSubtypes(true);
    setMaxDamage(0);
  }
  
  public String getUnlocalizedName(ItemStack p_77667_1_) {
    return "item." + ((p_77667_1_.getItemDamage() == 1) ? "NotoMod.usingTicket" : ((p_77667_1_.getItemDamage() == 0) ? "NotoMod.ticket" : "NotoMod.icCard"));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int p_77617_1_) {
    return (p_77617_1_ == 0) ? this.normalTicket : ((p_77617_1_ == 1) ? this.usingTicket : this.icTicket);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister p_94581_1_) {
    this.normalTicket = p_94581_1_.registerIcon("notomod:ticket");
    this.usingTicket = p_94581_1_.registerIcon("notomod:ticket2");
    this.icTicket = p_94581_1_.registerIcon("notomod:ticket_ic");
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 2));
  }
}
