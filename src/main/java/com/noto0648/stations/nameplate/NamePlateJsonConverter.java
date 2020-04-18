package com.noto0648.stations.nameplate;

import com.noto0648.stations.client.render.TileEntityNamePlateRender;
import com.noto0648.stations.client.texture.NewFontRenderer;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.GL11;

@NamePlateAnnotation
public class NamePlateJsonConverter extends NamePlateBase {
  private final List<NamePlateJson.LabelData> labels;
  
  private final String name;
  
  private final NamePlateJson plateData;
  
  public NamePlateJsonConverter(NamePlateJson plateJson, List<NamePlateJson.LabelData> list) {
    this.labels = list;
    this.name = plateJson.name;
    this.plateData = plateJson;
  }
  
  public void render(Map<String, String> map, boolean rotate, int plateFace) {
    if (this.labels != null) {
      if (this.plateData != null) {
        if (this.plateData.enableDepthMask)
          GL11.glDepthMask(false); 
        if (this.plateData.enableNormal)
          GL11.glNormal3f(0.0F, 0.0F, -1.0F); 
      } 
      if (plateFace != 0) {
        if (this.plateData.modelId == 1) {
          if (plateFace == 1)
            GL11.glTranslatef(0.0F, 0.25F, 0.0F); 
          if (plateFace == 2)
            GL11.glTranslatef(0.0F, -0.25F, 0.0F); 
        } 
        if (this.plateData.modelId == 2);
      } 
      GL11.glTranslated(0.0D, 0.0D, 0.1D);
      GL11.glScalef(0.01F, 0.01F, 0.01F);
      GL11.glTranslated(-110.0D, 20.0D, 0.0D);
      for (int i = 0; i < this.labels.size(); i++) {
        NamePlateJson.LabelData label = this.labels.get(i);
        String drawStr = map.get(label.label);
        if (label.enableReverse && rotate)
          drawStr = map.get(label.reverseLabel); 
        GL11.glPushMatrix();
        GL11.glColor3d(label.R, label.G, label.B);
        GL11.glTranslated(label.x, -label.y, 0.0D);
        GL11.glScaled(label.fontScale, label.fontScale, label.fontScale);
        int width = NewFontRenderer.INSTANCE.drawString(drawStr, false);
        if (label.justification == NamePlateJson.Justification.CENTER)
          GL11.glTranslated((-width / 2), 0.0D, 0.0D); 
        if (label.justification == NamePlateJson.Justification.RIGHT)
          GL11.glTranslated(-width, 0.0D, 0.0D); 
        NewFontRenderer.INSTANCE.drawString(0, 0, drawStr);
        GL11.glPopMatrix();
      } 
    } 
    if (this.plateData != null)
      if (this.plateData.enableDepthMask)
        GL11.glDepthMask(true);  
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
  }
  
  public void init(List<String> list) {
    if (this.labels == null) {
      list.add("JSON_ERROR");
    } else {
      for (int i = 0; i < this.labels.size(); i++)
        list.add(((NamePlateJson.LabelData)this.labels.get(i)).label); 
    } 
  }
  
  public String getName() {
    return (this.name == null) ? "NAME IS NULL" : this.name;
  }
  
  public boolean isUserRender() {
    return (this.plateData == null) ? false : ((this.plateData.modelId != 0));
  }
  
  public void userRender(int plateFace) {
    if (this.plateData.modelId == 1) {
      if (plateFace == 1)
        GL11.glTranslatef(0.0F, 0.5F, 0.0F); 
      if (plateFace == 2)
        GL11.glTranslatef(0.0F, -0.5F, 0.0F); 
      TileEntityNamePlateRender.subwayModel.renderAll();
    } 
    if (this.plateData.modelId == 2) {
      if (plateFace == 1)
        GL11.glTranslatef(0.0F, 0.5F, 0.0F); 
      if (plateFace == 2)
        GL11.glTranslatef(0.0F, -0.5F, 0.0F); 
      GL11.glScalef(1.5F, 1.5F, 1.5F);
      TileEntityNamePlateRender.kokutetuModel.renderAll();
    } 
  }
}
