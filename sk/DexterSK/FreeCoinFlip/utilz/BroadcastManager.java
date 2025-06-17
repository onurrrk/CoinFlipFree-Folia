/*    */ package sk.DexterSK.FreeCoinFlip.utilz;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import org.bukkit.entity.Player;
/*    */ import sk.DexterSK.FreeCoinFlip.config.CoinFlipConfig;
/*    */ 
/*    */ public class BroadcastManager {
/* 12 */   private HashMap<Player, Boolean> broadcast = new HashMap<>();
/*    */   
/*    */   public boolean inEntry(Player p) {
/* 16 */     return this.broadcast.containsKey(p);
/*    */   }
/*    */   
/*    */   public void createEntry(Player p) {
/* 20 */     this.broadcast.put(p, Boolean.valueOf(true));
/*    */   }
/*    */   
/*    */   public void removeEntry(Player p) {
/* 24 */     this.broadcast.remove(p);
/*    */   }
/*    */   
/*    */   public String toString(Player p) {
/* 28 */     if (inEntry(p))
/* 29 */       return CoinFlipConfig.getInstance().getString("Messages.ToggleON"); 
/* 31 */     return CoinFlipConfig.getInstance().getString("Messages.ToggleOFF");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFli\\utilz\BroadcastManager.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */