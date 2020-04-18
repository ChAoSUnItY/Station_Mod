package com.noto0648.stations.blocks;

import com.noto0648.stations.Stations;
import com.noto0648.stations.client.render.TileEntityNamePlateRender;
import com.noto0648.stations.common.Utils;
import com.noto0648.stations.tile.TileEntityNamePlate;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class BlockNamePlate extends BlockContainer implements ISimpleBlockRenderingHandler {
  public BlockNamePlate() {
    super(Material.rock);
    setBlockName("NotoMod.namePlate");
    setCreativeTab((CreativeTabs)Stations.tab);
  }
  
  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    Block block = world.getBlock(x, y, z);
    if (block != this)
      return block.getLightValue(world, x, y, z); 
    TileEntity te = world.getTileEntity(x, y, z);
    if (te != null && te instanceof TileEntityNamePlate)
      return ((TileEntityNamePlate)te).light ? 15 : 0; 
    return 0;
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean renderAsNormalBlock() {
    return false;
  }
  
  public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
    super.setBlockBoundsBasedOnState(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_);
    int meta = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_);
    if (meta == 0) {
      setBlockBounds(0.4F, 0.0F, -0.5F, 0.6F, 1.0F, 1.5F);
    } else if (meta == 1) {
      setBlockBounds(-0.5F, 0.0F, 0.4F, 1.5F, 1.0F, 0.6F);
    } else if (meta == 5) {
      setBlockBounds(0.0F, 0.0F, -0.5F, 0.2F, 1.0F, 1.5F);
    } else if (meta == 4) {
      setBlockBounds(0.8F, 0.0F, -0.5F, 1.0F, 1.0F, 1.5F);
    } else if (meta == 2) {
      setBlockBounds(-0.5F, 0.0F, 0.8F, 1.5F, 1.0F, 1.0F);
    } else if (meta == 3) {
      setBlockBounds(-0.5F, 0.0F, 0.0F, 1.5F, 1.0F, 0.2F);
    } 
  }
  
  public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (Utils.INSTANCE.haveWrench(p_149727_5_)) {
      p_149727_5_.openGui(Stations.instance, 1, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
      return true;
    } 
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
    int meta = p_149633_1_.getBlockMetadata(p_149633_2_, p_149633_3_, p_149633_4_);
    if (meta == 0)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + 0.4000000059604645D, p_149633_3_ + 0.0D, p_149633_4_ + -0.5D, p_149633_2_ + 0.6000000238418579D, p_149633_3_ + 1.0D, p_149633_4_ + 1.5D); 
    if (meta == 1)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + -0.5D, p_149633_3_ + 0.0D, p_149633_4_ + 0.4000000059604645D, p_149633_2_ + 1.5D, p_149633_3_ + 1.0D, p_149633_4_ + 0.6000000238418579D); 
    if (meta == 5)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + 0.0D, p_149633_3_ + 0.0D, p_149633_4_ + -0.5D, p_149633_2_ + 0.20000000298023224D, p_149633_3_ + 1.0D, p_149633_4_ + 1.5D); 
    if (meta == 4)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + 0.800000011920929D, p_149633_3_ + 0.0D, p_149633_4_ + -0.5D, p_149633_2_ + 1.0D, p_149633_3_ + 1.0D, p_149633_4_ + 1.5D); 
    if (meta == 2)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + -0.5D, p_149633_3_ + 0.0D, p_149633_4_ + 0.800000011920929D, p_149633_2_ + 1.5D, p_149633_3_ + 1.0D, p_149633_4_ + 1.0D); 
    if (meta == 3)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + -0.5D, p_149633_3_ + 0.0D, p_149633_4_ + 0.0D, p_149633_2_ + 1.5D, p_149633_3_ + 1.0D, p_149633_4_ + 0.20000000298023224D); 
    return AxisAlignedBB.getBoundingBox(p_149633_2_ + this.minX, p_149633_3_ + this.minY, p_149633_4_ + this.minZ, p_149633_2_ + this.maxX, p_149633_3_ + this.maxY, p_149633_4_ + this.maxZ);
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
    int meta = p_149633_1_.getBlockMetadata(p_149633_2_, p_149633_3_, p_149633_4_);
    if (meta == 0)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + 0.4000000059604645D, p_149633_3_ + 0.0D, p_149633_4_ + -0.5D, p_149633_2_ + 0.6000000238418579D, p_149633_3_ + 1.0D, p_149633_4_ + 1.5D); 
    if (meta == 1)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + -0.5D, p_149633_3_ + 0.0D, p_149633_4_ + 0.4000000059604645D, p_149633_2_ + 1.5D, p_149633_3_ + 1.0D, p_149633_4_ + 0.6000000238418579D); 
    if (meta == 5)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + 0.0D, p_149633_3_ + 0.0D, p_149633_4_ + -0.5D, p_149633_2_ + 0.20000000298023224D, p_149633_3_ + 1.0D, p_149633_4_ + 1.5D); 
    if (meta == 4)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + 0.800000011920929D, p_149633_3_ + 0.0D, p_149633_4_ + -0.5D, p_149633_2_ + 1.0D, p_149633_3_ + 1.0D, p_149633_4_ + 1.5D); 
    if (meta == 2)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + -0.5D, p_149633_3_ + 0.0D, p_149633_4_ + 0.800000011920929D, p_149633_2_ + 1.5D, p_149633_3_ + 1.0D, p_149633_4_ + 1.0D); 
    if (meta == 3)
      return AxisAlignedBB.getBoundingBox(p_149633_2_ + -0.5D, p_149633_3_ + 0.0D, p_149633_4_ + 0.0D, p_149633_2_ + 1.5D, p_149633_3_ + 1.0D, p_149633_4_ + 0.20000000298023224D); 
    return AxisAlignedBB.getBoundingBox(p_149633_2_ + this.minX, p_149633_3_ + this.minY, p_149633_4_ + this.minZ, p_149633_2_ + this.maxX, p_149633_3_ + this.maxY, p_149633_4_ + this.maxZ);
  }
  
  public int damageDropped(int p_149692_1_) {
    if (p_149692_1_ == 0 || p_149692_1_ == 1)
      return 0; 
    return p_149692_1_;
  }
  
  public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
    if (p_149660_5_ >= 2 && p_149660_5_ <= 5)
      return p_149660_5_; 
    return p_149660_9_;
  }
  
  public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.floor_double((p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
    int meta = p_149689_1_.getBlockMetadata(p_149689_2_, p_149689_3_, p_149689_4_);
    if (meta == 0) {
      if (l == 1 || l == 3)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, p_149689_6_.getItemDamage(), 2); 
      if (l == 0 || l == 2)
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, p_149689_6_.getItemDamage() + 1, 2); 
    } 
  }
  
  public int getRenderType() {
    return Stations.instance.namePlateRenderId;
  }
  
  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
    return (TileEntity)new TileEntityNamePlate();
  }
  
  @SideOnly(Side.CLIENT)
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    if (block == Stations.instance.namePlate && modelId == getRenderId()) {
      GL11.glPushMatrix();
      (Minecraft.getMinecraft()).renderEngine.bindTexture(TileEntityNamePlateRender.texture);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      TileEntityNamePlateRender.model.renderAll();
      GL11.glPopMatrix();
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldRender3DInInventory(int modelId) {
    return (getRenderId() == modelId);
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderId() {
    return Stations.instance.namePlateRenderId;
  }
}
