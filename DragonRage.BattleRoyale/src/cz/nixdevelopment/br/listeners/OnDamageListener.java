package cz.nixdevelopment.br.listeners;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import cz.nixdevelopment.br.BattleRoyale;

public class OnDamageListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public static void OnDamageEvent(EntityDamageEvent event) {
        
        if(event.getEntity() instanceof Player && BattleRoyale.GameInfo().IsGameActive() && BattleRoyale.IsPlayersGod() && event.getCause().equals(DamageCause.FALL)) {
            
            event.setCancelled(true);
            
        }
        
        if(event.getEntity() instanceof Player && BattleRoyale.GameInfo().IsGameActive()) {
            
            event.getEntity().getWorld().spawnParticle(Particle.HEART, event.getEntity().getLocation(), 5);
            
        }
        
    }
    
}
