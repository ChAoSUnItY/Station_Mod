package com.noto0648.stations.nameplate;

import com.noto0648.stations.client.texture.NewFontRenderer;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.GL11;

@NamePlateAnnotation
public class NamePlateToyohashiLine extends NamePlateBase {
  public void render(Map<String, String> map, boolean rotate, int plateFace) {
    String nowStation = map.get("stationName");
    String englishStation = map.get("englishName");
    String nextStation = map.get("nextStation");
    String prevStation = map.get("prevStation");
    GL11.glTranslated(0.0D, 0.0D, 0.1D);
    GL11.glScalef(0.01F, 0.01F, 0.01F);
    GL11.glColor3f(0.0F, 0.0F, 0.0F);
    int width = NewFontRenderer.INSTANCE.drawString(nowStation, false);
    GL11.glTranslated((-width / 2), 10.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(nowStation);
    GL11.glTranslated((width / 2), -10.0D, 0.0D);
    GL11.glPushMatrix();
    GL11.glScalef(0.5F, 0.5F, 0.5F);
    GL11.glTranslated(0.0D, -10.0D, 0.0D);
    int kanjiWidth = NewFontRenderer.INSTANCE.drawString(map.get("kanjiName"), false);
    GL11.glTranslated((-kanjiWidth / 2), 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(map.get("kanjiName"));
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glScalef(0.4F, 0.4F, 0.4F);
    GL11.glTranslated(0.0D, -100.0D, 0.0D);
    kanjiWidth = NewFontRenderer.INSTANCE.drawString(map.get("englishName"), false);
    GL11.glTranslated((-kanjiWidth / 2), 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(map.get("englishName"));
    GL11.glPopMatrix();
    String station = !rotate ? prevStation : nextStation;
    String eSta = !rotate ? map.get("prevEnglish") : map.get("nextEnglish");
    GL11.glPushMatrix();
    GL11.glTranslated(-107.0D, -40.0D, 0.0D);
    GL11.glScaled(0.38999998569488525D, 0.38999998569488525D, 0.38999998569488525D);
    NewFontRenderer.INSTANCE.drawString(station);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(-107.0D, -48.0D, 0.0D);
    GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
    NewFontRenderer.INSTANCE.drawString(eSta);
    GL11.glPopMatrix();
    station = rotate ? prevStation : nextStation;
    eSta = rotate ? map.get("prevEnglish") : map.get("nextEnglish");
    GL11.glPushMatrix();
    GL11.glTranslated(107.0D, -40.0D, 0.0D);
    GL11.glScaled(0.38999998569488525D, 0.38999998569488525D, 0.38999998569488525D);
    int w = NewFontRenderer.INSTANCE.drawString(station, false);
    GL11.glTranslated(-w, 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(station);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(107.0D, -48.0D, 0.0D);
    GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
    w = NewFontRenderer.INSTANCE.drawString(eSta, false);
    GL11.glTranslated(-w, 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(eSta);
    GL11.glPopMatrix();
  }
  
  public void init(List<String> list) {
    list.add("stationName");
    list.add("englishName");
    list.add("kanjiName");
    list.add("nextStation");
    list.add("nextEnglish");
    list.add("prevStation");
    list.add("prevEnglish");
  }
  
  public String getName() {
    return "Toyohashi Line";
  }
}
