package com.noto0648.stations.common;

import com.noto0648.stations.client.gui.GuiMarkMachine;
import com.noto0648.stations.client.gui.GuiNamePlate;
import com.noto0648.stations.client.gui.GuiNumberPlate;
import com.noto0648.stations.client.gui.GuiTetris;
import com.noto0648.stations.client.gui.GuiTicketCase;
import com.noto0648.stations.client.gui.GuiTicketMachine;
import com.noto0648.stations.container.ContainerTicketCase;
import com.noto0648.stations.tile.TileEntityMarkMachine;
import com.noto0648.stations.tile.TileEntityNamePlate;
import com.noto0648.stations.tile.TileEntityNumberPlate;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class StationsGuiHandler implements IGuiHandler {
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == 30)
      return new ContainerTicketCase(player.inventory, player.getCurrentEquippedItem(), world); 
    return null;
  }
  
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == 0)
      return new GuiMarkMachine((TileEntityMarkMachine)world.getTileEntity(x, y, z)); 
    if (ID == 1)
      return new GuiNamePlate((TileEntityNamePlate)world.getTileEntity(x, y, z)); 
    if (ID == 2)
      return new GuiTicketMachine(x, y, z); 
    if (ID == 3)
      return new GuiNumberPlate((TileEntityNumberPlate)world.getTileEntity(x, y, z)); 
    if (ID == 30)
      return new GuiTicketCase(player); 
    if (ID == 40)
      return new GuiTetris(); 
    return null;
  }
}
