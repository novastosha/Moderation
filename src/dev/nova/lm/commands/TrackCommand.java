package dev.nova.lm.commands;

import dev.nova.lm.GUIs;
import dev.nova.lm.LegendaryModeration;
import dev.nova.lm.methods.Tracking;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TrackCommand implements CommandExecutor {
    public String cmd = "track";

    private Tracking tracking = new Tracking();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase(cmd)) {

            if (player.hasPermission("lm.track")) {
                if (strings.length == 0) {
                    player.sendMessage("§cPlease select a player");

                } else {
                    Player t = Bukkit.getPlayer(strings[0]);
                    if(tracking.getTrackers(t) == null){
                        tracking.addToTrackList(t,player);
                        return true;
                    }
                    if (tracking.getTrackers(t).contains(player.getName())) {
                        tracking.removeTracker(t,player);
                        player.sendMessage(LegendaryModeration.prefix+"Removed: §3"+t.getName()+" §8from your tracking list!");

                    } else {
                        tracking.addToTrackList(t,player);
                        player.sendMessage(LegendaryModeration.prefix+"Added: §3"+t.getName()+" §8to your tracking list!");
                    }
                    return true;
                }
            } else {
                player.sendMessage("§CYou don't have permission!");
                return true;
            }
        }
        return true;
    }
}
