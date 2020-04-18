package com.noto0648.stations.common;

import com.noto0648.stations.Stations;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabsStations extends CreativeTabs {
  public CreativeTabsStations() {
    super("stationsTab");
  }
  
  public Item getTabIconItem() {
    return Stations.instance.ticket;
  }
}
