package chaos.mod.objects.item;

import chaos.mod.Main;
import chaos.mod.init.ItemInit;
import chaos.mod.objects.item.capabilities.CaseProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemCase extends ItemBase {
	public ItemCase(String name) {
		super(name);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemStack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			playerIn.openGui(Main.instance, 1, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(),
					playerIn.getPosition().getZ());
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		if (stack.getItem() == ItemInit.CASE) {
			return new CaseProvider();
		} else {
			return null;
		}
	}
}
