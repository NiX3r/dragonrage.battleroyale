package cz.nixdevelopment.br.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import cz.nixdevelopment.br.BattleRoyale;
import cz.nixdevelopment.br.utils.GameUtil;
import cz.nixdevelopment.br.utils.Messages;

public class OnDeathListener implements Listener {

    //EntityDeathEvent
    @EventHandler(priority = EventPriority.LOWEST)
    public static void OnJoinEvent(EntityDeathEvent event) {
        
        if(event.getEntity() instanceof Player) {
            
            if(BattleRoyale.GetGamePlayers().GetPlayer(event.getEntity().getUniqueId()).IsAlive()) {
                
                if(event.getEntity().getKiller() != null) {
                    BattleRoyale.GetGamePlayers().GetPlayer(event.getEntity().getKiller().getUniqueId()).IncreaseKills();
                    Bukkit.broadcastMessage(Messages.PlayerDieByPlayer(event.getEntity().getName(), event.getEntity().getKiller().getName()));
                }
                else {
                    Bukkit.broadcastMessage(Messages.PlayerDie(event.getEntity().getName()));
                }
                
                BattleRoyale.GetGamePlayers().GetPlayer(event.getEntity().getUniqueId()).IncreaseDeaths();
                BattleRoyale.GetGamePlayers().GetPlayer(event.getEntity().getUniqueId()).IncreaseLoses();
                BattleRoyale.GetGamePlayers().GetPlayer(event.getEntity().getUniqueId()).PlayerDied();
                BattleRoyale.GetGamePlayers().ResetInventory(event.getEntity().getName());
                BattleRoyale.GameInfo().DecrementLeft();
                
                if(BattleRoyale.GameInfo().GetLeft() == 1) {
                    
                    GameUtil.EndGame();
                    
                }
                
            }
            
            
        }
        
    }
    
}
