package com.noto0648.stations.nameplate;

import java.util.List;

public class NamePlateJson {
  public String name;
  
  public List<LabelData> labels;
  
  public String author;
  
  public String comment;
  
  public boolean enableDepthMask;
  
  public boolean enableNormal;
  
  public int modelId;
  
  public class LabelData {
    public int x;
    
    public int y;
    
    public NamePlateJson.Justification justification;
    
    public String label;
    
    public String reverseLabel;
    
    public boolean enableReverse;
    
    public double R;
    
    public double G;
    
    public double B;
    
    public double fontScale;
    
    public String author;
    
    public String comment;
  }
  
  public enum Justification {
    CENTER, RIGHT, LEFT;
  }
}
