package com.noto0648.stations.blocks;

import com.noto0648.stations.Stations;
import com.noto0648.stations.tile.TileEntityShutter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockShutter extends BlockContainer {
  public BlockShutter() {
    super(Material.iron);
    setBlockName("NotoMod.shutter");
    setBlockTextureName("notomod:shutter_main");
  }
  
  public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
    super.setBlockBoundsBasedOnState(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_);
    int meta = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_) % 8;
    if (p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_) < 8) {
      setBlockBounds(0.0F, meta * 0.125F, 0.4F, 1.0F, 1.0F, 0.6F);
    } else {
      setBlockBounds(0.4F, meta * 0.125F, 0.0F, 0.6F, 1.0F, 1.0F);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
    p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
  }
  
  public int quantityDropped(Random random) {
    return 0;
  }
  
  public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
    super.harvestBlock(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
    System.out.println("harvest");
  }
  
  public void onBlockHarvested(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_) {
    endProcess(p_149681_1_, p_149681_2_, p_149681_3_, p_149681_4_);
  }
  
  private void endProcess(World p_149636_1_, int p_149636_3_, int p_149636_4_, int p_149636_5_) {
    int i;
    for (i = 1;; i++) {
      Block block = p_149636_1_.getBlock(p_149636_3_, p_149636_4_ + i, p_149636_5_);
      int meta = p_149636_1_.getBlockMetadata(p_149636_3_, p_149636_4_ + i, p_149636_5_);
      if (block == Stations.instance.shutter) {
        p_149636_1_.setBlockToAir(p_149636_3_, p_149636_4_ + i, p_149636_5_);
      } else {
        if (block == Stations.instance.stationMaterial && (meta == 14 || meta == 15))
          p_149636_1_.setBlockToAir(p_149636_3_, p_149636_4_ + i, p_149636_5_); 
        break;
      } 
    } 
    i = 1;
    while (true) {
      Block block = p_149636_1_.getBlock(p_149636_3_, p_149636_4_ - i, p_149636_5_);
      if (block == Stations.instance.shutter) {
        p_149636_1_.setBlockToAir(p_149636_3_, p_149636_4_ - i, p_149636_5_);
        i++;
      } 
      break;
    } 
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return new ItemStack(Stations.instance.stationMaterial, 1, 14);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
    ret.add(new ItemStack(Stations.instance.stationMaterial, 1, 14));
    return ret;
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean renderAsNormalBlock() {
    return false;
  }
  
  public int getRenderType() {
    return -1;
  }
  
  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
    return (TileEntity)new TileEntityShutter();
  }
}
