package com.noto0648.stations.nameplate;

import com.noto0648.stations.client.texture.NewFontRenderer;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.GL11;

@NamePlateAnnotation
public class NamePlateDefault extends NamePlateBase {
  public void render(Map<String, String> map, boolean rotate, int plateFace) {
    String nowStation = map.get("stationName");
    String englishStation = map.get("englishName");
    String nextStation = map.get("nextStation");
    String prevStation = map.get("prevStation");
    GL11.glTranslated(0.0D, 0.0D, 0.1D);
    GL11.glScalef(0.01F, 0.01F, 0.01F);
    GL11.glColor3f(0.0F, 0.0F, 0.0F);
    int width = NewFontRenderer.INSTANCE.drawString(nowStation, false);
    GL11.glTranslated((-width / 2), 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(nowStation);
    GL11.glTranslated((width / 2), 0.0D, 0.0D);
    String station = !rotate ? prevStation : nextStation;
    GL11.glPushMatrix();
    GL11.glTranslated(-110.0D, -43.0D, 0.0D);
    GL11.glScaled(0.5D, 0.5D, 0.5D);
    NewFontRenderer.INSTANCE.drawString(station);
    GL11.glPopMatrix();
    station = rotate ? prevStation : nextStation;
    GL11.glPushMatrix();
    GL11.glTranslated(110.0D, -43.0D, 0.0D);
    GL11.glScaled(0.5D, 0.5D, 0.5D);
    int w = NewFontRenderer.INSTANCE.drawString(station, false);
    GL11.glTranslated(-w, 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(station);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    GL11.glTranslated(0.0D, -25.0D, 0.0D);
    GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
    w = NewFontRenderer.INSTANCE.drawString(englishStation, false);
    GL11.glTranslated((-w / 2), 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(englishStation);
    GL11.glPopMatrix();
  }
  
  public void init(List<String> list) {
    list.add("stationName");
    list.add("nextStation");
    list.add("prevStation");
    list.add("englishName");
  }
  
  public String getName() {
    return "Default";
  }
}
