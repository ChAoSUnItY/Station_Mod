package chaos.mod.objects.item;

import chaos.mod.Main;
import chaos.mod.init.ItemInit;

public class ItemTicket extends ItemBase {
	public final int type;
	
	public ItemTicket(String name, int type) {
		super(name);
		setMaxStackSize(1);
		setUnlocalizedName("noto." + name);
		setCreativeTab(Main.tab);
		this.type = type;
		
		ItemInit.ITEMS.add(this);
	}
	
	public int getType() {
		return this.type;
	}
}
