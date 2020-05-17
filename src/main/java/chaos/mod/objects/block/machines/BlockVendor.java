package chaos.mod.objects.block.machines;

import chaos.mod.Main;
import chaos.mod.objects.block.base.BlockHasFace;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockVendor extends BlockHasFace {
	public BlockVendor(String name, Material material) {
		super(name, material, false);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) playerIn.openGui(Main.instance, 2, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
