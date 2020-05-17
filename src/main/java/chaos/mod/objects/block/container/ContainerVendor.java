package chaos.mod.objects.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerVendor extends Container {
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return !playerIn.isSpectator();
	}
}
