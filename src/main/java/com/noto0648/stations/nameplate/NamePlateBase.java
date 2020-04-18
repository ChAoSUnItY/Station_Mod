package com.noto0648.stations.nameplate;

import java.util.List;
import java.util.Map;

public abstract class NamePlateBase {
  public abstract void render(Map<String, String> paramMap, boolean paramBoolean, int paramInt);
  
  public abstract void init(List<String> paramList);
  
  public abstract String getName();
  
  public boolean isUserRender() {
    return false;
  }
  
  public void userRender(int plateFace) {}
}
