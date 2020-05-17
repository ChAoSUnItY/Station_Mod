package chaos.mod.objects.block.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import chaos.mod.objects.block.gui.control.ControlListBox;
import chaos.mod.tileentity.TileEntityMarkMachine;
import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.network.PacketMarkData;
import chaos.mod.util.utils.UtilMarkData;
import chaos.mod.util.utils.UtilMarkDataComparator;
import chaos.mod.util.utils.UtilMinecraftDate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiMarkMachine extends GuiScreen {
	public List<UtilMarkData> markDataList = new ArrayList<UtilMarkData>();

	private World mainWorld;

	private TileEntityMarkMachine tile;

	private GuiTextField timeText;

	private GuiTextField typeText;

	private GuiTextField whereText;

	private GuiButton removeButton;

	private GuiButton addButton;

	private ControlListBox listBox;

	public GuiMarkMachine(TileEntityMarkMachine tileEntityMarkMachine) {
		this.tile = tileEntityMarkMachine;
		this.mainWorld = tileEntityMarkMachine.getWorld();
		this.markDataList = tileEntityMarkMachine.getMarkDataList();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.timeText = new GuiTextField(0, fontRenderer, this.width / 2 + 70, 60, 140, 20) {
			@Override
			public boolean textboxKeyTyped(char typedChar, int keyCode) {
				new String();
				if (String.valueOf(typedChar).matches("[^A-Za-z0-9 ]")) {
					return super.textboxKeyTyped(typedChar, keyCode);
				}
				for (int i = 0; i < "0123456789:".length(); i++) {
					if (typedChar == "0123456789:".charAt(i)) {
						return super.textboxKeyTyped(typedChar, keyCode);
					}
				}
				return false;
			}
		};
		this.timeText.setMaxStringLength(5);
		this.typeText = new GuiTextField(1, fontRenderer, this.width / 2 + 70, 90, 140, 20);
		this.whereText = new GuiTextField(2, fontRenderer, this.width / 2 + 70, 120, 140, 20);
		this.removeButton = new GuiButton(0, this.width / 2 + 10, 25, 100, 20, "Remove");
		this.addButton = new GuiButton(1, this.width / 2 + 110, 25, 100, 20, "Add");
		this.listBox = new ControlListBox(10, 10, this.width / 2 - 20, this.height - 20, this.markDataList);
		this.buttonList.add(this.removeButton);
		this.buttonList.add(this.addButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.removeButton.drawButton(mc, mouseX, mouseY, partialTicks);
		this.addButton.drawButton(mc, mouseX, mouseY, partialTicks);
		this.timeText.drawTextBox();
		this.typeText.drawTextBox();
		this.whereText.drawTextBox();
		this.fontRenderer.drawString("Time", this.width / 2 + 10, 60, 16777215);
		this.fontRenderer.drawString("TrainType", this.width / 2 + 10, 90, 16777215);
		this.fontRenderer.drawString("Dest", this.width / 2 + 10, 120, 16777215);
		this.fontRenderer.drawString((new UtilMinecraftDate(this.mainWorld.getWorldTime())).toString(),
				this.width - this.fontRenderer
						.getStringWidth((new UtilMinecraftDate(this.mainWorld.getWorldTime())).toString()),
				this.height - 12, 16777215);
		this.listBox.draw(mouseX, mouseY, fontRenderer);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		this.timeText.mouseClicked(mouseX, mouseY, mouseButton);
		this.typeText.mouseClicked(mouseX, mouseY, mouseButton);
		this.whereText.mouseClicked(mouseX, mouseY, mouseButton);
		this.listBox.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			if (GuiMarkMachine.this.listBox.selectedIndex > -1
					&& GuiMarkMachine.this.listBox.selectedIndex < GuiMarkMachine.this.markDataList.size()) {
				GuiMarkMachine.this.markDataList.remove(GuiMarkMachine.this.listBox.selectedIndex);
				GuiMarkMachine.this.listBox.items.remove(GuiMarkMachine.this.listBox.selectedIndex);
				GuiMarkMachine.this.listReload();
			}
			break;
		case 1:
			String time_text = GuiMarkMachine.this.timeText.getText();
			if (time_text.contains(":") && (time_text.split(":")).length == 2 && this.whereText.getText() != null
					&& this.whereText.getText().length() != 0 && this.typeText.getText() != null
					&& this.typeText.getText().length() != 0) {
				String[] splits = time_text.split(":");
				int hour = Integer.valueOf(splits[0]).intValue();
				int mins = Integer.valueOf(splits[1]).intValue();
				if (hour > 24 || hour < 0 || mins > 60 || mins < 0)
					return;
				UtilMarkData md = new UtilMarkData(hour, mins, this.whereText.getText(), this.typeText.getText());
				this.markDataList.add(md);
				listReload();
				this.timeText.setText("");
				this.typeText.setText("");
				this.whereText.setText("");
			}
			break;
		}
		updateScreen();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		this.timeText.textboxKeyTyped(typedChar, keyCode);
		this.typeText.textboxKeyTyped(typedChar, keyCode);
		this.whereText.textboxKeyTyped(typedChar, keyCode);
	}

	@Override
	public void updateScreen() {
		this.timeText.updateCursorCounter();
		this.typeText.updateCursorCounter();
		this.whereText.updateCursorCounter();
		super.updateScreen();
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		this.listBox.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}

	@Override
	public void onResize(Minecraft mcIn, int w, int h) {
		this.listBox.onResize(w / 2 - 20, h - 20);
		super.onResize(mcIn, w, h);
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		BlockPos pos = this.tile.getPos();
		PacketHandler.INSTANCE.sendToServer(new PacketMarkData(this.markDataList, pos.getX(), pos.getY(), pos.getZ()));
	}

	private void listReload() {
		List<UtilMarkData> markDatas = new ArrayList<UtilMarkData>(this.markDataList);
		this.markDataList.clear();
		Collections.sort(markDatas, (Comparator<? super UtilMarkData>) new UtilMarkDataComparator());
		for (int i = 0; i < markDatas.size(); i++) {
			this.markDataList.add(markDatas.get(i));
		}
		this.listBox.reloadList(markDatas);
		updateScreen();
	}
}