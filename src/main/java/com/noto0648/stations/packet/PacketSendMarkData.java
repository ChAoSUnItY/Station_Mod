package com.noto0648.stations.packet;

import com.noto0648.stations.common.MarkData;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;

public class PacketSendMarkData implements IMessage {
  private List<MarkData> markList;
  
  public int posX;
  
  public int posY;
  
  public int posZ;
  
  public PacketSendMarkData() {}
  
  public PacketSendMarkData(List<MarkData> list, int x, int y, int z) {
    this.markList = list;
    this.posX = x;
    this.posY = y;
    this.posZ = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    try {
      this.posX = buf.readInt();
      this.posY = buf.readInt();
      this.posZ = buf.readInt();
      int size = buf.readInt();
      this.markList = new ArrayList<MarkData>(size);
      this.markList.clear();
      for (int i = 0; i < size; i++) {
        int hours = buf.readInt();
        int minutes = buf.readInt();
        int typeLength = buf.readInt();
        byte[] typeChars = new byte[typeLength];
        buf.readBytes(typeChars);
        int destLength = buf.readInt();
        byte[] destChars = new byte[destLength];
        buf.readBytes(destChars);
        MarkData md = new MarkData();
        md.hours = hours;
        md.minutes = minutes;
        md.dest = new String(destChars, "UTF-8");
        md.type = new String(typeChars, "UTF-8");
        md.destColor = buf.readInt();
        md.timeColor = buf.readInt();
        md.typeColor = buf.readInt();
        this.markList.add(md);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    try {
      buf.writeInt(this.posX);
      buf.writeInt(this.posY);
      buf.writeInt(this.posZ);
      buf.writeInt(this.markList.size());
      for (int i = 0; i < this.markList.size(); i++) {
        MarkData mkd = this.markList.get(i);
        buf.writeInt(mkd.hours);
        buf.writeInt(mkd.minutes);
        byte[] typeBytes = mkd.type.getBytes("UTF-8");
        buf.writeInt(typeBytes.length);
        buf.writeBytes(typeBytes);
        byte[] destBytes = mkd.dest.getBytes("UTF-8");
        buf.writeInt(destBytes.length);
        buf.writeBytes(destBytes);
        buf.writeInt(mkd.destColor);
        buf.writeInt(mkd.timeColor);
        buf.writeInt(mkd.typeColor);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public List<MarkData> getLists() {
    return this.markList;
  }
}
