package chaos.mod.objects.item.capabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CaseProvider implements ICapabilityProvider, ICapabilitySerializable<NBTBase> {
	private final ItemStackHandler inventory;
	
	public CaseProvider() {
		inventory = new ItemStackHandler(11);
	}
	
	@Override
	public boolean hasCapability( Capability<?> capability, EnumFacing facing ) {
		if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ) {
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability( Capability<T> capability, EnumFacing facing ) {
		if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ) {
			return (T) inventory; 
		}
		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < 11; i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				stack.writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		return list;
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		
		NBTTagList list = (NBTTagList) nbt;
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) list.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.getSlots())
		    {
		    	inventory.setStackInSlot(slot, new ItemStack(tag));
		    }
		}
	}

}
