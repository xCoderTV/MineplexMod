package net.xCoderTV.Mineplex.settings;

import java.util.ArrayList;

public class SettingsManager
{
  public static ArrayList<String> color = new ArrayList();
  
  public static void saveSettings() {}
  
  public static String getColor()
  {
    if (color.contains("red")) {
      return "§c";
    }
    if (color.contains("yellow")) {
      return "§e";
    }
    if (color.contains("black")) {
      return "§0";
    }
    if (color.contains("aqua")) {
      return "§b";
    }
    if (color.contains("gold")) {
      return "§6";
    }
    if (color.contains("gray")) {
      return "§7";
    }
    if (color.contains("blue")) {
      return "§9";
    }
    if (color.contains("purple")) {
      return "§d";
    }
    if ((color.contains("white")) || (color.contains("reset"))) {
      return "§f";
    }
    if (color.contains("green")) {
      return "§a";
    }
    return "§f";
  }
}
