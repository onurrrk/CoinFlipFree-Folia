/*    */ package sk.DexterSK.FreeCoinFlip.utilz;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class UpdateChecker {
/*    */   private int pluginID;
/*    */   
/*    */   private URL url;
/*    */   
/*    */   private String latestVersion;
/*    */   
/*    */   private JavaPlugin plugin;
/*    */   
/*    */   public UpdateChecker(JavaPlugin plugin, int pluginID) {
/* 17 */     this.pluginID = 0;
/* 18 */     this.latestVersion = "";
/* 19 */     this.plugin = plugin;
/* 20 */     this.latestVersion = plugin.getDescription().getVersion();
/* 21 */     this.pluginID = pluginID;
/*    */     try {
/* 23 */       this.url = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + pluginID);
/* 25 */     } catch (MalformedURLException malformedURLException) {}
/*    */   }
/*    */   
/*    */   public int getProjectID() {
/* 29 */     return this.pluginID;
/*    */   }
/*    */   
/*    */   public JavaPlugin getPlugin() {
/* 33 */     return this.plugin;
/*    */   }
/*    */   
/*    */   public String getLatestVersion() {
/* 37 */     return this.latestVersion;
/*    */   }
/*    */   
/*    */   public String getResourceURL() {
/* 41 */     return "https://www.spigotmc.org/resources/" + this.pluginID;
/*    */   }
/*    */   
/*    */   public boolean checkForUpdates() {
/*    */     try {
/* 46 */       this.latestVersion = (new BufferedReader(new InputStreamReader(this.url.openConnection().getInputStream()))).readLine();
/* 47 */     } catch (IOException e) {
/* 48 */       e.printStackTrace();
/*    */     } 
/* 51 */     boolean hasUpdate = (Double.parseDouble(this.plugin.getDescription().getVersion()) < Double.parseDouble(this.latestVersion));
/* 53 */     return hasUpdate;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFli\\utilz\UpdateChecker.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */