package chaos.mod.objects.block.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.network.PacketVendorWorker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;

public class GuiVendor extends GuiScreen {
	public BlockPos pos;
	public GuiButton buttonOneWayTicket;
	public GuiButton buttonReturnTicket;
	public GuiButton buttonCouponTicket;
	public GuiButton buttonAdmissionTicket;

	String[] oneLine = new String[20];
	int[] twoLine = new int[20];

	public GuiVendor(InventoryPlayer invPlayer, EntityPlayer player, BlockPos pos) {
		this.pos = pos;

		this.oneLine[0] = I18n.format("container.vendor.1");
		this.twoLine[0] = 160;
		this.oneLine[1] = I18n.format("container.vendor.2");
		this.twoLine[1] = 320;
		this.oneLine[2] = I18n.format("container.vendor.3");
		this.twoLine[2] = 1600;
		this.oneLine[3] = I18n.format("container.vendor.4");
		this.twoLine[3] = 140;
	}

	@Override
	public void initGui() {
		super.initGui();
		buttonOneWayTicket = new GuiButton(0, 60, 15, 90, 30, "");
		buttonReturnTicket = new GuiButton(1, 160, 15, 90, 30, "");
		buttonCouponTicket = new GuiButton(2, 60, 50, 90, 30, "");
		buttonAdmissionTicket = new GuiButton(3, 160, 50, 90, 30, "");
		this.buttonList.add(this.buttonOneWayTicket);
		this.buttonList.add(this.buttonReturnTicket);
		this.buttonList.add(this.buttonCouponTicket);
		this.buttonList.add(this.buttonAdmissionTicket);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawRect(50, 10, this.width - 50, this.height - 50, -16777216);
		for (int x = 0; x < 4; x++) {
			if (this.oneLine[x] != null && x < 2) {
				GL11.glPushMatrix();
				drawRect(60 + x * 100, 15, 90 + x * 100 + 60, 45, -4144960);
				GL11.glTexCoord2d((60 + x * 60), 15.0D);
				GL11.glColor3f(0.0F, 0.0F, 0.0F);
				fontRenderer.drawString(this.oneLine[x], 105 + x * 100 - fontRenderer.getStringWidth(this.oneLine[x]) / 2,
						19, 0);
				fontRenderer.drawString(this.twoLine[x] + "",
						105 + x * 100 - fontRenderer.getStringWidth(this.twoLine[x] + "") / 2, 31, 0);
				GL11.glPopMatrix();
			} else if (this.oneLine[x] != null) {
				GL11.glPushMatrix();
				drawRect(60 + (x-2) * 100, 50, 90 + (x-2) * 100 + 60, 80, -4144960);
				GL11.glTexCoord2d((60 + x * 60), 15.0D);
				GL11.glColor3f(0.0F, 0.0F, 0.0F);
				fontRenderer.drawString(this.oneLine[x], 105 + (x-2) * 100 - fontRenderer.getStringWidth(this.oneLine[x]) / 2,
						54, 0);
				fontRenderer.drawString(this.twoLine[x] + "",
						105 + (x-2) * 100 - fontRenderer.getStringWidth(this.twoLine[x] + "") / 2, 66, 0);
				GL11.glPopMatrix();
			}
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			PacketHandler.INSTANCE.sendToServer(new PacketVendorWorker(this.pos, 0));
			break;
		case 1:
			PacketHandler.INSTANCE.sendToServer(new PacketVendorWorker(this.pos, 1));
			break;
		case 2:
			PacketHandler.INSTANCE.sendToServer(new PacketVendorWorker(this.pos, 2));
			break;
		case 3:
			PacketHandler.INSTANCE.sendToServer(new PacketVendorWorker(this.pos, 3));
			break;
		default:
			break;
		}
		Minecraft.getMinecraft().displayGuiScreen(null);
		updateScreen();
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}
}
