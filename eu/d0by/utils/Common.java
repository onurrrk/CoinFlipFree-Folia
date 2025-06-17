/*    */ package eu.d0by.utils;
/*    */ 
/*    */ import eu.d0by.utils.color.IridiumColorAPI;
/*    */ import java.awt.Color;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ 
/*    */ public class Common {
/*    */   public static String colorize(String string) {
/* 16 */     return IridiumColorAPI.process(string);
/*    */   }
/*    */   
/*    */   public static ChatColor fromHex(String string) {
/* 21 */     return IridiumColorAPI.getColor(string.replace("#", ""));
/*    */   }
/*    */   
/*    */   public static ChatColor fromString(String string) {
/* 26 */     return IridiumColorAPI.getColorByString(string);
/*    */   }
/*    */   
/*    */   public static String rainbow(String string, int saturation) {
/* 31 */     return IridiumColorAPI.rainbow(string, saturation);
/*    */   }
/*    */   
/*    */   public static boolean isValidLegacyColor(String string) {
/* 36 */     return IridiumColorAPI.isValidLegacyColor(string);
/*    */   }
/*    */   
/*    */   public static String CreateGradient(String chars, @Nonnull String start, @Nonnull String end, boolean hex) {
/* 41 */     Color color1 = new Color(16777215);
/* 42 */     Color color2 = new Color(16777215);
/* 44 */     if (hex) {
/* 46 */       color1 = new Color(Integer.parseInt(start.replace("#", ""), 16));
/* 47 */       color2 = new Color(Integer.parseInt(end.replace("#", ""), 16));
/*    */     } else {
/* 51 */       color1 = IridiumColorAPI.getJavaColorByString(start);
/* 52 */       color2 = IridiumColorAPI.getJavaColorByString(end);
/*    */     } 
/* 55 */     return IridiumColorAPI.color(chars, color1, color2);
/*    */   }
/*    */   
/*    */   public static List<String> colorize(List<String> list) {
/* 60 */     return (List<String>)list.stream().map(Common::colorize).collect(Collectors.toList());
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\eu\d0b\\utils\Common.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */