package com.noto0648.stations.blocks;

import com.noto0648.stations.Stations;
import com.noto0648.stations.client.render.TileEntityRailToyCornerRender;
import com.noto0648.stations.client.render.TileEntityRailToyRender;
import com.noto0648.stations.tile.TileEntityRailToy;
import com.noto0648.stations.tile.TileEntityRailToyCorner;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class BlockRailToy extends BlockContainer implements ISimpleBlockRenderingHandler {
  public BlockRailToy() {
    super(Material.rock);
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    setCreativeTab((CreativeTabs)Stations.tab);
    setBlockName("NotoMod.toyRail");
  }
  
  public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (!p_149727_1_.isRemote)
      return true; 
    return false;
  }
  
  public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.floor_double((p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
    TileEntity te = p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_);
    if (te != null && te instanceof TileEntityRailToy)
      ((TileEntityRailToy)te).setRotate(l); 
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
    p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
    p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean renderAsNormalBlock() {
    return false;
  }
  
  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
    if (p_149915_2_ == 1)
      return (TileEntity)new TileEntityRailToyCorner(); 
    return (TileEntity)new TileEntityRailToy();
  }
  
  public int getRenderType() {
    return Stations.instance.railToyRenderId;
  }
  
  @SideOnly(Side.CLIENT)
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    GL11.glPushMatrix();
    GL11.glTranslatef(0.5F, 1.4F, 0.5F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    if (metadata == 1) {
      (Minecraft.getMinecraft()).renderEngine.bindTexture(TileEntityRailToyCornerRender.texture);
      TileEntityRailToyCornerRender.model.allRender(0.065F);
    } else {
      (Minecraft.getMinecraft()).renderEngine.bindTexture(TileEntityRailToyRender.texture);
      TileEntityRailToyRender.model.allRender(0.065F);
    } 
    GL11.glPopMatrix();
  }
  
  @SideOnly(Side.CLIENT)
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderId() {
    return Stations.instance.railToyRenderId;
  }
}
