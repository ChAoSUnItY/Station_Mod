package chaos.mod.util.interfaces;

import net.minecraft.client.gui.FontRenderer;

public interface IGui {
	void drawRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);

	FontRenderer getFontRenderer();
}
