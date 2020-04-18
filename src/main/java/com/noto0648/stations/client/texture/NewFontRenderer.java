package com.noto0648.stations.client.texture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.texture.ITextureObject;
import org.lwjgl.opengl.GL11;

public class NewFontRenderer {
  public static String defaultString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-,.あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをんがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽっゃゅょぁぃぅぇぉ()";
  
  public static NewFontRenderer INSTANCE = new NewFontRenderer();
  
  private Map<Character, BufferedImage> images = new HashMap<Character, BufferedImage>();
  
  private Map<Character, Integer> widths = new HashMap<Character, Integer>();
  
  private Map<Character, FontTexture> textures = new HashMap<Character, FontTexture>();
  
  public void init() {
    for (int i = 0; i < defaultString.length(); i++) {
      char c = defaultString.charAt(i);
      Object[] objs = toImage(String.valueOf(c));
      this.images.put(Character.valueOf(c), (BufferedImage)objs[0]);
      this.widths.put(Character.valueOf(c), (Integer)objs[1]);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public Object[] toImage(String str) {
    BufferedImage bImage = new BufferedImage(32, 32, 2);
    Graphics2D objGrh = (Graphics2D)bImage.getGraphics();
    objGrh.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    Font f = new Font("SansSerif", 0, 32);
    objGrh.setFont(f);
    objGrh.setColor(Color.WHITE);
    int width = objGrh.getFontMetrics().stringWidth(str);
    objGrh.drawString(str, 0, 28);
    objGrh.dispose();
    return new Object[] { bImage, Integer.valueOf(width) };
  }
  
  @SideOnly(Side.CLIENT)
  public BufferedImage toImageBuffer(String str) {
    return (BufferedImage)toImage(str)[0];
  }
  
  @SideOnly(Side.CLIENT)
  public int drawString(String str) {
    return drawString(0, 0, str, true);
  }
  
  @SideOnly(Side.CLIENT)
  public int drawString(String str, boolean draw) {
    return drawString(0, 0, str, draw);
  }
  
  @SideOnly(Side.CLIENT)
  public int drawString(int x, int y, String str) {
    return drawString(x, y, str, true);
  }
  
  @SideOnly(Side.CLIENT)
  public int drawString(int x, int y, String str, boolean draw) {
    if (str == null)
      return -1; 
    int offsetX = x;
    int offsetY = y;
    if (draw)
      GL11.glEnable(3008); 
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (!this.images.containsKey(Character.valueOf(c))) {
        Object[] objs = toImage(String.valueOf(c));
        this.images.put(Character.valueOf(c), (BufferedImage)objs[0]);
        this.widths.put(Character.valueOf(c), (Integer)objs[1]);
      } 
      if (draw) {
        bindTexture(this.images.get(Character.valueOf(c)), c);
        GL11.glPushMatrix();
        GL11.glBegin(5);
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(0.0F + offsetX, 0.0F + offsetY, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(32.0F + offsetX, 0.0F + offsetY, 0.0F);
        GL11.glTexCoord2f(0.0F, -1.0F);
        GL11.glVertex3f(0.0F + offsetX, 32.0F + offsetY, 0.0F);
        GL11.glTexCoord2f(1.0F, -1.0F);
        GL11.glVertex3f(32.0F + offsetX, 32.0F + offsetY, 0.0F);
        GL11.glEnd();
        GL11.glPopMatrix();
      } 
      offsetX += ((Integer)this.widths.get(Character.valueOf(c))).intValue();
    } 
    return offsetX - x;
  }
  
  @SideOnly(Side.CLIENT)
  public void bindTexture(BufferedImage bufferedImage, char c) {
    try {
      Object obj = null;
      if (!this.textures.containsKey(Character.valueOf(c))) {
        FontTexture tex = new FontTexture(bufferedImage);
        tex.loadTexture(null);
        this.textures.put(Character.valueOf(c), tex);
        obj = tex;
      } 
      if (obj == null)
        obj = this.textures.get(Character.valueOf(c)); 
      GL11.glBindTexture(3553, ((ITextureObject)obj).getGlTextureId());
    } catch (Exception e) {}
  }
}
