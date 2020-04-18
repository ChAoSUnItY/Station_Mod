package com.noto0648.stations.client.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FontTexture extends AbstractTexture {
  private static final Logger logger = LogManager.getLogger();
  
  private BufferedImage buffer;
  
  public FontTexture(BufferedImage bImage) {
    this.buffer = bImage;
  }
  
  public void loadTexture(IResourceManager manager) throws IOException {
    TextureUtil.uploadTextureImageAllocate(getGlTextureId(), this.buffer, false, false);
  }
}
