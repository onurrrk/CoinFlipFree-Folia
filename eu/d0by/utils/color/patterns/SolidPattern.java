/*    */ package eu.d0by.utils.color.patterns;
/*    */ 
/*    */ import eu.d0by.utils.color.IridiumColorAPI;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class SolidPattern implements Pattern {
/*  9 */   public static final Pattern PATTERN = Pattern.compile("[<{]#([A-Fa-f0-9]{6})[}>]|[&]?#([A-Fa-f0-9]{6})");
/*    */   
/*    */   public String process(String string) {
/* 19 */     Matcher matcher = PATTERN.matcher(string);
/* 20 */     while (matcher.find()) {
/* 21 */       String color = matcher.group(1);
/* 22 */       if (color == null)
/* 22 */         color = matcher.group(2); 
/* 24 */       string = string.replace(matcher.group(), (CharSequence)IridiumColorAPI.getColor(color));
/*    */     } 
/* 26 */     return string;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\eu\d0b\\utils\color\patterns\SolidPattern.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */