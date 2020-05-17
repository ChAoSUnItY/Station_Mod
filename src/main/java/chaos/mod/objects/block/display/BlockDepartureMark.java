package chaos.mod.objects.block.display;

import chaos.mod.objects.block.base.BlockHasFace;
import chaos.mod.tileentity.TileEntityDepartureMark;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDepartureMark extends BlockHasFace implements ITileEntityProvider {
	public BlockDepartureMark(String name) {
		super(name, Material.IRON, false);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDepartureMark();
	}
}

