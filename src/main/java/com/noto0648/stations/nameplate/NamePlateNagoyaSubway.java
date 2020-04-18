package com.noto0648.stations.nameplate;

import com.noto0648.stations.client.texture.NewFontRenderer;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.GL11;

@NamePlateAnnotation
public class NamePlateNagoyaSubway extends NamePlateBase {
  public void render(Map<String, String> map, boolean rotate, int plateFace) {
    String nowStation = map.get("kanjiName");
    String englishStation = map.get("englishName");
    String nextStation = map.get("nextStation");
    String prevStation = map.get("prevStation");
    GL11.glNormal3f(-1.0F, 0.0F, 0.0F);
    GL11.glTranslated(0.0D, 0.0D, 0.1D);
    GL11.glScalef(0.01F, 0.01F, 0.01F);
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    GL11.glNormal3f(-1.0F, 0.0F, 0.0F);
    int width = NewFontRenderer.INSTANCE.drawString(nowStation, false);
    GL11.glScalef(0.6F, 0.6F, 0.6F);
    GL11.glTranslated((-width / 2), 30.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(nowStation);
    GL11.glTranslated((width / 2), -30.0D, 0.0D);
    GL11.glPushMatrix();
    GL11.glTranslated(0.0D, 0.0D, 0.0D);
    GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
    int w = NewFontRenderer.INSTANCE.drawString(englishStation, false);
    GL11.glTranslated((-w / 2), 15.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(englishStation);
    GL11.glPopMatrix();
    GL11.glColor3f(0.0F, 0.0F, 0.0F);
    String station = !rotate ? prevStation : nextStation;
    String eSta = !rotate ? map.get("prevEnglish") : map.get("nextEnglish");
    GL11.glScalef(1.66667F, 1.66667F, 1.66667F);
    GL11.glPushMatrix();
    GL11.glTranslated(-107.0D, -24.0D, 0.0D);
    GL11.glScaled(0.38999998569488525D, 0.38999998569488525D, 0.38999998569488525D);
    NewFontRenderer.INSTANCE.drawString(station);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(-107.0D, -32.0D, 0.0D);
    GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
    NewFontRenderer.INSTANCE.drawString(eSta);
    GL11.glPopMatrix();
    station = rotate ? prevStation : nextStation;
    eSta = rotate ? map.get("prevEnglish") : map.get("nextEnglish");
    GL11.glPushMatrix();
    GL11.glTranslated(107.0D, -24.0D, 0.0D);
    GL11.glScaled(0.38999998569488525D, 0.38999998569488525D, 0.38999998569488525D);
    int i = NewFontRenderer.INSTANCE.drawString(station, false);
    GL11.glTranslated(-i, 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(station);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(107.0D, -32.0D, 0.0D);
    GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
    i = NewFontRenderer.INSTANCE.drawString(eSta, false);
    GL11.glTranslated(-i, 0.0D, 0.0D);
    NewFontRenderer.INSTANCE.drawString(eSta);
    GL11.glPopMatrix();
  }
  
  public void init(List<String> list) {
    list.add("kanjiName");
    list.add("englishName");
    list.add("nextStation");
    list.add("nextEnglish");
    list.add("prevStation");
    list.add("prevEnglish");
  }
  
  public String getName() {
    return "Nagoya_Subway";
  }
}
