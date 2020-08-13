package dev.nova.lm.methods;

import dev.nova.lm.LegendaryModeration;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.plugin.Plugin;

public class SpecSaveLoc implements Listener {
    private Plugin plugin = LegendaryModeration.getPlugin(LegendaryModeration.class);

    @EventHandler
    public void playerSwitchGameMode(PlayerGameModeChangeEvent event) {
        GameMode gameMode = event.getNewGameMode();
        if (plugin.getConfig().getBoolean("SpL."+event.getPlayer().getUniqueId().toString()+".enabled")) {
            if (gameMode.equals(GameMode.SPECTATOR)) {

                plugin.getConfig().set("SpB." + event.getPlayer().getName(), event.getPlayer().getLocation());

                event.getPlayer().sendMessage("§3Saved your location if you want to teleport back");
            }

            if (!gameMode.equals(GameMode.SPECTATOR)) {

                if (plugin.getConfig().contains("SpB." + event.getPlayer().getName())) {
                    Location location = (Location) plugin.getConfig().get("SpB." + event.getPlayer().getName());
                    event.getPlayer().teleport(location);
                    plugin.getConfig().set("SpB." + event.getPlayer().getName(), null);
                    event.getPlayer().sendMessage("§3You got teleported back to your location!");
                }
            }
        }
    }
}
