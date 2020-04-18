package com.noto0648.stations.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class PacketSendTicket implements IMessage {
  private int ID;
  
  public int x;
  
  public int y;
  
  public int z;
  
  public PacketSendTicket() {}
  
  public PacketSendTicket(int id, int x, int y, int z) {
    this.ID = id;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.ID = buf.readInt();
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.ID);
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public int getIDValue() {
    return this.ID;
  }
}
