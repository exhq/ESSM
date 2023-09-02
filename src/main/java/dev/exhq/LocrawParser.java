package dev.exhq;
import com.google.gson.Gson;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;


public class LocrawParser implements ClientReceiveMessageEvents.AllowGame {
    public static LocrawData data;
    @Override
    public boolean allowReceiveGameMessage(Text message, boolean overlay) {
        if (message.getString().startsWith("{\"server\":")){
            String jsonString = message.getString();
            Gson gson = new Gson();
            data = gson.fromJson(jsonString, LocrawData.class);
            return false;
        }
        return true;
    }



}
