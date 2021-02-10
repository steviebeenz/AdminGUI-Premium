package com.rabbitcompany.admingui.listeners;

import com.rabbitcompany.admingui.AdminGUI;
import com.rabbitcompany.admingui.ui.AdminUI;
import com.rabbitcompany.admingui.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    private AdminGUI adminGUI;

    public PlayerDamageListener(AdminGUI plugin){
        adminGUI = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event){

        if(Bukkit.getVersion().contains("1.8")){
            if(event.getEntity() instanceof Player){
                Player p = (Player) event.getEntity();
                if(Settings.god.getOrDefault(p.getUniqueId(), false)) event.setCancelled(true);
            }
        }
    }
}
