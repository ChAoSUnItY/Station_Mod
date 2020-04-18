package com.noto0648.stations.nameplate;

import com.noto0648.stations.client.texture.NewFontRenderer;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.GL11;

@NamePlateAnnotation
public class NamePlateMeitetsu extends NamePlateBase {
  public void render(Map<String, String> map, boolean rotate, int plateFace) {
    String nowStation = map.get("kanjiName");
    String englishStation = map.get("englishName");
    String nextStation = map.get("nextStation");
    String prevStation = map.get("prevStation");
    GL11.glTranslated(0.0D, 0.0D, 0.1D);
    GL11.glScalef(0.01F, 0.01F, 0.01F);
    GL11.glColor3f(0.0F, 0.0F, 0.0F);
    int width = NewFontRenderer.INSTANCE.drawString(nowStation, false);
    GL11.glScalef(0.8F, 0.8F, 0.8F);
    GL11.glTranslated((-width / 2), 10.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(nowStation);
    GL11.glTranslated((width / 2), -10.0D, 0.0D);
    GL11.glPushMatrix();
    GL11.glScalef(0.4F, 0.4F, 0.4F);
    GL11.glTranslated(0.0D, -10.0D, 0.0D);
    int kanjiWidth = NewFontRenderer.INSTANCE.drawString(map.get("hiraganaName"), false);
    GL11.glTranslated((-kanjiWidth / 2), 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(map.get("hiraganaName"));
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(0.0D, -18.0D, 0.0D);
    GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
    int w = NewFontRenderer.INSTANCE.drawString(englishStation, false);
    GL11.glTranslated((-w / 2), 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(englishStation);
    GL11.glPopMatrix();
    GL11.glColor3f(0.0F, 0.0F, 0.0F);
    String station = !rotate ? prevStation : nextStation;
    String eSta = !rotate ? map.get("prevEnglish") : map.get("nextEnglish");
    GL11.glScalef(1.25F, 1.25F, 1.25F);
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
    int i = NewFontRenderer.INSTANCE.drawString(station, false);
    GL11.glTranslated(-i, 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(station);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(107.0D, -48.0D, 0.0D);
    GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
    i = NewFontRenderer.INSTANCE.drawString(eSta, false);
    GL11.glTranslated(-i, 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(eSta);
    GL11.glPopMatrix();
  }
  
  public void init(List<String> list) {
    list.add("kanjiName");
    list.add("hiraganaName");
    list.add("englishName");
    list.add("nextStation");
    list.add("nextEnglish");
    list.add("prevStation");
    list.add("prevEnglish");
  }
  
  public String getName() {
    return "Meitetsu";
  }
}
