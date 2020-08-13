package dev.nova.lm.methods;

import dev.nova.lm.LegendaryModeration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.util.Date;

public class Tickets implements Listener {
    private Plugin plugin = LegendaryModeration.getPlugin(LegendaryModeration.class);

    public void createTicket(Player player,@Nullable String reason){
        if(!plugin.getConfig().contains("Tickets."+player.getUniqueId().toString())){

            plugin.getConfig().set("Tickets."+player.getUniqueId().toString(),"");
            if(reason != null){
                plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".reason",reason);
            }
            plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".date",new Date());
            plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".owner",player.getName());
            plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".taken",false);
            plugin.saveConfig();

            player.sendMessage("§a[TICKETS]§8 Created your ticket! §aPlease until an admin help you!");
        }else{
            player.sendMessage("§cYou can't create multiple tickets!");
        }
    }

    public void acceptTicket(Player player, Player admin){
        if(plugin.getConfig().contains("Tickets."+player.getUniqueId().toString())){
            if(!plugin.getConfig().getBoolean("Tickets."+player.getUniqueId().toString()+".taken")) {
                player.sendMessage("§a[TICKETS]§8 Ticket accepted! §aAccepted by: "+admin.getName());
                admin.sendMessage("§a[TICKETS]§8 Ticket accepted!");
                plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".taken",true);
                plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".accepted-by",admin.getName());
                plugin.saveConfig();

            }else{admin.sendMessage("§cThis ticket is taken!");}
        }else{
            admin.sendMessage("§cCould not find that ticket!");
        }
    }
   public void closeTicket(Player player, Player admin){
       if(plugin.getConfig().contains("Tickets."+player.getUniqueId().toString())){
           if(plugin.getConfig().getBoolean("Tickets."+player.getUniqueId().toString()+".taken")) {
               if(admin.getName().equalsIgnoreCase(plugin.getConfig().getString("Tickets."+player.getUniqueId().toString()+".accepted-by"))){
                   plugin.getConfig().set("Tickets."+player.getUniqueId().toString(),null);
                   plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".reason",null);

                   plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".date",null);
                   plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".owner",null);
                   plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".taken",null);
                   plugin.saveConfig();

                   admin.sendMessage("§a[TICKETS]§8 Closed: "+player.getName()+"'s ticket!");
                   player.sendMessage("§a[TICKETS]§8 Your ticket got closed! §aHope its solved!");
               }

           }else{admin.sendMessage("§cThis ticket is not taken!");}
       }else{
           admin.sendMessage("§cCould not find that ticket!");
       }
   }
    public void rejectTicket(Player player, Player admin, @Nullable String reason){
        if(plugin.getConfig().contains("Tickets."+player.getUniqueId().toString())){
            if(!plugin.getConfig().getBoolean("Tickets."+player.getUniqueId().toString()+".taken")) {
                if(reason == null) {
                    player.sendMessage("§a[TICKETS]§8 Sorry but your ticket got rejected by: §a"+admin.getName());
                }else{
                    player.sendMessage("§a[TICKETS]§8 Ticket got rejected by: §a"+admin.getName()+" §8Reason: "+reason);
                }
                plugin.getConfig().set("Tickets."+player.getUniqueId().toString(),null);
                plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".reason",null);

                plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".date",null);
                plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".owner",null);
                plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".taken",null);
                plugin.saveConfig();
                admin.sendMessage("§a[TICKETS]§8 Rejected: "+player.getName()+"'s ticket!");
            }else{admin.sendMessage("§cThis ticket is taken!");}
        }else{
            admin.sendMessage("§cCould not find that ticket!");
        }
    }
    public void deleteTicket(Player player) {
        if (plugin.getConfig().contains("Tickets." + player.getUniqueId().toString())) {
                if(plugin.getConfig().getBoolean("Tickets."+player.getUniqueId().toString()+".taken")) {
                    plugin.getConfig().set("Tickets." + player.getUniqueId().toString(), null);
                    plugin.getConfig().set("Tickets." + player.getUniqueId().toString() + ".reason", null);

                    plugin.getConfig().set("Tickets." + player.getUniqueId().toString() + ".date", null);
                    plugin.getConfig().set("Tickets." + player.getUniqueId().toString() + ".owner", null);
                    plugin.getConfig().set("Tickets." + player.getUniqueId().toString() + ".taken", null);
                    if(plugin.getConfig().getString("Tickets."+player.getUniqueId().toString()+".accepted-by") != null){
                        Player admin = Bukkit.getPlayer(plugin.getConfig().getString("Tickets." + player.getUniqueId().toString() + ".accepted-by"));
                        admin.sendMessage("§a[TICKETS] "+player.getName()+"§8 deleted their ticket!");
                    }
                    plugin.getConfig().set("Tickets."+player.getUniqueId().toString()+".accepted-by",null);
                    plugin.saveConfig();

                    player.sendMessage("§a[TICKETS]§8 Deleted your ticket!");
          }
         }
    }
    public boolean hasTicket(Player player){
        if(plugin.getConfig().contains("Tickets."+player.getUniqueId().toString())){
            return true;
        }else{return false;}
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(plugin.getConfig().contains("Tickets."+player.getUniqueId().toString())){
            if(plugin.getConfig().getBoolean("Tickets."+player.getUniqueId().toString()+".taken")) {
                event.setCancelled(true);
                Player admin = Bukkit.getPlayer(plugin.getConfig().getString("Tickets."+player.getUniqueId().toString()+".accepted-by"));
                player.sendMessage("§a"+player.getName()+"§8: "+event.getMessage());
                admin.sendMessage("§a"+player.getName()+"§8: "+event.getMessage());
            }
            }

    }
    @EventHandler
    public void onChat2(AsyncPlayerChatEvent event){
        for(Player player : Bukkit.getOnlinePlayers()) {
            if (plugin.getConfig().contains("Tickets." + player.getUniqueId().toString())) {
                if (plugin.getConfig().getBoolean("Tickets." + player.getUniqueId().toString() + ".taken")) {
                    event.setCancelled(true);
                    Player admin = Bukkit.getPlayer(plugin.getConfig().getString("Tickets." + player.getUniqueId().toString() + ".accepted-by"));
                    if (admin.getName().equalsIgnoreCase(event.getPlayer().getName())) {
                        event.getPlayer().sendMessage("§a" + admin.getName() + "§8: " + event.getMessage());
                        player.sendMessage("§a" + admin.getName() + "§8: " + event.getMessage());
                    }
                }
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(plugin.getConfig().contains("Tickets."+player.getUniqueId().toString())){
            deleteTicket(player);
        }
    }
}
