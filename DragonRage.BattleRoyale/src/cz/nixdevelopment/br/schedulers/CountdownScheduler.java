package cz.nixdevelopment.br.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import cz.nixdevelopment.br.BattleRoyale;
import cz.nixdevelopment.br.utils.GameUtil;

public class CountdownScheduler {

    private static int taskID;
    
    public static void CountDown() {
        
        
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(BattleRoyale.inst, new Runnable() {

            int index = 60;
            
            @Override
            public void run() {
                
                if(BattleRoyale.GameInfo().IsGameReady()) {
                    
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        p.setLevel(index);
                    }
                    
                    if(index == 0) {
                        
                        StopWatch();
                        
                    }
                    else {
                        
                        if(index == 30) {
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                p.sendTitle("§5§lHra zacne za", "§530 sekund");
                            }
                        }
                        if(index == 10) {
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                p.sendTitle("§5§lHra zacne za", "§510 sekund");
                            }
                        }
                        if (index == 3) {
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                p.sendTitle("§4§lHra zacne za", "§c3 sekund");
                            }
                        }
                        if(index == 2) {
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                p.sendTitle("§6§lHra zacne za", "§e2 sekund");
                            }
                        }
                        if(index == 1) {
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                p.sendTitle("§2§lHra zacne za", "§a1 sekund");
                            }
                        }
                        
                        index--;
                        
                    }
                }
                else {
                    StopWatch();
                }
                           
            }
        
        },1L, 20L);
        
    }
    
    private static void StopWatch() {
        
        Bukkit.getScheduler().cancelTask(taskID);
        if(BattleRoyale.GameInfo().IsGameReady()) {
            GameUtil.ForceStartGame();
        }
        else {
            GameUtil.StopCountDown();
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.setLevel(0);
            }
        }
        
    }
    
}
