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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EchosShittySkyBlockMod implements ClientModInitializer {
	public static final dev.exhq.ESSMConfig CONFIG = dev.exhq.ESSMConfig.createAndLoad();
	public static final String MOD_ID = "essm";
	public static boolean shouldShowskill;
	public static String PurseString;
	public static String coinLogo;
	public static String skillInfo;
	public class RegexSubstringMatcher {

		public static String findMatchingSubstring(String input, String regexPattern) {
			Pattern pattern = Pattern.compile(regexPattern);
			Matcher matcher = pattern.matcher(input);

			if (matcher.find()) {
				return matcher.group();
			}

			return "";
		}
	}
	@Override
	public void onInitializeClient() {

		ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
			if (overlay){
				var noColor = message.getString().replaceAll("§[a-f0-9]", "");

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
					PurseString = RegexSubstringMatcher.findMatchingSubstring(i.getString().replaceAll("§.", "") , "Purse: ([0-9.,]+)");
					if (!((PurseString) == "")){
						coinLogo = PurseString.replaceAll("Purse: ", "")+" ⛃";
					}
				}

				var health = Objects.requireNonNull(RegexSubstringMatcher.findMatchingSubstring(noColor, "[0-9,]+/[0-9,]+❤")).replaceAll(",", "").replaceAll("❤", "").split("/");

				var mana = Objects.requireNonNull(RegexSubstringMatcher.findMatchingSubstring(noColor, "[0-9,]+/[0-9,]+✎")).replaceAll(",", "").replaceAll("✎", "").split("/");

				var usedMana = RegexSubstringMatcher.findMatchingSubstring(noColor, "(\\+|-)[1-9]+ Mana \\([^)]+\\)");

				if (!usedMana.isEmpty()){
					ManaUsage.showMana = true;
					ManaUsage.ManaUsage = RegexSubstringMatcher.findMatchingSubstring(usedMana, "(\\+|-)[1-9]+");
				} else {
					ManaUsage.showMana = false;
				}



				var GetSkill = RegexSubstringMatcher.findMatchingSubstring(noColor, "\\([0-9,k]+/[0-9,k]+\\)");
				if (!GetSkill.isEmpty()){
					shouldShowskill = true;
					var GetSkillPercentageRatio = (GetSkill.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(",", "").replaceAll("k", "000"));
					var Skillarray = GetSkillPercentageRatio.split("/");
					Trolley.skillprogress = (Integer.parseInt(Skillarray[0]) * 128 ) / (Integer.parseInt(Skillarray[1]));
					Trolley.skillprogressPercentage = (Integer.parseInt(Skillarray[0]) * 100 ) / (Integer.parseInt(Skillarray[1]));
					Trolley.Skilltext = RegexSubstringMatcher.findMatchingSubstring(noColor, "\\+[0-9]+.?[0-9] [a-zA-Z]+");
				} else {
					shouldShowskill = false;
				}
				Trolley.healthprogress = (Integer.parseInt(health[0]) * 128 ) / (Integer.parseInt(health[1]));

				Trolley.manaprogress = (Integer.parseInt(mana[0]) * 128 ) / (Integer.parseInt(mana[1]));


			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			var player = MinecraftClient.getInstance().player;

			if (player == null){
				return;
			}
		});

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(literal("dn").executes(context -> {
				context.getSource().sendFeedback(Text.literal("this is an neu reference"));
				MinecraftClient.getInstance().player.networkHandler.sendCommand("warp dungeon_hub");
				return 0;
			}));
		});
		HudRenderCallback.EVENT.register(new Trolley());
		HudRenderCallback.EVENT.register(new ManaUsage());
		HudRenderCallback.EVENT.register(new ScoreboardInfo());
		HudRenderCallback.EVENT.register((maxtrixStack, tickDelta) -> {

		});


		ClientReceiveMessageEvents.ALLOW_GAME.register((message, overlay) -> {
            return true;
		});

		//ClientReceiveMessageEvents.MODIFY_GAME.register((message, overlay) -> {
		//	if(overlay) return message;
		//	return
		//});
	}
}