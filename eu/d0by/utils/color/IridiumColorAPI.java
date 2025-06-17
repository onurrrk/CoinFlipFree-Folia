/*     */ package eu.d0by.utils.color;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import eu.d0by.utils.color.patterns.GradientPattern;
/*     */ import eu.d0by.utils.color.patterns.Pattern;
/*     */ import eu.d0by.utils.color.patterns.RainbowPattern;
/*     */ import eu.d0by.utils.color.patterns.SolidPattern;
/*     */ import java.awt.Color;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ 
/*     */ public class IridiumColorAPI {
/*  29 */   private static final int VERSION = getVersion();
/*     */   
/*  36 */   private static final boolean SUPPORTS_RGB = (VERSION >= 16);
/*     */   
/*  38 */   private static final List<String> SPECIAL_COLORS = Arrays.asList(new String[] { "&l", "&n", "&o", "&k", "&m", "§l", "§n", "§o", "§k", "§m" });
/*     */   
/*  45 */   private static final Map<String, Color> COLORS_JAVA = (Map<String, Color>)ImmutableMap.builder()
/*  46 */     .put(new String("black"), new Color(0))
/*  47 */     .put(new String("dark_blue"), new Color(170))
/*  48 */     .put(new String("dark_green"), new Color(43520))
/*  49 */     .put(new String("dark_aqua"), new Color(43690))
/*  50 */     .put(new String("dark_red"), new Color(11141120))
/*  51 */     .put(new String("dark_purple"), new Color(11141290))
/*  52 */     .put(new String("gold"), new Color(16755200))
/*  53 */     .put(new String("gray"), new Color(11184810))
/*  54 */     .put(new String("dark_gray"), new Color(5592405))
/*  55 */     .put(new String("blue"), new Color(5592575))
/*  56 */     .put(new String("green"), new Color(5635925))
/*  57 */     .put(new String("aqua"), new Color(5636095))
/*  58 */     .put(new String("red"), new Color(16733525))
/*  59 */     .put(new String("light_purple"), new Color(16733695))
/*  60 */     .put(new String("yellow"), new Color(16777045))
/*  61 */     .put(new String("white"), new Color(16777215)).build();
/*     */   
/*  67 */   private static final Map<Color, ChatColor> COLORS = (Map<Color, ChatColor>)ImmutableMap.builder()
/*  68 */     .put(new Color(0), ChatColor.getByChar('0'))
/*  69 */     .put(new Color(170), ChatColor.getByChar('1'))
/*  70 */     .put(new Color(43520), ChatColor.getByChar('2'))
/*  71 */     .put(new Color(43690), ChatColor.getByChar('3'))
/*  72 */     .put(new Color(11141120), ChatColor.getByChar('4'))
/*  73 */     .put(new Color(11141290), ChatColor.getByChar('5'))
/*  74 */     .put(new Color(16755200), ChatColor.getByChar('6'))
/*  75 */     .put(new Color(11184810), ChatColor.getByChar('7'))
/*  76 */     .put(new Color(5592405), ChatColor.getByChar('8'))
/*  77 */     .put(new Color(5592575), ChatColor.getByChar('9'))
/*  78 */     .put(new Color(5635925), ChatColor.getByChar('a'))
/*  79 */     .put(new Color(5636095), ChatColor.getByChar('b'))
/*  80 */     .put(new Color(16733525), ChatColor.getByChar('c'))
/*  81 */     .put(new Color(16733695), ChatColor.getByChar('d'))
/*  82 */     .put(new Color(16777045), ChatColor.getByChar('e'))
/*  83 */     .put(new Color(16777215), ChatColor.getByChar('f')).build();
/*     */   
/*  89 */   private static final Map<String, ChatColor> COLORS_MAP = (Map<String, ChatColor>)ImmutableMap.builder()
/*  90 */     .put(new String("black"), ChatColor.getByChar('0'))
/*  91 */     .put(new String("dark_blue"), ChatColor.getByChar('1'))
/*  92 */     .put(new String("dark_green"), ChatColor.getByChar('2'))
/*  93 */     .put(new String("dark_aqua"), ChatColor.getByChar('3'))
/*  94 */     .put(new String("dark_red"), ChatColor.getByChar('4'))
/*  95 */     .put(new String("dark_purple"), ChatColor.getByChar('5'))
/*  96 */     .put(new String("gold"), ChatColor.getByChar('6'))
/*  97 */     .put(new String("gray"), ChatColor.getByChar('7'))
/*  98 */     .put(new String("dark_gray"), ChatColor.getByChar('8'))
/*  99 */     .put(new String("blue"), ChatColor.getByChar('9'))
/* 100 */     .put(new String("green"), ChatColor.getByChar('a'))
/* 101 */     .put(new String("aqua"), ChatColor.getByChar('b'))
/* 102 */     .put(new String("red"), ChatColor.getByChar('c'))
/* 103 */     .put(new String("light_purple"), ChatColor.getByChar('d'))
/* 104 */     .put(new String("yellow"), ChatColor.getByChar('e'))
/* 105 */     .put(new String("white"), ChatColor.getByChar('f')).build();
/*     */   
/* 111 */   private static final List<Pattern> PATTERNS = Arrays.asList(new Pattern[] { (Pattern)new GradientPattern(), (Pattern)new SolidPattern(), (Pattern)new RainbowPattern() });
/*     */   
/*     */   @Nonnull
/*     */   public static String process(@Nonnull String string) {
/* 122 */     for (Pattern pattern : PATTERNS)
/* 123 */       string = pattern.process(string); 
/* 126 */     string = ChatColor.translateAlternateColorCodes('&', string);
/* 127 */     return string;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static List<String> process(@Nonnull List<String> strings) {
/* 139 */     return (List<String>)strings.stream()
/* 140 */       .map(IridiumColorAPI::process)
/* 141 */       .collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static String color(@Nonnull String string, @Nonnull Color color) {
/* 153 */     return (SUPPORTS_RGB ? (String)ChatColor.of(color) : (String)getClosestColor(color)) + string;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static String color(@Nonnull String string, @Nonnull Color start, @Nonnull Color end) {
/* 166 */     String originalString = string;
/* 168 */     ChatColor[] colors = createGradient(start, end, withoutSpecialChar(string).length());
/* 169 */     return apply(originalString, colors);
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static String rainbow(@Nonnull String string, float saturation) {
/* 181 */     String originalString = string;
/* 183 */     ChatColor[] colors = createRainbow(withoutSpecialChar(string).length(), saturation);
/* 184 */     return apply(originalString, colors);
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static ChatColor getColor(@Nonnull String string) {
/* 195 */     return SUPPORTS_RGB ? ChatColor.of(new Color(Integer.parseInt(string, 16))) : getClosestColor(new Color(Integer.parseInt(string, 16)));
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static String stripColorFormatting(@Nonnull String string) {
/* 208 */     return string.replaceAll("<#[0-9A-F]{6}>|[&§][a-f0-9lnokm]|<[/]?[A-Z]{5,8}(:[0-9A-F]{6})?[0-9]*>", "");
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   private static String apply(@Nonnull String source, ChatColor[] colors) {
/* 213 */     StringBuilder specialColors = new StringBuilder();
/* 214 */     StringBuilder stringBuilder = new StringBuilder();
/* 215 */     String[] characters = source.split("");
/* 216 */     int outIndex = 0;
/* 217 */     for (int i = 0; i < characters.length; i++) {
/* 218 */       if (characters[i].equals("&") || characters[i].equals("§")) {
/* 219 */         if (i + 1 < characters.length) {
/* 220 */           if (characters[i + 1].equals("r")) {
/* 221 */             specialColors.setLength(0);
/*     */           } else {
/* 223 */             specialColors.append(characters[i]);
/* 224 */             specialColors.append(characters[i + 1]);
/*     */           } 
/* 226 */           i++;
/*     */         } else {
/* 228 */           stringBuilder.append(colors[outIndex++]).append(specialColors).append(characters[i]);
/*     */         } 
/*     */       } else {
/* 230 */         stringBuilder.append(colors[outIndex++]).append(specialColors).append(characters[i]);
/*     */       } 
/*     */     } 
/* 232 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   private static String withoutSpecialChar(@Nonnull String source) {
/* 237 */     String workingString = source;
/* 238 */     for (String color : SPECIAL_COLORS) {
/* 239 */       if (workingString.contains(color))
/* 240 */         workingString = workingString.replace(color, ""); 
/*     */     } 
/* 243 */     return workingString;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   private static ChatColor[] createRainbow(int step, float saturation) {
/* 256 */     ChatColor[] colors = new ChatColor[step];
/* 257 */     double colorStep = 1.0D / step;
/* 259 */     for (int i = 0; i < step; i++) {
/* 260 */       Color color = Color.getHSBColor((float)(colorStep * i), saturation, saturation);
/* 261 */       if (SUPPORTS_RGB) {
/* 262 */         colors[i] = ChatColor.of(color);
/*     */       } else {
/* 264 */         colors[i] = getClosestColor(color);
/*     */       } 
/*     */     } 
/* 268 */     return colors;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   private static ChatColor[] createGradient(@Nonnull Color start, @Nonnull Color end, int step) {
/* 282 */     ChatColor[] colors = new ChatColor[step];
/* 283 */     int stepR = Math.abs(start.getRed() - end.getRed()) / (step - 1);
/* 284 */     int stepG = Math.abs(start.getGreen() - end.getGreen()) / (step - 1);
/* 285 */     int stepB = Math.abs(start.getBlue() - end.getBlue()) / (step - 1);
/* 286 */     int[] direction = { (start.getRed() < end.getRed()) ? 1 : -1, 
/* 288 */         (start.getGreen() < end.getGreen()) ? 1 : -1, 
/* 289 */         (start.getBlue() < end.getBlue()) ? 1 : -1 };
/* 292 */     for (int i = 0; i < step; i++) {
/* 293 */       Color color = new Color(start.getRed() + stepR * i * direction[0], start.getGreen() + stepG * i * direction[1], start.getBlue() + stepB * i * direction[2]);
/* 294 */       if (SUPPORTS_RGB) {
/* 295 */         colors[i] = ChatColor.of(color);
/*     */       } else {
/* 297 */         colors[i] = getClosestColor(color);
/*     */       } 
/*     */     } 
/* 301 */     return colors;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   private static ChatColor getClosestColor(Color color) {
/* 312 */     Color nearestColor = null;
/* 313 */     double nearestDistance = 2.147483647E9D;
/* 315 */     for (Color constantColor : COLORS.keySet()) {
/* 316 */       double distance = Math.pow((color.getRed() - constantColor.getRed()), 2.0D) + Math.pow((color.getGreen() - constantColor.getGreen()), 2.0D) + Math.pow((color.getBlue() - constantColor.getBlue()), 2.0D);
/* 317 */       if (nearestDistance > distance) {
/* 318 */         nearestColor = constantColor;
/* 319 */         nearestDistance = distance;
/*     */       } 
/*     */     } 
/* 322 */     return COLORS.get(nearestColor);
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static ChatColor getColorByString(String color) {
/* 333 */     if (COLORS_MAP.containsKey(color))
/* 334 */       return COLORS_MAP.get(color); 
/* 336 */     return COLORS_MAP.get("white");
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static Color getJavaColorByString(String color) {
/* 346 */     if (COLORS_MAP.containsKey(color))
/* 347 */       return COLORS_JAVA.get(color); 
/* 349 */     return COLORS_JAVA.get("white");
/*     */   }
/*     */   
/*     */   public static <K, V> K getKey(Map<K, V> map, V value) {
/* 353 */     for (Map.Entry<K, V> entry : map.entrySet()) {
/* 354 */       if (entry.getValue().equals(value))
/* 355 */         return entry.getKey(); 
/*     */     } 
/* 358 */     return null;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static Color getColorByChatColor(ChatColor color) {
/* 363 */     if (COLORS.containsValue(color))
/* 364 */       return getKey(COLORS, color); 
/* 366 */     return new Color(16777215);
/*     */   }
/*     */   
/*     */   public static boolean isValidLegacyColor(String color) {
/* 371 */     return COLORS_MAP.containsKey(color);
/*     */   }
/*     */   
/*     */   private static int getVersion() {
/* 381 */     String version = Bukkit.getVersion();
/* 382 */     Validate.notEmpty(version, "Cannot get major Minecraft version from null or empty string");
/* 385 */     int index = version.lastIndexOf("MC:");
/* 386 */     if (index != -1) {
/* 387 */       version = version.substring(index + 4, version.length() - 1);
/* 388 */     } else if (version.endsWith("SNAPSHOT")) {
/* 390 */       index = version.indexOf('-');
/* 391 */       version = version.substring(0, index);
/*     */     } 
/* 395 */     int lastDot = version.lastIndexOf('.');
/* 396 */     if (version.indexOf('.') != lastDot)
/* 396 */       version = version.substring(0, lastDot); 
/* 398 */     return Integer.parseInt(version.substring(2));
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\eu\d0b\\utils\color\IridiumColorAPI.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */