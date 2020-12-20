package cz.nixdevelopment.br.schedulers;

import org.bukkit.Bukkit;

import cz.nixdevelopment.br.BattleRoyale;

public class DropGodScheduler {

    public static void DropGod() {
        
        Bukkit.getScheduler().scheduleSyncDelayedTask(BattleRoyale.inst, new Runnable() {

            @Override
            public void run() {
                
                if(BattleRoyale.GameInfo().IsGameActive()) {
                    
                    BattleRoyale.SetPlayersGod(false);
                    
                }
                
            }
            
        }, 20 * 15);
        
    }
    
}
