package com.noto0648.stations.client.gui;

import com.noto0648.stations.Stations;
import com.noto0648.stations.client.gui.control.ControlButton;
import com.noto0648.stations.client.gui.control.ControlListBox;
import com.noto0648.stations.client.gui.control.ControlTextBox;
import com.noto0648.stations.common.MarkData;
import com.noto0648.stations.common.MarkDataComparator;
import com.noto0648.stations.common.MinecraftDate;
import com.noto0648.stations.packet.PacketSendMarkData;
import com.noto0648.stations.tile.TileEntityMarkMachine;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.world.World;

public class GuiMarkMachine extends GuiScreenBase implements IGui {
  public List<MarkData> markDataList = new ArrayList<MarkData>();
  
  private World mainWorld;
  
  private TileEntityMarkMachine tile;
  
  private ControlTextBox timeText;
  
  private ControlTextBox typeText;
  
  private ControlTextBox whereText;
  
  private ControlListBox listBox;
  
  private ControlButton removeButton;
  
  private ControlButton addButton;
  
  public GuiMarkMachine(TileEntityMarkMachine tileEntityMarkMachine) {
    this.listBox = new ControlListBox(this, 10, 10, this.width / 2 - 20, this.height - 20);
    this.tile = tileEntityMarkMachine;
    this.mainWorld = tileEntityMarkMachine.getWorldObj();
    this.markDataList = tileEntityMarkMachine.getMarkDataList();
    for (int i = 0; i < this.markDataList.size(); i++)
      this.listBox.items.add(((MarkData)this.markDataList.get(i)).toString()); 
    this.timeText = new ControlTextBox(this, this.width / 2 + 70, 60, 140, 20);
    this.timeText.setInputLimit("0123456789:");
    this.typeText = new ControlTextBox(this, this.width / 2 + 70, 90, 140, 20);
    this.whereText = new ControlTextBox(this, this.width / 2 + 70, 120, 140, 20);
    this.removeButton = new ControlButton(this, this.width / 2 + 10, 10, 100, 20, "Remove") {
        public void onButtonClick(int button) {
          playClickSound();
          if (GuiMarkMachine.this.listBox.selectedIndex > -1 && GuiMarkMachine.this.listBox.selectedIndex < GuiMarkMachine.this.markDataList.size()) {
            GuiMarkMachine.this.markDataList.remove(GuiMarkMachine.this.listBox.selectedIndex);
            GuiMarkMachine.this.listBox.items.remove(GuiMarkMachine.this.listBox.selectedIndex);
            GuiMarkMachine.this.listReload();
          } 
        }
      };
    this.addButton = new ControlButton(this, this.width / 2 + 110, 10, 100, 20, "Add") {
        public void onButtonClick(int button) {
          playClickSound();
          String time_text = GuiMarkMachine.this.timeText.getText();
          if (time_text.contains(":") && (time_text.split(":")).length == 2 && GuiMarkMachine.this.whereText.getText() != null && GuiMarkMachine.this.whereText.getText().length() != 0 && GuiMarkMachine.this.typeText.getText() != null && GuiMarkMachine.this.typeText.getText().length() != 0) {
            String[] splits = time_text.split(":");
            int hour = Integer.valueOf(splits[0]).intValue();
            int mins = Integer.valueOf(splits[1]).intValue();
            MarkData md = new MarkData(hour, mins, GuiMarkMachine.this.whereText.getText(), GuiMarkMachine.this.typeText.getText());
            GuiMarkMachine.this.markDataList.add(md);
            GuiMarkMachine.this.listReload();
            GuiMarkMachine.this.timeText.setText("");
            GuiMarkMachine.this.typeText.setText("");
            GuiMarkMachine.this.whereText.setText("");
          } 
        }
      };
    this.controlList.add(this.listBox);
    this.controlList.add(this.timeText);
    this.controlList.add(this.typeText);
    this.controlList.add(this.whereText);
    this.controlList.add(this.removeButton);
    this.controlList.add(this.addButton);
  }
  
  public void onGuiClosed() {
    super.onGuiClosed();
    Stations.packetDispatcher.sendToServer((IMessage)new PacketSendMarkData(this.markDataList, this.tile.xCoord, this.tile.yCoord, this.tile.zCoord));
  }
  
  protected void resize() {
    this.timeText.setLocation(this.width / 2 + 70, 60);
    this.typeText.setLocation(this.width / 2 + 70, 90);
    this.whereText.setLocation(this.width / 2 + 70, 120);
    this.removeButton.setLocation(this.width / 2 + 10, 10);
    this.addButton.setLocation(this.width / 2 + 110, 10);
    this.listBox.setSize(this.width / 2 - 20, this.height - 20);
  }
  
  protected void paint(int mouseX, int mouseY) {
    this.fontRendererObj.drawString("Time", this.width / 2 + 10, 60, 16777215);
    this.fontRendererObj.drawString("TrainType", this.width / 2 + 10, 90, 16777215);
    this.fontRendererObj.drawString("Dest", this.width / 2 + 10, 120, 16777215);
    this.fontRendererObj.drawString((new MinecraftDate(this.mainWorld.getWorldTime())).toString(), this.width - this.fontRendererObj.getStringWidth((new MinecraftDate(this.mainWorld.getWorldTime())).toString()), this.height - 12, 16777215);
  }
  
  private void listReload() {
    this.listBox.items.clear();
    Collections.sort(this.markDataList, (Comparator<? super MarkData>)new MarkDataComparator());
    for (int i = 0; i < this.markDataList.size(); i++)
      this.listBox.items.add(((MarkData)this.markDataList.get(i)).toString()); 
  }
}
