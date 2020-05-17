package chaos.mod.objects.item;

import chaos.mod.init.BlockInit;
import chaos.mod.tileentity.TileEntityDepartureMark;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemLanSetter extends ItemBase {
	public ItemLanSetter(String name) {
		super(name);
		setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getItem() == this) {
			Block blk = worldIn.getBlockState(pos).getBlock();
			if (blk == BlockInit.MARK_MACHINE) {
				NBTTagCompound compound = new NBTTagCompound();
				compound.setInteger("parentX", pos.getX());
				compound.setInteger("parentY", pos.getY());
				compound.setInteger("parentZ", pos.getZ());
				stack.setTagCompound(compound);
				if (worldIn.isRemote)
					player.sendMessage(new TextComponentString("Register parent!"));
			} else if (blk == BlockInit.DEPARTURE_MARK) {
				if (stack.hasTagCompound()) {
					BlockPos pos2 = new BlockPos(stack.getTagCompound().getInteger("parentX"),
							stack.getTagCompound().getInteger("parentY"), stack.getTagCompound().getInteger("parentZ"));
					if (worldIn.getBlockState(pos2).getBlock() == BlockInit.MARK_MACHINE) {
						TileEntity te = worldIn.getTileEntity(pos);
						if (te != null && te instanceof TileEntityDepartureMark) {
							System.out.println(
									((TileEntityDepartureMark) te).setParent(pos2.getX(), pos2.getY(), pos2.getZ()));
							if (((TileEntityDepartureMark) te).setParent(pos2.getX(), pos2.getY(), pos2.getZ())) {
								te.markDirty();
								if (worldIn.isRemote)
									player.sendMessage(new TextComponentString("Successful registration"));
							}
						}
					} else if (worldIn.isRemote) {
						player.sendMessage(new TextComponentString("Parent is not found"));
					}
				} else if (worldIn.isRemote) {
					player.sendMessage(new TextComponentString("Parent is not registered"));
				}
			} else if (player.isSneaking()) {
				NBTTagCompound tag = new NBTTagCompound();
				stack.setTagCompound(tag);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.SUCCESS;
	}
}
