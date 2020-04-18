package com.noto0648.stations.client.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.resources.IResourceManager;
import org.lwjgl.opengl.GL11;

public class TextureImporter {
  public static TextureImporter INSTANCE = new TextureImporter();
  
  private Map<String, FontTexture> textures = new HashMap<String, FontTexture>();
  
  public boolean readTexture(String path) {
    File f = new File(path);
    if (f.exists()) {
      try {
        BufferedImage bufferedImage = ImageIO.read(f);
        FontTexture ft = new FontTexture(bufferedImage);
        ft.loadTexture((IResourceManager)null);
        this.textures.put(path, ft);
      } catch (Exception e) {
        e.printStackTrace();
      } 
      return true;
    } 
    return false;
  }
  
  public boolean readTexture(String key, InputStream stream) {
    try {
      BufferedImage bufferedImage = ImageIO.read(stream);
      FontTexture ft = new FontTexture(bufferedImage);
      ft.loadTexture((IResourceManager)null);
      this.textures.put(key, ft);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return true;
  }
  
  public boolean bindTexture(String path) {
    if (!this.textures.containsKey(path))
      if (readTexture(path))
        return false;  
    if (this.textures.get(path) != null) {
      GL11.glBindTexture(3553, ((FontTexture)this.textures.get(path)).getGlTextureId());
      return true;
    } 
    return false;
  }
}
