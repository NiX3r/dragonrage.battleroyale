package cz.nixdevelopment.br.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import cz.nixdevelopment.br.BattleRoyale;
import cz.nixdevelopment.br.utils.GameUtil;
import cz.nixdevelopment.br.utils.Messages;

public class PhaseScheduler {

    private static int taskID;

    public static void CountDown() {
        
        
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(BattleRoyale.inst, new Runnable() {

            int phase = BattleRoyale.PhaseCounter() +1;
            
            @Override
            public void run() {
                
                if(phase == (BattleRoyale.PhaseCounter()+1)) {
                    phase--;
                }else {
                    if(BattleRoyale.GameInfo().IsGameActive()) {
                        
                        phase--;
                        
                        if(phase == 0) {
                            StopWatch();
                        }
                        else {
                            
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                
                                if(p.getLocation().getWorld().getName().equals(BattleRoyale.GetActualMap().GetWorld())) {
                                    
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + p.getName());
                                    Bukkit.dispatchCommand(p, "worldborder add " + ((700 / BattleRoyale.PhaseCounter()) * -1) + " 30");
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "deop " + p.getName());
                                    
                                    Bukkit.broadcastMessage(Messages.PhaseMove());
                                    
                                    break;
                                    
                                }
                                
                            }
                            
                        }
                        
                    }
                    else {
                        StopWatch();
                    }
                }
                           
            }
        
        },1L, 20L * BattleRoyale.TimeBetweenPhase());
        
    }
    
    private static void StopWatch() {
        
        Bukkit.getScheduler().cancelTask(taskID);
        
    }
    
}
