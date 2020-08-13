package dev.nova.lm.methods;

import dev.nova.lm.LegendaryModeration;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Tracking implements Listener {
    private Plugin plugin = LegendaryModeration.getPlugin(LegendaryModeration.class);

    public void addToTrackList(Player target, Player tracker) {
        List<String> tempListCHK = (List<String>) plugin.getConfig().get("LM.Trackers." + target.getUniqueId().toString());
        if (tempListCHK == null) {
            ArrayList<String> tempList = new ArrayList<String>();
            tempList.add(tracker.getName());
            plugin.getConfig().set("LM.Trackers." + target.getUniqueId().toString(),tempList);
        } else {
            tempListCHK.add(tracker.getName());
            plugin.getConfig().set("LM.Trackers." + target.getUniqueId().toString(),tempListCHK);
        }
        plugin.saveConfig();
    }
    public List<String> getTrackers(Player target){
        return (List<String>) plugin.getConfig().get("LM.Trackers." + target.getUniqueId().toString());
    }
    public void removeTracker(Player target, Player tracker) {

            List<String> tempList = (List<String>) plugin.getConfig().get(("LM.Trackers." + target.getUniqueId().toString()));
            if (tempList.contains(tracker.getName())) {
                tempList.remove(tracker.getName());
                plugin.getConfig().set("LM.Trackers." + target.getUniqueId().toString(), tempList);
                plugin.saveConfig();

            }
    }

    @EventHandler
    public void onMine(BlockBreakEvent blockBreakEvent){
        if(!blockBreakEvent.isCancelled()) {
            switch (blockBreakEvent.getBlock().getType().name()) {
                case "DIAMOND_ORE":
                    for (String tracker : getTrackers(blockBreakEvent.getPlayer())) {
                        Player tra = Bukkit.getPlayerExact(tracker);
                        tra.sendMessage(LegendaryModeration.prefix + "§3" +blockBreakEvent.getPlayer().getName()+" §8mined: §bDiamond");
                    }
                    break;
                case "GOLD_ORE":
                    for (String tracker : getTrackers(blockBreakEvent.getPlayer())) {
                        Player tra = Bukkit.getPlayerExact(tracker);
                        tra.sendMessage(LegendaryModeration.prefix + "§3" +blockBreakEvent.getPlayer().getName()+" §8mined: §eGold");
                    }
                    break;
                case "REDSTONE_ORE":
                    for (String tracker : getTrackers(blockBreakEvent.getPlayer())) {
                        Player tra = Bukkit.getPlayerExact(tracker);
                        tra.sendMessage(LegendaryModeration.prefix + "§3" +blockBreakEvent.getPlayer().getName()+" §8mined: §cRedstone");
                    }
                    break;
                case "QUARTZ_ORE":
                    for (String tracker : getTrackers(blockBreakEvent.getPlayer())) {
                        Player tra = Bukkit.getPlayerExact(tracker);
                        tra.sendMessage(LegendaryModeration.prefix + "§3" +blockBreakEvent.getPlayer().getName()+" §8mined: §FQuartz");
                    }
                    break;
                case "EMERALD_ORE":
                    for (String tracker : getTrackers(blockBreakEvent.getPlayer())) {
                        Player tra = Bukkit.getPlayerExact(tracker);
                        tra.sendMessage(LegendaryModeration.prefix + "§3" +blockBreakEvent.getPlayer().getName()+" §8mined: §aEmerald");
                    }
                    break;
                case "LAPIS_ORE":
                    for (String tracker : getTrackers(blockBreakEvent.getPlayer())) {
                        Player tra = Bukkit.getPlayerExact(tracker);
                        tra.sendMessage(LegendaryModeration.prefix + "§3" +blockBreakEvent.getPlayer().getName()+" §8mined: §9Lapis");
                    }
                    break;
                case "IRON_ORE":
                    for (String tracker : getTrackers(blockBreakEvent.getPlayer())) {
                        Player tra = Bukkit.getPlayerExact(tracker);
                        tra.sendMessage(LegendaryModeration.prefix + "§3" +blockBreakEvent.getPlayer().getName()+" §8mined: §7Iron");
                    }
                    break;
                case "COAL_ORE":
                    for (String tracker : getTrackers(blockBreakEvent.getPlayer())) {
                        Player tra = Bukkit.getPlayerExact(tracker);

                        tra.sendMessage(LegendaryModeration.prefix + "§3" +blockBreakEvent.getPlayer().getName()+" §8mined: §0Coal ");
                    }
                    break;
            }

        }
    }
}
