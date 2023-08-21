package dev.exhq;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.ExcludeFromScreen;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "essm")
@Config(name = "ESSMConfig", wrapperName = "ESSMConfig")
public class ESSMConfigModel {

    public boolean VanillaHeartToggle = false;

    @ExcludeFromScreen
    public ESSMhud.PosData Heart = new ESSMhud.PosData();
    public ESSMhud.PosData XP = new ESSMhud.PosData();

    public ESSMhud.PosData Health = new ESSMhud.PosData();

    public ESSMhud.PosData Mana = new ESSMhud.PosData();
    public ESSMhud.PosData UsedMana = new ESSMhud.PosData();



}
