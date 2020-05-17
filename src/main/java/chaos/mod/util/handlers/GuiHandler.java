package chaos.mod.util.handlers;

import chaos.mod.objects.block.container.ContainerMarkMachine;
import chaos.mod.objects.block.container.ContainerVendor;
import chaos.mod.objects.block.gui.GuiMarkMachine;
import chaos.mod.objects.block.gui.GuiVendor;
import chaos.mod.objects.item.container.ContainerCase;
import chaos.mod.objects.item.gui.GuiCase;
import chaos.mod.tileentity.TileEntityMarkMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	/***
	 * 1 > Case
	 */
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 1:
			return new ContainerCase(player.inventory, player);
		case 2:
			return new ContainerVendor();
		case 3:
			return new ContainerMarkMachine();
		default:
			return null;
		}
	}
	
	/***
	 * 1 > Case
	 * 2 > Vendor
	 * 3 > MarkMachine
	 */
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		//BlockPos playerPos = player.getPosition();
		switch (ID) {
		case 1:
			return new GuiCase(player.inventory, player);
		case 2:
			return new GuiVendor(player.inventory, player, pos);
		case 3:
			return new GuiMarkMachine((TileEntityMarkMachine)world.getTileEntity(pos));
		default:
			return null;
		}
	}
}
