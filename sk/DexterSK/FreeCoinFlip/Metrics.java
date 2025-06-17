/*     */ package sk.DexterSK.FreeCoinFlip;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.logging.Level;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class Metrics {
/*     */   private final Plugin plugin;
/*     */   
/*     */   private final MetricsBase metricsBase;
/*     */   
/*     */   public Metrics(JavaPlugin plugin, int serviceId) {
/*  64 */     this.plugin = (Plugin)plugin;
/*  66 */     File bStatsFolder = new File(plugin.getDataFolder().getParentFile(), "bStats");
/*  67 */     File configFile = new File(bStatsFolder, "config.yml");
/*  68 */     YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
/*  69 */     if (!config.isSet("serverUuid")) {
/*  70 */       config.addDefault("enabled", Boolean.valueOf(true));
/*  71 */       config.addDefault("serverUuid", UUID.randomUUID().toString());
/*  72 */       config.addDefault("logFailedRequests", Boolean.valueOf(false));
/*  73 */       config.addDefault("logSentData", Boolean.valueOf(false));
/*  74 */       config.addDefault("logResponseStatusText", Boolean.valueOf(false));
/*  76 */       config
/*  77 */         .options()
/*  78 */         .header(
/*  79 */           "bStats (https://bStats.org) collects some basic information for plugin authors, like how\nmany people use their plugin and their total player count. It's recommended to keep bStats\nenabled, but if you're not comfortable with this, you can turn this setting off. There is no\nperformance penalty associated with having metrics enabled, and data sent to bStats is fully\nanonymous.")
/*     */         
/*  84 */         .copyDefaults(true);
/*     */       try {
/*  86 */         config.save(configFile);
/*  87 */       } catch (IOException iOException) {}
/*     */     } 
/*  91 */     boolean enabled = config.getBoolean("enabled", true);
/*  92 */     String serverUUID = config.getString("serverUuid");
/*  93 */     boolean logErrors = config.getBoolean("logFailedRequests", false);
/*  94 */     boolean logSentData = config.getBoolean("logSentData", false);
/*  95 */     boolean logResponseStatusText = config.getBoolean("logResponseStatusText", false);
/*  96 */     this.metricsBase = 
/*  97 */       new MetricsBase(
/*  98 */         "bukkit", 
/*  99 */         serverUUID, 
/* 100 */         serviceId, 
/* 101 */         enabled, 
/* 102 */         this::appendPlatformData, 
/* 103 */         this::appendServiceData, submitDataTask -> {
/*     */         
/* 105 */         }plugin::isEnabled, (message, error) -> this.plugin.getLogger().log(Level.WARNING, message, error), message -> this.plugin.getLogger().log(Level.INFO, message), 
/*     */         
/* 108 */         logErrors, 
/* 109 */         logSentData, 
/* 110 */         logResponseStatusText);
/*     */   }
/*     */   
/*     */   public void addCustomChart(CustomChart chart) {
/* 119 */     this.metricsBase.addCustomChart(chart);
/*     */   }
/*     */   
/*     */   private void appendPlatformData(JsonObjectBuilder builder) {
/* 123 */     builder.appendField("playerAmount", getPlayerAmount());
/* 124 */     builder.appendField("onlineMode", Bukkit.getOnlineMode() ? 1 : 0);
/* 125 */     builder.appendField("bukkitVersion", Bukkit.getVersion());
/* 126 */     builder.appendField("bukkitName", Bukkit.getName());
/* 127 */     builder.appendField("javaVersion", System.getProperty("java.version"));
/* 128 */     builder.appendField("osName", System.getProperty("os.name"));
/* 129 */     builder.appendField("osArch", System.getProperty("os.arch"));
/* 130 */     builder.appendField("osVersion", System.getProperty("os.version"));
/* 131 */     builder.appendField("coreCount", Runtime.getRuntime().availableProcessors());
/*     */   }
/*     */   
/*     */   private void appendServiceData(JsonObjectBuilder builder) {
/* 135 */     builder.appendField("pluginVersion", this.plugin.getDescription().getVersion());
/*     */   }
/*     */   
/*     */   private int getPlayerAmount() {
/*     */     try {
/* 143 */       Method onlinePlayersMethod = Class.forName("org.bukkit.Server").getMethod("getOnlinePlayers", new Class[0]);
/* 144 */       return onlinePlayersMethod.getReturnType().equals(Collection.class) ? (
/* 145 */         (Collection)onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).size() : (
/* 146 */         (Player[])onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).length;
/* 147 */     } catch (Exception e) {
/* 149 */       return Bukkit.getOnlinePlayers().size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class MetricsBase {
/*     */     public static final String METRICS_VERSION = "3.0.0";
/*     */     
/*     */     private static final ScheduledExecutorService scheduler;
/*     */     
/*     */     private static final String REPORT_URL = "https://bStats.org/api/v2/data/%s";
/*     */     
/*     */     private final String platform;
/*     */     
/*     */     private final String serverUuid;
/*     */     
/*     */     private final int serviceId;
/*     */     
/*     */     private final Consumer<Metrics.JsonObjectBuilder> appendPlatformDataConsumer;
/*     */     
/*     */     private final Consumer<Metrics.JsonObjectBuilder> appendServiceDataConsumer;
/*     */     
/*     */     private final Consumer<Runnable> submitTaskConsumer;
/*     */     
/*     */     private final Supplier<Boolean> checkServiceEnabledSupplier;
/*     */     
/*     */     private final BiConsumer<String, Throwable> errorLogger;
/*     */     
/*     */     private final Consumer<String> infoLogger;
/*     */     
/*     */     private final boolean logErrors;
/*     */     
/*     */     private final boolean logSentData;
/*     */     
/*     */     private final boolean logResponseStatusText;
/*     */     
/*     */     static {
/* 158 */       scheduler = 
/* 159 */         Executors.newScheduledThreadPool(1, task -> new Thread(task, "bStats-Metrics"));
/*     */     }
/*     */     
/* 187 */     private final Set<Metrics.CustomChart> customCharts = new HashSet<>();
/*     */     
/*     */     private final boolean enabled;
/*     */     
/*     */     public MetricsBase(String platform, String serverUuid, int serviceId, boolean enabled, Consumer<Metrics.JsonObjectBuilder> appendPlatformDataConsumer, Consumer<Metrics.JsonObjectBuilder> appendServiceDataConsumer, Consumer<Runnable> submitTaskConsumer, Supplier<Boolean> checkServiceEnabledSupplier, BiConsumer<String, Throwable> errorLogger, Consumer<String> infoLogger, boolean logErrors, boolean logSentData, boolean logResponseStatusText) {
/* 226 */       this.platform = platform;
/* 227 */       this.serverUuid = serverUuid;
/* 228 */       this.serviceId = serviceId;
/* 229 */       this.enabled = enabled;
/* 230 */       this.appendPlatformDataConsumer = appendPlatformDataConsumer;
/* 231 */       this.appendServiceDataConsumer = appendServiceDataConsumer;
/* 232 */       this.submitTaskConsumer = submitTaskConsumer;
/* 233 */       this.checkServiceEnabledSupplier = checkServiceEnabledSupplier;
/* 234 */       this.errorLogger = errorLogger;
/* 235 */       this.infoLogger = infoLogger;
/* 236 */       this.logErrors = logErrors;
/* 237 */       this.logSentData = logSentData;
/* 238 */       this.logResponseStatusText = logResponseStatusText;
/* 239 */       checkRelocation();
/* 240 */       if (enabled)
/* 242 */         startSubmitting(); 
/*     */     }
/*     */     
/*     */     public void addCustomChart(Metrics.CustomChart chart) {
/* 247 */       this.customCharts.add(chart);
/*     */     }
/*     */     
/*     */     private void startSubmitting() {
/* 251 */       Runnable submitTask = () -> {
/*     */           if (!this.enabled || !((Boolean)this.checkServiceEnabledSupplier.get()).booleanValue()) {
/*     */             scheduler.shutdown();
/*     */             return;
/*     */           } 
/*     */           if (this.submitTaskConsumer != null) {
/*     */             this.submitTaskConsumer.accept(this::submitData);
/*     */           } else {
/*     */             submitData();
/*     */           } 
/*     */         };
/* 271 */       long initialDelay = (long)(60000.0D * (3.0D + Math.random() * 3.0D));
/* 272 */       long secondDelay = (long)(60000.0D * Math.random() * 30.0D);
/* 273 */       scheduler.schedule(submitTask, initialDelay, TimeUnit.MILLISECONDS);
/* 274 */       scheduler.scheduleAtFixedRate(
/* 275 */           submitTask, initialDelay + secondDelay, 1800000L, TimeUnit.MILLISECONDS);
/*     */     }
/*     */     
/*     */     private void submitData() {
/* 279 */       Metrics.JsonObjectBuilder baseJsonBuilder = new Metrics.JsonObjectBuilder();
/* 280 */       this.appendPlatformDataConsumer.accept(baseJsonBuilder);
/* 281 */       Metrics.JsonObjectBuilder serviceJsonBuilder = new Metrics.JsonObjectBuilder();
/* 282 */       this.appendServiceDataConsumer.accept(serviceJsonBuilder);
/* 283 */       Metrics.JsonObjectBuilder.JsonObject[] chartData = 
/* 284 */         (Metrics.JsonObjectBuilder.JsonObject[])this.customCharts.stream()
/* 285 */         .map(customChart -> customChart.getRequestJsonObject(this.errorLogger, this.logErrors))
/* 286 */         .filter(Objects::nonNull)
/* 287 */         .toArray(param1Int -> new Metrics.JsonObjectBuilder.JsonObject[param1Int]);
/* 288 */       serviceJsonBuilder.appendField("id", this.serviceId);
/* 289 */       serviceJsonBuilder.appendField("customCharts", chartData);
/* 290 */       baseJsonBuilder.appendField("service", serviceJsonBuilder.build());
/* 291 */       baseJsonBuilder.appendField("serverUUID", this.serverUuid);
/* 292 */       baseJsonBuilder.appendField("metricsVersion", "3.0.0");
/* 293 */       Metrics.JsonObjectBuilder.JsonObject data = baseJsonBuilder.build();
/* 294 */       scheduler.execute(() -> {
/*     */             try {
/*     */               sendData(param1JsonObject);
/* 299 */             } catch (Exception e) {
/*     */               if (this.logErrors)
/*     */                 this.errorLogger.accept("Could not submit bStats metrics data", e); 
/*     */             } 
/*     */           });
/*     */     }
/*     */     
/*     */     private void sendData(Metrics.JsonObjectBuilder.JsonObject data) throws Exception {
/*     */       StringBuilder builder;
/* 309 */       if (this.logSentData)
/* 310 */         this.infoLogger.accept("Sent bStats metrics data: " + data.toString()); 
/* 312 */       String url = String.format("https://bStats.org/api/v2/data/%s", new Object[] { this.platform });
/* 313 */       HttpsURLConnection connection = (HttpsURLConnection)(new URL(url)).openConnection();
/* 315 */       byte[] compressedData = compress(data.toString());
/* 316 */       connection.setRequestMethod("POST");
/* 317 */       connection.addRequestProperty("Accept", "application/json");
/* 318 */       connection.addRequestProperty("Connection", "close");
/* 319 */       connection.addRequestProperty("Content-Encoding", "gzip");
/* 320 */       connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
/* 321 */       connection.setRequestProperty("Content-Type", "application/json");
/* 322 */       connection.setRequestProperty("User-Agent", "Metrics-Service/1");
/* 323 */       connection.setDoOutput(true);
/* 324 */       Exception exception1 = null, exception2 = null;
/*     */       try {
/* 324 */         DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
/*     */         try {
/* 325 */           outputStream.write(compressedData);
/*     */         } finally {
/* 326 */           if (outputStream != null)
/* 326 */             outputStream.close(); 
/*     */         } 
/*     */       } finally {
/* 326 */         exception2 = null;
/* 326 */         if (exception1 == null) {
/* 326 */           exception1 = exception2;
/* 326 */         } else if (exception1 != exception2) {
/* 326 */           exception1.addSuppressed(exception2);
/*     */         } 
/*     */       } 
/* 328 */       Exception exception3 = null;
/*     */       try {
/* 328 */         BufferedReader bufferedReader = 
/* 329 */           new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*     */         try {
/*     */           String line;
/* 331 */           while ((line = bufferedReader.readLine()) != null)
/* 332 */             builder.append(line); 
/*     */         } finally {
/* 334 */           if (bufferedReader != null)
/* 334 */             bufferedReader.close(); 
/*     */         } 
/*     */       } finally {
/* 334 */         exception3 = null;
/* 334 */         if (exception2 == null) {
/* 334 */           exception2 = exception3;
/* 334 */         } else if (exception2 != exception3) {
/* 334 */           exception2.addSuppressed(exception3);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private void checkRelocation() {
/* 343 */       if (System.getProperty("bstats.relocatecheck") == null || 
/* 344 */         !System.getProperty("bstats.relocatecheck").equals("false")) {
/* 347 */         String defaultPackage = 
/* 348 */           new String(new byte[] { 111, 114, 103, 46, 98, 115, 116, 97, 116, 115 });
/* 349 */         String examplePackage = 
/* 350 */           new String(new byte[] { 
/* 350 */               121, 111, 117, 114, 46, 112, 97, 99, 107, 97, 
/* 350 */               103, 101 });
/* 353 */         if (MetricsBase.class.getPackage().getName().startsWith(defaultPackage) || 
/* 354 */           MetricsBase.class.getPackage().getName().startsWith(examplePackage))
/* 355 */           throw new IllegalStateException("bStats Metrics class has not been relocated correctly!"); 
/*     */       } 
/*     */     }
/*     */     
/*     */     private static byte[] compress(String str) throws IOException {
/* 367 */       if (str == null)
/* 368 */         return null; 
/* 370 */       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 371 */       Exception exception1 = null, exception2 = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DrilldownPie extends CustomChart {
/*     */     private final Callable<Map<String, Map<String, Integer>>> callable;
/*     */     
/*     */     public DrilldownPie(String chartId, Callable<Map<String, Map<String, Integer>>> callable) {
/* 389 */       super(chartId);
/* 390 */       this.callable = callable;
/*     */     }
/*     */     
/*     */     public Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 395 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 396 */       Map<String, Map<String, Integer>> map = this.callable.call();
/* 397 */       if (map == null || map.isEmpty())
/* 399 */         return null; 
/* 401 */       boolean reallyAllSkipped = true;
/* 402 */       for (Map.Entry<String, Map<String, Integer>> entryValues : map.entrySet()) {
/* 403 */         Metrics.JsonObjectBuilder valueBuilder = new Metrics.JsonObjectBuilder();
/* 404 */         boolean allSkipped = true;
/* 405 */         for (Map.Entry<String, Integer> valueEntry : (Iterable<Map.Entry<String, Integer>>)((Map)map.get(entryValues.getKey())).entrySet()) {
/* 406 */           valueBuilder.appendField(valueEntry.getKey(), ((Integer)valueEntry.getValue()).intValue());
/* 407 */           allSkipped = false;
/*     */         } 
/* 409 */         if (!allSkipped) {
/* 410 */           reallyAllSkipped = false;
/* 411 */           valuesBuilder.appendField(entryValues.getKey(), valueBuilder.build());
/*     */         } 
/*     */       } 
/* 414 */       if (reallyAllSkipped)
/* 416 */         return null; 
/* 418 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class AdvancedPie extends CustomChart {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */     
/*     */     public AdvancedPie(String chartId, Callable<Map<String, Integer>> callable) {
/* 433 */       super(chartId);
/* 434 */       this.callable = callable;
/*     */     }
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 439 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 440 */       Map<String, Integer> map = this.callable.call();
/* 441 */       if (map == null || map.isEmpty())
/* 443 */         return null; 
/* 445 */       boolean allSkipped = true;
/* 446 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 447 */         if (((Integer)entry.getValue()).intValue() == 0)
/*     */           continue; 
/* 451 */         allSkipped = false;
/* 452 */         valuesBuilder.appendField(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*     */       } 
/* 454 */       if (allSkipped)
/* 456 */         return null; 
/* 458 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MultiLineChart extends CustomChart {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */     
/*     */     public MultiLineChart(String chartId, Callable<Map<String, Integer>> callable) {
/* 473 */       super(chartId);
/* 474 */       this.callable = callable;
/*     */     }
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 479 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 480 */       Map<String, Integer> map = this.callable.call();
/* 481 */       if (map == null || map.isEmpty())
/* 483 */         return null; 
/* 485 */       boolean allSkipped = true;
/* 486 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 487 */         if (((Integer)entry.getValue()).intValue() == 0)
/*     */           continue; 
/* 491 */         allSkipped = false;
/* 492 */         valuesBuilder.appendField(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*     */       } 
/* 494 */       if (allSkipped)
/* 496 */         return null; 
/* 498 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SimpleBarChart extends CustomChart {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */     
/*     */     public SimpleBarChart(String chartId, Callable<Map<String, Integer>> callable) {
/* 513 */       super(chartId);
/* 514 */       this.callable = callable;
/*     */     }
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 519 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 520 */       Map<String, Integer> map = this.callable.call();
/* 521 */       if (map == null || map.isEmpty())
/* 523 */         return null; 
/* 525 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 526 */         valuesBuilder.appendField(entry.getKey(), new int[] { ((Integer)entry.getValue()).intValue() });
/*     */       } 
/* 528 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class CustomChart {
/*     */     private final String chartId;
/*     */     
/*     */     protected CustomChart(String chartId) {
/* 537 */       if (chartId == null)
/* 538 */         throw new IllegalArgumentException("chartId must not be null"); 
/* 540 */       this.chartId = chartId;
/*     */     }
/*     */     
/*     */     public Metrics.JsonObjectBuilder.JsonObject getRequestJsonObject(BiConsumer<String, Throwable> errorLogger, boolean logErrors) {
/* 545 */       Metrics.JsonObjectBuilder builder = new Metrics.JsonObjectBuilder();
/* 546 */       builder.appendField("chartId", this.chartId);
/*     */       try {
/* 548 */         Metrics.JsonObjectBuilder.JsonObject data = getChartData();
/* 549 */         if (data == null)
/* 551 */           return null; 
/* 553 */         builder.appendField("data", data);
/* 554 */       } catch (Throwable t) {
/* 555 */         if (logErrors)
/* 556 */           errorLogger.accept("Failed to get data for custom chart with id " + this.chartId, t); 
/* 558 */         return null;
/*     */       } 
/* 560 */       return builder.build();
/*     */     }
/*     */     
/*     */     protected abstract Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception;
/*     */   }
/*     */   
/*     */   public static class SimplePie extends CustomChart {
/*     */     private final Callable<String> callable;
/*     */     
/*     */     public SimplePie(String chartId, Callable<String> callable) {
/* 577 */       super(chartId);
/* 578 */       this.callable = callable;
/*     */     }
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 583 */       String value = this.callable.call();
/* 584 */       if (value == null || value.isEmpty())
/* 586 */         return null; 
/* 588 */       return (new Metrics.JsonObjectBuilder()).appendField("value", value).build();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class AdvancedBarChart extends CustomChart {
/*     */     private final Callable<Map<String, int[]>> callable;
/*     */     
/*     */     public AdvancedBarChart(String chartId, Callable<Map<String, int[]>> callable) {
/* 603 */       super(chartId);
/* 604 */       this.callable = callable;
/*     */     }
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 609 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 610 */       Map<String, int[]> map = this.callable.call();
/* 611 */       if (map == null || map.isEmpty())
/* 613 */         return null; 
/* 615 */       boolean allSkipped = true;
/* 616 */       for (Map.Entry<String, int[]> entry : map.entrySet()) {
/* 617 */         if (((int[])entry.getValue()).length == 0)
/*     */           continue; 
/* 621 */         allSkipped = false;
/* 622 */         valuesBuilder.appendField(entry.getKey(), entry.getValue());
/*     */       } 
/* 624 */       if (allSkipped)
/* 626 */         return null; 
/* 628 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SingleLineChart extends CustomChart {
/*     */     private final Callable<Integer> callable;
/*     */     
/*     */     public SingleLineChart(String chartId, Callable<Integer> callable) {
/* 643 */       super(chartId);
/* 644 */       this.callable = callable;
/*     */     }
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 649 */       int value = ((Integer)this.callable.call()).intValue();
/* 650 */       if (value == 0)
/* 652 */         return null; 
/* 654 */       return (new Metrics.JsonObjectBuilder()).appendField("value", value).build();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class JsonObjectBuilder {
/* 666 */     private StringBuilder builder = new StringBuilder();
/*     */     
/*     */     private boolean hasAtLeastOneField = false;
/*     */     
/*     */     public JsonObjectBuilder() {
/* 671 */       this.builder.append("{");
/*     */     }
/*     */     
/*     */     public JsonObjectBuilder appendNull(String key) {
/* 681 */       appendFieldUnescaped(key, "null");
/* 682 */       return this;
/*     */     }
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, String value) {
/* 693 */       if (value == null)
/* 694 */         throw new IllegalArgumentException("JSON value must not be null"); 
/* 696 */       appendFieldUnescaped(key, "\"" + escape(value) + "\"");
/* 697 */       return this;
/*     */     }
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, int value) {
/* 708 */       appendFieldUnescaped(key, String.valueOf(value));
/* 709 */       return this;
/*     */     }
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, JsonObject object) {
/* 720 */       if (object == null)
/* 721 */         throw new IllegalArgumentException("JSON object must not be null"); 
/* 723 */       appendFieldUnescaped(key, object.toString());
/* 724 */       return this;
/*     */     }
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, String[] values) {
/* 735 */       if (values == null)
/* 736 */         throw new IllegalArgumentException("JSON values must not be null"); 
/* 738 */       String escapedValues = 
/* 739 */         Arrays.<String>stream(values)
/* 740 */         .map(value -> "\"" + escape(value) + "\"")
/* 741 */         .collect(Collectors.joining(","));
/* 742 */       appendFieldUnescaped(key, "[" + escapedValues + "]");
/* 743 */       return this;
/*     */     }
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, int[] values) {
/* 754 */       if (values == null)
/* 755 */         throw new IllegalArgumentException("JSON values must not be null"); 
/* 757 */       String escapedValues = 
/* 758 */         Arrays.stream(values).<CharSequence>mapToObj(String::valueOf).collect(Collectors.joining(","));
/* 759 */       appendFieldUnescaped(key, "[" + escapedValues + "]");
/* 760 */       return this;
/*     */     }
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, JsonObject[] values) {
/* 771 */       if (values == null)
/* 772 */         throw new IllegalArgumentException("JSON values must not be null"); 
/* 774 */       String escapedValues = 
/* 775 */         Arrays.<JsonObject>stream(values).map(JsonObject::toString).collect(Collectors.joining(","));
/* 776 */       appendFieldUnescaped(key, "[" + escapedValues + "]");
/* 777 */       return this;
/*     */     }
/*     */     
/*     */     private void appendFieldUnescaped(String key, String escapedValue) {
/* 787 */       if (this.builder == null)
/* 788 */         throw new IllegalStateException("JSON has already been built"); 
/* 790 */       if (key == null)
/* 791 */         throw new IllegalArgumentException("JSON key must not be null"); 
/* 793 */       if (this.hasAtLeastOneField)
/* 794 */         this.builder.append(","); 
/* 796 */       this.builder.append("\"").append(escape(key)).append("\":").append(escapedValue);
/* 797 */       this.hasAtLeastOneField = true;
/*     */     }
/*     */     
/*     */     public JsonObject build() {
/* 806 */       if (this.builder == null)
/* 807 */         throw new IllegalStateException("JSON has already been built"); 
/* 809 */       JsonObject object = new JsonObject(this.builder.append("}").toString());
/* 810 */       this.builder = null;
/* 811 */       return object;
/*     */     }
/*     */     
/*     */     private static String escape(String value) {
/* 824 */       StringBuilder builder = new StringBuilder();
/* 825 */       for (int i = 0; i < value.length(); i++) {
/* 826 */         char c = value.charAt(i);
/* 827 */         if (c == '"') {
/* 828 */           builder.append("\\\"");
/* 829 */         } else if (c == '\\') {
/* 830 */           builder.append("\\\\");
/* 831 */         } else if (c <= '\017') {
/* 832 */           builder.append("\\u000").append(Integer.toHexString(c));
/* 833 */         } else if (c <= '\037') {
/* 834 */           builder.append("\\u00").append(Integer.toHexString(c));
/*     */         } else {
/* 836 */           builder.append(c);
/*     */         } 
/*     */       } 
/* 839 */       return builder.toString();
/*     */     }
/*     */     
/*     */     public static class JsonObject {
/*     */       private final String value;
/*     */       
/*     */       private JsonObject(String value) {
/* 854 */         this.value = value;
/*     */       }
/*     */       
/*     */       public String toString() {
/* 859 */         return this.value;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFlip\Metrics.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */