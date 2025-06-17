/*    */ package sk.DexterSK.FreeCoinFlip.utilz;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CoinManager {
/* 11 */   private HashMap<Player, CoinEntry> coins = new HashMap<>();
/*    */   
/*    */   public HashMap<Player, CoinEntry> getEntry() {
/* 15 */     return this.coins;
/*    */   }
/*    */   
/*    */   public void createEntry(Player p, double amount, boolean side) {
/* 19 */     CoinEntry entry = new CoinEntry(amount, side);
/* 20 */     this.coins.put(p, entry);
/*    */   }
/*    */   
/*    */   public void removeEntry(Player p) {
/* 24 */     this.coins.remove(p);
/*    */   }
/*    */   
/*    */   public boolean inEntry(Player p) {
/* 28 */     return this.coins.containsKey(p);
/*    */   }
/*    */   
/*    */   public String getSideConverted(Player p) {
/* 32 */     if (((CoinEntry)this.coins.get(p)).getSide())
/* 33 */       return "Heads"; 
/* 35 */     return "Tails";
/*    */   }
/*    */   
/*    */   public boolean getBooleanConverted(String side) {
/* 39 */     return !(!side.equalsIgnoreCase("heads") && !side.equalsIgnoreCase("head"));
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFli\\utilz\CoinManager.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */