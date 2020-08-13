package dev.nova.lm;

import dev.nova.lm.methods.Tickets;
import dev.nova.lm.methods.Tracking;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;

public class GUIs implements Listener {
    private Plugin plugin = LegendaryModeration.getPlugin(LegendaryModeration.class);
        public HashMap<Player,Player> Typeingnote = new HashMap<>();
        private Tracking tracking = new Tracking();
        private Tickets tickets = new Tickets();

    public void openOptionsGUI(Player player, Player target){
        ArrayList<String> loren = new ArrayList<>();
        ArrayList<String> loret = new ArrayList<>();
        ArrayList<String> ticketlore = new ArrayList<>();

        Inventory inventory = Bukkit.createInventory(player, 54,"§3Options > "+target.getName());
        //13 Skull
        //20 Note
        //15 Ticket
        //30 Track
        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setOwner(target.getName());
        meta.setDisplayName("§3§l"+target.getName());
        stack.setItemMeta(meta);
        inventory.setItem(13, stack);

        ItemStack note = new ItemStack(Material.EMPTY_MAP);
        ItemMeta noteM = note.getItemMeta();
        loren.add(" ");
        loren.add("§cPLEASE KNOW: §7This note will only be visible to you!");
        noteM.setDisplayName("§aSet a note for: §l"+target.getName());
        noteM.setLore(loren);
        note.setItemMeta(noteM);
        inventory.setItem(20,note);

        ItemStack exit = new ItemStack(Material.WOODEN_DOOR);
        ItemMeta exitM = exit.getItemMeta();
        exitM.setDisplayName("§cExit");
        exitM.setLore(LegendaryModeration.loreE);
        exit.setItemMeta(exitM);
        inventory.setItem(8,exit);
        if(tickets.hasTicket(target)){
            ItemStack ticket = new ItemStack(Material.NAME_TAG);
            ItemMeta metaTicket = ticket.getItemMeta();
            ticketlore.add(" ");
            ticketlore.add("§7Left Click - §6Options");
            if(!plugin.getConfig().getBoolean("Tickets."+player.getUniqueId().toString()+".taken")) {
                ticketlore.add("§7Right Click - §6Accept");
            }
            metaTicket.setDisplayName("§6Ticket of: §l"+target.getName());
            metaTicket.setLore(ticketlore);
            ticket.setItemMeta(metaTicket);
            inventory.setItem(22,ticket);

        }
        if(tracking.getTrackers(target) != null) {
            if (!tracking.getTrackers(target).contains(player.getName())) {
                ItemStack track = new ItemStack(Material.REDSTONE_BLOCK);
                ItemMeta trackM = track.getItemMeta();
                loret.add(" ");
                loret.add("§7Suspicious player?");
                loret.add("  ");
                loret.add("§c§lTrack §7this player to get messages when they: ");
                loret.add("  ");
                loret.add("§c -Get an anticheat notification.");
                loret.add("§c -Mine an ore.");
                trackM.setDisplayName("§cTrack: §l" + target.getName());
                trackM.setLore(loret);
                track.setItemMeta(trackM);
                inventory.setItem(30, track);

            } else {
                ItemStack track = new ItemStack(Material.SLIME_BLOCK);
                ItemMeta trackM = track.getItemMeta();
                loret.add(" ");
                loret.add("§7No longer suspicious player?");
                loret.add("  ");
                loret.add("§a§lUnTrack §7this player to no longer get messages when they: ");
                loret.add("  ");
                loret.add("§a -Get an anticheat notification.");
                loret.add("§a -Mine an ore.");
                trackM.setDisplayName("§aUnTrack: §l" + target.getName());
                trackM.setLore(loret);
                track.setItemMeta(trackM);
                inventory.setItem(30, track);

            }
//        }else{
  //          ItemStack track = new ItemStack(Material.REDSTONE_BLOCK);
    //        ItemMeta trackM = track.getItemMeta();
      //      loret.add(" ");
        //    loret.add("§7Suspicious player?");
          //  loret.add("  ");
            //loret.add("§c§lTrack §7this player to get messages when they: ");
//            loret.add("  ");
  //          loret.add("§c -Get an anticheat notification.");
    //        loret.add("§c -Mine an ore.");
      //      trackM.setDisplayName("§cTrack: §l" + target.getName());
        //    trackM.setLore(loret);
          //  track.setItemMeta(trackM);
            //inventory.setItem(30, track);
        }

        player.openInventory(inventory);
    }

