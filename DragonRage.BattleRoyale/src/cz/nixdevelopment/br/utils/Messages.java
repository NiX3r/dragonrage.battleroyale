package cz.nixdevelopment.br.utils;

import org.bukkit.Bukkit;

import cz.nixdevelopment.br.BattleRoyale;

public class Messages {

    public static String BattleRoyaleUsage(Boolean IsAdmin) {
        
        if(!IsAdmin) {
            return "§5§l-----===== §5BattleRoyale §5§l=====-----\n" +
                   "§5/br stats §7- zobrazi Tve statistiky\n" +
                   "§5/br stats <nick> §7- zobrazi hracovi statistiky";/* +
                   "§5/br map list §7- zobrazi seznam dostupnych map\n" +
                   "§5/br map vote <name> §7- zahlasuje pro mapu";*/
        }
        else {
            return "§5§l-----===== §5BattleRoyale §5§l=====-----\n" +
                    "§5/br stats §7- zobrazi Tve statistiky\n" +
                    "§5/br stats <nick> §7- zobrazi hracovi statistiky\n" +/*
                    "§5/br map list §7- zobrazi seznam dostupnych map\n" +
                    "§5/br map vote <name> §7- zahlasuje pro mapu\n" +*/
                    "§5/br start §7- zapne rucne hru\n" +
                    "§5/br stop §7- vypne rucne hru";
        }
        
    }
    
    public static String Stats(String nick, int kills, int deaths, int wins, int loses, int plays) {
        return "§5§l---=== §5Statistiky - §5§o" + nick + " §5§l===---\n" +
                "§5Zabiti: §7" + kills + "\n" +
                "§5Smrti: §7" + deaths + "\n" +
                "§5K/D Ratio: §7" + ((double)kills / (double)deaths) + "\n" +
                "§5Vyhry: §7" + wins + "\n" +
                "§5Prohry: §7" + loses + "\n" +
                "§5Odehrano: §7" + plays;
    }
    
    public static String PlayerDieByPlayer(String entity, String killer) {
        return BattleRoyale.Prefix + "Hrac §5" + entity + " §7byl zabit hracem §5" + killer + "§7. Stav hracu: §5" + BattleRoyale.GameInfo().GetLeft() + "§7/§5" + BattleRoyale.GameInfo().GetTotal();
    }
    public static String PlayerDie(String entity) {
        return BattleRoyale.Prefix + "Hrac " + entity + " umrel v akci! Stav hracu: §5" + BattleRoyale.GameInfo().GetLeft() + "§7/§5" + BattleRoyale.GameInfo().GetTotal();
    }
    public static String PlayerJoin(String nick, int total, int toStart) {
        return BattleRoyale.Prefix + "Hrac " + nick + " se pripojil. Celkem hracu: §5" + total + "§7/§5" + toStart;
    }
    public static String PlayerLeave(String nick) {
        return BattleRoyale.Prefix + "Hrac " + nick + " se odpojil! Stav hracu: §5" + BattleRoyale.GameInfo().GetLeft() + "§7/§5" + BattleRoyale.GameInfo().GetTotal();
    }
    public static String PlayerLeave(String nick, int total, int toStart) {
        return BattleRoyale.Prefix + "Hrac " + nick + " se odpojil. Celkem hracu: §5" + total + "§7/§5" + toStart;
    }
    public static String StopCountdown() {
        return BattleRoyale.Prefix + "Odpocet hry byl zastaven!";
    }
    public static String StartGame() {
        return BattleRoyale.Prefix + "Hra zahajena!";
    }
    public static String StartCountdown() {
        return BattleRoyale.Prefix + "Odpocet hry zahajen! Za §560 §7sekund zacne hra.";
    }
    public static String WinnerWinnerChickenDinner(String winner) {
        return BattleRoyale.Prefix + "Konec hry! Slepici veceri si odnasi hrac §5" + winner;
    }
    public static String PhaseMove() {
        return BattleRoyale.Prefix + "Pozor! Zona se posouva!";
    }
    public static String ForceStartMoreThanOne() {
        return BattleRoyale.Prefix + "Zacit hru muzes s vice jak jednim hracem!";
    }
    public static String NotPermission(String perm) {
        return BattleRoyale.Prefix + "Na tento prikaz nemas pravo: §5" + perm;
    }
    public static String UnknownCommand() {
        return BattleRoyale.Prefix + "Neznamy prikaz! Napis: §5/br";
    }

    public static String TargetOffline(String nick) {
        return BattleRoyale.Prefix + "Hrac " + nick + " neni online!";
    }
    
}
