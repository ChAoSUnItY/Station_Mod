package chaos.mod.objects.item;

import chaos.mod.Main;
import chaos.mod.init.ItemInit;
import chaos.mod.util.interfaces.IHasModel;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{
	public ItemBase(String name) {
		setUnlocalizedName("noto." + name);
		setRegistryName(name);
		setCreativeTab(Main.tab);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
