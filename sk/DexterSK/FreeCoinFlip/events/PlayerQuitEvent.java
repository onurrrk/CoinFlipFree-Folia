/*    */ package sk.DexterSK.FreeCoinFlip.events;
/*    */ 
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import sk.DexterSK.FreeCoinFlip.CoinFlip;
/*    */ import sk.DexterSK.FreeCoinFlip.utilz.CoinEntry;
/*    */ 
/*    */ public class PlayerQuitEvent implements Listener {
/*    */   @EventHandler
/*    */   public void onQuitEvent(org.bukkit.event.player.PlayerQuitEvent e) {
/* 12 */     Player p = e.getPlayer();
/* 13 */     if (CoinFlip.getInstance().getCoins().inEntry(p)) {
/* 14 */       CoinFlip.getEconomy().depositPlayer((OfflinePlayer)p, ((CoinEntry)CoinFlip.getInstance().getCoins().getEntry().get(p)).getAmount());
/* 15 */       CoinFlip.getInstance().getCoins().removeEntry(p);
/* 16 */       CoinFlip.getInstance().getMenuManager().updateInv();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFlip\events\PlayerQuitEvent.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */