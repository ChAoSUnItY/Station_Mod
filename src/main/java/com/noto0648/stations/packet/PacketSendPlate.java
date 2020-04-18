package com.noto0648.stations.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;

public class PacketSendPlate implements IMessage {
  public String currentType;
  
  public String texture;
  
  public Map<String, String> strMap;
  
  public int x;
  
  public int y;
  
  public int z;
  
  public boolean light;
  
  public PacketSendPlate() {}
  
  public PacketSendPlate(int _x, int _y, int _z, String type, Map<String, String> strings, String tex, boolean r) {
    this.currentType = type;
    this.strMap = strings;
    this.x = _x;
    this.y = _y;
    this.z = _z;
    this.texture = tex;
    this.light = r;
  }
  
  public void fromBytes(ByteBuf buf) {
    try {
      this.x = buf.readInt();
      this.y = buf.readInt();
      this.z = buf.readInt();
      this.light = buf.readBoolean();
      this.currentType = readString(buf);
      this.texture = readString(buf);
      int size = buf.readInt();
      this.strMap = new HashMap<String, String>(size);
      for (int i = 0; i < size; i++) {
        String key = readString(buf);
        String value = readString(buf);
        this.strMap.put(key, value);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    try {
      buf.writeInt(this.x);
      buf.writeInt(this.y);
      buf.writeInt(this.z);
      buf.writeBoolean(this.light);
      writeString(buf, this.currentType);
      writeString(buf, this.texture);
      String[] keys = (String[])this.strMap.keySet().toArray((Object[])new String[0]);
      buf.writeInt(keys.length);
      for (int i = 0; i < keys.length; i++) {
        writeString(buf, keys[i]);
        writeString(buf, this.strMap.get(keys[i]));
      } 
    } catch (Exception e) {
      e.printStackTrace();
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
  
  public String readString(ByteBuf buf) {
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
}
