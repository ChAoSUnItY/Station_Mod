package com.noto0648.stations.tile;

import com.noto0648.stations.common.Utils;
import com.noto0648.stations.nameplate.NamePlateBase;
import com.noto0648.stations.nameplate.NamePlateManager;
import com.noto0648.stations.packet.PacketSendPlate;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class TileEntityNamePlate extends TileBase {
  public String currentType;
  
  public String texture;
  
  public List<String> stringList;
  
  public List<String> keyList;
  
  public boolean light;
  
  public int pasteFace;
  
  public TileEntityNamePlate() {
    this.currentType = "default";
    this.texture = "DefaultTexture";
    this.stringList = new ArrayList<String>();
    this.keyList = new ArrayList<String>();
    this.stringList.add("まいくら");
    this.stringList.add("たくみとうげ");
    this.stringList.add("えんだ");
    this.stringList.add("Minecraft");
    this.keyList.add("stationName");
    this.keyList.add("nextStation");
    this.keyList.add("prevStation");
    this.keyList.add("englishName");
  }
  
  public void readFromNBT(NBTTagCompound p_145839_1_) {
    super.readFromNBT(p_145839_1_);
    this.currentType = p_145839_1_.getString("currentType");
    this.texture = p_145839_1_.getString("texture");
    this.light = p_145839_1_.getBoolean("light");
    this.pasteFace = p_145839_1_.getInteger("pasteFace");
    NBTTagList tagList = (NBTTagList)p_145839_1_.getTag("stringList");
    this.stringList.clear();
    for (int i = 0; i < tagList.tagCount(); i++)
      this.stringList.add(tagList.getStringTagAt(i)); 
    NBTTagList tagKeys = (NBTTagList)p_145839_1_.getTag("keyList");
    this.keyList.clear();
    for (int j = 0; j < tagKeys.tagCount(); j++)
      this.keyList.add(tagKeys.getStringTagAt(j)); 
  }
  
  public void writeToNBT(NBTTagCompound p_145841_1_) {
    super.writeToNBT(p_145841_1_);
    p_145841_1_.setString("currentType", this.currentType);
    p_145841_1_.setString("texture", this.texture);
    p_145841_1_.setBoolean("light", this.light);
    p_145841_1_.setInteger("pasteFace", this.pasteFace);
    NBTTagList tagList = new NBTTagList();
    for (int i = 0; i < this.stringList.size(); i++)
      tagList.appendTag((NBTBase)new NBTTagString(this.stringList.get(i))); 
    p_145841_1_.setTag("stringList", (NBTBase)tagList);
    NBTTagList tagKeys = new NBTTagList();
    for (int j = 0; j < this.keyList.size(); j++)
      tagKeys.appendTag((NBTBase)new NBTTagString(this.keyList.get(j))); 
    p_145841_1_.setTag("keyList", (NBTBase)tagKeys);
  }
  
  public Map<String, String> getHashMap() {
    NamePlateBase renderPlate = null;
    for (int i = 0; i < NamePlateManager.INSTANCE.getNamePlates().size(); i++) {
      String name = ((NamePlateBase)NamePlateManager.INSTANCE.getNamePlates().get(i)).getName();
      if (name.equalsIgnoreCase(this.currentType))
        renderPlate = NamePlateManager.INSTANCE.getNamePlates().get(i); 
    } 
    if (renderPlate != null) {
      List<String> strs = new ArrayList<String>();
      renderPlate.init(strs);
      if (strs.size() == this.stringList.size() && strs.size() == this.keyList.size()) {
        Map<String, String> result = new HashMap<String, String>(strs.size());
        for (int j = 0; j < strs.size(); j++) {
          String key = this.keyList.get(j);
          String value = this.stringList.get(j);
          result.put(key, value);
        } 
        return result;
      } 
    } 
    return null;
  }
  
  public void reload() {
    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
  }
  
  public void setNamePlateData(String type, String tex, boolean l, List<String> r, List<String> k) {
    this.currentType = type;
    this.texture = tex;
    this.light = l;
    this.stringList = r;
    this.keyList = k;
    Map<String, String> ms = new HashMap<String, String>();
    for (int i = 0; i < this.stringList.size(); i++)
      ms.put(this.keyList.get(i), this.stringList.get(i)); 
    Utils.INSTANCE.sendToPlayers((IMessage)new PacketSendPlate(this.xCoord, this.yCoord, this.zCoord, this.currentType, ms, this.texture, this.light), this);
  }
}
