package com.noto0648.stations.blocks;

import com.noto0648.stations.Stations;
import com.noto0648.stations.common.Utils;
import com.noto0648.stations.tile.TileEntityShutter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockStation extends Block {
  public Map<String, IIcon> iconMap = new HashMap<String, IIcon>();
  
  public BlockStation() {
    super(Material.rock);
    setBlockName("NotoMod.stationMaterial");
    setCreativeTab((CreativeTabs)Stations.tab);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister par1) {
    this.iconMap.put("station_brick", par1.registerIcon("notomod:station_brick"));
    this.iconMap.put("station_brick2", par1.registerIcon("notomod:station_brick2"));
    this.iconMap.put("station_brick2_left", par1.registerIcon("notomod:station_brick2_left"));
    this.iconMap.put("station_brick2_down", par1.registerIcon("notomod:station_brick2_down"));
    this.iconMap.put("station_brick2_right", par1.registerIcon("notomod:station_brick2_right"));
    this.iconMap.put("station_brick_up", par1.registerIcon("notomod:station_brick_up"));
    this.iconMap.put("station_brick_left", par1.registerIcon("notomod:station_brick_left"));
    this.iconMap.put("station_brick_down", par1.registerIcon("notomod:station_brick_down"));
    this.iconMap.put("station_brick_right", par1.registerIcon("notomod:station_brick_right"));
    this.iconMap.put("brick_side", par1.registerIcon("notomod:brick_side"));
    this.iconMap.put("asphalt", par1.registerIcon("notomod:asphalt"));
    this.iconMap.put("iron_block", par1.registerIcon("iron_block"));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int side, int meta) {
    if (meta == 0)
      return this.iconMap.get("station_brick"); 
    if (meta <= 4 && meta >= 1 && side != 1)
      return this.iconMap.get("brick_side"); 
    if (meta == 1)
      return this.iconMap.get("station_brick2_left"); 
    if (meta == 2)
      return this.iconMap.get("station_brick2"); 
    if (meta == 3)
      return this.iconMap.get("station_brick2_down"); 
    if (meta == 4)
      return this.iconMap.get("station_brick2_right"); 
    if (meta <= 8 && meta >= 5 && side != 1)
      return this.iconMap.get("station_brick"); 
    if (meta == 5)
      return this.iconMap.get("station_brick_up"); 
    if (meta == 6)
      return this.iconMap.get("station_brick_left"); 
    if (meta == 7)
      return this.iconMap.get("station_brick_down"); 
    if (meta == 8)
      return this.iconMap.get("station_brick_right"); 
    if (meta == 9)
      return this.iconMap.get("asphalt"); 
    if (meta == 14 || meta == 15)
      return this.iconMap.get("iron_block"); 
    return this.blockIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
    p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
    p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
    p_149666_3_.add(new ItemStack(p_149666_1_, 1, 5));
    p_149666_3_.add(new ItemStack(p_149666_1_, 1, 9));
    p_149666_3_.add(new ItemStack(p_149666_1_, 1, 14));
  }
  
  public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.floor_double((p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (p_149689_6_.getItemDamage() == 1) {
      if (l == 0)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2); 
      if (l == 1)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 1, 2); 
      if (l == 3)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2); 
      if (l == 2)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2); 
    } else if (p_149689_6_.getItemDamage() == 5) {
      if (l == 0)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 7, 2); 
      if (l == 1)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 6, 2); 
      if (l == 2)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2); 
      if (l == 3)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 8, 2); 
    } else if (p_149689_6_.getItemDamage() == 14 || p_149689_6_.getItemDamage() == 15) {
      if (l == 3 || l == 1) {
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 15, 2);
      } else {
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 14, 2);
      } 
    } 
  }
  
  public int damageDropped(int p_149692_1_) {
    if (p_149692_1_ <= 4 && p_149692_1_ >= 1)
      return 1; 
    if (p_149692_1_ <= 8 && p_149692_1_ >= 5)
      return 5; 
    if (p_149692_1_ == 14 || p_149692_1_ == 15)
      return 14; 
    return p_149692_1_;
  }
  
  public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    int meta = p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_);
    if (meta == 14 && Utils.INSTANCE.haveWrench(p_149727_5_)) {
      p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, 15, 2);
      return true;
    } 
    if (meta == 15 && Utils.INSTANCE.haveWrench(p_149727_5_)) {
      p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, 14, 2);
      return true;
    } 
    return false;
  }
  
  public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
    int metadata = p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_, p_149695_4_);
    if (metadata == 14 || metadata == 15)
      if (!p_149695_1_.isRemote)
        if (p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_)) {
          int i = 1;
          while (true) {
            Block block = p_149695_1_.getBlock(p_149695_2_, p_149695_3_ - i, p_149695_4_);
            TileEntity te = p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_ - i, p_149695_4_);
            if (block == Stations.instance.shutter && te != null && te instanceof TileEntityShutter) {
              i++;
              continue;
            } 
            TileEntity tile = p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_ - i - 1, p_149695_4_);
            if (tile != null && tile instanceof TileEntityShutter)
              ((TileEntityShutter)tile).setUndoFlag(true); 
            break;
          } 
        } else if (!p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_)) {
          int i = 1;
          while (true) {
            Block block = p_149695_1_.getBlock(p_149695_2_, p_149695_3_ - i, p_149695_4_);
            TileEntity te = p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_ - i, p_149695_4_);
            if (block == Stations.instance.shutter && te != null && te instanceof TileEntityShutter) {
              i++;
              continue;
            } 
            if (i == 1 && te == null) {
              p_149695_1_.setBlock(p_149695_2_, p_149695_3_ - 1, p_149695_4_, Stations.instance.shutter, (metadata == 14) ? 7 : 15, 2);
              break;
            } 
            if (i == 1 && te != null) {
              if (te != null && te instanceof TileEntityShutter)
                ((TileEntityShutter)te).setUndoFlag(false); 
              break;
            } 
            TileEntity tile = p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_ - i - 1, p_149695_4_);
            if (tile != null && tile instanceof TileEntityShutter)
              ((TileEntityShutter)tile).setUndoFlag(false); 
            break;
          } 
        }   
  }
  
  public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
    if (p_149749_6_ == 14 || p_149749_6_ == 15) {
      int i = 1;
      while (true) {
        Block block = p_149749_1_.getBlock(p_149749_2_, p_149749_3_ - i, p_149749_4_);
        if (block == Stations.instance.shutter) {
          p_149749_1_.setBlockToAir(p_149749_2_, p_149749_3_ - i, p_149749_4_);
          i++;
        } 
        break;
      } 
    } 
    super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
  }
  
  public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
    int meta = p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);
    if (meta == 14 || meta == 15)
      if (!p_149674_1_.isRemote && !p_149674_1_.isBlockIndirectlyGettingPowered(p_149674_2_, p_149674_3_, p_149674_4_))
        p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, this, meta, 2);  
  }
}
