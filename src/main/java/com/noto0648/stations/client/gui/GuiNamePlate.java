package com.noto0648.stations.client.gui;

import com.noto0648.stations.Stations;
import com.noto0648.stations.client.gui.control.ControlButton;
import com.noto0648.stations.client.gui.control.ControlCheckBox;
import com.noto0648.stations.client.gui.control.ControlComboBox;
import com.noto0648.stations.client.gui.control.ControlListBox;
import com.noto0648.stations.client.gui.control.ControlTextBox;
import com.noto0648.stations.nameplate.NamePlateBase;
import com.noto0648.stations.nameplate.NamePlateManager;
import com.noto0648.stations.packet.PacketSendPlate;
import com.noto0648.stations.tile.TileEntityNamePlate;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiScreen;

public class GuiNamePlate extends GuiScreenBase implements IGui {
  private ControlListBox plateList;
  
  private ControlListBox textList;
  
  private ControlTextBox field;
  
  private ControlComboBox textureComboBox;
  
  private ControlButton doneButton;
  
  private ControlCheckBox lightCheck;
  
  private TileEntityNamePlate tile;
  
  private Map<String, String> strMaps = new HashMap<String, String>();
  
  private List<String> textures = new ArrayList<String>();
  
  public GuiNamePlate(TileEntityNamePlate tileEntityNamePlate) {
    this.tile = tileEntityNamePlate;
    this.field = new ControlTextBox(this, 220, 100, 200, 20) {
        public void textChanged() {
          List<String> strs = new ArrayList<String>();
          if (GuiNamePlate.this.plateList.selectedIndex != -1 && GuiNamePlate.this.textList.selectedIndex != -1) {
            ((NamePlateBase)NamePlateManager.INSTANCE.getNamePlates().get(GuiNamePlate.this.plateList.selectedIndex)).init(strs);
            GuiNamePlate.this.strMaps.put(strs.get(GuiNamePlate.this.textList.selectedIndex), GuiNamePlate.this.field.getText());
          } 
        }
      };
    this.field.setEnabled(false);
    this.controlList.add(this.field);
    this.plateList = new ControlListBox(this, 10, 10, this.width / 2 - 20, this.height / 2 - 15) {
        public void selectChanged() {
          GuiNamePlate.this.textList.items.clear();
          GuiNamePlate.this.textList.selectedIndex = -1;
          GuiNamePlate.this.strMaps.clear();
          if (this.selectedIndex != -1) {
            List<String> strs = new ArrayList<String>();
            ((NamePlateBase)NamePlateManager.INSTANCE.getNamePlates().get(this.selectedIndex)).init(strs);
            for (int i = 0; i < strs.size(); i++) {
              GuiNamePlate.this.textList.items.add(strs.get(i));
              if (!GuiNamePlate.this.strMaps.containsKey(strs.get(i)))
                GuiNamePlate.this.strMaps.put(strs.get(i), ""); 
            } 
          } 
        }
      };
    this.controlList.add(this.plateList);
    this.textList = new ControlListBox(this, 10, this.height / 2 + 10, this.width / 2 - 20, this.height / 2 - 15) {
        public void selectChanged() {
          if (this.selectedIndex != -1) {
            GuiNamePlate.this.field.setEnabled(true);
            List<String> strs = new ArrayList<String>();
            ((NamePlateBase)NamePlateManager.INSTANCE.getNamePlates().get(GuiNamePlate.this.plateList.selectedIndex)).init(strs);
            GuiNamePlate.this.field.setText((String)GuiNamePlate.this.strMaps.get(strs.get(this.selectedIndex)));
          } else {
            GuiNamePlate.this.field.setEnabled(false);
          } 
        }
      };
    this.controlList.add(this.textList);
    List<NamePlateBase> plates = NamePlateManager.INSTANCE.getNamePlates();
    int i;
    for (i = 0; i < plates.size(); i++) {
      String plateName = ((NamePlateBase)plates.get(i)).getName();
      this.plateList.items.add(plateName);
      if (plateName.equalsIgnoreCase(this.tile.currentType)) {
        List<String> strs = new ArrayList<String>();
        ((NamePlateBase)plates.get(i)).init(strs);
        this.plateList.setSelectedIndex(i);
        for (int j = 0; j < this.tile.stringList.size(); j++)
          this.strMaps.put(this.tile.keyList.get(j), this.tile.stringList.get(j)); 
      } 
    } 
    this.textures.add("DefaultTexture");
    for (i = 0; i < NamePlateManager.platesImages.size(); i++)
      this.textures.add(NamePlateManager.platesImages.get(i)); 
    this.textureComboBox = new ControlComboBox(this, this.width / 2 + 10, 10, 200, 20) {
      
      };
    for (i = 0; i < this.textures.size(); i++)
      this.textureComboBox.items.add((new File(this.textures.get(i))).getName()); 
    this.textureComboBox.setSelectedIndex(0);
    for (i = 0; i < this.textures.size(); i++) {
      if (((String)this.textures.get(i)).equalsIgnoreCase(this.tile.texture))
        this.textureComboBox.setSelectedIndex(i); 
    } 
    this.doneButton = new ControlButton(this, this.width / 2 + 10, this.height / 2 + 60, 200, 20, "Done") {
        public void onButtonClick(int button) {
          playClickSound();
          Stations.packetDispatcher.sendToServer((IMessage)new PacketSendPlate(GuiNamePlate.this.tile.xCoord, GuiNamePlate.this.tile.yCoord, GuiNamePlate.this.tile.zCoord, GuiNamePlate.this.plateList.getText(), GuiNamePlate.this.strMaps, GuiNamePlate.this.textures.get(GuiNamePlate.this.textureComboBox.selectedIndex), GuiNamePlate.this.lightCheck.getCheck()));
          GuiNamePlate.this.mc.displayGuiScreen((GuiScreen)null);
        }
      };
    this.controlList.add(this.doneButton);
    this.lightCheck = new ControlCheckBox(this, this.width / 2 + 10, this.height / 2 + 10, "Shining");
    this.lightCheck.setCheck(this.tile.light);
    this.controlList.add(this.lightCheck);
    this.controlList.add(this.textureComboBox);
  }
  
  protected void resize() {
    this.field.setLocation(this.width / 2 + 10, this.height / 2 + 10);
    this.textureComboBox.setLocation(this.width / 2 + 10, 10);
    this.doneButton.setLocation(this.width / 2 + 10, this.height / 2 + 60);
    this.plateList.setSize(this.width / 2 - 20, this.height / 2 - 15);
    this.textList.locationY = this.height / 2 + 10;
    this.textList.setSize(this.width / 2 - 20, this.height / 2 - 15);
    this.lightCheck.setLocation(this.width / 2 + 10, this.height / 2 + 40);
  }
  
  protected void paint(int mouseX, int mouseY) {}
}
