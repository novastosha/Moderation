package dev.nova.lm.methods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class Whitelisted implements Listener {
    private Punishments punishments = new Punishments();
    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        String host = event.getHostname();

        if(host.startsWith("whitelist")){

        }
        if(host.startsWith("events")){
            punishments.kick(player,"No events is running! Do not connect with this IP!",null,true);
        }
    }
}
