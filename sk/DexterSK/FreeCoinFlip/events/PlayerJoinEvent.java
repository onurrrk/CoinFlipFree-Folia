/*    */ package sk.DexterSK.FreeCoinFlip.events;
/*    */ 
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import sk.DexterSK.FreeCoinFlip.CoinFlip;
/*    */ 
/*    */ public class PlayerJoinEvent implements Listener {
/*    */   @EventHandler
/*    */   public void onJoinEvent(org.bukkit.event.player.PlayerJoinEvent e) {
/* 12 */     Player player = e.getPlayer();
/* 14 */     if (player.hasPermission("cf.admin") || player.isOp())
/* 16 */       if ((CoinFlip.getInstance()).hasUpdate.booleanValue())
/* 17 */         CoinFlip.sendMessage((CommandSender)player, "&eAn update (&bv" + (CoinFlip.getInstance()).latestVer + "&e) is available! You are running &bv" + CoinFlip.getInstance().getDescription().getVersion() + "&e. " + (CoinFlip.getInstance()).url);  
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFlip\events\PlayerJoinEvent.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */