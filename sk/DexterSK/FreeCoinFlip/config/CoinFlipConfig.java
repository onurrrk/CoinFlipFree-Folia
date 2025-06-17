/*     */ package sk.DexterSK.FreeCoinFlip.config;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.configuration.Configuration;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.Vector;
/*     */ import sk.DexterSK.FreeCoinFlip.CoinFlip;
/*     */ 
/*     */ public class CoinFlipConfig implements ConfigurationSection {
/*     */   private static final String CONFIG_NAME = "config.yml";
/*     */   
/*     */   private static CoinFlipConfig instance;
/*     */   
/*     */   private YamlConfiguration configuration;
/*     */   
/*     */   public CoinFlipConfig() {
/*  35 */     reload();
/*     */   }
/*     */   
/*     */   public static CoinFlipConfig getInstance() {
/*  39 */     return (instance == null) ? (instance = new CoinFlipConfig()) : instance;
/*     */   }
/*     */   
/*     */   public void reload() {
/*  44 */     if (!CoinFlip.getInstance().getDataFolder().exists())
/*     */       try {
/*  47 */         CoinFlip.getInstance().getDataFolder().mkdir();
/*  48 */       } catch (Exception exception) {
/*  49 */         exception.printStackTrace();
/*     */       }  
/*  54 */     File configFile = new File(CoinFlip.getInstance().getDataFolder(), "config.yml");
/*  55 */     if (!configFile.exists()) {
/*     */       try {
/*  58 */         configFile.createNewFile();
/*  59 */         Files.copy(Objects.<InputStream>requireNonNull(CoinFlip.getInstance().getResource("config.yml")), configFile.toPath(), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*  60 */       } catch (IOException e) {
/*  61 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*  66 */       this.configuration = YamlConfiguration.loadConfiguration(configFile);
/*  68 */       InputStream defConfigStream = CoinFlip.getInstance().getResource("config.yml");
/*  69 */       if (defConfigStream != null) {
/*  71 */         File file = new File(CoinFlip.getInstance().getDataFolder(), "config.yml.tmp");
/*     */         try {
/*  73 */           Files.copy(defConfigStream, file.toPath(), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*  74 */         } catch (IOException e) {
/*  75 */           e.printStackTrace();
/*     */           return;
/*     */         } 
/*  79 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(file);
/*  82 */         for (String string : defConfig.getKeys(true)) {
/*  84 */           if (!this.configuration.contains(string)) {
/*  86 */             this.configuration.set(string, defConfig.get(string));
/*  88 */             CoinFlip.getInstance().getLogger().warning("Your config.yml missing: " + string + ".");
/*     */           } 
/*     */         } 
/*  91 */         file.delete();
/*     */       } 
/*     */     } 
/*  95 */     save();
/*     */   }
/*     */   
/*     */   public void save() {
/* 100 */     File configFile = new File(CoinFlip.getInstance().getDataFolder(), "config.yml");
/*     */     try {
/* 103 */       if (!configFile.exists())
/* 104 */         configFile.createNewFile(); 
/* 106 */       this.configuration.save(configFile);
/* 108 */     } catch (IOException ex) {
/* 109 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<String> getKeys(boolean b) {
/* 115 */     return this.configuration.getKeys(b);
/*     */   }
/*     */   
/*     */   public Map<String, Object> getValues(boolean b) {
/* 120 */     return this.configuration.getValues(b);
/*     */   }
/*     */   
/*     */   public boolean contains(String s) {
/* 125 */     return this.configuration.contains(s);
/*     */   }
/*     */   
/*     */   public boolean contains(String s, boolean b) {
/* 130 */     return this.configuration.contains(s, b);
/*     */   }
/*     */   
/*     */   public boolean isSet(String s) {
/* 135 */     return this.configuration.isSet(s);
/*     */   }
/*     */   
/*     */   public String getCurrentPath() {
/* 140 */     return this.configuration.getCurrentPath();
/*     */   }
/*     */   
/*     */   public String getName() {
/* 145 */     return this.configuration.getName();
/*     */   }
/*     */   
/*     */   public Configuration getRoot() {
/* 150 */     return this.configuration.getRoot();
/*     */   }
/*     */   
/*     */   public ConfigurationSection getParent() {
/* 155 */     return this.configuration.getParent();
/*     */   }
/*     */   
/*     */   public Object get(String s) {
/* 160 */     return this.configuration.get(s);
/*     */   }
/*     */   
/*     */   public Object get(String s, Object o) {
/* 165 */     return this.configuration.get(s, o);
/*     */   }
/*     */   
/*     */   public void set(String s, Object o) {
/* 170 */     this.configuration.set(s, o);
/*     */   }
/*     */   
/*     */   public ConfigurationSection createSection(String s) {
/* 175 */     return this.configuration.createSection(s);
/*     */   }
/*     */   
/*     */   public ConfigurationSection createSection(String s, Map<?, ?> map) {
/* 180 */     return this.configuration.createSection(s, map);
/*     */   }
/*     */   
/*     */   public String getString(String s) {
/* 185 */     return this.configuration.getString(s);
/*     */   }
/*     */   
/*     */   public String getString(String s, String s1) {
/* 190 */     return this.configuration.getString(s, s1);
/*     */   }
/*     */   
/*     */   public boolean isString(String s) {
/* 195 */     return this.configuration.isString(s);
/*     */   }
/*     */   
/*     */   public int getInt(String s) {
/* 200 */     return this.configuration.getInt(s);
/*     */   }
/*     */   
/*     */   public int getInt(String s, int i) {
/* 205 */     return this.configuration.getInt(s, i);
/*     */   }
/*     */   
/*     */   public boolean isInt(String s) {
/* 210 */     return this.configuration.isInt(s);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String s) {
/* 215 */     return this.configuration.getBoolean(s);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String s, boolean b) {
/* 220 */     return this.configuration.getBoolean(s, b);
/*     */   }
/*     */   
/*     */   public boolean isBoolean(String s) {
/* 225 */     return this.configuration.isBoolean(s);
/*     */   }
/*     */   
/*     */   public double getDouble(String s) {
/* 230 */     return this.configuration.getDouble(s);
/*     */   }
/*     */   
/*     */   public double getDouble(String s, double v) {
/* 235 */     return this.configuration.getDouble(s, v);
/*     */   }
/*     */   
/*     */   public boolean isDouble(String s) {
/* 240 */     return this.configuration.isDouble(s);
/*     */   }
/*     */   
/*     */   public long getLong(String s) {
/* 245 */     return this.configuration.getLong(s);
/*     */   }
/*     */   
/*     */   public long getLong(String s, long l) {
/* 250 */     return this.configuration.getLong(s, l);
/*     */   }
/*     */   
/*     */   public boolean isLong(String s) {
/* 255 */     return this.configuration.isLong(s);
/*     */   }
/*     */   
/*     */   public List<?> getList(String s) {
/* 260 */     return this.configuration.getList(s);
/*     */   }
/*     */   
/*     */   public List<?> getList(String s, List<?> list) {
/* 265 */     return this.configuration.getList(s, list);
/*     */   }
/*     */   
/*     */   public boolean isList(String s) {
/* 270 */     return this.configuration.isList(s);
/*     */   }
/*     */   
/*     */   public List<String> getStringList(String s) {
/* 275 */     return this.configuration.getStringList(s);
/*     */   }
/*     */   
/*     */   public List<Integer> getIntegerList(String s) {
/* 280 */     return this.configuration.getIntegerList(s);
/*     */   }
/*     */   
/*     */   public List<Boolean> getBooleanList(String s) {
/* 285 */     return this.configuration.getBooleanList(s);
/*     */   }
/*     */   
/*     */   public List<Double> getDoubleList(String s) {
/* 290 */     return this.configuration.getDoubleList(s);
/*     */   }
/*     */   
/*     */   public List<Float> getFloatList(String s) {
/* 295 */     return this.configuration.getFloatList(s);
/*     */   }
/*     */   
/*     */   public List<Long> getLongList(String s) {
/* 300 */     return this.configuration.getLongList(s);
/*     */   }
/*     */   
/*     */   public List<Byte> getByteList(String s) {
/* 305 */     return this.configuration.getByteList(s);
/*     */   }
/*     */   
/*     */   public List<Character> getCharacterList(String s) {
/* 310 */     return this.configuration.getCharacterList(s);
/*     */   }
/*     */   
/*     */   public List<Short> getShortList(String s) {
/* 315 */     return this.configuration.getShortList(s);
/*     */   }
/*     */   
/*     */   public List<Map<?, ?>> getMapList(String s) {
/* 320 */     return this.configuration.getMapList(s);
/*     */   }
/*     */   
/*     */   public <T> T getObject(String s, Class<T> aClass) {
/* 325 */     return (T)this.configuration.getObject(s, aClass);
/*     */   }
/*     */   
/*     */   public <T> T getObject(String s, Class<T> aClass, T t) {
/* 330 */     return (T)this.configuration.getObject(s, aClass, t);
/*     */   }
/*     */   
/*     */   public <T extends ConfigurationSerializable> T getSerializable(String s, Class<T> aClass) {
/* 335 */     return (T)this.configuration.getSerializable(s, aClass);
/*     */   }
/*     */   
/*     */   public <T extends ConfigurationSerializable> T getSerializable(String s, Class<T> aClass, T t) {
/* 340 */     return (T)this.configuration.getSerializable(s, aClass, (ConfigurationSerializable)t);
/*     */   }
/*     */   
/*     */   public Vector getVector(String s) {
/* 345 */     return this.configuration.getVector(s);
/*     */   }
/*     */   
/*     */   public Vector getVector(String s, Vector vector) {
/* 350 */     return this.configuration.getVector(s, vector);
/*     */   }
/*     */   
/*     */   public boolean isVector(String s) {
/* 355 */     return this.configuration.isVector(s);
/*     */   }
/*     */   
/*     */   public OfflinePlayer getOfflinePlayer(String s) {
/* 360 */     return this.configuration.getOfflinePlayer(s);
/*     */   }
/*     */   
/*     */   public OfflinePlayer getOfflinePlayer(String s, OfflinePlayer offlinePlayer) {
/* 365 */     return this.configuration.getOfflinePlayer(s, offlinePlayer);
/*     */   }
/*     */   
/*     */   public boolean isOfflinePlayer(String s) {
/* 370 */     return this.configuration.isOfflinePlayer(s);
/*     */   }
/*     */   
/*     */   public ItemStack getItemStack(String s) {
/* 375 */     return this.configuration.getItemStack(s);
/*     */   }
/*     */   
/*     */   public ItemStack getItemStack(String s, ItemStack itemStack) {
/* 380 */     return this.configuration.getItemStack(s, itemStack);
/*     */   }
/*     */   
/*     */   public boolean isItemStack(String s) {
/* 385 */     return this.configuration.isItemStack(s);
/*     */   }
/*     */   
/*     */   public Color getColor(String s) {
/* 390 */     return this.configuration.getColor(s);
/*     */   }
/*     */   
/*     */   public Color getColor(String s, Color color) {
/* 395 */     return this.configuration.getColor(s, color);
/*     */   }
/*     */   
/*     */   public boolean isColor(String s) {
/* 400 */     return this.configuration.isColor(s);
/*     */   }
/*     */   
/*     */   public Location getLocation(String s) {
/* 405 */     return this.configuration.getLocation(s);
/*     */   }
/*     */   
/*     */   public Location getLocation(String s, Location location) {
/* 410 */     return this.configuration.getLocation(s, location);
/*     */   }
/*     */   
/*     */   public boolean isLocation(String s) {
/* 415 */     return this.configuration.isLocation(s);
/*     */   }
/*     */   
/*     */   public ConfigurationSection getConfigurationSection(String s) {
/* 420 */     return this.configuration.getConfigurationSection(s);
/*     */   }
/*     */   
/*     */   public boolean isConfigurationSection(String s) {
/* 425 */     return this.configuration.isConfigurationSection(s);
/*     */   }
/*     */   
/*     */   public ConfigurationSection getDefaultSection() {
/* 430 */     return this.configuration.getDefaultSection();
/*     */   }
/*     */   
/*     */   public void addDefault(String s, Object o) {
/* 435 */     this.configuration.addDefault(s, o);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFlip\config\CoinFlipConfig.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */