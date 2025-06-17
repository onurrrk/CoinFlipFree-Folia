/*    */ package sk.DexterSK.FreeCoinFlip.utilz;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import me.clip.placeholderapi.PlaceholderAPI;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class StringUtils {
/*    */   public static String setPlaceholders(OfflinePlayer paramOfflinePlayer, String paramString) {
/* 15 */     if (isPluginEnabled("PlaceholderAPI"))
/* 16 */       return PlaceholderAPI.setPlaceholders(paramOfflinePlayer, paramString); 
/* 17 */     return paramString;
/*    */   }
/*    */   
/*    */   public static boolean isPluginEnabled(String paramString) {
/* 22 */     return (Bukkit.getPluginManager().getPlugin(paramString) != null && ((Plugin)Objects.<Plugin>requireNonNull(Bukkit.getPluginManager().getPlugin(paramString))).isEnabled());
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFli\\utilz\StringUtils.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */