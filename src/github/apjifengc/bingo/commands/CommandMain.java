/**
 * Command /bingo start
 * @author APJifengc
 */
package github.apjifengc.bingo.commands;

import github.apjifengc.bingo.Bingo;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandMain implements TabExecutor {

	private final Bingo plugin;

	public CommandMain(Bingo plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bingo")) {
			if (args.length == 0) {
				new Cmd_help().onHelpCommand(sender);
			} else {
				if (args[0].equalsIgnoreCase("help")) {
					new Cmd_help().onHelpCommand(sender);
				}
			}
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bingo")) {
			if (args.length == 1) {
				return getSubCommands(sender).stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
			}
		}
		return Arrays.asList();
	}

	public List<String> getSubCommands(CommandSender sender) {
		List<String> sub = new ArrayList<String>();
		if (sender.hasPermission("bingo.use.gui"))
			sub.add("gui");
		if (sender.hasPermission("bingo.use.join"))
			sub.add("join");
		if (sender.hasPermission("bingo.use.leave"))
			sub.add("leave");
		if (sender.hasPermission("bingo.admin.start"))
			sub.add("start");
		if (sender.hasPermission("bingo.admin.stop"))
			sub.add("stop");
		if (sender.hasPermission("bingo.admin.setting"))
			sub.add("setting");
		return sub;
	}
}
