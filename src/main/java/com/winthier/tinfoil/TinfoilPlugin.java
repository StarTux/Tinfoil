package com.winthier.tinfoil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.RED;
import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;

public final class TinfoilPlugin extends JavaPlugin {
    @Override
    public boolean onCommand(CommandSender sender,
                             Command command, String label, String[] args) {
        if (args.length < 2) return false;
        final Player player = getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(text("Player not found: " + args[0], RED));
            return true;
        }
        StringBuilder sb = new StringBuilder(args[1]);
        for (int i = 2; i < args.length; ++i) {
            sb.append(" ").append(args[i]);
        }
        String commandLine = sb.toString();
        getServer().getScheduler().runTask(this, () -> {
                player.performCommand(commandLine);
            });
        getLogger().info(sender.getName() + " made " + player.getName() + " type: /" + commandLine);
        sender.sendMessage(text("Made " + player.getName() + " type: /" + commandLine, YELLOW));
        return true;
    }
}
