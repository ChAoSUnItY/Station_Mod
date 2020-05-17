package chaos.mod.objects.block.base;

import chaos.mod.Main;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {
	public BlockBase(String name, Material material) {
		super(material);
		constructor(name);
	}
	
	public BlockBase(String name) {
		super(Material.ROCK);
		constructor(name);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	private void constructor(String name) {
		setUnlocalizedName("noto." + name);
		setRegistryName(name);
		setCreativeTab(Main.tab);
		setHardness(3);
		setResistance(20);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
