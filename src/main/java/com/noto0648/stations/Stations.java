package com.noto0648.stations;

import com.noto0648.stations.blocks.BlockFence;
import com.noto0648.stations.blocks.BlockNamePlate;
import com.noto0648.stations.blocks.BlockPillar;
import com.noto0648.stations.blocks.BlockRailToy;
import com.noto0648.stations.blocks.BlockShutter;
import com.noto0648.stations.blocks.BlockStation;
import com.noto0648.stations.blocks.BlockTicketMachine;
import com.noto0648.stations.common.CreativeTabsStations;
import com.noto0648.stations.common.ServerProxy;
import com.noto0648.stations.common.StationsGuiHandler;
import com.noto0648.stations.items.ItemBlockBase;
import com.noto0648.stations.items.ItemClock;
import com.noto0648.stations.items.ItemLanSetter;
import com.noto0648.stations.items.ItemStaffArmor;
import com.noto0648.stations.items.ItemTicket;
import com.noto0648.stations.items.ItemTicketCase;
import com.noto0648.stations.items.ItemTorqueWrench;
import com.noto0648.stations.nameplate.NamePlateManager;
import com.noto0648.stations.packet.PacketCaptionMarkData;
import com.noto0648.stations.packet.PacketCaptionPlate;
import com.noto0648.stations.packet.PacketCaptionTicket;
import com.noto0648.stations.packet.PacketCaptionTile;
import com.noto0648.stations.packet.PacketSendMarkData;
import com.noto0648.stations.packet.PacketSendPlate;
import com.noto0648.stations.packet.PacketSendTicket;
import com.noto0648.stations.packet.PacketSendTile;
import com.noto0648.stations.tile.TileEntityMarkMachine;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = "stationsMod", name = "Stations Mod", version = "1.7.10.3")
public class Stations {
  @Instance("stationsMod")
  public static Stations instance;
  
  @SidedProxy(clientSide = "com.noto0648.stations.client.ClientProxy", serverSide = "com.noto0648.stations.common.ServerProxy")
  public static ServerProxy proxy;
  
  public static final SimpleNetworkWrapper packetDispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("stationsMod");
  
  public static CreativeTabsStations tab = new CreativeTabsStations();
  
  public Block stationMaterial;
  
  public Block stationFence;
  
  public Block pillarBlock;
  
  public Block namePlate;
  
  public Block ticketMachine;
  
  public Block shutter;
  
  public Block railToy;
  
  public Item ticket;
  
  public Item lanSetter;
  
  public Item torqueWrench;
  
  public Item clock;
  
  public Item[] staff_armor = new Item[4];
  
  public Item ticketCase;
  
  public int fenceRendererId;
  
  public int railToyRenderId;
  
  public int pillarRenderId;
  
  public int namePlateRenderId;
  
  public int tickerMachineRenderId;
  
  public boolean isLoadedEconomy;
  
  public static ItemArmor.ArmorMaterial staffArmorMaterial = EnumHelper.addArmorMaterial("station_staff", 18, new int[] { 1, 1, 1, 1 }, 0);
  
  public int armorRenderId = -1;
  
  @EventHandler
  public void init(FMLInitializationEvent event) {
    proxy.register();
    GameRegistry.registerTileEntity(TileEntityMarkMachine.class, "NotoMod.MarkMachine");
    NetworkRegistry.INSTANCE.registerGuiHandler(this, (IGuiHandler)new StationsGuiHandler());
    packetDispatcher.registerMessage(PacketCaptionMarkData.class, PacketSendMarkData.class, 0, Side.SERVER);
    packetDispatcher.registerMessage(PacketCaptionPlate.class, PacketSendPlate.class, 1, Side.SERVER);
    packetDispatcher.registerMessage(PacketCaptionTicket.class, PacketSendTicket.class, 2, Side.SERVER);
    packetDispatcher.registerMessage(PacketCaptionTile.class, PacketSendTile.class, 32, Side.SERVER);
    NamePlateManager.INSTANCE.init();
    this.isLoadedEconomy = Loader.isModLoaded("mceconomy2");
    this.isLoadedEconomy = true;
  }
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    proxy.preInit();
    this.stationMaterial = (Block)new BlockStation();
    GameRegistry.registerBlock(this.stationMaterial, ItemBlockBase.class, "NotoMod.stationMaterial");
    this.stationFence = (Block)new BlockFence();
    GameRegistry.registerBlock(this.stationFence, ItemBlockBase.class, "NotoMod.stationFence");
    this.pillarBlock = (Block)new BlockPillar();
    GameRegistry.registerBlock(this.pillarBlock, ItemBlockBase.class, "NotoMod.blockPillar");
    this.namePlate = (Block)new BlockNamePlate();
    GameRegistry.registerBlock(this.namePlate, ItemBlockBase.class, "NotoMod.namePlate");
    this.ticketMachine = (Block)new BlockTicketMachine();
    GameRegistry.registerBlock(this.ticketMachine, ItemBlockBase.class, "NotoMod.ticketMachine");
    this.railToy = (Block)new BlockRailToy();
    GameRegistry.registerBlock(this.railToy, ItemBlockBase.class, "NotoMod.railToy");
    this.shutter = (Block)new BlockShutter();
    GameRegistry.registerBlock(this.shutter, "NotoMod.shutter");
    this.ticket = (Item)new ItemTicket();
    GameRegistry.registerItem(this.ticket, "NotoMod.ticket");
    this.lanSetter = (Item)new ItemLanSetter();
    GameRegistry.registerItem(this.lanSetter, "NotoMod.lanSetter");
    this.torqueWrench = (Item)new ItemTorqueWrench();
    GameRegistry.registerItem(this.torqueWrench, "NotoMod.torqueWrench");
    this.clock = (Item)new ItemClock();
    GameRegistry.registerItem(this.clock, "NotoMod.clock");
    this.ticketCase = (Item)new ItemTicketCase();
    GameRegistry.registerItem(this.ticketCase, "NotoMod.ticketCase");
    this.staff_armor[0] = (new ItemStaffArmor(staffArmorMaterial, this.armorRenderId, 0)).setUnlocalizedName("NotoMod.armorStaffHelmet").setTextureName("notomod:staff_helmet");
    this.staff_armor[1] = (new ItemStaffArmor(staffArmorMaterial, this.armorRenderId, 1)).setUnlocalizedName("NotoMod.armorStaffChestPlate").setTextureName("notomod:staff_chest_plate");
    this.staff_armor[2] = (new ItemStaffArmor(staffArmorMaterial, this.armorRenderId, 2)).setUnlocalizedName("NotoMod.armorStaffLeg").setTextureName("notomod:staff_leggings");
    this.staff_armor[3] = (new ItemStaffArmor(staffArmorMaterial, this.armorRenderId, 3)).setUnlocalizedName("NotoMod.armorStaffBoots").setTextureName("notomod:staff_boots");
    GameRegistry.registerItem(this.staff_armor[0], "NotoMod.armorStaffHelmet");
    GameRegistry.registerItem(this.staff_armor[1], "NotoMod.armorStaffChestPlate");
    GameRegistry.registerItem(this.staff_armor[2], "NotoMod.armorStaffLeg");
    GameRegistry.registerItem(this.staff_armor[3], "NotoMod.armorStaffBoots");
  }
}
