package com.noto0648.stations.tile;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRailToyCorner extends TileEntityRailToy {
  private static ForgeDirection[][] cornerDirections = new ForgeDirection[][] { { ForgeDirection.EAST, ForgeDirection.SOUTH }, { ForgeDirection.WEST, ForgeDirection.SOUTH }, { ForgeDirection.NORTH, ForgeDirection.WEST }, { ForgeDirection.EAST, ForgeDirection.NORTH } };
  
  public ForgeDirection[] getConnectDirection() {
    return cornerDirections[this.rotate];
  }
}
