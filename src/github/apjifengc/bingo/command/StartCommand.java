package github.apjifengc.bingo.command;

import github.apjifengc.bingo.Bingo;
import github.apjifengc.bingo.exception.BadTaskException;
import github.apjifengc.bingo.game.BingoGame;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class StartCommand {
	void onStartCommand(CommandSender sender, Bingo plugin) {
		if (sender.hasPermission("bingo.admin.start")) {
			if (!plugin.hasBingoGame()) {
				BingoGame game = new BingoGame();
				try {
					game.generateTasks();
				} catch (IOException | InvalidConfigurationException | BadTaskException e) {
					e.printStackTrace();
					sender.sendMessage("§c§l Bingo §9§l▌ §cUnable to start a game: " + e.getMessage());
					return;
				}
				plugin.setCurrentGame(game);
				Bukkit.broadcastMessage(
						"§9§l-==============  §c§lBingo§9§l  ==============-\n  §aA Bingo game has started!\n  §eUse command §7§n/bingo join§e to join the game!");
			} else {
				sender.sendMessage("§c§l Bingo §9§l▌ §cThere is already a game running!");
			}
		} else {
			sender.sendMessage("§c§l Bingo §9§l▌ §cYou don't have the permission for this command.");
		}
	}
}
