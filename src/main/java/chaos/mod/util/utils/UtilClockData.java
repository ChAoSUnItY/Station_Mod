package chaos.mod.util.utils;

import org.lwjgl.opengl.GL11;

import chaos.mod.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UtilClockData {
	private static Minecraft mc = FMLClientHandler.instance().getClient();

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void renderScreen(RenderGameOverlayEvent event) {
		if (mc.player.getHeldItemMainhand() != null
				&& (mc.player.getHeldItemMainhand().getItem() == ItemInit.DIGITAL_CLOCK
						|| mc.player.getHeldItemMainhand().getItem() == ItemInit.ANALOG_CLOCK)) {
			GL11.glPushMatrix();
			if (mc.player.getHeldItemMainhand().getItem() == ItemInit.DIGITAL_CLOCK
					&& event.getType() == ElementType.TEXT) {
				mc.fontRenderer.drawStringWithShadow((new UtilMinecraftDate(mc.world.getWorldTime())).toString(), 1, 1,
						16777215);
			} else if (mc.player.getHeldItemMainhand().getItem() == ItemInit.ANALOG_CLOCK
					&& event.getType() == ElementType.CROSSHAIRS) {
				GL11.glLineWidth(5.0F);
				UtilMinecraftDate md = new UtilMinecraftDate((World) mc.world);
				Tessellator tes = Tessellator.getInstance();
				BufferBuilder builder = tes.getBuffer();
				GL11.glDisable(3553);
				builder.begin(9, DefaultVertexFormats.POSITION);
				float th1;
				for (th1 = 0.0F; th1 <= 360.0F; th1++) {
					float th2 = th1 + 10.0F;
					float th1_rad = th1 / 180.0F * 3.1415927F;
					float th2_rad = th2 / 180.0F * 3.1415927F;
					float x1 = 20.0F * MathHelper.cos(th1_rad);
					float y1 = 20.0F * MathHelper.sin(th1_rad);
					float x2 = 20.0F * MathHelper.cos(th2_rad);
					float y2 = 20.0F * MathHelper.sin(th2_rad);
					builder.pos((x1 + 22), (y1 + 22), 0.0D).endVertex();
					builder.pos((x2 + 22), (y2 + 22), 0.0D).endVertex();
				}
				tes.draw();
				GL11.glPointSize(4.0F);
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				GL11.glColor3f(0.0F, 0.0F, 0.0F);
				builder.begin(0, DefaultVertexFormats.POSITION_NORMAL);
				for (int i = 0; i < 12; i++) {
					float f = (float) ((30 * i) * Math.PI / 180.0D);
					tes.getBuffer().pos(22.0F + MathHelper.sin(f) * 18.0F, 22.0D + Math.cos(f) * 18.0D, 0.0D)
							.normal(0, 0, 1).endVertex();
				}
				tes.draw();
				GL11.glColor3f(0.0F, 0.0F, 0.0F);
				GL11.glLineWidth(3.0F);
				builder.begin(1, DefaultVertexFormats.POSITION_NORMAL);
				builder.pos(22.0D, 22.0D, 0.0D).normal(0, 0, 1).endVertex();
				float minute = (float) ((6.0F * (60 - md.getMinutes()) - 180.0F) * Math.PI / 180.0D);
				builder.pos(22.0F + MathHelper.sin(minute) * 18.0F, 22.0D + Math.cos(minute) * 18.0D, 0.0D)
						.normal(0, 0, 1).endVertex();
				tes.draw();
				GL11.glLineWidth(5.0F);
				builder.begin(1, DefaultVertexFormats.POSITION_NORMAL);
				builder.pos(22.0D, 22.0D, 0.0D).normal(0, 0, 1).endVertex();
				float hours = (float) ((0.5F * (720 - md.getHours() % 12 * 60 - md.getMinutes()) - 180.0F) * Math.PI
						/ 180.0D);
				builder.pos((22.0F + MathHelper.sin(hours) * 15.0F), 22.0D + Math.cos(hours) * 15.0D, 0.0D)
						.normal(0, 0, 1).endVertex();
				tes.draw();
				GL11.glLineWidth(1.0F);
				GL11.glPointSize(1.0F);
				GL11.glEnable(3553);
			}
			GL11.glPopMatrix();
		}
	}
}
