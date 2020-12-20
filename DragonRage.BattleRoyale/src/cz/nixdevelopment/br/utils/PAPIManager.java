package cz.nixdevelopment.br.utils;

import org.bukkit.entity.Player;

import cz.nixdevelopment.br.BattleRoyale;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PAPIManager extends PlaceholderExpansion{

	@Override
    public String getAuthor() {
        return "NiX3r";
    }
 
    @Override
    public String getIdentifier() {
        return "br";
    }
 
    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getPlugin() {
        // TODO Auto-generated method stub
        return BattleRoyale.inst.getName();
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        
        if(identifier.equals("kills")) {
            return String.valueOf(BattleRoyale.GetGamePlayers().GetPlayer(p.getUniqueId()).GetKills());
        }
        else if(identifier.equals("deaths")) {
            return String.valueOf(BattleRoyale.GetGamePlayers().GetPlayer(p.getUniqueId()).GetDeaths());
        }
        else if(identifier.equals("kdratio")) {
            return String.valueOf((double) BattleRoyale.GetGamePlayers().GetPlayer(p.getUniqueId()).GetKills() / (double) BattleRoyale.GetGamePlayers().GetPlayer(p.getUniqueId()).GetKills());
        }
        else if(identifier.equals("wins")) {
            return String.valueOf(BattleRoyale.GetGamePlayers().GetPlayer(p.getUniqueId()).GetWins());
        }
        else if(identifier.equals("loses")) {
            return String.valueOf(BattleRoyale.GetGamePlayers().GetPlayer(p.getUniqueId()).GetLoses());
        }
        else if(identifier.equals("wlratio")) {
            return String.valueOf((double) BattleRoyale.GetGamePlayers().GetPlayer(p.getUniqueId()).GetWins() / (double) BattleRoyale.GetGamePlayers().GetPlayer(p.getUniqueId()).GetLoses());
        }
        else if(identifier.equals("plays")) {
            return String.valueOf(BattleRoyale.GetGamePlayers().GetPlayer(p.getUniqueId()).GetPlays());
        }
        
        else if(identifier.equals("state")) {
            if(BattleRoyale.GameInfo().IsGameActive()) {
                return "Ve hre";
            }
            else {
                if(BattleRoyale.GameInfo().IsGameReady()) {
                    return "Pripravena";
                }
                else {
                    return "Cekani na hrace";
                }
            }
        }
        else if(identifier.equals("map")) {
            if(BattleRoyale.GetActualMap() == null) {
                return "zadna";
            }
            else {
                return BattleRoyale.GetActualMap().GetName();
            }
        }
        else if(identifier.equals("total")) {
            return String.valueOf(BattleRoyale.GameInfo().GetTotal());
        }
        else if(identifier.equals("left")) {
            return String.valueOf(BattleRoyale.GameInfo().GetLeft());
        }
        else {
            return "InvalidIdentifier";
        }
        
    }
	
}
