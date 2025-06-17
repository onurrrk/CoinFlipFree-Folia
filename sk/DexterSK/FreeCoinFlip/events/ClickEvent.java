/*    */ package sk.DexterSK.FreeCoinFlip.events;
/*    */ 
/*    */ import net.milkbowl.vault.economy.Economy;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import sk.DexterSK.FreeCoinFlip.CoinFlip;
/*    */ import sk.DexterSK.FreeCoinFlip.config.CoinFlipConfig;
/*    */ import sk.DexterSK.FreeCoinFlip.utilz.Chat;
/*    */ import sk.DexterSK.FreeCoinFlip.utilz.CoinEntry;
/*    */ import sk.DexterSK.FreeCoinFlip.utilz.CoinManager;
/*    */ import sk.DexterSK.FreeCoinFlip.utilz.InventoryManager;
/*    */ 
/*    */ public class ClickEvent implements Listener {
/*    */   @EventHandler
/*    */   public void onClickEvent(InventoryClickEvent e) {
/* 19 */     Player p = (Player)e.getWhoClicked();
/* 20 */     Inventory open = e.getClickedInventory();
/* 21 */     InventoryView view = e.getView();
/* 22 */     ItemStack item = e.getCurrentItem();
/* 23 */     Economy econ = CoinFlip.getEconomy();
/* 24 */     CoinManager coins = CoinFlip.getInstance().getCoins();
/* 25 */     InventoryManager menu = CoinFlip.getInstance().getMenuManager();
/* 26 */     if (item == null || !item.hasItemMeta())
/*    */       return; 
/* 33 */     if (view.getTitle().equals(Chat.color(CoinFlip.getInstance().getMenuManager().getTitle())) || view.getTitle().equals(Chat.color(CoinFlipConfig.getInstance().getString("AnimationGUI.Title")))) {
/* 34 */       e.setCancelled(true);
/* 35 */       if (item.equals(menu.playerRefresh())) {
/* 37 */         p.openInventory(menu.getMenu());
/*    */         return;
/*    */       } 
/* 40 */       Player other = Bukkit.getServer().getPlayer(ChatColor.stripColor(item.getItemMeta().getDisplayName()));
/* 41 */       if (item.equals(menu.playerHelp()))
/*    */         return; 
/* 50 */       if (CoinFlipConfig.getInstance().contains("GUI.FillerItems.name") && item.getItemMeta().getDisplayName().equals(Chat.color(CoinFlipConfig.getInstance().getString("GUI.FillerItems.name"))))
/*    */         return; 
/* 53 */       if (p.equals(other)) {
/* 55 */         p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.CantEnterSelfBet")));
/*    */         return;
/*    */       } 
/* 62 */       if (coins.inEntry(other)) {
/* 63 */         double amount = ((CoinEntry)coins.getEntry().get(other)).getAmount();
/* 64 */         if (econ.getBalance((OfflinePlayer)p) > amount) {
/* 65 */           econ.withdrawPlayer((OfflinePlayer)p, amount);
/* 66 */           p.closeInventory();
/* 67 */           coins.removeEntry(other);
/* 68 */           Player winner = CoinFlip.getInstance().getStats().getWinner(p, other);
/* 69 */           double multiplier = CoinFlipConfig.getInstance().getDouble("Settings.Multiplier", 2.0D);
/* 70 */           amount *= multiplier;
/* 72 */           double tax = CoinFlipConfig.getInstance().getDouble("Settings.Tax.Rate");
/* 73 */           boolean enable = CoinFlipConfig.getInstance().getBoolean("Settings.Tax.Enabled");
/* 74 */           long tax_deduction = 0L;
/* 76 */           if (enable) {
/* 78 */             tax_deduction = (long)(tax * amount / 100.0D);
/* 79 */             amount -= tax_deduction;
/*    */           } 
/* 82 */           if (p.equals(winner)) {
/* 83 */             CoinFlip.getInstance().getAnimation().setAnimation(p, p, other, amount, tax_deduction);
/*    */           } else {
/* 86 */             CoinFlip.getInstance().getAnimation().setAnimation(p, other, p, amount, tax_deduction);
/*    */           } 
/* 88 */           menu.updateInv();
/*    */         } else {
/* 91 */           p.closeInventory();
/* 92 */           p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.NotEnoughMoney")));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFlip\events\ClickEvent.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */