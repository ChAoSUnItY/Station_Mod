package chaos.mod.objects.block.gui.control;

import net.minecraft.client.gui.GuiScreen;

public class ControlContextMenu extends Control {
	private boolean isShow;

	private String[] data;

	private int selectData = -1;

	public ControlContextMenu(GuiScreen gui) {
		super(gui);
	}

	public ControlContextMenu(GuiScreen gui, String[] str) {
		super(gui);
		this.data = str;
		this.height = 12 * this.data.length;
	}

	public void draw(int mouseX, int mouseY) {
	}

	public void initGui() {
		int maxWidth = 0;
		for (int i = 0; i < this.data.length; i++)
			maxWidth = Math.max(getFont().getStringWidth(this.data[i]), maxWidth);
		this.width = maxWidth + 4;
	}

	public void drawTopLayer(int mouseX, int mouseY) {
		if (this.isShow) {
			drawRect(this.locationX, this.locationY, this.locationX + this.width, this.locationY + this.height,
					-290410320);
			if (onTheMouse(mouseX, mouseY)) {
				this.selectData = (mouseY - this.locationY) / 12;
				this.selectData = Math.min(this.selectData, this.data.length - 1);
			} else {
				this.selectData = -1;
			}
			if (this.selectData != -1)
				drawRect(this.locationX, this.locationY + this.selectData * 12, this.locationX + this.width,
						this.locationY + this.selectData * 12 + 12, -300871170);
			for (int i = 0; i < this.data.length; i++)
				getFont().drawStringWithShadow(this.data[i], this.locationX + 2, this.locationY + 2 + i * 12, 16777215);
		}
	}

	public void update() {
	}

	public void mouseClicked(int mouseX, int mouseY, int button) {
		if (this.isShow)
			if (onTheMouse(mouseX, mouseY)) {
				int select = (mouseY - this.locationY) / 12;
				dataClick(select);
				hide();
			} else {
				hide();
			}
	}

	public void show(int x, int y, int ctrlId) {
		this.isShow = true;
		this.locationX = x;
		this.locationY = y;
		if (getGui() instanceof GuiScreen)
			((GuiScreen) getGui()).confirmClicked(true, ctrlId);
	}

	public void hide() {
		if (getGui() instanceof GuiScreen)
			((GuiScreen) getGui()).confirmClicked(false, -1);
		this.isShow = false;
	}

	public void dataClick(int id) {
	}
}
