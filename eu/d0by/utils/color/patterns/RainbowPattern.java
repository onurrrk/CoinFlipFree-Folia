/*    */ package eu.d0by.utils.color.patterns;
/*    */ 
/*    */ import eu.d0by.utils.color.IridiumColorAPI;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class RainbowPattern implements Pattern {
/*  8 */   Pattern pattern = Pattern.compile("<RAINBOW([0-9]{1,3})>(.*?)</RAINBOW>");
/*    */   
/*    */   public String process(String string) {
/* 18 */     Matcher matcher = this.pattern.matcher(string);
/* 19 */     while (matcher.find()) {
/* 20 */       String saturation = matcher.group(1);
/* 21 */       String content = matcher.group(2);
/* 22 */       string = string.replace(matcher.group(), IridiumColorAPI.rainbow(content, Float.parseFloat(saturation)));
/*    */     } 
/* 24 */     return string;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\eu\d0b\\utils\color\patterns\RainbowPattern.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */