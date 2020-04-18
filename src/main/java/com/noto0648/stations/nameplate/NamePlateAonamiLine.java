package com.noto0648.stations.nameplate;

import com.noto0648.stations.client.render.TileEntityNamePlateRender;
import com.noto0648.stations.client.texture.NewFontRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.GL11;

@NamePlateAnnotation
public class NamePlateAonamiLine extends NamePlateBase {
  @SideOnly(Side.CLIENT)
  public void render(Map<String, String> map, boolean rotate, int plateFace) {
    String nowStation = map.get("stationName");
    String nowEnglish = map.get("englishName");
    GL11.glEnable(3042);
    GL11.glBlendFunc(1, 771);
    GL11.glDepthMask(false);
    GL11.glNormal3f(-1.0F, 0.0F, 0.0F);
    if (plateFace == 1)
      GL11.glTranslatef(0.0F, 0.25F, 0.0F); 
    if (plateFace == 2)
      GL11.glTranslatef(0.0F, -0.25F, 0.0F); 
    GL11.glTranslated(0.0D, 0.0D, 0.07D);
    GL11.glScalef(0.01F, 0.01F, 0.01F);
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    GL11.glPushMatrix();
    GL11.glScaled(0.4D, 0.4D, 0.4D);
    int width = NewFontRenderer.INSTANCE.drawString(nowStation, false);
    GL11.glTranslated((-width / 2), 10.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(nowStation);
    GL11.glTranslated((width / 2), -10.0D, 0.0D);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(0.0D, -20.0D, 0.0D);
    GL11.glScaled(0.4D, 0.4D, 0.4D);
    int engW = NewFontRenderer.INSTANCE.drawString(nowEnglish, false);
    GL11.glTranslated((-engW / 2), 10.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(nowEnglish);
    GL11.glTranslated((width / 2), -10.0D, 0.0D);
    GL11.glPopMatrix();
    String nextStation = !rotate ? map.get("prevStation") : map.get("nextStation");
    String nextEnglish = !rotate ? map.get("prevEnglish") : map.get("nextEnglish");
    GL11.glPushMatrix();
    GL11.glTranslated(-80.0D, 10.0D, 0.0D);
    GL11.glScaled(0.15D, 0.15D, 0.15D);
    int strW = NewFontRenderer.INSTANCE.drawString(nextStation, false);
    NewFontRenderer.INSTANCE.drawString(nextStation);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(-80.0D, 5.0D, 0.0D);
    GL11.glScaled(0.15D, 0.15D, 0.15D);
    strW = NewFontRenderer.INSTANCE.drawString(nextEnglish, false);
    NewFontRenderer.INSTANCE.drawString(nextEnglish);
    GL11.glPopMatrix();
    nextStation = rotate ? map.get("prevStation") : map.get("nextStation");
    nextEnglish = rotate ? map.get("prevEnglish") : map.get("nextEnglish");
    GL11.glPushMatrix();
    GL11.glTranslated(70.0D, 10.0D, 0.0D);
    GL11.glScaled(0.15D, 0.15D, 0.15D);
    strW = NewFontRenderer.INSTANCE.drawString(nextStation, false);
    GL11.glTranslatef(-strW, 0.0F, 0.0F);
    NewFontRenderer.INSTANCE.drawString(nextStation);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(70.0D, 5.0D, 0.0D);
    GL11.glScaled(0.15D, 0.15D, 0.15D);
    strW = NewFontRenderer.INSTANCE.drawString(nextEnglish, false);
    GL11.glTranslatef(-strW, 0.0F, 0.0F);
    NewFontRenderer.INSTANCE.drawString(nextEnglish);
    GL11.glPopMatrix();
    GL11.glDepthMask(true);
    GL11.glDisable(3042);
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
  }
  
  public void init(List<String> list) {
    list.add("stationName");
    list.add("englishName");
    list.add("nextStation");
    list.add("nextEnglish");
    list.add("prevStation");
    list.add("prevEnglish");
  }
  
  public String getName() {
    return "Aonami Line";
  }
  
  public boolean isUserRender() {
    return true;
  }
  
  public void userRender(int plateFace) {
    if (plateFace == 1)
      GL11.glTranslatef(0.0F, 0.5F, 0.0F); 
    if (plateFace == 2)
      GL11.glTranslatef(0.0F, -0.5F, 0.0F); 
    TileEntityNamePlateRender.subwayModel.renderAll();
  }
}
