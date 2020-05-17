package chaos.mod.tileentity.render;

import org.lwjgl.opengl.GL11;

import chaos.mod.tileentity.TileEntityDepartureMark;
import chaos.mod.tileentity.TileEntityMarkMachine;
import chaos.mod.util.utils.UtilMarkData;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDepartureMark extends TileEntitySpecialRenderer<TileEntityDepartureMark> {
	private FontRenderer font;

	@Override
	public void render(TileEntityDepartureMark te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		int meta = te.getBlockMetadata();
		EnumFacing enumFacing = EnumFacing.getFront(meta);
		this.font = getFontRenderer();
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		if (enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH)
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glPushMatrix();
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glPopMatrix();
		for (int i = 0; i < 2; i++) {
			GL11.glPushMatrix();
			if (i == 0) {
				if (enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH)
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
				if (enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.EAST)
					GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			} else {
				if (enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH)
					GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
				if (enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.EAST)
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			}
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslated(-1.13D, -0.1D, -0.146D);
			GL11.glScaled(0.018D, 0.018D, 0.018D);
			GL11.glColor3f(1.0F, 1.0F, 1.0F);
			drawStrings((TileEntityDepartureMark) te);
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}

	private void drawStrings(TileEntityDepartureMark tile) {
		if (tile.isRegistered()) {
			TileEntity te = tile.getWorld().getTileEntity(new BlockPos(tile.getParentX(), tile.getParentY(), tile.getParentZ()));
			if (te != null && te instanceof TileEntityMarkMachine) {
				UtilMarkData[] mka = ((TileEntityMarkMachine) te).getStringIndex();
				if (mka != null) {
					if (mka.length > 0 && mka[0] != null) {
						this.font.drawString((mka[0]).type, 0, 0, (mka[0]).typeColor);
						this.font.drawString(toEm(mka[0].getTimeString()), 44, 0, (mka[0]).timeColor);
						this.font.drawString((mka[0]).dest, 74, 0, (mka[0]).destColor);
					}
					if (mka.length > 1 && mka[1] != null) {
						this.font.drawString((mka[1]).type, 0, 18, (mka[1]).typeColor);
						this.font.drawString(toEm(mka[1].getTimeString()), 44, 18, (mka[1]).timeColor);
						this.font.drawString((mka[1]).dest, 74, 18, (mka[1]).destColor);
					}
				}
			} else {
				this.font.drawString("Disconnect", 0, 0, 16777215);
			}
		} else {
			this.font.drawString("��    ��    ��", 0, 0, 16777215);
		}
	}

	public static String toEm(String s) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9')
				sb.setCharAt(i, (char) (c - 48 + 65296));
			if (c == ':')
				sb.setCharAt(i, '�G');
		}
		return sb.toString();
	}
}
