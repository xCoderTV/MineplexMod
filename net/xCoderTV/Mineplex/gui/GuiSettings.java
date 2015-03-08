package net.xCoderTV.Mineplex.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiLockIconButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.client.stream.IStream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.storage.WorldInfo;
import net.xCoderTV.Mineplex.settings.SettingsManager;

public class GuiSettings
  extends GuiScreen
  implements GuiYesNoCallback
{
  private final GuiScreen field_146441_g;
  private final GameSettings game_settings_1;
  private GuiButton field_175357_i;
  private GuiLockIconButton field_175356_r;
  protected String field_146442_a = "Options";
  private GuiTextField connectField;
  private static final String __OBFID = "CL_00000700";
  
  public GuiSettings(GuiScreen p_i1046_1_, GameSettings p_i1046_2_)
  {
    this.field_146441_g = p_i1046_1_;
    this.game_settings_1 = p_i1046_2_;
  }
  
  public void initGui()
  {
    int var1 = 0;
    this.field_146442_a = I18n.format("Mineplex Mod Settings", new Object[0]);
    




    this.connectField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20);
    GuiTextField.isFocused = false;
    this.connectField.setMaxStringLength(16);
    this.connectField.setText("Color");
    





    this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format(SettingsManager.getColor() + "Save", new Object[0])));
    this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 6 + 168, I18n.format(SettingsManager.getColor() + "Back", new Object[0])));
  }
  
  public void keyTyped(char c, int i)
  {
    try
    {
      super.keyTyped(c, i);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    this.connectField.textboxKeyTyped(c, i);
  }
  
  public void mouseClicked(int i, int j, int k)
  {
    try
    {
      super.mouseClicked(i, j, k);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    this.connectField.mouseClicked(i, j, k);
  }
  
  public String func_175355_a(EnumDifficulty p_175355_1_)
  {
    ChatComponentText var2 = new ChatComponentText("");
    var2.appendSibling(new ChatComponentTranslation("options.difficulty", new Object[0]));
    var2.appendText(": ");
    var2.appendSibling(new ChatComponentTranslation(p_175355_1_.getDifficultyResourceKey(), new Object[0]));
    return var2.getFormattedText();
  }
  
  public void confirmClicked(boolean result, int id)
  {
    mc.displayGuiScreen(this);
    if ((id == 109) && (result) && (mc.theWorld != null))
    {
      mc.theWorld.getWorldInfo().setDifficultyLocked(true);
      this.field_175356_r.func_175229_b(true);
      this.field_175356_r.enabled = false;
      this.field_175357_i.enabled = false;
    }
  }
  
  protected void actionPerformed(GuiButton button)
    throws IOException
  {
    if (button.enabled)
    {
      if ((button.id < 100) && ((button instanceof GuiOptionButton)))
      {
        GameSettings.Options var2 = ((GuiOptionButton)button).returnEnumOptions();
        this.game_settings_1.setOptionValue(var2, 1);
        button.displayString = this.game_settings_1.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
      }
      if (button.id == 1) {
        if ((this.connectField.getText().equalsIgnoreCase("red")) || (this.connectField.getText().equalsIgnoreCase("yellow")) || (this.connectField.getText().equalsIgnoreCase("green")) || (this.connectField.getText().equalsIgnoreCase("aqua")) || (this.connectField.getText().equalsIgnoreCase("gold")) || (this.connectField.getText().equalsIgnoreCase("black")) || (this.connectField.getText().equalsIgnoreCase("reset")) || (this.connectField.getText().equalsIgnoreCase("white")) || (this.connectField.getText().equalsIgnoreCase("gray")) || (this.connectField.getText().equalsIgnoreCase("blue")) || (this.connectField.getText().equalsIgnoreCase("purple")))
        {
          SettingsManager.color.clear();
          SettingsManager.color.add(this.connectField.getText());
          Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§9Mod> §7Color set to " + this.connectField.getText() + "."));
        }
        else
        {
          Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§9Mod> §7The color was not found. Vaild colors: red,yellow,green,aqua,gold,black,reset,white,gray,blue,purple."));
        }
      }
      if (button.id == 108)
      {
        mc.theWorld.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum(mc.theWorld.getDifficulty().getDifficultyId() + 1));
        this.field_175357_i.displayString = func_175355_a(mc.theWorld.getDifficulty());
      }
      if (button.id == 109) {
        mc.displayGuiScreen(new GuiYesNo(this, new ChatComponentTranslation("difficulty.lock.title", new Object[0]).getFormattedText(), new ChatComponentTranslation("difficulty.lock.question", new Object[] { new ChatComponentTranslation(mc.theWorld.getWorldInfo().getDifficulty().getDifficultyResourceKey(), new Object[0]) }).getFormattedText(), 109));
      }
      if (button.id == 110)
      {
        mc.gameSettings.saveOptions();
        mc.displayGuiScreen(new GuiCustomizeSkin(this));
      }
      if (button.id == 8675309) {
        mc.entityRenderer.activateNextShader();
      }
      if (button.id == 101)
      {
        mc.gameSettings.saveOptions();
        mc.displayGuiScreen(new GuiVideoSettings(this, this.game_settings_1));
      }
      if (button.id == 100)
      {
        mc.gameSettings.saveOptions();
        mc.displayGuiScreen(new GuiControls(this, this.game_settings_1));
      }
      if (button.id == 102)
      {
        mc.gameSettings.saveOptions();
        mc.displayGuiScreen(new GuiLanguage(this, this.game_settings_1, mc.getLanguageManager()));
      }
      if (button.id == 103)
      {
        mc.gameSettings.saveOptions();
        mc.displayGuiScreen(new ScreenChatOptions(this, this.game_settings_1));
      }
      if (button.id == 104)
      {
        mc.gameSettings.saveOptions();
        mc.displayGuiScreen(new GuiSnooper(this, this.game_settings_1));
      }
      if (button.id == 2)
      {
        mc.gameSettings.saveOptions();
        mc.displayGuiScreen(this.field_146441_g);
      }
      if (button.id == 105)
      {
        mc.gameSettings.saveOptions();
        mc.displayGuiScreen(new GuiScreenResourcePacks(this));
      }
      if (button.id == 107)
      {
        mc.gameSettings.saveOptions();
        IStream var3 = mc.getTwitchStream();
        if ((var3.func_152936_l()) && (var3.func_152928_D())) {
          mc.displayGuiScreen(new GuiStreamOptions(this, this.game_settings_1));
        } else {
          GuiStreamUnavailable.func_152321_a(this);
        }
      }
    }
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks)
  {
    this.connectField.drawTextBox();
    drawDefaultBackground();
    drawCenteredString(this.fontRendererObj, this.field_146442_a, this.width / 2, 15, 16777215);
    super.drawScreen(mouseX, mouseY, partialTicks);
  }
}
