package dev.exhq;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.ExcludeFromScreen;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "essm")
@Config(name = "ESSMConfig", wrapperName = "ESSMConfig")
public class ESSMConfigModel {

    public boolean DisableActionbar = false;
    public boolean disableVanillaHealthbar = false;

    @ExcludeFromScreen
    public ESSMhud.PosData Heart = new ESSMhud.PosData(1,.95,1);

    @ExcludeFromScreen
    public ESSMhud.PosData XP = new ESSMhud.PosData(1, 0.943,1);

    @ExcludeFromScreen
    public ESSMhud.PosData Health = new ESSMhud.PosData(1,0.01,1.6f);

    @ExcludeFromScreen
    public ESSMhud.PosData Mana = new ESSMhud.PosData(1,0.07,1.6f);

    @ExcludeFromScreen
    public ESSMhud.PosData UsedMana = new ESSMhud.PosData(0.04, 1,1);

    @ExcludeFromScreen
    public ESSMhud.PosData PurseAmount = new ESSMhud.PosData(0.003, 1, 1);

    @ExcludeFromScreen
    public ESSMhud.PosData Hotbar = new ESSMhud.PosData(1,1,1);


}
