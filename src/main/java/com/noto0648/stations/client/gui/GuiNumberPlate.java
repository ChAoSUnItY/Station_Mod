package com.noto0648.stations.client.gui;

import com.noto0648.stations.client.gui.control.ControlButton;
import com.noto0648.stations.client.gui.control.ControlTextBox;
import com.noto0648.stations.common.Utils;
import com.noto0648.stations.packet.IPacketSender;
import com.noto0648.stations.tile.TileEntityNumberPlate;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class GuiNumberPlate extends GuiScreenBase implements IPacketSender {
  private TileEntityNumberPlate tile;
  
  private ControlTextBox textBox;
  
  private ControlButton button;
  
  private IPacketSender instance;
  
  public GuiNumberPlate(TileEntityNumberPlate tileEntityNumberPlate) {
    this.tile = tileEntityNumberPlate;
    this.instance = this;
    this.textBox = new ControlTextBox(this, (this.width - 200) / 2, (this.height - 20) / 2, 200, 20);
    this.textBox.setText(this.tile.getDrawStr());
    this.button = new ControlButton(this, (this.width - 200) / 2, (this.height - 20) / 2 + 40, 200, 20, "Done") {
        public void onButtonClick(int button) {
          if (button == 0) {
            playClickSound();
            Utils.INSTANCE.sendPacket(GuiNumberPlate.this.instance);
            Minecraft.getMinecraft().displayGuiScreen(null);
          } 
        }
      };
    this.controlList.add(this.textBox);
    this.controlList.add(this.button);
  }
  
  protected void paint(int mouseX, int mouseY) {}
  
  protected void resize() {
    this.textBox.setLocation((this.width - 200) / 2, (this.height - 20) / 2);
    this.button.setLocation((this.width - 200) / 2, (this.height - 20) / 2 + 40);
  }
  
  public TileEntity getTile() {
    return (TileEntity)this.tile;
  }
  
  public void setSendData(List<Object> list) {
    list.add(Byte.valueOf((byte)3));
    list.add(this.textBox.getText());
  }
}
