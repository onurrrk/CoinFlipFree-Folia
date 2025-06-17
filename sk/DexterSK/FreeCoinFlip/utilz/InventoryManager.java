/*     */ package sk.DexterSK.FreeCoinFlip.utilz;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ import sk.DexterSK.FreeCoinFlip.CoinFlip;
/*     */ import sk.DexterSK.FreeCoinFlip.config.CoinFlipConfig;
/*     */ 
/*     */ public class InventoryManager {
/*     */   private CoinFlipConfig config;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private String title;
/*     */   
/*     */   private Inventory menu;
/*     */   
/*     */   private HashMap<Player, CoinEntry> coins;
/*     */   
/*     */   public InventoryManager() {
/*  21 */     this.config = CoinFlipConfig.getInstance();
/*  22 */     this.size = this.config.getInt("GUI.Size", 27);
/*  23 */     this.title = this.config.getString("GUI.Title", "&lCOIN FLIP");
/*  24 */     this.coins = CoinFlip.getInstance().getCoins().getEntry();
/*  25 */     this.menu = Bukkit.createInventory(null, this.size, Chat.color(this.title));
/*  26 */     crateGlass(this.menu);
/*  27 */     this.menu.setItem(this.size - 2, playerHelp());
/*  28 */     this.menu.setItem(this.size - 1, playerRefresh());
/*     */   }
/*     */   
/*     */   public void reload() {
/*  32 */     this.config = CoinFlipConfig.getInstance();
/*  33 */     this.size = this.config.getInt("GUI.Size", 27);
/*  34 */     this.title = this.config.getString("GUI.Title", "&lCOIN FLIP");
/*  35 */     updateInv();
/*     */   }
/*     */   
/*     */   public Inventory getMenu() {
/*  39 */     return this.menu;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  43 */     return this.title;
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  47 */     return this.size;
/*     */   }
/*     */   
/*     */   public void updateInv() {
/*  51 */     int index = 0;
/*  52 */     this.menu = Bukkit.createInventory(null, this.size, Chat.color(this.title));
/*  53 */     crateGlass(this.menu);
/*  55 */     for (Player p : this.coins.keySet()) {
/*  57 */       this.menu.setItem(index, playerBet(p));
/*  58 */       index++;
/*     */     } 
/*  61 */     this.menu.setItem(this.size - 2, playerHelp());
/*  62 */     this.menu.setItem(this.size - 1, playerRefresh());
/*     */   }
/*     */   
/*     */   public ItemStack playerBet(Player p) {
/*  68 */     XMaterial itemMat = XMaterial.matchXMaterial(this.config.getString("GUI.SkullItem.type")).orElse(XMaterial.PLAYER_HEAD);
/*  69 */     ItemStack item = itemMat.parseItem();
/*  71 */     if (itemMat.equals(XMaterial.PLAYER_HEAD)) {
/*  73 */       SkullMeta meta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(itemMat.parseMaterial());
/*  74 */       List<String> desc = this.config.getStringList("GUI.SkullItem.lore");
/*  75 */       List<String> lore = new ArrayList<>();
/*  77 */       for (String text : desc)
/*  78 */         lore.add(StringUtils.setPlaceholders((OfflinePlayer)p, Chat.color(text.replaceAll("%name%", p.getName()).replaceAll("%money%", CoinFlip.getAmount(((CoinEntry)this.coins.get(p)).getAmount())).replaceAll("%side%", CoinFlip.getInstance().getCoins().getSideConverted(p))))); 
/*  80 */       meta.setDisplayName(Chat.color(this.config.getString("GUI.SkullItem.name").replaceAll("%name%", p.getName())));
/*  81 */       meta.setOwner(p.getName());
/*  82 */       meta.setLore(lore);
/*  83 */       item.setItemMeta((ItemMeta)meta);
/*     */     } else {
/*  87 */       ItemMeta meta = Bukkit.getItemFactory().getItemMeta(itemMat.parseMaterial());
/*  88 */       List<String> desc = this.config.getStringList("GUI.SkullItem.lore");
/*  89 */       List<String> lore = new ArrayList<>();
/*  91 */       for (String text : desc)
/*  92 */         lore.add(StringUtils.setPlaceholders((OfflinePlayer)p, Chat.color(text.replaceAll("%name%", p.getName()).replaceAll("%money%", CoinFlip.getAmount(((CoinEntry)this.coins.get(p)).getAmount())).replaceAll("%side%", CoinFlip.getInstance().getCoins().getSideConverted(p))))); 
/*  94 */       meta.setDisplayName(Chat.color(this.config.getString("GUI.SkullItem.name").replaceAll("%name%", p.getName())));
/*  95 */       meta.setLore(lore);
/*  96 */       item.setItemMeta(meta);
/*     */     } 
/*  99 */     return item;
/*     */   }
/*     */   
/*     */   public ItemStack playerHelp() {
/* 103 */     XMaterial itemMat = XMaterial.matchXMaterial(this.config.getString("GUI.BookInfo.type")).orElse(XMaterial.BOOK);
/* 104 */     ItemStack item = new ItemStack(itemMat.parseMaterial(), 1);
/* 105 */     ItemMeta meta = item.getItemMeta();
/* 106 */     List<String> desc = this.config.getStringList("GUI.BookInfo.lore");
/* 107 */     List<String> lore = new ArrayList<>();
/* 108 */     for (String text : desc)
/* 109 */       lore.add(StringUtils.setPlaceholders(null, Chat.color(text))); 
/* 111 */     meta.setDisplayName(StringUtils.setPlaceholders(null, Chat.color(this.config.getString("GUI.BookInfo.name"))));
/* 112 */     meta.setLore(lore);
/* 113 */     item.setItemMeta(meta);
/* 114 */     return item;
/*     */   }
/*     */   
/*     */   public ItemStack playerRefresh() {
/* 118 */     XMaterial itemMat = XMaterial.matchXMaterial(this.config.getString("GUI.RefreshItem.type")).orElse(XMaterial.CHEST);
/* 119 */     ItemStack item = new ItemStack(itemMat.parseMaterial(), 1);
/* 120 */     ItemMeta meta = item.getItemMeta();
/* 121 */     List<String> desc = this.config.getStringList("GUI.RefreshItem.lore");
/* 122 */     List<String> lore = new ArrayList<>();
/* 123 */     for (String text : desc)
/* 124 */       lore.add(Chat.color(text)); 
/* 126 */     meta.setDisplayName(Chat.color(this.config.getString("GUI.RefreshItem.name")));
/* 127 */     meta.setLore(lore);
/* 128 */     item.setItemMeta(meta);
/* 129 */     return item;
/*     */   }
/*     */   
/*     */   public void crateGlass(Inventory animation) {
/* 134 */     if (!this.config.contains("GUI.FillerItems"))
/*     */       return; 
/* 137 */     if (this.config.contains("GUI.FillerItems.slots")) {
/* 139 */       List<Integer> slots = this.config.getIntegerList("GUI.FillerItems.slots");
/* 141 */       String def = "GRAY_STAINED_GLASS_PANE";
/* 142 */       if (this.config.contains("GUI.FillerItems.type"))
/* 143 */         def = this.config.getString("GUI.FillerItems.type"); 
/* 145 */       ItemStack glass = XMaterial.valueOf(def).parseItem();
/* 146 */       if (glass != null) {
/* 148 */         ItemMeta meta = glass.getItemMeta();
/* 149 */         meta.setDisplayName(Chat.color(this.config.getString("GUI.FillerItems.name")));
/* 150 */         glass.setItemMeta(meta);
/*     */       } 
/* 153 */       for (Iterator<Integer> iterator = slots.iterator(); iterator.hasNext(); ) {
/* 153 */         int slot = ((Integer)iterator.next()).intValue();
/* 155 */         if (glass != null)
/* 156 */           animation.setItem(slot, glass); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFli\\utilz\InventoryManager.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */