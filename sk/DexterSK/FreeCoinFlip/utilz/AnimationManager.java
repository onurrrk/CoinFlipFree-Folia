/*     */ package sk.DexterSK.FreeCoinFlip.utilz;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import sk.DexterSK.FreeCoinFlip.CoinFlip;
/*     */ import sk.DexterSK.FreeCoinFlip.config.CoinFlipConfig;
/*     */ 
/*     */ public class AnimationManager {
/*  19 */   private CoinFlipConfig config = CoinFlipConfig.getInstance();
/*     */   
/*  20 */   private String title = this.config.getString("AnimationGUI.Title");
/*     */   
/*  21 */   private int size = this.config.getInt("AnimationGUI.Size");
/*     */   
/*     */   public void reload() {
/*  25 */     this.config = CoinFlipConfig.getInstance();
/*  26 */     this.title = this.config.getString("AnimationGUI.Title");
/*  27 */     this.size = this.config.getInt("AnimationGUI.Size");
/*     */   }
/*     */   
/*     */   public ItemStack players(Player winner) {
/*  32 */     XMaterial itemMat = XMaterial.matchXMaterial(this.config.getString("GUI.AnimationItem.type", "PLAYER_HEAD")).orElse(XMaterial.PLAYER_HEAD);
/*  33 */     ItemStack p1 = new ItemStack(itemMat.parseMaterial(), 1);
/*  35 */     SkullMeta meta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(itemMat.parseMaterial());
/*  36 */     meta.setDisplayName(StringUtils.setPlaceholders((OfflinePlayer)winner, Chat.color(this.config.getString("GUI.AnimationItem.name", "&f&l%name%").replace("%name%", winner.getName()))));
/*     */     try {
/*  38 */       meta.setOwningPlayer((OfflinePlayer)winner);
/*  40 */     } catch (ClassCastException|NoSuchMethodError ex) {
/*  41 */       meta.setOwner(winner.getName());
/*     */     } 
/*  44 */     p1.setItemMeta((ItemMeta)meta);
/*  45 */     return p1;
/*     */   }
/*     */   
/*     */   public void setAnimation(final Player p, final Player winner, final Player loser, final double amount, final double taxed_money) {
/*  49 */     final Inventory animation = Bukkit.createInventory(null, this.size, Chat.color(this.title));
/*  50 */     p.openInventory(animation);
/*  51 */     (new BukkitRunnable() {
/*  52 */         int counter = 0;
/*     */         
/*  53 */         int counter2 = 1;
/*     */         
/*  54 */         int counter3 = 1;
/*     */         
/*     */         public void run() {
/*  58 */           CoinFlip.getInstance().getMenuManager().crateGlass(animation);
/*  60 */           XSound.playSoundFromString(p, AnimationManager.this.config.getString("GUI.ClickSound", "BLOCK_COMPARATOR_CLICK, 1f, 1f"));
/*  62 */           if (this.counter % this.counter2 == 0) {
/*  63 */             this.counter = 0;
/*  64 */             this.counter2++;
/*     */           } 
/*  66 */           if (this.counter3 == 1) {
/*  67 */             animation.setItem(13, AnimationManager.this.players(loser));
/*  68 */             p.updateInventory();
/*  69 */             this.counter3--;
/*     */           } else {
/*  72 */             animation.setItem(13, AnimationManager.this.players(winner));
/*  73 */             p.updateInventory();
/*  74 */             this.counter3++;
/*     */           } 
/*  76 */           this.counter++;
/*  77 */           if (this.counter2 > 6) {
/*  79 */             XSound.playSoundFromString(p, AnimationManager.this.config.getString("GUI.EndSpinSound", "ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f"));
/*  82 */             CoinFlip.getInstance().getMenuManager().crateGlass(animation);
/*  83 */             animation.setItem(13, AnimationManager.this.players(winner));
/*  84 */             p.updateInventory();
/*  86 */             double tax = CoinFlipConfig.getInstance().getDouble("Settings.Tax.Rate");
/*  87 */             boolean enable = CoinFlipConfig.getInstance().getBoolean("Settings.Tax.Enabled");
/*  90 */             CoinFlip.getEconomy().depositPlayer((OfflinePlayer)winner, amount);
/*  91 */             for (Player p : Bukkit.getOnlinePlayers()) {
/*  92 */               if (!CoinFlip.getInstance().getBroadcast().inEntry(p)) {
/*  94 */                 if (enable) {
/*  95 */                   p.sendMessage(StringUtils.setPlaceholders((OfflinePlayer)p, Chat.color(CoinFlipConfig.getInstance().getString("Messages.WinBroadcastWithTax").replaceAll("%winner%", winner.getName()).replaceAll("%loser%", loser.getName()).replaceAll("%amount%", CoinFlip.getAmount(amount)).replace("%taxed_money%", CoinFlip.getAmount(taxed_money)).replace("%tax_rate%", String.valueOf(tax)))));
/*     */                   continue;
/*     */                 } 
/*  97 */                 p.sendMessage(StringUtils.setPlaceholders((OfflinePlayer)p, Chat.color(CoinFlipConfig.getInstance().getString("Messages.WinBroadcast").replaceAll("%winner%", winner.getName()).replaceAll("%loser%", loser.getName()).replaceAll("%amount%", CoinFlip.getAmount(amount)))));
/*     */               } 
/*     */             } 
/* 100 */             cancel();
/*     */           } 
/*     */         }
/* 103 */       }).runTaskTimer((Plugin)CoinFlip.getInstance(), 0L, 5L);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFli\\utilz\AnimationManager.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */