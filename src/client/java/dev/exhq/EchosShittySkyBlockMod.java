package dev.exhq;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Collections;

public class EchosShittySkyBlockMod implements ClientModInitializer {
	public static final String MOD_ID = "essm";
	@Override
	public void onInitializeClient() {

		ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
			if (overlay){
				var scoreboard = MinecraftClient.getInstance().world.getScoreboard();
				var activeObjective = scoreboard.getObjectiveForSlot(Scoreboard.SIDEBAR_DISPLAY_SLOT_ID);
				var actualScoreboardContent = new ArrayList<Text>();
				if (activeObjective == null){
					return;
				}
				for (ScoreboardPlayerScore playerScore : scoreboard.getAllPlayerScores(activeObjective)) {
					var team = scoreboard.getPlayerTeam(playerScore.getPlayerName());
					actualScoreboardContent.add(Team.decorateName(team, Text.literal(playerScore.getPlayerName())));
				}
				Collections.reverse(actualScoreboardContent);
				for (Text i : actualScoreboardContent) {
					System.out.println(i.getString().replaceAll("§[^a-f0-9]", ""));
				}
				System.out.println(message.getString());
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			var player = MinecraftClient.getInstance().player;

			if (player == null){
				return;
			}

			Trolley.progress = (int)(player.getHealth()/player.getMaxHealth()*128);
		});

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(literal("trolgessbar")
					.then(argument("progress", IntegerArgumentType.integer(0,128)).executes(context -> {
						int progress = IntegerArgumentType.getInteger(context, "progress");
						Trolley.progress=progress;
						context.getSource().sendFeedback(Text.literal(":trolley:"));
						return 0;
					})));
		});
		HudRenderCallback.EVENT.register(new Trolley());
		HudRenderCallback.EVENT.register((maxtrixStack, tickDelta) -> {

		});


		ClientReceiveMessageEvents.ALLOW_GAME.register((message, overlay) -> {
			return true;
		});

		ClientReceiveMessageEvents.MODIFY_GAME.register((message, overlay) -> {
			return message;
		});
	}
}