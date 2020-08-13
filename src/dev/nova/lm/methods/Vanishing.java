package dev.nova.lm.methods;

import dev.nova.lm.LegendaryModeration;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Vanishing {


    public static ArrayList<Player> vanished = new ArrayList<>();
    private Plugin plugin = LegendaryModeration.getPlugin(LegendaryModeration.class);

    public void vanish(Player player, @Nullable Player target) {
        if (target == null) {
            if (!vanished.contains(player)) {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    player1.hidePlayer(plugin, player);
                }
                vanished.add(player);
                player.sendMessage(LegendaryModeration.prefix + "You are vanished!");
            } else {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    player1.showPlayer(plugin, player);
                }
                vanished.remove(player);
                player.sendMessage(LegendaryModeration.prefix + "You are unvanished!");
            }

        }else{
            if (!vanished.contains(target)) {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    player1.hidePlayer(plugin, target);
                }
                vanished.add(target);
                player.sendMessage(LegendaryModeration.prefix + "Vanished: §3"+target.getName());
                target.sendMessage(LegendaryModeration.prefix + "You are vanished!");
            } else {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    player1.showPlayer(plugin, target);
                }
                vanished.remove(target);
                player.sendMessage(LegendaryModeration.prefix + "Unvanished: §3"+target.getName());
                target.sendMessage(LegendaryModeration.prefix + "You are unvanished!");
            }
        }
    }
}
