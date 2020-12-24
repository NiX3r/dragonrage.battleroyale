package cz.nixdevelopment.br.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import cz.nixdevelopment.br.BattleRoyale;
import cz.nixdevelopment.br.utils.GameUtil;
import cz.nixdevelopment.br.utils.Messages;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldBorder;

public class PhaseScheduler {

    private static int taskID;

    public static void CountDown() {
        
        
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(BattleRoyale.inst, new Runnable() {

            int phase = BattleRoyale.PhaseCounter();
            int counter = BattleRoyale.TimeBetweenPhase();
            int help = 0;
            
            @Override
            public void run() {
                
                counter--;
                help = BattleRoyale.SetSecToNextPhase(counter);
                
                if(!BattleRoyale.GameInfo().IsGameActive())
                    StopWatch();
                
                if(counter == 0) {
                    
                    counter = BattleRoyale.TimeBetweenPhase();
                    if(BattleRoyale.GameInfo().IsGameActive()) {
                        
                        phase--;
                        
                        if(phase == 0) {
                            StopWatch();
                        }
                        else {
                            
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                
                                if(p.getLocation().getWorld().getName().equals(BattleRoyale.GetActualMap().GetWorld())) {

                                    Boolean isSended = false;
                                    
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set minecraft.command.worldborder");
                                    
                                    while(!isSended) {
                                        if(p.hasPermission("minecraft.command.worldborder"))
                                            isSended = p.performCommand("worldborder add " + ((700 / BattleRoyale.PhaseCounter()) * -1) + " 30");
                                    }

                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset minecraft.command.worldborder");
                                    
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
        
        },1L, 20L);
        
    }
    
    private static void StopWatch() {
        
        Bukkit.getScheduler().cancelTask(taskID);
        
    }
    
}
