package dev.nova.lm.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(!event.isCancelled()){
            if(event.getMessage().startsWith("#")){
                if(event.getPlayer().hasPermission("lm.staff" )){
                    event.setCancelled(true);
                    for(Player staff : Bukkit.getOnlinePlayers()) {
                        if(staff.hasPermission("lm.staff")){
                            String msg = event.getMessage();
                            msg = msg.replaceAll("#","");
                            staff.sendMessage("§b[STAFF] §3"+event.getPlayer().getName()+"§f: "+msg);
                        }
                    }

                }
            }
        }
    }
}
