/*    */ package sk.DexterSK.FreeCoinFlip.utilz;
/*    */ 
/*    */ import eu.d0by.utils.Common;
/*    */ import java.util.HashMap;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class StatsManager {
/* 15 */   private HashMap<Player, Integer> wins = new HashMap<>();
/*    */   
/* 16 */   private HashMap<Player, Integer> loses = new HashMap<>();
/*    */   
/*    */   public boolean inEntry(Player p) {
/* 20 */     return !(!this.wins.containsKey(p) && !this.loses.containsKey(p));
/*    */   }
/*    */   
/*    */   public int getWinStats(Player p) {
/* 25 */     return this.wins.containsKey(p) ? ((Integer)this.wins.get(p)).intValue() : 0;
/*    */   }
/*    */   
/*    */   public int getLoseStats(Player p) {
/* 29 */     return this.loses.containsKey(p) ? ((Integer)this.loses.get(p)).intValue() : 0;
/*    */   }
/*    */   
/*    */   public void incrementWin(Player p) {
/* 33 */     this.wins.put(p, Integer.valueOf(this.wins.containsKey(p) ? (((Integer)this.wins.get(p)).intValue() + 1) : 1));
/*    */   }
/*    */   
/*    */   public void incrementLose(Player p) {
/* 37 */     this.loses.put(p, Integer.valueOf(this.loses.containsKey(p) ? (((Integer)this.loses.get(p)).intValue() + 1) : 1));
/*    */   }
/*    */   
/*    */   public Player getWinner(Player first, Player second) {
/* 41 */     if (Math.random() > 0.5D) {
/* 42 */       incrementWin(first);
/* 43 */       incrementLose(second);
/* 44 */       return first;
/*    */     } 
/* 46 */     incrementWin(second);
/* 47 */     incrementLose(first);
/* 48 */     return second;
/*    */   }
/*    */   
/*    */   public void toString(Player p, Player wanted) {
/* 52 */     p.sendMessage(Common.colorize("&e&l&n" + wanted.getName() + " &e&l&nStats"));
/* 53 */     p.sendMessage(Common.colorize(""));
/* 54 */     p.sendMessage(Common.colorize("&6&l* &eDaily Win: &f" + getWinStats(wanted)));
/* 55 */     p.sendMessage(Common.colorize("&6&l* &eDaily Lost: &f" + getLoseStats(wanted)));
/* 56 */     p.sendMessage(Common.colorize(""));
/* 57 */     p.sendMessage(Common.colorize("&7&o(( Stats reset daily!! ))"));
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFli\\utilz\StatsManager.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */