package chaos.mod.objects.item.container;

import chaos.mod.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCase extends Container {
	public ContainerCase(InventoryPlayer invPlayer, EntityPlayer player) {
		for (int x = 0; x < 11; x++) {
			this.addSlotToContainer(new SlotItemHandler(
					player.getHeldItemMainhand().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), x,
					8 + x * 18, 20) {
				@Override
				public boolean isItemValid(ItemStack stack) {
					return stack.getItem() == ItemInit.USED_TICKET || stack.getItem() == ItemInit.UNUSED_TICKET
							|| stack.getItem() == ItemInit.IC_CARD;
				}
			});
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 26 + x * 18, 51 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(invPlayer, x, 26 + x * 18, 109));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack stackInSlot = slot.getStack();
			stack = stackInSlot.copy();

			int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();

			if (index < containerSlots) {
				if (!this.mergeItemStack(stackInSlot, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stackInSlot, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}

			if (stackInSlot.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			slot.onTake(playerIn, stackInSlot);

		}
		return stack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return !playerIn.isSpectator();
	}

}