    public void openTicketsOptionsGUI(Player player, Player target){
        if(tickets.hasTicket(target)) {
            Inventory inventory = Bukkit.createInventory(player, 54, "§3Tickets > " + target.getName());
            int i = 0;
            ArrayList<String> lore = new ArrayList<>();

        }
    }

    public void openListPlayersGUI(Player player){
        Inventory inventory = Bukkit.createInventory(player, 54,"§3List Players");
        int i = 0;
        ArrayList<String> lore = new ArrayList<>();

            for(Player guiPlayer : Bukkit.getOnlinePlayers()) {
                //if (!players.contains(guiPlayer)) {

                    lore.add(" ");
                    if(guiPlayer.hasPermission("lm.staff")){
                        lore.add("§7Staff: §3Yes");
                    }else{
                        lore.add("§7Staff: §3No");
                    }
                    if(plugin.getConfig().contains("Notes."+player.getUniqueId().toString()+".Player."+guiPlayer.getUniqueId().toString())){
                        lore.add("§7Note: §3"+plugin.getConfig().get("Notes."+player.getUniqueId().toString()+".Player."+guiPlayer.getUniqueId().toString()));
                        lore.add(" ");
                    }else{
                        lore.add(" ");
                    }
                    lore.add("§7Left click - §3Options");
                    lore.add("§7Right click - §3Teleport");

                    ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta meta = (SkullMeta) stack.getItemMeta();
                    meta.setOwner(guiPlayer.getName());
                    meta.setDisplayName("§3§l"+guiPlayer.getName());
                    meta.setLore(lore);
                    stack.setItemMeta(meta);
                    inventory.setItem(i, stack);

              //  }
            }


        player.openInventory(inventory);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in

        if (inventory.getName().equals("§3List Players")) {
            event.setCancelled(true);
            if(clicked.getType().equals(Material.SKULL_ITEM)){
                if(event.isLeftClick()){

                    String l = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
                    Player player1 = Bukkit.getPlayer(l);
                    player.closeInventory();
                    openOptionsGUI(player,player1);
                }
                if(event.isRightClick()){

                    String l = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
                    Player player1 = Bukkit.getPlayer(l);
                    player.teleport(player1);
                    player.sendMessage(LegendaryModeration.prefix+"Teleported to: §3"+l);
                }
            }
        }
        if (inventory.getName().contains("§3Options > ")) {
            event.setCancelled(true);
            if(clicked.getType().equals(Material.EMPTY_MAP)) {
                String l = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
                l = l.replaceAll("Set a note for: ","");
                Player player1 = Bukkit.getPlayer(l);
                player.closeInventory();
                Typeingnote.put(player, player1);
                player.sendMessage(LegendaryModeration.prefix+"Type your note in the chat!");

            }
            if(clicked.getType().equals(Material.REDSTONE_BLOCK)) {
                String l = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
                l = l.replaceAll("Track: ","");
                Player player1 = Bukkit.getPlayer(l);
                player.closeInventory();
                player.performCommand("track "+player1.getName());

                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                openOptionsGUI(player,player1);
            }
            if(clicked.getType().equals(Material.SLIME_BLOCK)) {

                String l = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
                l = l.replaceAll("UnTrack: ","");
                Player player1 = Bukkit.getPlayer(l);
                player.closeInventory();
                player.performCommand("track "+player1.getName());

                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                openOptionsGUI(player,player1);
            }
            if (clicked.getType().equals(Material.NAME_TAG)){
                if(event.isRightClick()){

                    String l = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
                    l = l.replaceAll("Ticket of: ","");
                    Player player1 = Bukkit.getPlayer(l);
                    tickets.acceptTicket(player1,player);
                }
                if(event.isLeftClick()){
                    String l = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
                    l = l.replaceAll("Ticket of: ","");
                    Player player1 = Bukkit.getPlayer(l);
                    player.closeInventory();
                    openTicketsOptionsGUI(player,player1);
                }
            }
            }
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){

        if(Typeingnote.containsKey(event.getPlayer())){
            event.getPlayer().sendMessage(LegendaryModeration.prefix+"Set note for §3"+Typeingnote.get(event.getPlayer()).getName()+" §8to: §3"+event.getMessage());
            plugin.getConfig().set("Notes."+event.getPlayer().getUniqueId().toString()+".Player."+Typeingnote.get(event.getPlayer()).getUniqueId().toString(), event.getMessage());

            Typeingnote.remove(event.getPlayer());
            event.setCancelled(true);
        }
    }
}
