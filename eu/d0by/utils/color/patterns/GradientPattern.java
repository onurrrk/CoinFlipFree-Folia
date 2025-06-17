/*    */ package eu.d0by.utils.color.patterns;
/*    */ 
/*    */ import eu.d0by.utils.color.IridiumColorAPI;
/*    */ import java.awt.Color;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class GradientPattern implements Pattern {
/* 10 */   public static final Pattern PATTERN = Pattern.compile("[<{]#([A-Fa-f0-9]{6})[>}](.*?)[<{]/#([A-Fa-f0-9]{6})[>}]");
/*    */   
/*    */   public String process(String string) {
/* 20 */     Matcher matcher = PATTERN.matcher(string);
/* 21 */     while (matcher.find()) {
/* 22 */       String start = matcher.group(1);
/* 23 */       String end = matcher.group(3);
/* 24 */       String content = matcher.group(2);
/* 25 */       string = string.replace(matcher.group(), IridiumColorAPI.color(content, new Color(Integer.parseInt(start, 16)), new Color(Integer.parseInt(end, 16))));
/*    */     } 
/* 27 */     return string;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\eu\d0b\\utils\color\patterns\GradientPattern.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */