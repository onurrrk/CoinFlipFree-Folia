/*     */ package sk.DexterSK.FreeCoinFlip;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import eu.d0by.utils.Common;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import net.milkbowl.vault.economy.Economy;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.command.TabCompleter;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import sk.DexterSK.FreeCoinFlip.commands.CoinFlipCommand;
/*     */ import sk.DexterSK.FreeCoinFlip.config.CoinFlipConfig;
/*     */ import sk.DexterSK.FreeCoinFlip.events.ClickEvent;
/*     */ import sk.DexterSK.FreeCoinFlip.events.PlayerJoinEvent;
/*     */ import sk.DexterSK.FreeCoinFlip.events.PlayerQuitEvent;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.AnimationManager;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.BroadcastManager;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.CoinManager;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.InventoryManager;
/*     */ import sk.DexterSK.FreeCoinFlip.utilz.StatsManager;
/*     */ 
/*     */ public class CoinFlip extends JavaPlugin {
/*  40 */   public Boolean hasUpdate = Boolean.valueOf(false);
/*     */   
/*  41 */   public String url = "<unknown>";
/*     */   
/*  42 */   public String latestVer = "<unknown>";
/*     */   
/*  47 */   private static String prefix = "&7[<#882285>FreeCoinFlip</#EE6F20>&7]";
/*     */   
/*     */   private static CoinFlip plugin;
/*     */   
/*     */   private CoinManager coins;
/*     */   
/*     */   private StatsManager stats;
/*     */   
/*     */   private InventoryManager menu;
/*     */   
/*     */   private AnimationManager animation;
/*     */   
/*     */   private BroadcastManager broadcast;
/*     */   
/*     */   private static Economy econ;
/*     */   
/*     */   private CoinFlipConfig config;
/*     */   
/*     */   private Metrics metrics;
/*     */   
/*     */   static {
/*  48 */     econ = null;
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  52 */     plugin = this;
/*  53 */     saveDefaultConfig();
/*  54 */     if (!setupEconomy()) {
/*  55 */       System.out.println(String.format("[FreeCoinFlip] - Disabled due to no Vault dependency found!", new Object[] { getDescription().getName() }));
/*  56 */       getServer().getPluginManager().disablePlugin((Plugin)this);
/*     */       return;
/*     */     } 
/*  59 */     this.broadcast = new BroadcastManager();
/*  60 */     this.stats = new StatsManager();
/*  61 */     this.coins = new CoinManager();
/*  62 */     this.menu = new InventoryManager();
/*  63 */     this.animation = new AnimationManager();
/*  64 */     this.config = CoinFlipConfig.getInstance();
/*  65 */     if (this.config.contains("Settings.Prefix"))
/*  66 */       prefix = this.config.getString("Settings.Prefix"); 
/*  68 */     CoinFlipCommand mainCommand = new CoinFlipCommand();
/*  69 */     PluginCommand pluginCommand = getCommand("coinflip");
/*  70 */     pluginCommand.setExecutor((CommandExecutor)mainCommand);
/*  71 */     pluginCommand.setTabCompleter((TabCompleter)mainCommand);
/*  72 */     int pluginId = 17018;
/*  73 */     this.metrics = new Metrics(this, pluginId);
/*  74 */     getServer().getPluginManager().registerEvents((Listener)new ClickEvent(), (Plugin)this);
/*  75 */     getServer().getPluginManager().registerEvents((Listener)new PlayerQuitEvent(), (Plugin)this);
/*  76 */     getServer().getPluginManager().registerEvents((Listener)new PlayerJoinEvent(), (Plugin)this);
/*     */   }
/*     */   
/*     */   public void onDisable() {}
/*     */   
/*     */   public void onReload() {
/*  83 */     CoinFlipConfig.getInstance().reload();
/*  84 */     this.menu.reload();
/*  85 */     this.animation.reload();
/*     */   }
/*     */   
/*     */   public static CoinFlip getInstance() {
/*  89 */     return plugin;
/*     */   }
/*     */   
/*     */   public BroadcastManager getBroadcast() {
/*  93 */     return this.broadcast;
/*     */   }
/*     */   
/*     */   public StatsManager getStats() {
/*  97 */     return this.stats;
/*     */   }
/*     */   
/*     */   public CoinManager getCoins() {
/* 101 */     return this.coins;
/*     */   }
/*     */   
/*     */   public Inventory getMenu() {
/* 105 */     return this.menu.getMenu();
/*     */   }
/*     */   
/*     */   public AnimationManager getAnimation() {
/* 109 */     return this.animation;
/*     */   }
/*     */   
/*     */   public InventoryManager getMenuManager() {
/* 113 */     return this.menu;
/*     */   }
/*     */   
/*     */   public static Economy getEconomy() {
/* 117 */     return econ;
/*     */   }
/*     */   
/*     */   private boolean setupEconomy() {
/* 121 */     if (getServer().getPluginManager().getPlugin("Vault") == null)
/* 122 */       return false; 
/* 124 */     RegisteredServiceProvider rsp = getServer().getServicesManager().getRegistration(Economy.class);
/* 125 */     if (rsp == null)
/* 126 */       return false; 
/* 128 */     econ = (Economy)rsp.getProvider();
/* 129 */     return (econ != null);
/*     */   }
/*     */   
/*     */   public static String getPrefix() {
/* 133 */     return prefix;
/*     */   }
/*     */   
/*     */   public static void sendMessage(CommandSender commandSender, String string) {
/* 137 */     if (getPrefix() == null || getPrefix().isEmpty()) {
/* 138 */       sendMessage(commandSender, string, false);
/*     */       return;
/*     */     } 
/* 141 */     commandSender.sendMessage(Common.colorize(String.valueOf(getPrefix()) + " " + string));
/*     */   }
/*     */   
/*     */   public static void sendMessage(CommandSender commandSender, String string, boolean bl) {
/* 145 */     String string2 = bl ? (String.valueOf(getPrefix()) + " ") : "";
/* 146 */     commandSender.sendMessage(Common.colorize(String.valueOf(string2) + string));
/*     */   }
/*     */   
/*     */   public static final String getAmount(double amt) {
/* 152 */     String format = (amt >= 1.0E15D) ? "# ### ### ### ### ###.##" : ((amt >= 1.0E12D) ? "# ### ### ### ###.##" : ((amt >= 1.0E9D) ? "# ### ### ###.##" : ((amt >= 1000000.0D) ? "# ### ###.##" : ((amt >= 1000.0D) ? "### ###.##" : "###.##"))));
/* 153 */     String temp = (new DecimalFormat(format.replace(" ", ","))).format((new BigDecimal(amt)).setScale(2, 4).doubleValue());
/* 154 */     return (temp.endsWith(".00") ? temp.substring(temp.indexOf('.')) : temp).replace(" ", new String(",".getBytes(), Charsets.UTF_8));
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFlip\CoinFlip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */