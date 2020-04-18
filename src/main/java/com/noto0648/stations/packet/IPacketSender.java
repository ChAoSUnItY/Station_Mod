package com.noto0648.stations.packet;

import java.util.List;
import net.minecraft.tileentity.TileEntity;

public interface IPacketSender {
  TileEntity getTile();
  
  void setSendData(List<Object> paramList);
}
