package com.noto0648.stations.nameplate;

import com.google.gson.Gson;
import com.noto0648.stations.Stations;
import cpw.mods.fml.common.Loader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class NamePlateManager {
  public static NamePlateManager INSTANCE = new NamePlateManager();
  
  private static Gson gson = new Gson();
  
  private List<NamePlateBase> plates = new ArrayList<NamePlateBase>();
  
  public static List<String> platesImages = new ArrayList<String>();
  
  public void init() {
    INSTANCE.registerNamePlate(new NamePlateDefault());
    scanningNamePlate();
  }
  
  public void registerNamePlate(NamePlateBase plate) {
    this.plates.add(plate);
  }
  
  public void removeNamePlate(NamePlateBase plate) {
    this.plates.remove(plate);
  }
  
  private void scanningNamePlate() {
    File modsDir = new File(Loader.instance().getConfigDir().getParentFile(), "mods");
    File modDir = new File(modsDir, "stations_mod");
    File platesDir = new File(modDir, "name_plates");
    platesDir.mkdirs();
    File[] images = platesDir.listFiles();
    List<String> zipPaths = new ArrayList<String>();
    int i;
    for (i = 0; i < images.length; i++) {
      if (images[i].getPath().endsWith(".png")) {
        platesImages.add(images[i].getPath());
        Stations.proxy.readTexture(images[i].getPath());
      } else if (images[i].getPath().endsWith(".json")) {
        try {
          BufferedReader stream = new BufferedReader(new InputStreamReader(new FileInputStream(images[i])));
          StringBuilder result = new StringBuilder();
          String line;
          while ((line = stream.readLine()) != null)
            result.append(line); 
          NamePlateJson namePlateData = (NamePlateJson)gson.fromJson(result.toString(), NamePlateJson.class);
          INSTANCE.registerNamePlate(new NamePlateJsonConverter(namePlateData, namePlateData.labels));
          stream.close();
        } catch (Exception e) {
          e.printStackTrace();
        } 
      } else if (images[i].getPath().endsWith(".zip") || images[i].getPath().endsWith(".jar")) {
        zipPaths.add(images[i].getPath());
      } 
    } 
    for (i = 0; i < zipPaths.size(); i++) {
      try {
        ZipFile zip = new ZipFile(zipPaths.get(i), Charset.forName("MS932"));
        for (Enumeration<? extends ZipEntry> e = zip.entries(); e.hasMoreElements(); ) {
          ZipEntry entry = e.nextElement();
          if (entry.getName().endsWith(".class")) {
            if (entry.getName().contains("$"))
              continue; 
            ClassLoader clsLoader = getClass().getClassLoader();
            Class<?> cls = clsLoader.loadClass(entry.getName().replace(".class", "").replace("/", "."));
            for (int k = 0; k < (cls.getAnnotations()).length; k++) {
              Annotation annotation = cls.getAnnotations()[k];
              if (annotation instanceof NamePlateAnnotation) {
                Object inst = cls.newInstance();
                INSTANCE.registerNamePlate((NamePlateBase)inst);
              } 
            } 
            continue;
          } 
          if (entry.getName().endsWith(".json")) {
            BufferedReader stream = new BufferedReader(new InputStreamReader(zip.getInputStream(entry)));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = stream.readLine()) != null)
              result.append(line); 
            NamePlateJson namePlateData = (NamePlateJson)gson.fromJson(result.toString(), NamePlateJson.class);
            INSTANCE.registerNamePlate(new NamePlateJsonConverter(namePlateData, namePlateData.labels));
            stream.close();
            continue;
          } 
          if (entry.getName().endsWith(".png")) {
            String key = (String)zipPaths.get(i) + "/" + entry.getName();
            platesImages.add(key);
            Stations.proxy.readTexture(key, zip.getInputStream(entry));
          } 
        } 
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public List<NamePlateBase> getNamePlates() {
    return this.plates;
  }
}
