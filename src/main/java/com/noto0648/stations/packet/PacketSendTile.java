package com.noto0648.stations.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;

public class PacketSendTile implements IMessage {
  private List<Object> data;
  
  public int posX;
  
  public int posY;
  
  public int posZ;
  
  public PacketSendTile() {}
  
  public PacketSendTile(List<Object> par1, int x, int y, int z) {
    this.data = par1;
    this.posX = x;
    this.posY = y;
    this.posZ = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.posX = buf.readInt();
    this.posY = buf.readInt();
    this.posZ = buf.readInt();
    int size = buf.readInt();
    this.data = new ArrayList(size);
    for (int i = 0; i < size; i++) {
      byte tag = buf.readByte();
      if (tag == 0) {
        this.data.add(Character.valueOf(buf.readChar()));
      } else if (tag == 1) {
        this.data.add(readString(buf));
      } else if (tag == 2) {
        this.data.add(Float.valueOf(buf.readFloat()));
      } else if (tag == 3) {
        this.data.add(Double.valueOf(buf.readDouble()));
      } else if (tag == 4) {
        this.data.add(Byte.valueOf(buf.readByte()));
      } else if (tag == 5) {
        this.data.add(Long.valueOf(buf.readLong()));
      } else {
        this.data.add(Integer.valueOf(buf.readInt()));
      } 
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.posX);
    buf.writeInt(this.posY);
    buf.writeInt(this.posZ);
    buf.writeInt(this.data.size());
    for (int i = 0; i < this.data.size(); i++) {
      Object obj = this.data.get(i);
      if (obj instanceof Character) {
        buf.writeByte(0);
        buf.writeChar(((Character)obj).charValue());
      } else if (obj instanceof String) {
        buf.writeByte(1);
        writeString(buf, (String)obj);
      } else if (obj instanceof Float) {
        buf.writeByte(2);
        buf.writeFloat(((Float)obj).floatValue());
      } else if (obj instanceof Double) {
        buf.writeByte(3);
        buf.writeDouble(((Double)obj).doubleValue());
      } else if (obj instanceof Byte) {
        buf.writeByte(4);
        buf.writeByte(((Byte)obj).byteValue());
      } else if (obj instanceof Long) {
        buf.writeByte(5);
        buf.writeLong(((Long)obj).longValue());
      } else {
        buf.writeByte(15);
        buf.writeInt(((Integer)obj).intValue());
      } 
    } 
  }
  
  public void writeString(ByteBuf buf, String str) {
    try {
      byte[] typeBytes = str.getBytes("UTF-8");
      buf.writeInt(typeBytes.length);
      buf.writeBytes(typeBytes);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private String readString(ByteBuf buf) {
    String str = null;
    try {
      int destLength = buf.readInt();
      byte[] destChars = new byte[destLength];
      buf.readBytes(destChars);
      str = new String(destChars, "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return str;
  }
  
  public List<Object> getDataList() {
    return this.data;
  }
}
