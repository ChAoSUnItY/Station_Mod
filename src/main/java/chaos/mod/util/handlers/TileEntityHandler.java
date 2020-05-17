package chaos.mod.util.handlers;

import chaos.mod.tileentity.TileEntityDepartureMark;
import chaos.mod.tileentity.TileEntityMarkMachine;
import chaos.mod.tileentity.render.RenderDepartureMark;
import chaos.mod.util.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler extends TileEntity{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityMarkMachine.class, new ResourceLocation(Reference.MODID, "mark_machine"));
		GameRegistry.registerTileEntity(TileEntityDepartureMark.class, new ResourceLocation(Reference.MODID, "departure_mark"));
	}
	
	public static void registerTESRs() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDepartureMark.class, new RenderDepartureMark());
	}
}
