package com.noto0648.stations.client;

import com.noto0648.stations.Stations;
import com.noto0648.stations.client.render.RenderClockData;
import com.noto0648.stations.client.render.TileEntityFenceRender;
import com.noto0648.stations.client.render.TileEntityMarkRender;
import com.noto0648.stations.client.render.TileEntityNamePlateRender;
import com.noto0648.stations.client.render.TileEntityNumberPlateRender;
import com.noto0648.stations.client.render.TileEntityRailToyCornerRender;
import com.noto0648.stations.client.render.TileEntityRailToyRender;
import com.noto0648.stations.client.render.TileEntityShutterRender;
import com.noto0648.stations.client.render.TileEntityTicketGateRender;
import com.noto0648.stations.client.render.TileEntityTicketMachineRender;
import com.noto0648.stations.client.texture.NewFontRenderer;
import com.noto0648.stations.client.texture.TextureImporter;
import com.noto0648.stations.common.ServerProxy;
import com.noto0648.stations.tile.TileEntityFence;
import com.noto0648.stations.tile.TileEntityMark;
import com.noto0648.stations.tile.TileEntityNamePlate;
import com.noto0648.stations.tile.TileEntityNumberPlate;
import com.noto0648.stations.tile.TileEntityRailToy;
import com.noto0648.stations.tile.TileEntityRailToyCorner;
import com.noto0648.stations.tile.TileEntityShutter;
import com.noto0648.stations.tile.TileEntityTicketGate;
import com.noto0648.stations.tile.TileEntityTicketMachine;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import java.io.InputStream;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends ServerProxy {
  public void register() {
    NewFontRenderer.INSTANCE.init();
    Stations.instance.fenceRendererId = RenderingRegistry.getNextAvailableRenderId();
    Stations.instance.railToyRenderId = RenderingRegistry.getNextAvailableRenderId();
    Stations.instance.pillarRenderId = RenderingRegistry.getNextAvailableRenderId();
    Stations.instance.namePlateRenderId = RenderingRegistry.getNextAvailableRenderId();
    Stations.instance.tickerMachineRenderId = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)Stations.instance.stationFence);
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)Stations.instance.pillarBlock);
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)Stations.instance.namePlate);
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)Stations.instance.ticketMachine);
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)Stations.instance.ticketMachine);
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)Stations.instance.railToy);
    ClientRegistry.registerTileEntity(TileEntityFence.class, "NotoMod.fence", (TileEntitySpecialRenderer)new TileEntityFenceRender());
    ClientRegistry.registerTileEntity(TileEntityMark.class, "NotoMod.mark", (TileEntitySpecialRenderer)new TileEntityMarkRender());
    ClientRegistry.registerTileEntity(TileEntityNamePlate.class, "NotoMod.namePlate", (TileEntitySpecialRenderer)new TileEntityNamePlateRender());
    ClientRegistry.registerTileEntity(TileEntityTicketMachine.class, "NotoMod.ticketMachine", (TileEntitySpecialRenderer)new TileEntityTicketMachineRender());
    ClientRegistry.registerTileEntity(TileEntityShutter.class, "NotoMod.shutter", (TileEntitySpecialRenderer)new TileEntityShutterRender());
    ClientRegistry.registerTileEntity(TileEntityNumberPlate.class, "NotoMod.numberPlates", (TileEntitySpecialRenderer)new TileEntityNumberPlateRender());
    ClientRegistry.registerTileEntity(TileEntityTicketGate.class, "NotoMod.ticketGate", (TileEntitySpecialRenderer)new TileEntityTicketGateRender());
    ClientRegistry.registerTileEntity(TileEntityRailToy.class, "NotoMod.railToy", (TileEntitySpecialRenderer)new TileEntityRailToyRender());
    ClientRegistry.registerTileEntity(TileEntityRailToyCorner.class, "NotoMod.railToyCorner", (TileEntitySpecialRenderer)new TileEntityRailToyCornerRender());
  }
  
  public void preInit() {
    MinecraftForge.EVENT_BUS.register(new RenderClockData());
    Stations.instance.armorRenderId = RenderingRegistry.addNewArmourRendererPrefix("station_staff");
  }
  
  public boolean readTexture(String key, InputStream stream) {
    return TextureImporter.INSTANCE.readTexture(key, stream);
  }
  
  public boolean readTexture(String path) {
    return TextureImporter.INSTANCE.readTexture(path);
  }
}
