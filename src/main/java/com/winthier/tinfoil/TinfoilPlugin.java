package com.winthier.tinfoil;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TinfoilPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if (args.length < 2) return false;
        final Player player = getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("" + ChatColor.RED + "Player not found: " + args[0]);
            return true;
        }
        StringBuilder sb = new StringBuilder(args[1]);
        for (int i = 2; i < args.length; ++i) {
            sb.append(" ").append(args[i]);
        }
        String commandLine = sb.toString();
        new BukkitRunnable() {
            @Override public void run() {
                player.performCommand(commandLine);
            }
        }.runTask(this);
        getLogger().info(sender.getName() + " made " + player.getName() + " type: /" + commandLine);
        sender.sendMessage("" + ChatColor.YELLOW + "Made " + player.getName() + " type: /" + commandLine);
        return true;
    }
}
