/*     */ package sk.DexterSK.FreeCoinFlip.commands;
/*     */ 
/*     */ import eu.d0by.utils.Common;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.TabCompleter;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.util.StringUtil;
/*     */ import sk.DexterSK.FreeCoinFlip.CoinFlip;
/*     */ import sk.DexterSK.FreeCoinFlip.config.CoinFlipConfig;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.Chat;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.CoinEntry;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.CoinManager;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.InventoryManager;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.StringUtils;
/*     */ 
/*     */ public class CoinFlipCommand implements CommandExecutor, TabCompleter {
/*  24 */   private CoinManager coins = CoinFlip.getInstance().getCoins();
/*     */   
/*  25 */   private InventoryManager menu = CoinFlip.getInstance().getMenuManager();
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  31 */     if (args.length == 1)
/*  33 */       if (args[0].equalsIgnoreCase("reload")) {
/*  35 */         if (sender.hasPermission("cf.reload")) {
/*     */           try {
/*  39 */             CoinFlip.getInstance().onReload();
/*  40 */             sender.sendMessage(Common.colorize(CoinFlipConfig.getInstance().getString("Messages.ReloadSuccess")));
/*  42 */           } catch (Throwable t) {
/*  44 */             t.printStackTrace();
/*  45 */             sender.sendMessage(CoinFlipConfig.getInstance().getString("Messages.ReloadError"));
/*     */           } 
/*     */         } else {
/*  49 */           sender.sendMessage(Common.colorize(CoinFlipConfig.getInstance().getString("Messages.NotEnoughPerm")));
/*     */         } 
/*  51 */         return false;
/*     */       }  
/*  55 */     if (!(sender instanceof Player)) {
/*  57 */       sender.sendMessage(Chat.color("&cThis command can be run only by players"));
/*  58 */       return false;
/*     */     } 
/*  61 */     if (cmd.getName().equalsIgnoreCase("coinflip")) {
/*  62 */       Player p = (Player)sender;
/*  64 */       if (args.length == 0) {
/*  66 */         p.openInventory(CoinFlip.getInstance().getMenu());
/*  67 */         return false;
/*     */       } 
/*  70 */       if (args.length == 1) {
/*  72 */         if (args[0].equalsIgnoreCase("toggle")) {
/*  74 */           if (!CoinFlip.getInstance().getBroadcast().inEntry(p)) {
/*  76 */             CoinFlip.getInstance().getBroadcast().createEntry(p);
/*  77 */             p.sendMessage(Chat.color(CoinFlip.getInstance().getBroadcast().toString(p)));
/*  78 */             return false;
/*     */           } 
/*  80 */           CoinFlip.getInstance().getBroadcast().removeEntry(p);
/*  81 */           p.sendMessage(Chat.color(CoinFlip.getInstance().getBroadcast().toString(p)));
/*  82 */           return false;
/*     */         } 
/*  84 */         if (args[0].equalsIgnoreCase("stats")) {
/*  86 */           if (!p.hasPermission("cf.stats")) {
/*  88 */             CoinFlip.sendMessage((CommandSender)p, Common.colorize(CoinFlipConfig.getInstance().getString("Messages.NotEnoughPerm")));
/*  89 */             return false;
/*     */           } 
/*  91 */           CoinFlip.getInstance().getStats().toString(p, p);
/*  93 */         } else if (isNumeric(args[0])) {
/*     */           try {
/*  96 */             double amount = Double.parseDouble(args[0]);
/*  97 */             boolean side = true;
/*  98 */             if (this.coins.getEntry().size() < this.menu.getSize())
/*  99 */               if (!this.coins.inEntry(p)) {
/* 100 */                 if (CoinFlip.getEconomy().getBalance((OfflinePlayer)p) >= amount) {
/* 101 */                   if (amount >= CoinFlipConfig.getInstance().getInt("Settings.minAmount")) {
/* 102 */                     CoinFlip.getEconomy().withdrawPlayer((OfflinePlayer)p, amount);
/* 103 */                     p.sendMessage(StringUtils.setPlaceholders((OfflinePlayer)p, Chat.color(CoinFlipConfig.getInstance().getString("Messages.LostMoney").replaceAll("%amount%", CoinFlip.getAmount(amount)))));
/* 104 */                     p.sendMessage(StringUtils.setPlaceholders((OfflinePlayer)p, Chat.color(CoinFlipConfig.getInstance().getString("Messages.Entered").replaceAll("%amount%", CoinFlip.getAmount(amount)))));
/* 107 */                     if (CoinFlipConfig.getInstance().getBoolean("Settings.Broadcast.Enabled") && amount >= CoinFlipConfig.getInstance().getInt("Settings.Broadcast.MinAmount"))
/* 108 */                       for (Player pp : Bukkit.getOnlinePlayers())
/* 109 */                         pp.sendMessage(StringUtils.setPlaceholders((OfflinePlayer)pp, Chat.color(CoinFlipConfig.getInstance().getString("Settings.Broadcast.Message").replace("%amount%", CoinFlip.getAmount(amount)).replace("%player%", p.getName()))));  
/* 111 */                     this.coins.createEntry(p, amount, true);
/* 112 */                     this.menu.updateInv();
/* 113 */                     return false;
/*     */                   } 
/* 115 */                   p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.NotEnoughEnterMoney")));
/*     */                 } else {
/* 118 */                   p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.NotEnoughMoney")));
/*     */                 } 
/*     */               } else {
/* 122 */                 p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.AlreadyInBet")));
/*     */               }  
/* 126 */           } catch (NumberFormatException e) {
/* 127 */             p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.Usage")));
/*     */           } 
/*     */         } else {
/* 132 */           if (!args[0].equalsIgnoreCase("cancel")) {
/* 134 */             p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.CanceledHelp")));
/* 135 */             return false;
/*     */           } 
/* 137 */           if (this.coins.inEntry(p)) {
/* 139 */             double amount = ((CoinEntry)this.coins.getEntry().get(p)).getAmount();
/* 140 */             CoinFlip.getEconomy().depositPlayer((OfflinePlayer)p, amount);
/* 141 */             this.coins.removeEntry(p);
/* 142 */             this.menu.updateInv();
/* 143 */             p.sendMessage(StringUtils.setPlaceholders((OfflinePlayer)p, Chat.color(CoinFlipConfig.getInstance().getString("Messages.ReceivedMoney").replaceAll("%amount%", CoinFlip.getAmount(amount)))));
/* 144 */             p.sendMessage(StringUtils.setPlaceholders((OfflinePlayer)p, Chat.color(CoinFlipConfig.getInstance().getString("Messages.Canceled"))));
/* 145 */             return false;
/*     */           } 
/* 147 */           p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.NotInBet")));
/*     */         } 
/*     */       } 
/* 151 */       if (args.length == 2)
/* 153 */         if (args[0].equalsIgnoreCase("stats")) {
/* 155 */           if (p.hasPermission("cf.stats.other")) {
/* 157 */             Player wanted = Bukkit.getPlayer(args[1]);
/* 158 */             if (wanted != null) {
/* 159 */               CoinFlip.getInstance().getStats().toString(p, wanted);
/*     */             } else {
/* 161 */               CoinFlip.sendMessage((CommandSender)p, Common.colorize(CoinFlipConfig.getInstance().getString("Messages.CantFindPlayer").replace("{PLAYER}", args[1])));
/*     */             } 
/*     */           } else {
/* 164 */             CoinFlip.sendMessage((CommandSender)p, Common.colorize(CoinFlipConfig.getInstance().getString("Messages.NotEnoughPerm")));
/*     */           } 
/*     */         } else {
/*     */           try {
/* 169 */             double amount = Double.parseDouble(args[0]);
/* 170 */             boolean side = this.coins.getBooleanConverted(args[1]);
/* 171 */             if (this.coins.getEntry().size() < this.menu.getSize())
/* 172 */               if (!this.coins.inEntry(p)) {
/* 173 */                 if (CoinFlip.getEconomy().getBalance((OfflinePlayer)p) >= amount) {
/* 174 */                   if (amount >= CoinFlipConfig.getInstance().getInt("Settings.minAmount")) {
/* 175 */                     CoinFlip.getEconomy().withdrawPlayer((OfflinePlayer)p, amount);
/* 176 */                     p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.LostMoney").replaceAll("%amount%", CoinFlip.getAmount(amount))));
/* 177 */                     p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.Entered").replaceAll("%amount%", CoinFlip.getAmount(amount))));
/* 180 */                     if (CoinFlipConfig.getInstance().getBoolean("Settings.Broadcast.Enabled") && amount >= CoinFlipConfig.getInstance().getInt("Settings.Broadcast.MinAmount"))
/* 181 */                       for (Player pp : Bukkit.getOnlinePlayers())
/* 182 */                         pp.sendMessage(StringUtils.setPlaceholders((OfflinePlayer)pp, Chat.color(CoinFlipConfig.getInstance().getString("Settings.Broadcast.Message").replace("%amount%", CoinFlip.getAmount(amount)).replace("%player%", p.getName()))));  
/* 184 */                     this.coins.createEntry(p, amount, side);
/* 185 */                     this.menu.updateInv();
/* 186 */                     return false;
/*     */                   } 
/* 188 */                   p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.NotEnoughEnterMoney")));
/*     */                 } else {
/* 191 */                   p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.NotEnoughMoney")));
/*     */                 } 
/*     */               } else {
/* 195 */                 p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.AlreadyInBet")));
/*     */               }  
/* 199 */           } catch (NumberFormatException e) {
/* 200 */             p.sendMessage(Chat.color(CoinFlipConfig.getInstance().getString("Messages.Usage")));
/*     */           } 
/*     */         }  
/*     */     } 
/* 205 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean isNumeric(String str) {
/* 210 */     return (str != null && str.matches("[0-9.]+"));
/*     */   }
/*     */   
/*     */   public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
/* 216 */     if (strings.length < 1)
/* 217 */       return new ArrayList<>(); 
/* 219 */     return (List<String>)StringUtil.copyPartialMatches(strings[strings.length - 1], onTabCompleteInternal(commandSender, command, s, strings), new ArrayList());
/*     */   }
/*     */   
/*     */   public List<String> onTabCompleteInternal(CommandSender commandSender, Command command, String s, String[] strings) {
/* 224 */     if (strings.length > 0)
/* 226 */       if (strings.length > 1) {
/* 228 */         if (isNumeric(strings[0]))
/* 229 */           return List.of("heads", "tails"); 
/* 231 */         if (strings[0].equalsIgnoreCase("stats"))
/* 233 */           if (commandSender.hasPermission("cf.stats.other")) {
/* 235 */             ArrayList<String> list = new ArrayList<>();
/* 237 */             for (Player p : Bukkit.getOnlinePlayers())
/* 238 */               list.add(p.getName()); 
/* 240 */             if (strings.length == 3 && commandSender.hasPermission("cf.stats.reset"))
/* 241 */               return List.of("reset"); 
/* 242 */             if (strings.length == 2)
/* 243 */               return list; 
/* 245 */             return new ArrayList<>();
/*     */           }  
/*     */       } else {
/* 251 */         ArrayList<String> list = new ArrayList<>();
/* 253 */         list.add("[amount]");
/* 254 */         list.add("cancel");
/* 255 */         list.add("stats");
/* 256 */         list.add("toggle");
/* 257 */         list.add("help");
/* 258 */         list.add("reload");
/* 266 */         return list;
/*     */       }  
/* 269 */     return new ArrayList<>();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFlip\commands\CoinFlipCommand.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */