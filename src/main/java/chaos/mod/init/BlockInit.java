package chaos.mod.init;

import java.util.ArrayList;
import java.util.List;

import chaos.mod.objects.block.BlockFence;
import chaos.mod.objects.block.BlockTactilePaving;
import chaos.mod.objects.block.base.BlockBase;
import chaos.mod.objects.block.base.BlockHasFace;
import chaos.mod.objects.block.display.BlockDepartureMark;
import chaos.mod.objects.block.machines.BlockMarkMachine;
import chaos.mod.objects.block.machines.BlockTrunstile;
import chaos.mod.objects.block.machines.BlockVendor;
import chaos.mod.objects.block.subblocks.BlockPillar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	private static final Material COMMON = Material.ROCK;

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block FENCE = new BlockFence("fence", Material.IRON);
	
	public static final Block TURNSTILE = new BlockTrunstile("turnstile", COMMON);
	
	public static final Block VENDOR = new BlockVendor("vendor", COMMON);
	
	public static final Block WHITE_BRICK = new BlockBase("white_brick", COMMON);
	
	public static final Block ASPHALT = new BlockBase("asphalt", COMMON);
	
	public static final Block CARE_BLOCK = new BlockHasFace("care_block", COMMON, true);
	
	public static final Block WBTP =new BlockHasFace("wbtp", COMMON, true);
	
	public static final Block TACTILE_PAVING = new BlockTactilePaving("tactile_paving");
	
	public static final Block PILLAR = new BlockPillar("pillar", COMMON);
	
	public static final Block MARK_MACHINE = new BlockMarkMachine("mark_machine");
	
	public static final Block DEPARTURE_MARK = new BlockDepartureMark("departure_mark");
}
