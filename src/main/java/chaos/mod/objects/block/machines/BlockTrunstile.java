package chaos.mod.objects.block.machines;

import java.util.List;
import java.util.Random;

import chaos.mod.init.ItemInit;
import chaos.mod.objects.block.base.BlockHasFace;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTrunstile extends BlockHasFace {
	public static final PropertyBool OPEN = PropertyBool.create("open");

	public BlockTrunstile(String name, Material material) {
		super(name, material, false);
		this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, false));
	}

	@Override
	public int tickRate(World worldIn) {
		return 40;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (state.getValue(OPEN)) {
			worldIn.setBlockState(pos, state.withProperty(OPEN, false));
			worldIn.playSound(null, pos, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundCategory.BLOCKS, 1F, 1F);
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		if (!state.getValue(OPEN)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0, 0, 1, 1.5D, 1));
		} else {
			switch (state.getValue(FACING)) {
			case NORTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0, 0, 3 / 16D, 1.5D, 1));
				break;
			case EAST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0, 0, 1, 1.5D, 3 / 16D));
				break;
			case SOUTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(13 / 16D, 0, 0, 1, 1.5D, 1));
				break;
			case WEST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0, 13 / 16D, 1, 1.5D, 1));
				break;
			default:
				return;
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (state.getValue(OPEN)) {
			return false;
		} else if (playerIn.getHeldItem(hand).getItem() == ItemInit.UNUSED_TICKET
				|| playerIn.getHeldItem(hand).getItem() == ItemInit.USED_TICKET) {
			worldIn.setBlockState(pos, state.withProperty(OPEN, true));
			worldIn.playSound(null, pos, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundCategory.BLOCKS, 1F, 1F);
			worldIn.scheduleBlockUpdate(pos, state.getBlock(), this.tickRate(worldIn), 4);
			int meta = playerIn.getHeldItem(hand).getItemDamage();
			if (meta == 1) {
				playerIn.getHeldItem(hand).shrink(1);
			} else if (meta == 0) {
				playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemInit.USED_TICKET, 1, 1));
			}
			return true;
		}
		return false;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos, state.withProperty(OPEN, false));
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, OPEN });
	}
}
