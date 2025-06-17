/*      */ package sk.DexterSK.FreeCoinFlip.utilz;
/*      */ 
/*      */ import com.google.common.cache.Cache;
/*      */ import com.google.common.cache.CacheBuilder;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import java.util.regex.PatternSyntaxException;
/*      */ import java.util.stream.Collectors;
/*      */ import javax.annotation.Nonnull;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.meta.ItemMeta;
/*      */ import org.bukkit.inventory.meta.SpawnEggMeta;
/*      */ import org.bukkit.potion.Potion;
/*      */ 
/*      */ public enum XMaterial {
/*   70 */   ACACIA_BOAT(new String[] { "BOAT_ACACIA" }),
/*   71 */   ACACIA_BUTTON(new String[] { "WOOD_BUTTON" }),
/*   72 */   ACACIA_CHEST_BOAT(new String[0]),
/*   73 */   ACACIA_DOOR(new String[] { "ACACIA_DOOR", "ACACIA_DOOR_ITEM" }),
/*   74 */   ACACIA_FENCE(new String[0]),
/*   75 */   ACACIA_FENCE_GATE(new String[0]),
/*   76 */   ACACIA_LEAVES(0, new String[] { "LEAVES_2" }),
/*   77 */   ACACIA_LOG(0, new String[] { "LOG_2" }),
/*   78 */   ACACIA_PLANKS(4, new String[] { "WOOD" }),
/*   79 */   ACACIA_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*   80 */   ACACIA_SAPLING(4, new String[] { "SAPLING" }),
/*   81 */   ACACIA_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*   82 */   ACACIA_SLAB(4, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*   83 */   ACACIA_STAIRS(new String[0]),
/*   84 */   ACACIA_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*   85 */   ACACIA_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*   86 */   ACACIA_WOOD(0, new String[] { "LOG_2" }),
/*   87 */   ACTIVATOR_RAIL(new String[0]),
/*   88 */   AIR(new String[0]),
/*   96 */   ALLAY_SPAWN_EGG(new String[0]),
/*   97 */   ALLIUM(2, new String[] { "RED_ROSE" }),
/*   98 */   AMETHYST_BLOCK(new String[0]),
/*   99 */   AMETHYST_CLUSTER(new String[0]),
/*  100 */   AMETHYST_SHARD(new String[0]),
/*  101 */   ANCIENT_DEBRIS(new String[0]),
/*  102 */   ANDESITE(5, new String[] { "STONE" }),
/*  103 */   ANDESITE_SLAB(new String[0]),
/*  104 */   ANDESITE_STAIRS(new String[0]),
/*  105 */   ANDESITE_WALL(new String[0]),
/*  106 */   ANVIL(new String[0]),
/*  107 */   APPLE(new String[0]),
/*  108 */   ARMOR_STAND(new String[0]),
/*  109 */   ARROW(new String[0]),
/*  110 */   ATTACHED_MELON_STEM(7, new String[] { "MELON_STEM" }),
/*  111 */   ATTACHED_PUMPKIN_STEM(7, new String[] { "PUMPKIN_STEM" }),
/*  112 */   AXOLOTL_BUCKET(new String[0]),
/*  113 */   AXOLOTL_SPAWN_EGG(new String[0]),
/*  114 */   AZALEA(new String[0]),
/*  115 */   AZALEA_LEAVES(new String[0]),
/*  116 */   AZURE_BLUET(3, new String[] { "RED_ROSE" }),
/*  117 */   BAKED_POTATO(new String[0]),
/*  118 */   BAMBOO(new String[0]),
/*  119 */   BAMBOO_SAPLING(new String[0]),
/*  120 */   BARREL(new String[0]),
/*  121 */   BARRIER(new String[0]),
/*  122 */   BASALT(new String[0]),
/*  123 */   BAT_SPAWN_EGG(65, new String[] { "MONSTER_EGG" }),
/*  124 */   BEACON(new String[0]),
/*  125 */   BEDROCK(new String[0]),
/*  126 */   BEEF(new String[] { "RAW_BEEF" }),
/*  127 */   BEEHIVE(new String[0]),
/*  128 */   BEETROOT(new String[] { "BEETROOT_BLOCK" }),
/*  132 */   BEETROOTS(new String[] { "BEETROOT" }),
/*  133 */   BEETROOT_SEEDS(new String[0]),
/*  134 */   BEETROOT_SOUP(new String[0]),
/*  135 */   BEE_NEST(new String[0]),
/*  136 */   BEE_SPAWN_EGG(new String[0]),
/*  137 */   BELL(new String[0]),
/*  138 */   BIG_DRIPLEAF(new String[0]),
/*  139 */   BIG_DRIPLEAF_STEM(new String[0]),
/*  140 */   BIRCH_BOAT(new String[] { "BOAT_BIRCH" }),
/*  141 */   BIRCH_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  142 */   BIRCH_CHEST_BOAT(new String[0]),
/*  143 */   BIRCH_DOOR(new String[] { "BIRCH_DOOR", "BIRCH_DOOR_ITEM" }),
/*  144 */   BIRCH_FENCE(new String[0]),
/*  145 */   BIRCH_FENCE_GATE(new String[0]),
/*  146 */   BIRCH_LEAVES(2, new String[] { "LEAVES" }),
/*  147 */   BIRCH_LOG(2, new String[] { "LOG" }),
/*  148 */   BIRCH_PLANKS(2, new String[] { "WOOD" }),
/*  149 */   BIRCH_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*  150 */   BIRCH_SAPLING(2, new String[] { "SAPLING" }),
/*  151 */   BIRCH_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  152 */   BIRCH_SLAB(2, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  153 */   BIRCH_STAIRS(new String[] { "BIRCH_WOOD_STAIRS" }),
/*  154 */   BIRCH_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*  155 */   BIRCH_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  156 */   BIRCH_WOOD(2, new String[] { "LOG" }),
/*  157 */   BLACKSTONE(new String[0]),
/*  158 */   BLACKSTONE_SLAB(new String[0]),
/*  159 */   BLACKSTONE_STAIRS(new String[0]),
/*  160 */   BLACKSTONE_WALL(new String[0]),
/*  161 */   BLACK_BANNER(new String[] { "STANDING_BANNER", "BANNER" }),
/*  162 */   BLACK_BED(
/*      */     
/*  165 */     supports(12) ? 15 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  166 */   BLACK_CANDLE(new String[0]),
/*  167 */   BLACK_CANDLE_CAKE(new String[0]),
/*  168 */   BLACK_CARPET(15, new String[] { "CARPET" }),
/*  169 */   BLACK_CONCRETE(15, new String[] { "CONCRETE" }),
/*  170 */   BLACK_CONCRETE_POWDER(15, new String[] { "CONCRETE_POWDER" }),
/*  171 */   BLACK_DYE(new String[0]),
/*  172 */   BLACK_GLAZED_TERRACOTTA(new String[0]),
/*  173 */   BLACK_SHULKER_BOX(new String[0]),
/*  174 */   BLACK_STAINED_GLASS(15, new String[] { "STAINED_GLASS" }),
/*  175 */   BLACK_STAINED_GLASS_PANE(15, new String[] { "STAINED_GLASS_PANE" }),
/*  176 */   BLACK_TERRACOTTA(15, new String[] { "STAINED_CLAY" }),
/*  177 */   BLACK_WALL_BANNER(new String[] { "WALL_BANNER" }),
/*  178 */   BLACK_WOOL(15, new String[] { "WOOL" }),
/*  179 */   BLAST_FURNACE(new String[0]),
/*  180 */   BLAZE_POWDER(new String[0]),
/*  181 */   BLAZE_ROD(new String[0]),
/*  182 */   BLAZE_SPAWN_EGG(61, new String[] { "MONSTER_EGG" }),
/*  183 */   BLUE_BANNER(4, new String[] { "STANDING_BANNER", "BANNER" }),
/*  184 */   BLUE_BED(supports(12) ? 11 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  185 */   BLUE_CANDLE(new String[0]),
/*  186 */   BLUE_CANDLE_CAKE(new String[0]),
/*  187 */   BLUE_CARPET(11, new String[] { "CARPET" }),
/*  188 */   BLUE_CONCRETE(11, new String[] { "CONCRETE" }),
/*  189 */   BLUE_CONCRETE_POWDER(11, new String[] { "CONCRETE_POWDER" }),
/*  190 */   BLUE_DYE(4, new String[] { "INK_SACK", "LAPIS_LAZULI" }),
/*  191 */   BLUE_GLAZED_TERRACOTTA(new String[0]),
/*  192 */   BLUE_ICE(new String[0]),
/*  193 */   BLUE_ORCHID(1, new String[] { "RED_ROSE" }),
/*  194 */   BLUE_SHULKER_BOX(new String[0]),
/*  195 */   BLUE_STAINED_GLASS(11, new String[] { "STAINED_GLASS" }),
/*  196 */   BLUE_STAINED_GLASS_PANE(11, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  197 */   BLUE_TERRACOTTA(11, new String[] { "STAINED_CLAY" }),
/*  198 */   BLUE_WALL_BANNER(4, new String[] { "WALL_BANNER" }),
/*  199 */   BLUE_WOOL(11, new String[] { "WOOL" }),
/*  200 */   BONE(new String[0]),
/*  201 */   BONE_BLOCK(new String[0]),
/*  202 */   BONE_MEAL(15, new String[] { "INK_SACK" }),
/*  203 */   BOOK(new String[0]),
/*  204 */   BOOKSHELF(new String[0]),
/*  205 */   BOW(new String[0]),
/*  206 */   BOWL(new String[0]),
/*  207 */   BRAIN_CORAL(new String[0]),
/*  208 */   BRAIN_CORAL_BLOCK(new String[0]),
/*  209 */   BRAIN_CORAL_FAN(new String[0]),
/*  210 */   BRAIN_CORAL_WALL_FAN(new String[0]),
/*  211 */   BREAD(new String[0]),
/*  212 */   BREWING_STAND(new String[] { "BREWING_STAND", "BREWING_STAND_ITEM" }),
/*  213 */   BRICK(new String[] { "CLAY_BRICK" }),
/*  214 */   BRICKS(new String[] { "BRICKS", "BRICK" }),
/*  215 */   BRICK_SLAB(4, new String[] { "STEP" }),
/*  216 */   BRICK_STAIRS(new String[0]),
/*  217 */   BRICK_WALL(new String[0]),
/*  218 */   BROWN_BANNER(3, new String[] { "STANDING_BANNER", "BANNER" }),
/*  219 */   BROWN_BED(supports(12) ? 12 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  220 */   BROWN_CANDLE(new String[0]),
/*  221 */   BROWN_CANDLE_CAKE(new String[0]),
/*  222 */   BROWN_CARPET(12, new String[] { "CARPET" }),
/*  223 */   BROWN_CONCRETE(12, new String[] { "CONCRETE" }),
/*  224 */   BROWN_CONCRETE_POWDER(12, new String[] { "CONCRETE_POWDER" }),
/*  225 */   BROWN_DYE(3, new String[] { "INK_SACK", "DYE", "COCOA_BEANS" }),
/*  226 */   BROWN_GLAZED_TERRACOTTA(new String[0]),
/*  227 */   BROWN_MUSHROOM(new String[0]),
/*  228 */   BROWN_MUSHROOM_BLOCK(new String[] { "BROWN_MUSHROOM", "HUGE_MUSHROOM_1" }),
/*  229 */   BROWN_SHULKER_BOX(new String[0]),
/*  230 */   BROWN_STAINED_GLASS(12, new String[] { "STAINED_GLASS" }),
/*  231 */   BROWN_STAINED_GLASS_PANE(12, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  232 */   BROWN_TERRACOTTA(12, new String[] { "STAINED_CLAY" }),
/*  233 */   BROWN_WALL_BANNER(3, new String[] { "WALL_BANNER" }),
/*  234 */   BROWN_WOOL(12, new String[] { "WOOL" }),
/*  235 */   BUBBLE_COLUMN(new String[0]),
/*  236 */   BUBBLE_CORAL(new String[0]),
/*  237 */   BUBBLE_CORAL_BLOCK(new String[0]),
/*  238 */   BUBBLE_CORAL_FAN(new String[0]),
/*  239 */   BUBBLE_CORAL_WALL_FAN(new String[0]),
/*  240 */   BUCKET(new String[0]),
/*  241 */   BUDDING_AMETHYST(new String[0]),
/*  242 */   BUNDLE(new String[0]),
/*  243 */   CACTUS(new String[0]),
/*  244 */   CAKE(new String[] { "CAKE_BLOCK" }),
/*  245 */   CALCITE(new String[0]),
/*  246 */   CAMPFIRE(new String[0]),
/*  247 */   CANDLE(new String[0]),
/*  248 */   CANDLE_CAKE(new String[0]),
/*  249 */   CARROT(new String[] { "CARROT_ITEM" }),
/*  250 */   CARROTS(new String[] { "CARROT" }),
/*  251 */   CARROT_ON_A_STICK(new String[] { "CARROT_STICK" }),
/*  252 */   CARTOGRAPHY_TABLE(new String[0]),
/*  253 */   CARVED_PUMPKIN(new String[0]),
/*  254 */   CAT_SPAWN_EGG(new String[0]),
/*  255 */   CAULDRON(new String[] { "CAULDRON", "CAULDRON_ITEM" }),
/*  256 */   CAVE_AIR(new String[] { "AIR" }),
/*  262 */   CAVE_SPIDER_SPAWN_EGG(59, new String[] { "MONSTER_EGG" }),
/*  263 */   CAVE_VINES(new String[0]),
/*  264 */   CAVE_VINES_PLANT(new String[0]),
/*  265 */   CHAIN(new String[0]),
/*  266 */   CHAINMAIL_BOOTS(new String[0]),
/*  267 */   CHAINMAIL_CHESTPLATE(new String[0]),
/*  268 */   CHAINMAIL_HELMET(new String[0]),
/*  269 */   CHAINMAIL_LEGGINGS(new String[0]),
/*  270 */   CHAIN_COMMAND_BLOCK(new String[] { "COMMAND", "COMMAND_CHAIN" }),
/*  271 */   CHARCOAL(1, new String[] { "COAL" }),
/*  272 */   CHEST(new String[] { "LOCKED_CHEST" }),
/*  273 */   CHEST_MINECART(new String[] { "STORAGE_MINECART" }),
/*  274 */   CHICKEN(new String[] { "RAW_CHICKEN" }),
/*  275 */   CHICKEN_SPAWN_EGG(93, new String[] { "MONSTER_EGG" }),
/*  276 */   CHIPPED_ANVIL(1, new String[] { "ANVIL" }),
/*  277 */   CHISELED_DEEPSLATE(new String[0]),
/*  278 */   CHISELED_NETHER_BRICKS(1, new String[] { "NETHER_BRICKS" }),
/*  279 */   CHISELED_POLISHED_BLACKSTONE(new String[] { "POLISHED_BLACKSTONE" }),
/*  280 */   CHISELED_QUARTZ_BLOCK(1, new String[] { "QUARTZ_BLOCK" }),
/*  281 */   CHISELED_RED_SANDSTONE(1, new String[] { "RED_SANDSTONE" }),
/*  282 */   CHISELED_SANDSTONE(1, new String[] { "SANDSTONE" }),
/*  283 */   CHISELED_STONE_BRICKS(3, new String[] { "SMOOTH_BRICK" }),
/*  284 */   CHORUS_FLOWER(new String[0]),
/*  285 */   CHORUS_FRUIT(new String[0]),
/*  286 */   CHORUS_PLANT(new String[0]),
/*  287 */   CLAY(new String[0]),
/*  288 */   CLAY_BALL(new String[0]),
/*  289 */   CLOCK(new String[] { "WATCH" }),
/*  290 */   COAL(new String[0]),
/*  291 */   COAL_BLOCK(new String[0]),
/*  292 */   COAL_ORE(new String[0]),
/*  293 */   COARSE_DIRT(1, new String[] { "DIRT" }),
/*  294 */   COBBLED_DEEPSLATE(new String[0]),
/*  295 */   COBBLED_DEEPSLATE_SLAB(new String[0]),
/*  296 */   COBBLED_DEEPSLATE_STAIRS(new String[0]),
/*  297 */   COBBLED_DEEPSLATE_WALL(new String[0]),
/*  298 */   COBBLESTONE(new String[0]),
/*  299 */   COBBLESTONE_SLAB(3, new String[] { "STEP" }),
/*  300 */   COBBLESTONE_STAIRS(new String[0]),
/*  301 */   COBBLESTONE_WALL(new String[] { "COBBLE_WALL" }),
/*  302 */   COBWEB(new String[] { "WEB" }),
/*  303 */   COCOA(new String[0]),
/*  304 */   COCOA_BEANS(3, new String[] { "INK_SACK" }),
/*  305 */   COD(new String[] { "RAW_FISH" }),
/*  306 */   COD_BUCKET(new String[0]),
/*  307 */   COD_SPAWN_EGG(new String[0]),
/*  308 */   COMMAND_BLOCK(new String[] { "COMMAND" }),
/*  309 */   COMMAND_BLOCK_MINECART(new String[] { "COMMAND_MINECART" }),
/*  310 */   COMPARATOR(new String[] { "REDSTONE_COMPARATOR_OFF", "REDSTONE_COMPARATOR_ON", "REDSTONE_COMPARATOR" }),
/*  318 */   COMPASS(new String[0]),
/*  319 */   COMPOSTER(new String[0]),
/*  320 */   CONDUIT(new String[0]),
/*  321 */   COOKED_BEEF(new String[0]),
/*  322 */   COOKED_CHICKEN(new String[0]),
/*  323 */   COOKED_COD(new String[] { "COOKED_FISH" }),
/*  324 */   COOKED_MUTTON(new String[0]),
/*  325 */   COOKED_PORKCHOP(new String[] { "GRILLED_PORK" }),
/*  326 */   COOKED_RABBIT(new String[0]),
/*  327 */   COOKED_SALMON(1, new String[] { "COOKED_FISH" }),
/*  328 */   COOKIE(new String[0]),
/*  329 */   COPPER_BLOCK(new String[0]),
/*  330 */   COPPER_INGOT(new String[0]),
/*  331 */   COPPER_ORE(new String[0]),
/*  332 */   CORNFLOWER(new String[0]),
/*  333 */   COW_SPAWN_EGG(92, new String[] { "MONSTER_EGG" }),
/*  334 */   CRACKED_DEEPSLATE_BRICKS(new String[0]),
/*  335 */   CRACKED_DEEPSLATE_TILES(new String[0]),
/*  336 */   CRACKED_NETHER_BRICKS(2, new String[] { "NETHER_BRICKS" }),
/*  337 */   CRACKED_POLISHED_BLACKSTONE_BRICKS(new String[] { "POLISHED_BLACKSTONE_BRICKS" }),
/*  338 */   CRACKED_STONE_BRICKS(2, new String[] { "SMOOTH_BRICK" }),
/*  339 */   CRAFTING_TABLE(new String[] { "WORKBENCH" }),
/*  340 */   CREEPER_BANNER_PATTERN(new String[0]),
/*  341 */   CREEPER_HEAD(4, new String[] { "SKULL", "SKULL_ITEM" }),
/*  342 */   CREEPER_SPAWN_EGG(50, new String[] { "MONSTER_EGG" }),
/*  343 */   CREEPER_WALL_HEAD(4, new String[] { "SKULL", "SKULL_ITEM" }),
/*  344 */   CRIMSON_BUTTON(new String[0]),
/*  345 */   CRIMSON_DOOR(new String[0]),
/*  346 */   CRIMSON_FENCE(new String[0]),
/*  347 */   CRIMSON_FENCE_GATE(new String[0]),
/*  348 */   CRIMSON_FUNGUS(new String[0]),
/*  349 */   CRIMSON_HYPHAE(new String[0]),
/*  350 */   CRIMSON_NYLIUM(new String[0]),
/*  351 */   CRIMSON_PLANKS(new String[0]),
/*  352 */   CRIMSON_PRESSURE_PLATE(new String[0]),
/*  353 */   CRIMSON_ROOTS(new String[0]),
/*  354 */   CRIMSON_SIGN(new String[] { "SIGN_POST" }),
/*  355 */   CRIMSON_SLAB(new String[0]),
/*  356 */   CRIMSON_STAIRS(new String[0]),
/*  357 */   CRIMSON_STEM(new String[0]),
/*  358 */   CRIMSON_TRAPDOOR(new String[0]),
/*  359 */   CRIMSON_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  360 */   CROSSBOW(new String[0]),
/*  361 */   CRYING_OBSIDIAN(new String[0]),
/*  362 */   CUT_COPPER(new String[0]),
/*  363 */   CUT_COPPER_SLAB(new String[0]),
/*  364 */   CUT_COPPER_STAIRS(new String[0]),
/*  365 */   CUT_RED_SANDSTONE(new String[0]),
/*  366 */   CUT_RED_SANDSTONE_SLAB(new String[] { "STONE_SLAB2" }),
/*  367 */   CUT_SANDSTONE(new String[0]),
/*  368 */   CUT_SANDSTONE_SLAB(1, new String[] { "STEP" }),
/*  369 */   CYAN_BANNER(6, new String[] { "STANDING_BANNER", "BANNER" }),
/*  370 */   CYAN_BED(supports(12) ? 9 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  371 */   CYAN_CANDLE(new String[0]),
/*  372 */   CYAN_CANDLE_CAKE(new String[0]),
/*  373 */   CYAN_CARPET(9, new String[] { "CARPET" }),
/*  374 */   CYAN_CONCRETE(9, new String[] { "CONCRETE" }),
/*  375 */   CYAN_CONCRETE_POWDER(9, new String[] { "CONCRETE_POWDER" }),
/*  376 */   CYAN_DYE(6, new String[] { "INK_SACK" }),
/*  377 */   CYAN_GLAZED_TERRACOTTA(new String[0]),
/*  378 */   CYAN_SHULKER_BOX(new String[0]),
/*  379 */   CYAN_STAINED_GLASS(9, new String[] { "STAINED_GLASS" }),
/*  380 */   CYAN_STAINED_GLASS_PANE(9, new String[] { "STAINED_GLASS_PANE" }),
/*  381 */   CYAN_TERRACOTTA(9, new String[] { "STAINED_CLAY" }),
/*  382 */   CYAN_WALL_BANNER(6, new String[] { "WALL_BANNER" }),
/*  383 */   CYAN_WOOL(9, new String[] { "WOOL" }),
/*  384 */   DAMAGED_ANVIL(2, new String[] { "ANVIL" }),
/*  385 */   DANDELION(new String[] { "YELLOW_FLOWER" }),
/*  386 */   DARK_OAK_BOAT(new String[] { "BOAT_DARK_OAK" }),
/*  387 */   DARK_OAK_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  388 */   DARK_OAK_CHEST_BOAT(new String[0]),
/*  389 */   DARK_OAK_DOOR(new String[] { "DARK_OAK_DOOR", "DARK_OAK_DOOR_ITEM" }),
/*  390 */   DARK_OAK_FENCE(new String[0]),
/*  391 */   DARK_OAK_FENCE_GATE(new String[0]),
/*  392 */   DARK_OAK_LEAVES(1, new String[] { "LEAVES_2" }),
/*  393 */   DARK_OAK_LOG(1, new String[] { "LOG_2" }),
/*  394 */   DARK_OAK_PLANKS(5, new String[] { "WOOD" }),
/*  395 */   DARK_OAK_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*  396 */   DARK_OAK_SAPLING(5, new String[] { "SAPLING" }),
/*  397 */   DARK_OAK_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  398 */   DARK_OAK_SLAB(5, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  399 */   DARK_OAK_STAIRS(new String[0]),
/*  400 */   DARK_OAK_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*  401 */   DARK_OAK_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  402 */   DARK_OAK_WOOD(1, new String[] { "LOG_2" }),
/*  403 */   DARK_PRISMARINE(2, new String[] { "PRISMARINE" }),
/*  404 */   DARK_PRISMARINE_SLAB(new String[0]),
/*  405 */   DARK_PRISMARINE_STAIRS(new String[0]),
/*  406 */   DAYLIGHT_DETECTOR(new String[] { "DAYLIGHT_DETECTOR_INVERTED" }),
/*  407 */   DEAD_BRAIN_CORAL(new String[0]),
/*  408 */   DEAD_BRAIN_CORAL_BLOCK(new String[0]),
/*  409 */   DEAD_BRAIN_CORAL_FAN(new String[0]),
/*  410 */   DEAD_BRAIN_CORAL_WALL_FAN(new String[0]),
/*  411 */   DEAD_BUBBLE_CORAL(new String[0]),
/*  412 */   DEAD_BUBBLE_CORAL_BLOCK(new String[0]),
/*  413 */   DEAD_BUBBLE_CORAL_FAN(new String[0]),
/*  414 */   DEAD_BUBBLE_CORAL_WALL_FAN(new String[0]),
/*  415 */   DEAD_BUSH(new String[] { "LONG_GRASS" }),
/*  416 */   DEAD_FIRE_CORAL(new String[0]),
/*  417 */   DEAD_FIRE_CORAL_BLOCK(new String[0]),
/*  418 */   DEAD_FIRE_CORAL_FAN(new String[0]),
/*  419 */   DEAD_FIRE_CORAL_WALL_FAN(new String[0]),
/*  420 */   DEAD_HORN_CORAL(new String[0]),
/*  421 */   DEAD_HORN_CORAL_BLOCK(new String[0]),
/*  422 */   DEAD_HORN_CORAL_FAN(new String[0]),
/*  423 */   DEAD_HORN_CORAL_WALL_FAN(new String[0]),
/*  424 */   DEAD_TUBE_CORAL(new String[0]),
/*  425 */   DEAD_TUBE_CORAL_BLOCK(new String[0]),
/*  426 */   DEAD_TUBE_CORAL_FAN(new String[0]),
/*  427 */   DEAD_TUBE_CORAL_WALL_FAN(new String[0]),
/*  428 */   DEBUG_STICK(new String[0]),
/*  429 */   DEEPSLATE(new String[0]),
/*  430 */   DEEPSLATE_BRICKS(new String[0]),
/*  431 */   DEEPSLATE_BRICK_SLAB(new String[0]),
/*  432 */   DEEPSLATE_BRICK_STAIRS(new String[0]),
/*  433 */   DEEPSLATE_BRICK_WALL(new String[0]),
/*  434 */   DEEPSLATE_COAL_ORE(new String[0]),
/*  435 */   DEEPSLATE_COPPER_ORE(new String[0]),
/*  436 */   DEEPSLATE_DIAMOND_ORE(new String[0]),
/*  437 */   DEEPSLATE_EMERALD_ORE(new String[0]),
/*  438 */   DEEPSLATE_GOLD_ORE(new String[0]),
/*  439 */   DEEPSLATE_IRON_ORE(new String[0]),
/*  440 */   DEEPSLATE_LAPIS_ORE(new String[0]),
/*  441 */   DEEPSLATE_REDSTONE_ORE(new String[0]),
/*  442 */   DEEPSLATE_TILES(new String[0]),
/*  443 */   DEEPSLATE_TILE_SLAB(new String[0]),
/*  444 */   DEEPSLATE_TILE_STAIRS(new String[0]),
/*  445 */   DEEPSLATE_TILE_WALL(new String[0]),
/*  446 */   DETECTOR_RAIL(new String[0]),
/*  447 */   DIAMOND(new String[0]),
/*  448 */   DIAMOND_AXE(new String[0]),
/*  449 */   DIAMOND_BLOCK(new String[0]),
/*  450 */   DIAMOND_BOOTS(new String[0]),
/*  451 */   DIAMOND_CHESTPLATE(new String[0]),
/*  452 */   DIAMOND_HELMET(new String[0]),
/*  453 */   DIAMOND_HOE(new String[0]),
/*  454 */   DIAMOND_HORSE_ARMOR(new String[] { "DIAMOND_BARDING" }),
/*  455 */   DIAMOND_LEGGINGS(new String[0]),
/*  456 */   DIAMOND_ORE(new String[0]),
/*  457 */   DIAMOND_PICKAXE(new String[0]),
/*  458 */   DIAMOND_SHOVEL(new String[] { "DIAMOND_SPADE" }),
/*  459 */   DIAMOND_SWORD(new String[0]),
/*  460 */   DIORITE(3, new String[] { "STONE" }),
/*  461 */   DIORITE_SLAB(new String[0]),
/*  462 */   DIORITE_STAIRS(new String[0]),
/*  463 */   DIORITE_WALL(new String[0]),
/*  464 */   DIRT(new String[0]),
/*  465 */   DIRT_PATH(new String[] { "GRASS_PATH" }),
/*  469 */   DISC_FRAGMENT_5(new String[0]),
/*  470 */   DISPENSER(new String[0]),
/*  471 */   DOLPHIN_SPAWN_EGG(new String[0]),
/*  472 */   DONKEY_SPAWN_EGG(32, new String[] { "MONSTER_EGG" }),
/*  473 */   DRAGON_BREATH(new String[] { "DRAGONS_BREATH" }),
/*  474 */   DRAGON_EGG(new String[0]),
/*  475 */   DRAGON_HEAD(5, new String[] { "SKULL", "SKULL_ITEM" }),
/*  476 */   DRAGON_WALL_HEAD(5, new String[] { "SKULL", "SKULL_ITEM" }),
/*  477 */   DRIED_KELP(new String[0]),
/*  478 */   DRIED_KELP_BLOCK(new String[0]),
/*  479 */   DRIPSTONE_BLOCK(new String[0]),
/*  480 */   DROPPER(new String[0]),
/*  481 */   DROWNED_SPAWN_EGG(new String[0]),
/*  482 */   ECHO_SHARD(new String[0]),
/*  483 */   EGG(new String[0]),
/*  484 */   ELDER_GUARDIAN_SPAWN_EGG(4, new String[] { "MONSTER_EGG" }),
/*  485 */   ELYTRA(new String[0]),
/*  486 */   EMERALD(new String[0]),
/*  487 */   EMERALD_BLOCK(new String[0]),
/*  488 */   EMERALD_ORE(new String[0]),
/*  489 */   ENCHANTED_BOOK(new String[0]),
/*  490 */   ENCHANTED_GOLDEN_APPLE(1, new String[] { "GOLDEN_APPLE" }),
/*  491 */   ENCHANTING_TABLE(new String[] { "ENCHANTMENT_TABLE" }),
/*  492 */   ENDERMAN_SPAWN_EGG(58, new String[] { "MONSTER_EGG" }),
/*  493 */   ENDERMITE_SPAWN_EGG(67, new String[] { "MONSTER_EGG" }),
/*  494 */   ENDER_CHEST(new String[0]),
/*  495 */   ENDER_EYE(new String[] { "EYE_OF_ENDER" }),
/*  496 */   ENDER_PEARL(new String[0]),
/*  497 */   END_CRYSTAL(new String[0]),
/*  498 */   END_GATEWAY(new String[0]),
/*  499 */   END_PORTAL(new String[] { "ENDER_PORTAL" }),
/*  500 */   END_PORTAL_FRAME(new String[] { "ENDER_PORTAL_FRAME" }),
/*  501 */   END_ROD(new String[0]),
/*  502 */   END_STONE(new String[] { "ENDER_STONE" }),
/*  503 */   END_STONE_BRICKS(new String[] { "END_BRICKS" }),
/*  504 */   END_STONE_BRICK_SLAB(new String[0]),
/*  505 */   END_STONE_BRICK_STAIRS(new String[0]),
/*  506 */   END_STONE_BRICK_WALL(new String[0]),
/*  507 */   EVOKER_SPAWN_EGG(34, new String[] { "MONSTER_EGG" }),
/*  508 */   EXPERIENCE_BOTTLE(new String[] { "EXP_BOTTLE" }),
/*  509 */   EXPOSED_COPPER(new String[0]),
/*  510 */   EXPOSED_CUT_COPPER(new String[0]),
/*  511 */   EXPOSED_CUT_COPPER_SLAB(new String[0]),
/*  512 */   EXPOSED_CUT_COPPER_STAIRS(new String[0]),
/*  513 */   FARMLAND(new String[] { "SOIL" }),
/*  514 */   FEATHER(new String[0]),
/*  515 */   FERMENTED_SPIDER_EYE(new String[0]),
/*  516 */   FERN(2, new String[] { "LONG_GRASS" }),
/*  517 */   FILLED_MAP(new String[] { "MAP" }),
/*  524 */   FIRE(new String[0]),
/*  525 */   FIREWORK_ROCKET(new String[] { "FIREWORK" }),
/*  526 */   FIREWORK_STAR(new String[] { "FIREWORK_CHARGE" }),
/*  527 */   FIRE_CHARGE(new String[] { "FIREBALL" }),
/*  528 */   FIRE_CORAL(new String[0]),
/*  529 */   FIRE_CORAL_BLOCK(new String[0]),
/*  530 */   FIRE_CORAL_FAN(new String[0]),
/*  531 */   FIRE_CORAL_WALL_FAN(new String[0]),
/*  532 */   FISHING_ROD(new String[0]),
/*  533 */   FLETCHING_TABLE(new String[0]),
/*  534 */   FLINT(new String[0]),
/*  535 */   FLINT_AND_STEEL(new String[0]),
/*  536 */   FLOWERING_AZALEA(new String[0]),
/*  537 */   FLOWERING_AZALEA_LEAVES(new String[0]),
/*  538 */   FLOWER_BANNER_PATTERN(new String[0]),
/*  539 */   FLOWER_POT(new String[] { "FLOWER_POT", "FLOWER_POT_ITEM" }),
/*  540 */   FOX_SPAWN_EGG(new String[0]),
/*  541 */   FROGSPAWN(new String[0]),
/*  542 */   FROG_SPAWN_EGG(new String[0]),
/*  543 */   FROSTED_ICE(new String[0]),
/*  547 */   FURNACE(new String[] { "BURNING_FURNACE" }),
/*  548 */   FURNACE_MINECART(new String[] { "POWERED_MINECART" }),
/*  549 */   GHAST_SPAWN_EGG(56, new String[] { "MONSTER_EGG" }),
/*  550 */   GHAST_TEAR(new String[0]),
/*  551 */   GILDED_BLACKSTONE(new String[0]),
/*  552 */   GLASS(new String[0]),
/*  553 */   GLASS_BOTTLE(new String[0]),
/*  554 */   GLASS_PANE(new String[] { "THIN_GLASS" }),
/*  555 */   GLISTERING_MELON_SLICE(new String[] { "SPECKLED_MELON" }),
/*  556 */   GLOBE_BANNER_PATTERN(new String[0]),
/*  557 */   GLOWSTONE(new String[0]),
/*  558 */   GLOWSTONE_DUST(new String[0]),
/*  559 */   GLOW_BERRIES(new String[0]),
/*  560 */   GLOW_INK_SAC(new String[0]),
/*  561 */   GLOW_ITEM_FRAME(new String[0]),
/*  562 */   GLOW_LICHEN(new String[0]),
/*  563 */   GLOW_SQUID_SPAWN_EGG(new String[0]),
/*  564 */   GOAT_HORN(new String[0]),
/*  565 */   GOAT_SPAWN_EGG(new String[0]),
/*  566 */   GOLDEN_APPLE(new String[0]),
/*  567 */   GOLDEN_AXE(new String[] { "GOLD_AXE" }),
/*  568 */   GOLDEN_BOOTS(new String[] { "GOLD_BOOTS" }),
/*  569 */   GOLDEN_CARROT(new String[0]),
/*  570 */   GOLDEN_CHESTPLATE(new String[] { "GOLD_CHESTPLATE" }),
/*  571 */   GOLDEN_HELMET(new String[] { "GOLD_HELMET" }),
/*  572 */   GOLDEN_HOE(new String[] { "GOLD_HOE" }),
/*  573 */   GOLDEN_HORSE_ARMOR(new String[] { "GOLD_BARDING" }),
/*  574 */   GOLDEN_LEGGINGS(new String[] { "GOLD_LEGGINGS" }),
/*  575 */   GOLDEN_PICKAXE(new String[] { "GOLD_PICKAXE" }),
/*  576 */   GOLDEN_SHOVEL(new String[] { "GOLD_SPADE" }),
/*  577 */   GOLDEN_SWORD(new String[] { "GOLD_SWORD" }),
/*  578 */   GOLD_BLOCK(new String[0]),
/*  579 */   GOLD_INGOT(new String[0]),
/*  580 */   GOLD_NUGGET(new String[0]),
/*  581 */   GOLD_ORE(new String[0]),
/*  582 */   GRANITE(1, new String[] { "STONE" }),
/*  583 */   GRANITE_SLAB(new String[0]),
/*  584 */   GRANITE_STAIRS(new String[0]),
/*  585 */   GRANITE_WALL(new String[0]),
/*  586 */   GRASS(1, new String[] { "LONG_GRASS" }),
/*  587 */   GRASS_BLOCK(new String[] { "GRASS" }),
/*  588 */   GRAVEL(new String[0]),
/*  589 */   GRAY_BANNER(8, new String[] { "STANDING_BANNER", "BANNER" }),
/*  590 */   GRAY_BED(supports(12) ? 7 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  591 */   GRAY_CANDLE(new String[0]),
/*  592 */   GRAY_CANDLE_CAKE(new String[0]),
/*  593 */   GRAY_CARPET(7, new String[] { "CARPET" }),
/*  594 */   GRAY_CONCRETE(7, new String[] { "CONCRETE" }),
/*  595 */   GRAY_CONCRETE_POWDER(7, new String[] { "CONCRETE_POWDER" }),
/*  596 */   GRAY_DYE(8, new String[] { "INK_SACK" }),
/*  597 */   GRAY_GLAZED_TERRACOTTA(new String[0]),
/*  598 */   GRAY_SHULKER_BOX(new String[0]),
/*  599 */   GRAY_STAINED_GLASS(7, new String[] { "STAINED_GLASS" }),
/*  600 */   GRAY_STAINED_GLASS_PANE(7, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  601 */   GRAY_TERRACOTTA(7, new String[] { "STAINED_CLAY" }),
/*  602 */   GRAY_WALL_BANNER(8, new String[] { "WALL_BANNER" }),
/*  603 */   GRAY_WOOL(7, new String[] { "WOOL" }),
/*  604 */   GREEN_BANNER(2, new String[] { "STANDING_BANNER", "BANNER" }),
/*  605 */   GREEN_BED(supports(12) ? 13 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  606 */   GREEN_CANDLE(new String[0]),
/*  607 */   GREEN_CANDLE_CAKE(new String[0]),
/*  608 */   GREEN_CARPET(13, new String[] { "CARPET" }),
/*  609 */   GREEN_CONCRETE(13, new String[] { "CONCRETE" }),
/*  610 */   GREEN_CONCRETE_POWDER(13, new String[] { "CONCRETE_POWDER" }),
/*  611 */   GREEN_DYE(
/*      */     
/*  615 */     2, new String[] { "INK_SACK", "CACTUS_GREEN" }),
/*  616 */   GREEN_GLAZED_TERRACOTTA(new String[0]),
/*  617 */   GREEN_SHULKER_BOX(new String[0]),
/*  618 */   GREEN_STAINED_GLASS(13, new String[] { "STAINED_GLASS" }),
/*  619 */   GREEN_STAINED_GLASS_PANE(13, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  620 */   GREEN_TERRACOTTA(13, new String[] { "STAINED_CLAY" }),
/*  621 */   GREEN_WALL_BANNER(2, new String[] { "WALL_BANNER" }),
/*  622 */   GREEN_WOOL(13, new String[] { "WOOL" }),
/*  623 */   GRINDSTONE(new String[0]),
/*  624 */   GUARDIAN_SPAWN_EGG(68, new String[] { "MONSTER_EGG" }),
/*  625 */   GUNPOWDER(new String[] { "SULPHUR" }),
/*  626 */   HANGING_ROOTS(new String[0]),
/*  627 */   HAY_BLOCK(new String[0]),
/*  628 */   HEART_OF_THE_SEA(new String[0]),
/*  629 */   HEAVY_WEIGHTED_PRESSURE_PLATE(new String[] { "IRON_PLATE" }),
/*  630 */   HOGLIN_SPAWN_EGG(new String[] { "MONSTER_EGG" }),
/*  631 */   HONEYCOMB(new String[0]),
/*  632 */   HONEYCOMB_BLOCK(new String[0]),
/*  633 */   HONEY_BLOCK(new String[0]),
/*  634 */   HONEY_BOTTLE(new String[0]),
/*  635 */   HOPPER(new String[0]),
/*  636 */   HOPPER_MINECART(new String[0]),
/*  637 */   HORN_CORAL(new String[0]),
/*  638 */   HORN_CORAL_BLOCK(new String[0]),
/*  639 */   HORN_CORAL_FAN(new String[0]),
/*  640 */   HORN_CORAL_WALL_FAN(new String[0]),
/*  641 */   HORSE_SPAWN_EGG(100, new String[] { "MONSTER_EGG" }),
/*  642 */   HUSK_SPAWN_EGG(23, new String[] { "MONSTER_EGG" }),
/*  643 */   ICE(new String[0]),
/*  644 */   INFESTED_CHISELED_STONE_BRICKS(5, new String[] { "MONSTER_EGGS" }),
/*  645 */   INFESTED_COBBLESTONE(1, new String[] { "MONSTER_EGGS" }),
/*  646 */   INFESTED_CRACKED_STONE_BRICKS(4, new String[] { "MONSTER_EGGS" }),
/*  647 */   INFESTED_DEEPSLATE(new String[0]),
/*  648 */   INFESTED_MOSSY_STONE_BRICKS(3, new String[] { "MONSTER_EGGS" }),
/*  649 */   INFESTED_STONE(new String[] { "MONSTER_EGGS" }),
/*  650 */   INFESTED_STONE_BRICKS(2, new String[] { "MONSTER_EGGS" }),
/*  651 */   INK_SAC(new String[] { "INK_SACK" }),
/*  657 */   IRON_AXE(new String[0]),
/*  658 */   IRON_BARS(new String[] { "IRON_FENCE" }),
/*  659 */   IRON_BLOCK(new String[0]),
/*  660 */   IRON_BOOTS(new String[0]),
/*  661 */   IRON_CHESTPLATE(new String[0]),
/*  662 */   IRON_DOOR(new String[] { "IRON_DOOR_BLOCK" }),
/*  663 */   IRON_HELMET(new String[0]),
/*  664 */   IRON_HOE(new String[0]),
/*  665 */   IRON_HORSE_ARMOR(new String[] { "IRON_BARDING" }),
/*  666 */   IRON_INGOT(new String[0]),
/*  667 */   IRON_LEGGINGS(new String[0]),
/*  668 */   IRON_NUGGET(new String[0]),
/*  669 */   IRON_ORE(new String[0]),
/*  670 */   IRON_PICKAXE(new String[0]),
/*  671 */   IRON_SHOVEL(new String[] { "IRON_SPADE" }),
/*  672 */   IRON_SWORD(new String[0]),
/*  673 */   IRON_TRAPDOOR(new String[0]),
/*  674 */   ITEM_FRAME(new String[0]),
/*  675 */   JACK_O_LANTERN(new String[0]),
/*  676 */   JIGSAW(new String[0]),
/*  677 */   JUKEBOX(new String[0]),
/*  678 */   JUNGLE_BOAT(new String[] { "BOAT_JUNGLE" }),
/*  679 */   JUNGLE_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  680 */   JUNGLE_CHEST_BOAT(new String[0]),
/*  681 */   JUNGLE_DOOR(new String[] { "JUNGLE_DOOR", "JUNGLE_DOOR_ITEM" }),
/*  682 */   JUNGLE_FENCE(new String[0]),
/*  683 */   JUNGLE_FENCE_GATE(new String[0]),
/*  684 */   JUNGLE_LEAVES(3, new String[] { "LEAVES" }),
/*  685 */   JUNGLE_LOG(3, new String[] { "LOG" }),
/*  686 */   JUNGLE_PLANKS(3, new String[] { "WOOD" }),
/*  687 */   JUNGLE_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*  688 */   JUNGLE_SAPLING(3, new String[] { "SAPLING" }),
/*  689 */   JUNGLE_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  690 */   JUNGLE_SLAB(3, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  691 */   JUNGLE_STAIRS(new String[] { "JUNGLE_WOOD_STAIRS" }),
/*  692 */   JUNGLE_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*  693 */   JUNGLE_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  694 */   JUNGLE_WOOD(3, new String[] { "LOG" }),
/*  695 */   KELP(new String[0]),
/*  696 */   KELP_PLANT(new String[0]),
/*  697 */   KNOWLEDGE_BOOK(new String[] { "BOOK" }),
/*  698 */   LADDER(new String[0]),
/*  699 */   LANTERN(new String[0]),
/*  700 */   LAPIS_BLOCK(new String[0]),
/*  701 */   LAPIS_LAZULI(4, new String[] { "INK_SACK" }),
/*  702 */   LAPIS_ORE(new String[0]),
/*  703 */   LARGE_AMETHYST_BUD(new String[0]),
/*  704 */   LARGE_FERN(3, new String[] { "DOUBLE_PLANT" }),
/*  705 */   LAVA(new String[] { "STATIONARY_LAVA" }),
/*  706 */   LAVA_BUCKET(new String[0]),
/*  707 */   LAVA_CAULDRON(new String[0]),
/*  708 */   LEAD(new String[] { "LEASH" }),
/*  709 */   LEATHER(new String[0]),
/*  710 */   LEATHER_BOOTS(new String[0]),
/*  711 */   LEATHER_CHESTPLATE(new String[0]),
/*  712 */   LEATHER_HELMET(new String[0]),
/*  713 */   LEATHER_HORSE_ARMOR(new String[] { "IRON_HORSE_ARMOR" }),
/*  714 */   LEATHER_LEGGINGS(new String[0]),
/*  715 */   LECTERN(new String[0]),
/*  716 */   LEVER(new String[0]),
/*  717 */   LIGHT(new String[0]),
/*  718 */   LIGHTNING_ROD(new String[0]),
/*  719 */   LIGHT_BLUE_BANNER(12, new String[] { "STANDING_BANNER", "BANNER" }),
/*  720 */   LIGHT_BLUE_BED(supports(12) ? 3 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  721 */   LIGHT_BLUE_CANDLE(new String[0]),
/*  722 */   LIGHT_BLUE_CANDLE_CAKE(new String[0]),
/*  723 */   LIGHT_BLUE_CARPET(3, new String[] { "CARPET" }),
/*  724 */   LIGHT_BLUE_CONCRETE(3, new String[] { "CONCRETE" }),
/*  725 */   LIGHT_BLUE_CONCRETE_POWDER(3, new String[] { "CONCRETE_POWDER" }),
/*  726 */   LIGHT_BLUE_DYE(12, new String[] { "INK_SACK" }),
/*  727 */   LIGHT_BLUE_GLAZED_TERRACOTTA(new String[0]),
/*  728 */   LIGHT_BLUE_SHULKER_BOX(new String[0]),
/*  729 */   LIGHT_BLUE_STAINED_GLASS(3, new String[] { "STAINED_GLASS" }),
/*  730 */   LIGHT_BLUE_STAINED_GLASS_PANE(3, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  731 */   LIGHT_BLUE_TERRACOTTA(3, new String[] { "STAINED_CLAY" }),
/*  732 */   LIGHT_BLUE_WALL_BANNER(12, new String[] { "WALL_BANNER", "STANDING_BANNER", "BANNER" }),
/*  733 */   LIGHT_BLUE_WOOL(3, new String[] { "WOOL" }),
/*  734 */   LIGHT_GRAY_BANNER(7, new String[] { "STANDING_BANNER", "BANNER" }),
/*  735 */   LIGHT_GRAY_BED(supports(12) ? 8 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  736 */   LIGHT_GRAY_CANDLE(new String[0]),
/*  737 */   LIGHT_GRAY_CANDLE_CAKE(new String[0]),
/*  738 */   LIGHT_GRAY_CARPET(8, new String[] { "CARPET" }),
/*  739 */   LIGHT_GRAY_CONCRETE(8, new String[] { "CONCRETE" }),
/*  740 */   LIGHT_GRAY_CONCRETE_POWDER(8, new String[] { "CONCRETE_POWDER" }),
/*  741 */   LIGHT_GRAY_DYE(7, new String[] { "INK_SACK" }),
/*  742 */   LIGHT_GRAY_GLAZED_TERRACOTTA(new String[] { "SILVER_GLAZED_TERRACOTTA" }),
/*  747 */   LIGHT_GRAY_SHULKER_BOX(new String[] { "SILVER_SHULKER_BOX" }),
/*  748 */   LIGHT_GRAY_STAINED_GLASS(8, new String[] { "STAINED_GLASS" }),
/*  749 */   LIGHT_GRAY_STAINED_GLASS_PANE(8, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  750 */   LIGHT_GRAY_TERRACOTTA(8, new String[] { "STAINED_CLAY" }),
/*  751 */   LIGHT_GRAY_WALL_BANNER(7, new String[] { "WALL_BANNER" }),
/*  752 */   LIGHT_GRAY_WOOL(8, new String[] { "WOOL" }),
/*  753 */   LIGHT_WEIGHTED_PRESSURE_PLATE(new String[] { "GOLD_PLATE" }),
/*  754 */   LILAC(1, new String[] { "DOUBLE_PLANT" }),
/*  755 */   LILY_OF_THE_VALLEY(new String[0]),
/*  756 */   LILY_PAD(new String[] { "WATER_LILY" }),
/*  757 */   LIME_BANNER(10, new String[] { "STANDING_BANNER", "BANNER" }),
/*  758 */   LIME_BED(supports(12) ? 5 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  759 */   LIME_CANDLE(new String[0]),
/*  760 */   LIME_CANDLE_CAKE(new String[0]),
/*  761 */   LIME_CARPET(5, new String[] { "CARPET" }),
/*  762 */   LIME_CONCRETE(5, new String[] { "CONCRETE" }),
/*  763 */   LIME_CONCRETE_POWDER(5, new String[] { "CONCRETE_POWDER" }),
/*  764 */   LIME_DYE(10, new String[] { "INK_SACK" }),
/*  765 */   LIME_GLAZED_TERRACOTTA(new String[0]),
/*  766 */   LIME_SHULKER_BOX(new String[0]),
/*  767 */   LIME_STAINED_GLASS(5, new String[] { "STAINED_GLASS" }),
/*  768 */   LIME_STAINED_GLASS_PANE(5, new String[] { "STAINED_GLASS_PANE" }),
/*  769 */   LIME_TERRACOTTA(5, new String[] { "STAINED_CLAY" }),
/*  770 */   LIME_WALL_BANNER(10, new String[] { "WALL_BANNER" }),
/*  771 */   LIME_WOOL(5, new String[] { "WOOL" }),
/*  772 */   LINGERING_POTION(new String[0]),
/*  773 */   LLAMA_SPAWN_EGG(103, new String[] { "MONSTER_EGG" }),
/*  774 */   LODESTONE(new String[0]),
/*  775 */   LOOM(new String[0]),
/*  776 */   MAGENTA_BANNER(13, new String[] { "STANDING_BANNER", "BANNER" }),
/*  777 */   MAGENTA_BED(supports(12) ? 2 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  778 */   MAGENTA_CANDLE(new String[0]),
/*  779 */   MAGENTA_CANDLE_CAKE(new String[0]),
/*  780 */   MAGENTA_CARPET(2, new String[] { "CARPET" }),
/*  781 */   MAGENTA_CONCRETE(2, new String[] { "CONCRETE" }),
/*  782 */   MAGENTA_CONCRETE_POWDER(2, new String[] { "CONCRETE_POWDER" }),
/*  783 */   MAGENTA_DYE(13, new String[] { "INK_SACK" }),
/*  784 */   MAGENTA_GLAZED_TERRACOTTA(new String[0]),
/*  785 */   MAGENTA_SHULKER_BOX(new String[0]),
/*  786 */   MAGENTA_STAINED_GLASS(2, new String[] { "STAINED_GLASS" }),
/*  787 */   MAGENTA_STAINED_GLASS_PANE(2, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  788 */   MAGENTA_TERRACOTTA(2, new String[] { "STAINED_CLAY" }),
/*  789 */   MAGENTA_WALL_BANNER(13, new String[] { "WALL_BANNER" }),
/*  790 */   MAGENTA_WOOL(2, new String[] { "WOOL" }),
/*  791 */   MAGMA_BLOCK(new String[] { "MAGMA" }),
/*  792 */   MAGMA_CREAM(new String[0]),
/*  793 */   MAGMA_CUBE_SPAWN_EGG(62, new String[] { "MONSTER_EGG" }),
/*  794 */   MANGROVE_BOAT(new String[0]),
/*  795 */   MANGROVE_BUTTON(new String[0]),
/*  796 */   MANGROVE_CHEST_BOAT(new String[0]),
/*  797 */   MANGROVE_DOOR(new String[0]),
/*  798 */   MANGROVE_FENCE(new String[0]),
/*  799 */   MANGROVE_FENCE_GATE(new String[0]),
/*  800 */   MANGROVE_LEAVES(new String[0]),
/*  801 */   MANGROVE_LOG(new String[0]),
/*  802 */   MANGROVE_PLANKS(new String[0]),
/*  803 */   MANGROVE_PRESSURE_PLATE(new String[0]),
/*  804 */   MANGROVE_PROPAGULE(new String[0]),
/*  805 */   MANGROVE_ROOTS(new String[0]),
/*  806 */   MANGROVE_SIGN(new String[0]),
/*  807 */   MANGROVE_SLAB(new String[0]),
/*  808 */   MANGROVE_STAIRS(new String[0]),
/*  809 */   MANGROVE_TRAPDOOR(new String[0]),
/*  810 */   MANGROVE_WALL_SIGN(new String[0]),
/*  811 */   MANGROVE_WOOD(new String[0]),
/*  812 */   MAP(new String[] { "EMPTY_MAP" }),
/*  820 */   MEDIUM_AMETHYST_BUD(new String[0]),
/*  821 */   MELON(new String[] { "MELON_BLOCK" }),
/*  822 */   MELON_SEEDS(new String[0]),
/*  823 */   MELON_SLICE(new String[] { "MELON" }),
/*  824 */   MELON_STEM(new String[0]),
/*  825 */   MILK_BUCKET(new String[0]),
/*  826 */   MINECART(new String[0]),
/*  827 */   MOJANG_BANNER_PATTERN(new String[0]),
/*  828 */   MOOSHROOM_SPAWN_EGG(96, new String[] { "MONSTER_EGG" }),
/*  829 */   MOSSY_COBBLESTONE(new String[0]),
/*  830 */   MOSSY_COBBLESTONE_SLAB(new String[0]),
/*  831 */   MOSSY_COBBLESTONE_STAIRS(new String[0]),
/*  832 */   MOSSY_COBBLESTONE_WALL(1, new String[] { "COBBLE_WALL", "COBBLESTONE_WALL" }),
/*  833 */   MOSSY_STONE_BRICKS(1, new String[] { "SMOOTH_BRICK" }),
/*  834 */   MOSSY_STONE_BRICK_SLAB(new String[0]),
/*  835 */   MOSSY_STONE_BRICK_STAIRS(new String[0]),
/*  836 */   MOSSY_STONE_BRICK_WALL(new String[0]),
/*  837 */   MOSS_BLOCK(new String[0]),
/*  838 */   MOSS_CARPET(new String[0]),
/*  839 */   MOVING_PISTON(new String[] { "PISTON_MOVING_PIECE" }),
/*  840 */   MUD(new String[0]),
/*  841 */   MUDDY_MANGROVE_ROOTS(new String[0]),
/*  842 */   MUD_BRICKS(new String[0]),
/*  843 */   MUD_BRICK_SLAB(new String[0]),
/*  844 */   MUD_BRICK_STAIRS(new String[0]),
/*  845 */   MUD_BRICK_WALL(new String[0]),
/*  846 */   MULE_SPAWN_EGG(32, new String[] { "MONSTER_EGG" }),
/*  847 */   MUSHROOM_STEM(new String[] { "BROWN_MUSHROOM" }),
/*  848 */   MUSHROOM_STEW(new String[] { "MUSHROOM_SOUP" }),
/*  849 */   MUSIC_DISC_11(new String[] { "RECORD_11" }),
/*  850 */   MUSIC_DISC_13(new String[] { "GOLD_RECORD" }),
/*  851 */   MUSIC_DISC_5(new String[0]),
/*  852 */   MUSIC_DISC_BLOCKS(new String[] { "RECORD_3" }),
/*  853 */   MUSIC_DISC_CAT(new String[] { "GREEN_RECORD" }),
/*  854 */   MUSIC_DISC_CHIRP(new String[] { "RECORD_4" }),
/*  855 */   MUSIC_DISC_FAR(new String[] { "RECORD_5" }),
/*  856 */   MUSIC_DISC_MALL(new String[] { "RECORD_6" }),
/*  857 */   MUSIC_DISC_MELLOHI(new String[] { "RECORD_7" }),
/*  858 */   MUSIC_DISC_OTHERSIDE(new String[0]),
/*  859 */   MUSIC_DISC_PIGSTEP(new String[0]),
/*  860 */   MUSIC_DISC_STAL(new String[] { "RECORD_8" }),
/*  861 */   MUSIC_DISC_STRAD(new String[] { "RECORD_9" }),
/*  862 */   MUSIC_DISC_WAIT(new String[] { "RECORD_12" }),
/*  863 */   MUSIC_DISC_WARD(new String[] { "RECORD_10" }),
/*  864 */   MUTTON(new String[0]),
/*  865 */   MYCELIUM(new String[] { "MYCEL" }),
/*  866 */   NAME_TAG(new String[0]),
/*  867 */   NAUTILUS_SHELL(new String[0]),
/*  868 */   NETHERITE_AXE(new String[0]),
/*  869 */   NETHERITE_BLOCK(new String[0]),
/*  870 */   NETHERITE_BOOTS(new String[0]),
/*  871 */   NETHERITE_CHESTPLATE(new String[0]),
/*  872 */   NETHERITE_HELMET(new String[0]),
/*  873 */   NETHERITE_HOE(new String[0]),
/*  874 */   NETHERITE_INGOT(new String[0]),
/*  875 */   NETHERITE_LEGGINGS(new String[0]),
/*  876 */   NETHERITE_PICKAXE(new String[0]),
/*  877 */   NETHERITE_SCRAP(new String[0]),
/*  878 */   NETHERITE_SHOVEL(new String[0]),
/*  879 */   NETHERITE_SWORD(new String[0]),
/*  880 */   NETHERRACK(new String[0]),
/*  881 */   NETHER_BRICK(new String[] { "NETHER_BRICK_ITEM" }),
/*  882 */   NETHER_BRICKS(new String[] { "NETHER_BRICK" }),
/*  883 */   NETHER_BRICK_FENCE(new String[] { "NETHER_FENCE" }),
/*  884 */   NETHER_BRICK_SLAB(6, new String[] { "STEP" }),
/*  885 */   NETHER_BRICK_STAIRS(new String[0]),
/*  886 */   NETHER_BRICK_WALL(new String[0]),
/*  887 */   NETHER_GOLD_ORE(new String[0]),
/*  888 */   NETHER_PORTAL(new String[] { "PORTAL" }),
/*  889 */   NETHER_QUARTZ_ORE(new String[] { "QUARTZ_ORE" }),
/*  890 */   NETHER_SPROUTS(new String[0]),
/*  891 */   NETHER_STAR(new String[0]),
/*  892 */   NETHER_WART(new String[] { "NETHER_WARTS", "NETHER_STALK" }),
/*  898 */   NETHER_WART_BLOCK(new String[0]),
/*  899 */   NOTE_BLOCK(new String[0]),
/*  900 */   OAK_BOAT(new String[] { "BOAT" }),
/*  901 */   OAK_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  902 */   OAK_CHEST_BOAT(new String[0]),
/*  903 */   OAK_DOOR(new String[] { "WOODEN_DOOR", "WOOD_DOOR" }),
/*  904 */   OAK_FENCE(new String[] { "FENCE" }),
/*  905 */   OAK_FENCE_GATE(new String[] { "FENCE_GATE" }),
/*  906 */   OAK_LEAVES(new String[] { "LEAVES" }),
/*  907 */   OAK_LOG(new String[] { "LOG" }),
/*  908 */   OAK_PLANKS(new String[] { "WOOD" }),
/*  909 */   OAK_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*  910 */   OAK_SAPLING(new String[] { "SAPLING" }),
/*  911 */   OAK_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  912 */   OAK_SLAB(new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  913 */   OAK_STAIRS(new String[] { "WOOD_STAIRS" }),
/*  914 */   OAK_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*  915 */   OAK_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  916 */   OAK_WOOD(new String[] { "LOG" }),
/*  917 */   OBSERVER(new String[0]),
/*  918 */   OBSIDIAN(new String[0]),
/*  919 */   OCELOT_SPAWN_EGG(98, new String[] { "MONSTER_EGG" }),
/*  920 */   OCHRE_FROGLIGHT(new String[0]),
/*  921 */   ORANGE_BANNER(14, new String[] { "STANDING_BANNER", "BANNER" }),
/*  922 */   ORANGE_BED(supports(12) ? 1 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  923 */   ORANGE_CANDLE(new String[0]),
/*  924 */   ORANGE_CANDLE_CAKE(new String[0]),
/*  925 */   ORANGE_CARPET(1, new String[] { "CARPET" }),
/*  926 */   ORANGE_CONCRETE(1, new String[] { "CONCRETE" }),
/*  927 */   ORANGE_CONCRETE_POWDER(1, new String[] { "CONCRETE_POWDER" }),
/*  928 */   ORANGE_DYE(14, new String[] { "INK_SACK" }),
/*  929 */   ORANGE_GLAZED_TERRACOTTA(new String[0]),
/*  930 */   ORANGE_SHULKER_BOX(new String[0]),
/*  931 */   ORANGE_STAINED_GLASS(1, new String[] { "STAINED_GLASS" }),
/*  932 */   ORANGE_STAINED_GLASS_PANE(1, new String[] { "STAINED_GLASS_PANE" }),
/*  933 */   ORANGE_TERRACOTTA(1, new String[] { "STAINED_CLAY" }),
/*  934 */   ORANGE_TULIP(5, new String[] { "RED_ROSE" }),
/*  935 */   ORANGE_WALL_BANNER(14, new String[] { "WALL_BANNER" }),
/*  936 */   ORANGE_WOOL(1, new String[] { "WOOL" }),
/*  937 */   OXEYE_DAISY(8, new String[] { "RED_ROSE" }),
/*  938 */   OXIDIZED_COPPER(new String[0]),
/*  939 */   OXIDIZED_CUT_COPPER(new String[0]),
/*  940 */   OXIDIZED_CUT_COPPER_SLAB(new String[0]),
/*  941 */   OXIDIZED_CUT_COPPER_STAIRS(new String[0]),
/*  942 */   PACKED_ICE(new String[0]),
/*  943 */   PACKED_MUD(new String[0]),
/*  944 */   PAINTING(new String[0]),
/*  945 */   PANDA_SPAWN_EGG(new String[0]),
/*  946 */   PAPER(new String[0]),
/*  947 */   PARROT_SPAWN_EGG(105, new String[] { "MONSTER_EGG" }),
/*  948 */   PEARLESCENT_FROGLIGHT(new String[0]),
/*  949 */   PEONY(5, new String[] { "DOUBLE_PLANT" }),
/*  950 */   PETRIFIED_OAK_SLAB(new String[] { "WOOD_STEP" }),
/*  951 */   PHANTOM_MEMBRANE(new String[0]),
/*  952 */   PHANTOM_SPAWN_EGG(new String[0]),
/*  953 */   PIGLIN_BANNER_PATTERN(new String[0]),
/*  954 */   PIGLIN_BRUTE_SPAWN_EGG(new String[0]),
/*  955 */   PIGLIN_SPAWN_EGG(57, new String[] { "MONSTER_EGG" }),
/*  956 */   PIG_SPAWN_EGG(90, new String[] { "MONSTER_EGG" }),
/*  957 */   PILLAGER_SPAWN_EGG(new String[0]),
/*  958 */   PINK_BANNER(9, new String[] { "STANDING_BANNER", "BANNER" }),
/*  959 */   PINK_BED(supports(12) ? 6 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  960 */   PINK_CANDLE(new String[0]),
/*  961 */   PINK_CANDLE_CAKE(new String[0]),
/*  962 */   PINK_CARPET(6, new String[] { "CARPET" }),
/*  963 */   PINK_CONCRETE(6, new String[] { "CONCRETE" }),
/*  964 */   PINK_CONCRETE_POWDER(6, new String[] { "CONCRETE_POWDER" }),
/*  965 */   PINK_DYE(9, new String[] { "INK_SACK" }),
/*  966 */   PINK_GLAZED_TERRACOTTA(new String[0]),
/*  967 */   PINK_SHULKER_BOX(new String[0]),
/*  968 */   PINK_STAINED_GLASS(6, new String[] { "STAINED_GLASS" }),
/*  969 */   PINK_STAINED_GLASS_PANE(6, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  970 */   PINK_TERRACOTTA(6, new String[] { "STAINED_CLAY" }),
/*  971 */   PINK_TULIP(7, new String[] { "RED_ROSE" }),
/*  972 */   PINK_WALL_BANNER(9, new String[] { "WALL_BANNER" }),
/*  973 */   PINK_WOOL(6, new String[] { "WOOL" }),
/*  974 */   PISTON(new String[] { "PISTON_BASE" }),
/*  975 */   PISTON_HEAD(new String[] { "PISTON_EXTENSION" }),
/*  976 */   PLAYER_HEAD(3, new String[] { "SKULL", "SKULL_ITEM" }),
/*  977 */   PLAYER_WALL_HEAD(3, new String[] { "SKULL", "SKULL_ITEM" }),
/*  978 */   PODZOL(2, new String[] { "DIRT" }),
/*  979 */   POINTED_DRIPSTONE(new String[0]),
/*  980 */   POISONOUS_POTATO(new String[0]),
/*  981 */   POLAR_BEAR_SPAWN_EGG(102, new String[] { "MONSTER_EGG" }),
/*  982 */   POLISHED_ANDESITE(6, new String[] { "STONE" }),
/*  983 */   POLISHED_ANDESITE_SLAB(new String[0]),
/*  984 */   POLISHED_ANDESITE_STAIRS(new String[0]),
/*  985 */   POLISHED_BASALT(new String[0]),
/*  986 */   POLISHED_BLACKSTONE(new String[0]),
/*  987 */   POLISHED_BLACKSTONE_BRICKS(new String[0]),
/*  988 */   POLISHED_BLACKSTONE_BRICK_SLAB(new String[0]),
/*  989 */   POLISHED_BLACKSTONE_BRICK_STAIRS(new String[0]),
/*  990 */   POLISHED_BLACKSTONE_BRICK_WALL(new String[0]),
/*  991 */   POLISHED_BLACKSTONE_BUTTON(new String[0]),
/*  992 */   POLISHED_BLACKSTONE_PRESSURE_PLATE(new String[0]),
/*  993 */   POLISHED_BLACKSTONE_SLAB(new String[0]),
/*  994 */   POLISHED_BLACKSTONE_STAIRS(new String[0]),
/*  995 */   POLISHED_BLACKSTONE_WALL(new String[0]),
/*  996 */   POLISHED_DEEPSLATE(new String[0]),
/*  997 */   POLISHED_DEEPSLATE_SLAB(new String[0]),
/*  998 */   POLISHED_DEEPSLATE_STAIRS(new String[0]),
/*  999 */   POLISHED_DEEPSLATE_WALL(new String[0]),
/* 1000 */   POLISHED_DIORITE(4, new String[] { "STONE" }),
/* 1001 */   POLISHED_DIORITE_SLAB(new String[0]),
/* 1002 */   POLISHED_DIORITE_STAIRS(new String[0]),
/* 1003 */   POLISHED_GRANITE(2, new String[] { "STONE" }),
/* 1004 */   POLISHED_GRANITE_SLAB(new String[0]),
/* 1005 */   POLISHED_GRANITE_STAIRS(new String[0]),
/* 1006 */   POPPED_CHORUS_FRUIT(new String[] { "CHORUS_FRUIT_POPPED" }),
/* 1007 */   POPPY(new String[] { "RED_ROSE" }),
/* 1008 */   PORKCHOP(new String[] { "PORK" }),
/* 1009 */   POTATO(new String[] { "POTATO_ITEM" }),
/* 1010 */   POTATOES(new String[] { "POTATO" }),
/* 1011 */   POTION(new String[0]),
/* 1012 */   POTTED_ACACIA_SAPLING(4, new String[] { "FLOWER_POT" }),
/* 1013 */   POTTED_ALLIUM(2, new String[] { "FLOWER_POT" }),
/* 1014 */   POTTED_AZALEA_BUSH(new String[0]),
/* 1015 */   POTTED_AZURE_BLUET(3, new String[] { "FLOWER_POT" }),
/* 1016 */   POTTED_BAMBOO(new String[0]),
/* 1017 */   POTTED_BIRCH_SAPLING(2, new String[] { "FLOWER_POT" }),
/* 1018 */   POTTED_BLUE_ORCHID(1, new String[] { "FLOWER_POT" }),
/* 1019 */   POTTED_BROWN_MUSHROOM(new String[] { "FLOWER_POT" }),
/* 1020 */   POTTED_CACTUS(new String[] { "FLOWER_POT" }),
/* 1021 */   POTTED_CORNFLOWER(new String[0]),
/* 1022 */   POTTED_CRIMSON_FUNGUS(new String[0]),
/* 1023 */   POTTED_CRIMSON_ROOTS(new String[0]),
/* 1024 */   POTTED_DANDELION(new String[] { "FLOWER_POT" }),
/* 1025 */   POTTED_DARK_OAK_SAPLING(5, new String[] { "FLOWER_POT" }),
/* 1026 */   POTTED_DEAD_BUSH(new String[] { "FLOWER_POT" }),
/* 1027 */   POTTED_FERN(2, new String[] { "FLOWER_POT" }),
/* 1028 */   POTTED_FLOWERING_AZALEA_BUSH(new String[0]),
/* 1029 */   POTTED_JUNGLE_SAPLING(3, new String[] { "FLOWER_POT" }),
/* 1030 */   POTTED_LILY_OF_THE_VALLEY(new String[0]),
/* 1031 */   POTTED_MANGROVE_PROPAGULE(new String[0]),
/* 1032 */   POTTED_OAK_SAPLING(new String[] { "FLOWER_POT" }),
/* 1033 */   POTTED_ORANGE_TULIP(5, new String[] { "FLOWER_POT" }),
/* 1034 */   POTTED_OXEYE_DAISY(8, new String[] { "FLOWER_POT" }),
/* 1035 */   POTTED_PINK_TULIP(7, new String[] { "FLOWER_POT" }),
/* 1036 */   POTTED_POPPY(new String[] { "FLOWER_POT" }),
/* 1037 */   POTTED_RED_MUSHROOM(new String[] { "FLOWER_POT" }),
/* 1038 */   POTTED_RED_TULIP(4, new String[] { "FLOWER_POT" }),
/* 1039 */   POTTED_SPRUCE_SAPLING(1, new String[] { "FLOWER_POT" }),
/* 1040 */   POTTED_WARPED_FUNGUS(new String[0]),
/* 1041 */   POTTED_WARPED_ROOTS(new String[0]),
/* 1042 */   POTTED_WHITE_TULIP(6, new String[] { "FLOWER_POT" }),
/* 1043 */   POTTED_WITHER_ROSE(new String[0]),
/* 1044 */   POWDER_SNOW(new String[0]),
/* 1045 */   POWDER_SNOW_BUCKET(new String[0]),
/* 1046 */   POWDER_SNOW_CAULDRON(new String[0]),
/* 1047 */   POWERED_RAIL(new String[0]),
/* 1048 */   PRISMARINE(new String[0]),
/* 1049 */   PRISMARINE_BRICKS(1, new String[] { "PRISMARINE" }),
/* 1050 */   PRISMARINE_BRICK_SLAB(new String[0]),
/* 1051 */   PRISMARINE_BRICK_STAIRS(new String[0]),
/* 1052 */   PRISMARINE_CRYSTALS(new String[0]),
/* 1053 */   PRISMARINE_SHARD(new String[0]),
/* 1054 */   PRISMARINE_SLAB(new String[0]),
/* 1055 */   PRISMARINE_STAIRS(new String[0]),
/* 1056 */   PRISMARINE_WALL(new String[0]),
/* 1057 */   PUFFERFISH(3, new String[] { "RAW_FISH" }),
/* 1058 */   PUFFERFISH_BUCKET(new String[0]),
/* 1059 */   PUFFERFISH_SPAWN_EGG(new String[0]),
/* 1060 */   PUMPKIN(new String[0]),
/* 1061 */   PUMPKIN_PIE(new String[0]),
/* 1062 */   PUMPKIN_SEEDS(new String[0]),
/* 1063 */   PUMPKIN_STEM(new String[0]),
/* 1064 */   PURPLE_BANNER(5, new String[] { "STANDING_BANNER", "BANNER" }),
/* 1065 */   PURPLE_BED(supports(12) ? 10 : 0, new String[] { "BED_BLOCK", "BED" }),
/* 1066 */   PURPLE_CANDLE(new String[0]),
/* 1067 */   PURPLE_CANDLE_CAKE(new String[0]),
/* 1068 */   PURPLE_CARPET(10, new String[] { "CARPET" }),
/* 1069 */   PURPLE_CONCRETE(10, new String[] { "CONCRETE" }),
/* 1070 */   PURPLE_CONCRETE_POWDER(10, new String[] { "CONCRETE_POWDER" }),
/* 1071 */   PURPLE_DYE(5, new String[] { "INK_SACK" }),
/* 1072 */   PURPLE_GLAZED_TERRACOTTA(new String[0]),
/* 1073 */   PURPLE_SHULKER_BOX(new String[0]),
/* 1074 */   PURPLE_STAINED_GLASS(10, new String[] { "STAINED_GLASS" }),
/* 1075 */   PURPLE_STAINED_GLASS_PANE(10, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/* 1076 */   PURPLE_TERRACOTTA(10, new String[] { "STAINED_CLAY" }),
/* 1077 */   PURPLE_WALL_BANNER(5, new String[] { "WALL_BANNER" }),
/* 1078 */   PURPLE_WOOL(10, new String[] { "WOOL" }),
/* 1079 */   PURPUR_BLOCK(new String[0]),
/* 1080 */   PURPUR_PILLAR(new String[0]),
/* 1081 */   PURPUR_SLAB(new String[] { "PURPUR_DOUBLE_SLAB" }),
/* 1082 */   PURPUR_STAIRS(new String[0]),
/* 1083 */   QUARTZ(new String[0]),
/* 1084 */   QUARTZ_BLOCK(new String[0]),
/* 1085 */   QUARTZ_BRICKS(new String[0]),
/* 1086 */   QUARTZ_PILLAR(2, new String[] { "QUARTZ_BLOCK" }),
/* 1087 */   QUARTZ_SLAB(7, new String[] { "STEP" }),
/* 1088 */   QUARTZ_STAIRS(new String[0]),
/* 1089 */   RABBIT(new String[0]),
/* 1090 */   RABBIT_FOOT(new String[0]),
/* 1091 */   RABBIT_HIDE(new String[0]),
/* 1092 */   RABBIT_SPAWN_EGG(101, new String[] { "MONSTER_EGG" }),
/* 1093 */   RABBIT_STEW(new String[0]),
/* 1094 */   RAIL(new String[] { "RAILS" }),
/* 1095 */   RAVAGER_SPAWN_EGG(new String[0]),
/* 1096 */   RAW_COPPER(new String[0]),
/* 1097 */   RAW_COPPER_BLOCK(new String[0]),
/* 1098 */   RAW_GOLD(new String[0]),
/* 1099 */   RAW_GOLD_BLOCK(new String[0]),
/* 1100 */   RAW_IRON(new String[0]),
/* 1101 */   RAW_IRON_BLOCK(new String[0]),
/* 1102 */   RECOVERY_COMPASS(new String[0]),
/* 1103 */   REDSTONE(new String[0]),
/* 1104 */   REDSTONE_BLOCK(new String[0]),
/* 1105 */   REDSTONE_LAMP(new String[] { "REDSTONE_LAMP_ON", "REDSTONE_LAMP_OFF" }),
/* 1112 */   REDSTONE_ORE(new String[] { "GLOWING_REDSTONE_ORE" }),
/* 1113 */   REDSTONE_TORCH(new String[] { "REDSTONE_TORCH_OFF", "REDSTONE_TORCH_ON" }),
/* 1119 */   REDSTONE_WALL_TORCH(new String[0]),
/* 1120 */   REDSTONE_WIRE(new String[0]),
/* 1121 */   RED_BANNER(1, new String[] { "STANDING_BANNER", "BANNER" }),
/* 1122 */   RED_BED(
/*      */     
/* 1125 */     supports(12) ? 14 : 0, new String[] { "BED_BLOCK", "BED" }),
/* 1126 */   RED_CANDLE(new String[0]),
/* 1127 */   RED_CANDLE_CAKE(new String[0]),
/* 1128 */   RED_CARPET(14, new String[] { "CARPET" }),
/* 1129 */   RED_CONCRETE(14, new String[] { "CONCRETE" }),
/* 1130 */   RED_CONCRETE_POWDER(14, new String[] { "CONCRETE_POWDER" }),
/* 1131 */   RED_DYE(
/*      */     
/* 1135 */     1, new String[] { "INK_SACK", "ROSE_RED" }),
/* 1136 */   RED_GLAZED_TERRACOTTA(new String[0]),
/* 1137 */   RED_MUSHROOM(new String[0]),
/* 1138 */   RED_MUSHROOM_BLOCK(new String[] { "RED_MUSHROOM", "HUGE_MUSHROOM_2" }),
/* 1139 */   RED_NETHER_BRICKS(new String[] { "RED_NETHER_BRICK" }),
/* 1140 */   RED_NETHER_BRICK_SLAB(new String[0]),
/* 1141 */   RED_NETHER_BRICK_STAIRS(new String[0]),
/* 1142 */   RED_NETHER_BRICK_WALL(new String[0]),
/* 1143 */   RED_SAND(1, new String[] { "SAND" }),
/* 1144 */   RED_SANDSTONE(new String[0]),
/* 1145 */   RED_SANDSTONE_SLAB(new String[] { "DOUBLE_STONE_SLAB2", "STONE_SLAB2" }),
/* 1146 */   RED_SANDSTONE_STAIRS(new String[0]),
/* 1147 */   RED_SANDSTONE_WALL(new String[0]),
/* 1148 */   RED_SHULKER_BOX(new String[0]),
/* 1149 */   RED_STAINED_GLASS(14, new String[] { "STAINED_GLASS" }),
/* 1150 */   RED_STAINED_GLASS_PANE(14, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/* 1151 */   RED_TERRACOTTA(14, new String[] { "STAINED_CLAY" }),
/* 1152 */   RED_TULIP(4, new String[] { "RED_ROSE" }),
/* 1153 */   RED_WALL_BANNER(1, new String[] { "WALL_BANNER" }),
/* 1154 */   RED_WOOL(14, new String[] { "WOOL" }),
/* 1155 */   REINFORCED_DEEPSLATE(new String[0]),
/* 1156 */   REPEATER(new String[] { "DIODE_BLOCK_ON", "DIODE_BLOCK_OFF", "DIODE" }),
/* 1157 */   REPEATING_COMMAND_BLOCK(new String[] { "COMMAND", "COMMAND_REPEATING" }),
/* 1158 */   RESPAWN_ANCHOR(new String[0]),
/* 1159 */   ROOTED_DIRT(new String[0]),
/* 1160 */   ROSE_BUSH(4, new String[] { "DOUBLE_PLANT" }),
/* 1161 */   ROTTEN_FLESH(new String[0]),
/* 1162 */   SADDLE(new String[0]),
/* 1163 */   SALMON(1, new String[] { "RAW_FISH" }),
/* 1164 */   SALMON_BUCKET(new String[0]),
/* 1165 */   SALMON_SPAWN_EGG(new String[0]),
/* 1166 */   SAND(new String[0]),
/* 1167 */   SANDSTONE(new String[0]),
/* 1168 */   SANDSTONE_SLAB(1, new String[] { "DOUBLE_STEP", "STEP", "STONE_SLAB" }),
/* 1169 */   SANDSTONE_STAIRS(new String[0]),
/* 1170 */   SANDSTONE_WALL(new String[0]),
/* 1171 */   SCAFFOLDING(new String[0]),
/* 1172 */   SCULK(new String[0]),
/* 1173 */   SCULK_CATALYST(new String[0]),
/* 1174 */   SCULK_SENSOR(new String[0]),
/* 1175 */   SCULK_SHRIEKER(new String[0]),
/* 1176 */   SCULK_VEIN(new String[0]),
/* 1177 */   SCUTE(new String[0]),
/* 1178 */   SEAGRASS(new String[0]),
/* 1179 */   SEA_LANTERN(new String[0]),
/* 1180 */   SEA_PICKLE(new String[0]),
/* 1181 */   SHEARS(new String[0]),
/* 1182 */   SHEEP_SPAWN_EGG(91, new String[] { "MONSTER_EGG" }),
/* 1183 */   SHIELD(new String[0]),
/* 1184 */   SHROOMLIGHT(new String[0]),
/* 1185 */   SHULKER_BOX(new String[] { "PURPLE_SHULKER_BOX" }),
/* 1186 */   SHULKER_SHELL(new String[0]),
/* 1187 */   SHULKER_SPAWN_EGG(69, new String[] { "MONSTER_EGG" }),
/* 1188 */   SILVERFISH_SPAWN_EGG(60, new String[] { "MONSTER_EGG" }),
/* 1189 */   SKELETON_HORSE_SPAWN_EGG(28, new String[] { "MONSTER_EGG" }),
/* 1190 */   SKELETON_SKULL(new String[] { "SKULL", "SKULL_ITEM" }),
/* 1191 */   SKELETON_SPAWN_EGG(51, new String[] { "MONSTER_EGG" }),
/* 1192 */   SKELETON_WALL_SKULL(new String[] { "SKULL", "SKULL_ITEM" }),
/* 1193 */   SKULL_BANNER_PATTERN(new String[0]),
/* 1194 */   SLIME_BALL(new String[0]),
/* 1195 */   SLIME_BLOCK(new String[0]),
/* 1196 */   SLIME_SPAWN_EGG(55, new String[] { "MONSTER_EGG" }),
/* 1197 */   SMALL_AMETHYST_BUD(new String[0]),
/* 1198 */   SMALL_DRIPLEAF(new String[0]),
/* 1199 */   SMITHING_TABLE(new String[0]),
/* 1200 */   SMOKER(new String[0]),
/* 1201 */   SMOOTH_BASALT(new String[0]),
/* 1202 */   SMOOTH_QUARTZ(new String[0]),
/* 1203 */   SMOOTH_QUARTZ_SLAB(new String[0]),
/* 1204 */   SMOOTH_QUARTZ_STAIRS(new String[0]),
/* 1205 */   SMOOTH_RED_SANDSTONE(2, new String[] { "RED_SANDSTONE" }),
/* 1206 */   SMOOTH_RED_SANDSTONE_SLAB(new String[] { "STONE_SLAB2" }),
/* 1207 */   SMOOTH_RED_SANDSTONE_STAIRS(new String[0]),
/* 1208 */   SMOOTH_SANDSTONE(2, new String[] { "SANDSTONE" }),
/* 1209 */   SMOOTH_SANDSTONE_SLAB(new String[0]),
/* 1210 */   SMOOTH_SANDSTONE_STAIRS(new String[0]),
/* 1211 */   SMOOTH_STONE(new String[0]),
/* 1212 */   SMOOTH_STONE_SLAB(new String[0]),
/* 1213 */   SNOW(new String[0]),
/* 1214 */   SNOWBALL(new String[] { "SNOW_BALL" }),
/* 1215 */   SNOW_BLOCK(new String[0]),
/* 1216 */   SOUL_CAMPFIRE(new String[0]),
/* 1217 */   SOUL_FIRE(new String[0]),
/* 1218 */   SOUL_LANTERN(new String[0]),
/* 1219 */   SOUL_SAND(new String[0]),
/* 1220 */   SOUL_SOIL(new String[0]),
/* 1221 */   SOUL_TORCH(new String[0]),
/* 1222 */   SOUL_WALL_TORCH(new String[0]),
/* 1223 */   SPAWNER(new String[] { "MOB_SPAWNER" }),
/* 1224 */   SPECTRAL_ARROW(new String[0]),
/* 1225 */   SPIDER_EYE(new String[0]),
/* 1226 */   SPIDER_SPAWN_EGG(52, new String[] { "MONSTER_EGG" }),
/* 1227 */   SPLASH_POTION(new String[0]),
/* 1228 */   SPONGE(new String[0]),
/* 1229 */   SPORE_BLOSSOM(new String[0]),
/* 1230 */   SPRUCE_BOAT(new String[] { "BOAT_SPRUCE" }),
/* 1231 */   SPRUCE_BUTTON(new String[] { "WOOD_BUTTON" }),
/* 1232 */   SPRUCE_CHEST_BOAT(new String[0]),
/* 1233 */   SPRUCE_DOOR(new String[] { "SPRUCE_DOOR", "SPRUCE_DOOR_ITEM" }),
/* 1234 */   SPRUCE_FENCE(new String[0]),
/* 1235 */   SPRUCE_FENCE_GATE(new String[0]),
/* 1236 */   SPRUCE_LEAVES(1, new String[] { "LEAVES" }),
/* 1237 */   SPRUCE_LOG(1, new String[] { "LOG" }),
/* 1238 */   SPRUCE_PLANKS(1, new String[] { "WOOD" }),
/* 1239 */   SPRUCE_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/* 1240 */   SPRUCE_SAPLING(1, new String[] { "SAPLING" }),
/* 1241 */   SPRUCE_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/* 1242 */   SPRUCE_SLAB(1, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/* 1243 */   SPRUCE_STAIRS(new String[] { "SPRUCE_WOOD_STAIRS" }),
/* 1244 */   SPRUCE_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/* 1245 */   SPRUCE_WALL_SIGN(new String[] { "WALL_SIGN" }),
/* 1246 */   SPRUCE_WOOD(1, new String[] { "LOG" }),
/* 1247 */   SPYGLASS(new String[0]),
/* 1248 */   SQUID_SPAWN_EGG(94, new String[] { "MONSTER_EGG" }),
/* 1249 */   STICK(new String[0]),
/* 1250 */   STICKY_PISTON(new String[] { "PISTON_BASE", "PISTON_STICKY_BASE" }),
/* 1251 */   STONE(new String[0]),
/* 1252 */   STONECUTTER(new String[0]),
/* 1253 */   STONE_AXE(new String[0]),
/* 1254 */   STONE_BRICKS(new String[] { "SMOOTH_BRICK" }),
/* 1255 */   STONE_BRICK_SLAB(5, new String[] { "DOUBLE_STEP", "STEP", "STONE_SLAB" }),
/* 1256 */   STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }),
/* 1257 */   STONE_BRICK_WALL(new String[0]),
/* 1258 */   STONE_BUTTON(new String[0]),
/* 1259 */   STONE_HOE(new String[0]),
/* 1260 */   STONE_PICKAXE(new String[0]),
/* 1261 */   STONE_PRESSURE_PLATE(new String[] { "STONE_PLATE" }),
/* 1262 */   STONE_SHOVEL(new String[] { "STONE_SPADE" }),
/* 1263 */   STONE_SLAB(new String[] { "DOUBLE_STEP", "STEP" }),
/* 1264 */   STONE_STAIRS(new String[0]),
/* 1265 */   STONE_SWORD(new String[0]),
/* 1266 */   STRAY_SPAWN_EGG(6, new String[] { "MONSTER_EGG" }),
/* 1267 */   STRIDER_SPAWN_EGG(new String[0]),
/* 1268 */   STRING(new String[0]),
/* 1269 */   STRIPPED_ACACIA_LOG(new String[0]),
/* 1270 */   STRIPPED_ACACIA_WOOD(new String[0]),
/* 1271 */   STRIPPED_BIRCH_LOG(new String[0]),
/* 1272 */   STRIPPED_BIRCH_WOOD(new String[0]),
/* 1273 */   STRIPPED_CRIMSON_HYPHAE(new String[0]),
/* 1274 */   STRIPPED_CRIMSON_STEM(new String[0]),
/* 1275 */   STRIPPED_DARK_OAK_LOG(new String[0]),
/* 1276 */   STRIPPED_DARK_OAK_WOOD(new String[0]),
/* 1277 */   STRIPPED_JUNGLE_LOG(new String[0]),
/* 1278 */   STRIPPED_JUNGLE_WOOD(new String[0]),
/* 1279 */   STRIPPED_MANGROVE_LOG(new String[0]),
/* 1280 */   STRIPPED_MANGROVE_WOOD(new String[0]),
/* 1281 */   STRIPPED_OAK_LOG(new String[0]),
/* 1282 */   STRIPPED_OAK_WOOD(new String[0]),
/* 1283 */   STRIPPED_SPRUCE_LOG(new String[0]),
/* 1284 */   STRIPPED_SPRUCE_WOOD(new String[0]),
/* 1285 */   STRIPPED_WARPED_HYPHAE(new String[0]),
/* 1286 */   STRIPPED_WARPED_STEM(new String[0]),
/* 1287 */   STRUCTURE_BLOCK(new String[0]),
/* 1288 */   STRUCTURE_VOID(
/*      */     
/* 1292 */     10, new String[] { "BARRIER" }),
/* 1293 */   SUGAR(new String[0]),
/* 1294 */   SUGAR_CANE(new String[] { "SUGAR_CANE_BLOCK" }),
/* 1298 */   SUNFLOWER(new String[] { "DOUBLE_PLANT" }),
/* 1299 */   SUSPICIOUS_STEW(new String[0]),
/* 1300 */   SWEET_BERRIES(new String[0]),
/* 1301 */   SWEET_BERRY_BUSH(new String[0]),
/* 1302 */   TADPOLE_BUCKET(new String[0]),
/* 1303 */   TADPOLE_SPAWN_EGG(new String[0]),
/* 1304 */   TALL_GRASS(2, new String[] { "DOUBLE_PLANT" }),
/* 1305 */   TALL_SEAGRASS(new String[0]),
/* 1306 */   TARGET(new String[0]),
/* 1307 */   TERRACOTTA(new String[] { "HARD_CLAY" }),
/* 1308 */   TINTED_GLASS(new String[0]),
/* 1309 */   TIPPED_ARROW(new String[0]),
/* 1310 */   TNT(new String[0]),
/* 1311 */   TNT_MINECART(new String[] { "EXPLOSIVE_MINECART" }),
/* 1312 */   TORCH(new String[0]),
/* 1313 */   TOTEM_OF_UNDYING(new String[] { "TOTEM" }),
/* 1314 */   TRADER_LLAMA_SPAWN_EGG(new String[0]),
/* 1315 */   TRAPPED_CHEST(new String[0]),
/* 1316 */   TRIDENT(new String[0]),
/* 1317 */   TRIPWIRE(new String[0]),
/* 1318 */   TRIPWIRE_HOOK(new String[0]),
/* 1319 */   TROPICAL_FISH(2, new String[] { "RAW_FISH" }),
/* 1320 */   TROPICAL_FISH_BUCKET(new String[] { "BUCKET", "WATER_BUCKET" }),
/* 1321 */   TROPICAL_FISH_SPAWN_EGG(new String[] { "MONSTER_EGG" }),
/* 1322 */   TUBE_CORAL(new String[0]),
/* 1323 */   TUBE_CORAL_BLOCK(new String[0]),
/* 1324 */   TUBE_CORAL_FAN(new String[0]),
/* 1325 */   TUBE_CORAL_WALL_FAN(new String[0]),
/* 1326 */   TUFF(new String[0]),
/* 1327 */   TURTLE_EGG(new String[0]),
/* 1328 */   TURTLE_HELMET(new String[0]),
/* 1329 */   TURTLE_SPAWN_EGG(new String[0]),
/* 1330 */   TWISTING_VINES(new String[0]),
/* 1331 */   TWISTING_VINES_PLANT(new String[0]),
/* 1332 */   VERDANT_FROGLIGHT(new String[0]),
/* 1333 */   VEX_SPAWN_EGG(35, new String[] { "MONSTER_EGG" }),
/* 1334 */   VILLAGER_SPAWN_EGG(120, new String[] { "MONSTER_EGG" }),
/* 1335 */   VINDICATOR_SPAWN_EGG(36, new String[] { "MONSTER_EGG" }),
/* 1336 */   VINE(new String[0]),
/* 1337 */   VOID_AIR(new String[] { "AIR" }),
/* 1343 */   WALL_TORCH(new String[] { "TORCH" }),
/* 1344 */   WANDERING_TRADER_SPAWN_EGG(new String[0]),
/* 1345 */   WARDEN_SPAWN_EGG(new String[0]),
/* 1346 */   WARPED_BUTTON(new String[0]),
/* 1347 */   WARPED_DOOR(new String[0]),
/* 1348 */   WARPED_FENCE(new String[0]),
/* 1349 */   WARPED_FENCE_GATE(new String[0]),
/* 1350 */   WARPED_FUNGUS(new String[0]),
/* 1351 */   WARPED_FUNGUS_ON_A_STICK(new String[0]),
/* 1352 */   WARPED_HYPHAE(new String[0]),
/* 1353 */   WARPED_NYLIUM(new String[0]),
/* 1354 */   WARPED_PLANKS(new String[0]),
/* 1355 */   WARPED_PRESSURE_PLATE(new String[0]),
/* 1356 */   WARPED_ROOTS(new String[0]),
/* 1357 */   WARPED_SIGN(new String[] { "SIGN_POST" }),
/* 1358 */   WARPED_SLAB(new String[0]),
/* 1359 */   WARPED_STAIRS(new String[0]),
/* 1360 */   WARPED_STEM(new String[0]),
/* 1361 */   WARPED_TRAPDOOR(new String[0]),
/* 1362 */   WARPED_WALL_SIGN(new String[] { "WALL_SIGN" }),
/* 1363 */   WARPED_WART_BLOCK(new String[0]),
/* 1364 */   WATER(new String[] { "STATIONARY_WATER" }),
/* 1371 */   WATER_BUCKET(new String[0]),
/* 1372 */   WATER_CAULDRON(new String[0]),
/* 1373 */   WAXED_COPPER_BLOCK(new String[0]),
/* 1374 */   WAXED_CUT_COPPER(new String[0]),
/* 1375 */   WAXED_CUT_COPPER_SLAB(new String[0]),
/* 1376 */   WAXED_CUT_COPPER_STAIRS(new String[0]),
/* 1377 */   WAXED_EXPOSED_COPPER(new String[0]),
/* 1378 */   WAXED_EXPOSED_CUT_COPPER(new String[0]),
/* 1379 */   WAXED_EXPOSED_CUT_COPPER_SLAB(new String[0]),
/* 1380 */   WAXED_EXPOSED_CUT_COPPER_STAIRS(new String[0]),
/* 1381 */   WAXED_OXIDIZED_COPPER(new String[0]),
/* 1382 */   WAXED_OXIDIZED_CUT_COPPER(new String[0]),
/* 1383 */   WAXED_OXIDIZED_CUT_COPPER_SLAB(new String[0]),
/* 1384 */   WAXED_OXIDIZED_CUT_COPPER_STAIRS(new String[0]),
/* 1385 */   WAXED_WEATHERED_COPPER(new String[0]),
/* 1386 */   WAXED_WEATHERED_CUT_COPPER(new String[0]),
/* 1387 */   WAXED_WEATHERED_CUT_COPPER_SLAB(new String[0]),
/* 1388 */   WAXED_WEATHERED_CUT_COPPER_STAIRS(new String[0]),
/* 1389 */   WEATHERED_COPPER(new String[0]),
/* 1390 */   WEATHERED_CUT_COPPER(new String[0]),
/* 1391 */   WEATHERED_CUT_COPPER_SLAB(new String[0]),
/* 1392 */   WEATHERED_CUT_COPPER_STAIRS(new String[0]),
/* 1393 */   WEEPING_VINES(new String[0]),
/* 1394 */   WEEPING_VINES_PLANT(new String[0]),
/* 1395 */   WET_SPONGE(1, new String[] { "SPONGE" }),
/* 1396 */   WHEAT(new String[] { "CROPS" }),
/* 1400 */   WHEAT_SEEDS(new String[] { "SEEDS" }),
/* 1401 */   WHITE_BANNER(15, new String[] { "STANDING_BANNER", "BANNER" }),
/* 1402 */   WHITE_BED(new String[] { "BED_BLOCK", "BED" }),
/* 1403 */   WHITE_CANDLE(new String[0]),
/* 1404 */   WHITE_CANDLE_CAKE(new String[0]),
/* 1405 */   WHITE_CARPET(new String[] { "CARPET" }),
/* 1406 */   WHITE_CONCRETE(new String[] { "CONCRETE" }),
/* 1407 */   WHITE_CONCRETE_POWDER(new String[] { "CONCRETE_POWDER" }),
/* 1408 */   WHITE_DYE(15, new String[] { "INK_SACK", "BONE_MEAL" }),
/* 1409 */   WHITE_GLAZED_TERRACOTTA(new String[0]),
/* 1410 */   WHITE_SHULKER_BOX(new String[0]),
/* 1411 */   WHITE_STAINED_GLASS(new String[] { "STAINED_GLASS" }),
/* 1412 */   WHITE_STAINED_GLASS_PANE(new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/* 1413 */   WHITE_TERRACOTTA(new String[] { "STAINED_CLAY" }),
/* 1414 */   WHITE_TULIP(6, new String[] { "RED_ROSE" }),
/* 1415 */   WHITE_WALL_BANNER(15, new String[] { "WALL_BANNER" }),
/* 1416 */   WHITE_WOOL(new String[] { "WOOL" }),
/* 1417 */   WITCH_SPAWN_EGG(66, new String[] { "MONSTER_EGG" }),
/* 1418 */   WITHER_ROSE(new String[0]),
/* 1419 */   WITHER_SKELETON_SKULL(1, new String[] { "SKULL", "SKULL_ITEM" }),
/* 1420 */   WITHER_SKELETON_SPAWN_EGG(5, new String[] { "MONSTER_EGG" }),
/* 1421 */   WITHER_SKELETON_WALL_SKULL(1, new String[] { "SKULL", "SKULL_ITEM" }),
/* 1422 */   WOLF_SPAWN_EGG(95, new String[] { "MONSTER_EGG" }),
/* 1423 */   WOODEN_AXE(new String[] { "WOOD_AXE" }),
/* 1424 */   WOODEN_HOE(new String[] { "WOOD_HOE" }),
/* 1425 */   WOODEN_PICKAXE(new String[] { "WOOD_PICKAXE" }),
/* 1426 */   WOODEN_SHOVEL(new String[] { "WOOD_SPADE" }),
/* 1427 */   WOODEN_SWORD(new String[] { "WOOD_SWORD" }),
/* 1428 */   WRITABLE_BOOK(new String[] { "BOOK_AND_QUILL" }),
/* 1429 */   WRITTEN_BOOK(new String[0]),
/* 1430 */   YELLOW_BANNER(11, new String[] { "STANDING_BANNER", "BANNER" }),
/* 1431 */   YELLOW_BED(supports(12) ? 4 : 0, new String[] { "BED_BLOCK", "BED" }),
/* 1432 */   YELLOW_CANDLE(new String[0]),
/* 1433 */   YELLOW_CANDLE_CAKE(new String[0]),
/* 1434 */   YELLOW_CARPET(4, new String[] { "CARPET" }),
/* 1435 */   YELLOW_CONCRETE(4, new String[] { "CONCRETE" }),
/* 1436 */   YELLOW_CONCRETE_POWDER(4, new String[] { "CONCRETE_POWDER" }),
/* 1437 */   YELLOW_DYE(
/*      */     
/* 1441 */     11, new String[] { "INK_SACK", "DANDELION_YELLOW" }),
/* 1442 */   YELLOW_GLAZED_TERRACOTTA(new String[0]),
/* 1443 */   YELLOW_SHULKER_BOX(new String[0]),
/* 1444 */   YELLOW_STAINED_GLASS(4, new String[] { "STAINED_GLASS" }),
/* 1445 */   YELLOW_STAINED_GLASS_PANE(4, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/* 1446 */   YELLOW_TERRACOTTA(4, new String[] { "STAINED_CLAY" }),
/* 1447 */   YELLOW_WALL_BANNER(11, new String[] { "WALL_BANNER" }),
/* 1448 */   YELLOW_WOOL(4, new String[] { "WOOL" }),
/* 1449 */   ZOGLIN_SPAWN_EGG(new String[0]),
/* 1450 */   ZOMBIE_HEAD(2, new String[] { "SKULL", "SKULL_ITEM" }),
/* 1451 */   ZOMBIE_HORSE_SPAWN_EGG(29, new String[] { "MONSTER_EGG" }),
/* 1452 */   ZOMBIE_SPAWN_EGG(54, new String[] { "MONSTER_EGG" }),
/* 1453 */   ZOMBIE_VILLAGER_SPAWN_EGG(27, new String[] { "MONSTER_EGG" }),
/* 1454 */   ZOMBIE_WALL_HEAD(2, new String[] { "SKULL", "SKULL_ITEM" }),
/* 1455 */   ZOMBIFIED_PIGLIN_SPAWN_EGG(57, new String[] { "MONSTER_EGG", "ZOMBIE_PIGMAN_SPAWN_EGG" });
/*      */   
/*      */   public static final XMaterial[] VALUES;
/*      */   
/*      */   private static final Map<String, XMaterial> NAMES;
/*      */   
/*      */   private static final Cache<String, XMaterial> NAME_CACHE;
/*      */   
/*      */   private static final Cache<String, Pattern> CACHED_REGEX;
/*      */   
/*      */   private static final byte MAX_DATA_VALUE = 120;
/*      */   
/*      */   private static final byte UNKNOWN_DATA_VALUE = -1;
/*      */   
/*      */   private static final short MAX_ID = 2267;
/*      */   
/*      */   private static final Set<String> DUPLICATED;
/*      */   
/*      */   private final byte data;
/*      */   
/*      */   @Nonnull
/*      */   private final String[] legacy;
/*      */   
/*      */   @Nullable
/*      */   private final Material material;
/*      */   
/*      */   static {
/* 1464 */     VALUES = values();
/* 1471 */     NAMES = new HashMap<>();
/* 1479 */     NAME_CACHE = CacheBuilder.newBuilder()
/* 1480 */       .expireAfterAccess(1L, TimeUnit.HOURS)
/* 1481 */       .build();
/* 1487 */     CACHED_REGEX = CacheBuilder.newBuilder()
/* 1488 */       .expireAfterAccess(3L, TimeUnit.HOURS)
/* 1489 */       .build();
/*      */     byte b;
/*      */     int i;
/*      */     XMaterial[] arrayOfXMaterial;
/* 1525 */     for (i = (arrayOfXMaterial = VALUES).length, b = 0; b < i; ) {
/* 1525 */       XMaterial material = arrayOfXMaterial[b];
/* 1525 */       NAMES.put(material.name(), material);
/* 1525 */       b++;
/*      */     } 
/* 1529 */     if (Data.ISFLAT) {
/* 1531 */       DUPLICATED = null;
/*      */     } else {
/* 1535 */       DUPLICATED = new HashSet<>(4);
/* 1536 */       DUPLICATED.add(GRASS.name());
/* 1537 */       DUPLICATED.add(MELON.name());
/* 1538 */       DUPLICATED.add(BRICK.name());
/* 1539 */       DUPLICATED.add(NETHER_BRICK.name());
/*      */     } 
/*      */   }
/*      */   
/*      */   XMaterial(int data, String... legacy) {
/* 1567 */     this.data = (byte)data;
/* 1568 */     this.legacy = legacy;
/* 1570 */     Material mat = null;
/* 1571 */     if ((!Data.ISFLAT && isDuplicated()) || (mat = Material.getMaterial(name())) == null)
/* 1572 */       for (int i = legacy.length - 1; i >= 0; i--) {
/* 1573 */         mat = Material.getMaterial(legacy[i]);
/* 1574 */         if (mat != null)
/*      */           break; 
/*      */       }  
/* 1577 */     this.material = mat;
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   private static Optional<XMaterial> getIfPresent(@Nonnull String name) {
/* 1593 */     return Optional.ofNullable(NAMES.get(name));
/*      */   }
/*      */   
/*      */   public static int getVersion() {
/* 1604 */     return Data.VERSION;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   private static XMaterial requestOldXMaterial(@Nonnull String name, byte data) {
/* 1616 */     String holder = String.valueOf(name) + data;
/* 1617 */     XMaterial cache = (XMaterial)NAME_CACHE.getIfPresent(holder);
/* 1618 */     if (cache != null)
/* 1618 */       return cache; 
/*      */     byte b;
/*      */     int i;
/*      */     XMaterial[] arrayOfXMaterial;
/* 1620 */     for (i = (arrayOfXMaterial = VALUES).length, b = 0; b < i; ) {
/* 1620 */       XMaterial material = arrayOfXMaterial[b];
/* 1622 */       if ((data == -1 || data == material.data) && material.anyMatchLegacy(name)) {
/* 1623 */         NAME_CACHE.put(holder, material);
/* 1624 */         return material;
/*      */       } 
/*      */       b++;
/*      */     } 
/* 1628 */     return null;
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   public static Optional<XMaterial> matchXMaterial(@Nonnull String name) {
/* 1641 */     if (name == null || name.isEmpty())
/* 1641 */       throw new IllegalArgumentException("Cannot match a material with null or empty material name"); 
/* 1642 */     Optional<XMaterial> oldMatch = matchXMaterialWithData(name);
/* 1643 */     return oldMatch.isPresent() ? oldMatch : matchDefinedXMaterial(format(name), (byte)-1);
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   private static Optional<XMaterial> matchXMaterialWithData(@Nonnull String name) {
/* 1665 */     int index = name.indexOf(':');
/* 1666 */     if (index != -1) {
/* 1667 */       String mat = format(name.substring(0, index));
/*      */       try {
/* 1670 */         byte data = (byte)Integer.parseInt(name.substring(index + 1).replace(" ", ""));
/* 1671 */         return (data >= 0 && data < 120) ? matchDefinedXMaterial(mat, data) : matchDefinedXMaterial(mat, (byte)-1);
/* 1672 */       } catch (NumberFormatException ignored) {
/* 1673 */         return matchDefinedXMaterial(mat, (byte)-1);
/*      */       } 
/*      */     } 
/* 1677 */     return Optional.empty();
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   public static XMaterial matchXMaterial(@Nonnull Material material) {
/* 1690 */     Objects.requireNonNull(material, "Cannot match null material");
/* 1691 */     return matchDefinedXMaterial(material.name(), (byte)-1)
/* 1692 */       .<Throwable>orElseThrow(() -> new IllegalArgumentException("Unsupported material with no data value: " + paramMaterial.name()));
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   public static XMaterial matchXMaterial(@Nonnull ItemStack item) {
/* 1708 */     Objects.requireNonNull(item, "Cannot match null ItemStack");
/* 1709 */     String material = item.getType().name();
/* 1710 */     byte data = (byte)((Data.ISFLAT || item.getType().getMaxDurability() > 0) ? 0 : item.getDurability());
/* 1713 */     if (supports(9) && !supports(13) && item.hasItemMeta() && material.equals("MONSTER_EGG")) {
/* 1714 */       ItemMeta meta = item.getItemMeta();
/* 1715 */       if (meta instanceof SpawnEggMeta) {
/* 1716 */         SpawnEggMeta egg = (SpawnEggMeta)meta;
/* 1717 */         material = String.valueOf(egg.getSpawnedType().name()) + "_SPAWN_EGG";
/*      */       } 
/*      */     } 
/* 1723 */     if (!supports(9) && material.endsWith("ION"))
/* 1725 */       return Potion.fromItemStack(item).isSplash() ? SPLASH_POTION : POTION; 
/* 1732 */     if (supports(13) && !supports(14)) {
/* 1734 */       if (material.equals("CACTUS_GREEN"))
/* 1734 */         return GREEN_DYE; 
/* 1735 */       if (material.equals("ROSE_RED"))
/* 1735 */         return RED_DYE; 
/* 1736 */       if (material.equals("DANDELION_YELLOW"))
/* 1736 */         return YELLOW_DYE; 
/*      */     } 
/* 1743 */     Optional<XMaterial> result = matchDefinedXMaterial(material, data);
/* 1744 */     if (result.isPresent())
/* 1744 */       return result.get(); 
/* 1745 */     throw new IllegalArgumentException("Unsupported material from item: " + material + " (" + data + ')');
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   protected static Optional<XMaterial> matchDefinedXMaterial(@Nonnull String name, byte data) {
/* 1764 */     Boolean duplicated = null;
/* 1765 */     boolean isAMap = name.equalsIgnoreCase("MAP");
/* 1768 */     if (Data.ISFLAT || (!isAMap && data <= 0 && !(duplicated = Boolean.valueOf(isDuplicated(name))).booleanValue())) {
/* 1769 */       Optional<XMaterial> xMaterial = getIfPresent(name);
/* 1770 */       if (xMaterial.isPresent())
/* 1770 */         return xMaterial; 
/*      */     } 
/* 1774 */     XMaterial oldXMaterial = requestOldXMaterial(name, data);
/* 1775 */     if (oldXMaterial == null)
/* 1777 */       return (data >= 0 && isAMap) ? Optional.<XMaterial>of(FILLED_MAP) : Optional.<XMaterial>empty(); 
/* 1780 */     if (!Data.ISFLAT && oldXMaterial.isPlural() && ((duplicated == null) ? isDuplicated(name) : duplicated.booleanValue()))
/* 1780 */       return getIfPresent(name); 
/* 1781 */     return Optional.of(oldXMaterial);
/*      */   }
/*      */   
/*      */   private static boolean isDuplicated(@Nonnull String name) {
/* 1798 */     return DUPLICATED.contains(name);
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   @Deprecated
/*      */   public static Optional<XMaterial> matchXMaterial(int id, byte data) {
/* 1818 */     if (id < 0 || id > 2267 || data < 0)
/* 1818 */       return Optional.empty(); 
/*      */     byte b;
/*      */     int i;
/*      */     XMaterial[] arrayOfXMaterial;
/* 1819 */     for (i = (arrayOfXMaterial = VALUES).length, b = 0; b < i; ) {
/* 1819 */       XMaterial materials = arrayOfXMaterial[b];
/* 1820 */       if (materials.data == data && materials.getId() == id)
/* 1820 */         return Optional.of(materials); 
/*      */       b++;
/*      */     } 
/* 1822 */     return Optional.empty();
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   protected static String format(@Nonnull String name) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual length : ()I
/*      */     //   4: istore_1
/*      */     //   5: iload_1
/*      */     //   6: newarray char
/*      */     //   8: astore_2
/*      */     //   9: iconst_0
/*      */     //   10: istore_3
/*      */     //   11: iconst_0
/*      */     //   12: istore #4
/*      */     //   14: iconst_0
/*      */     //   15: istore #5
/*      */     //   17: goto -> 175
/*      */     //   20: aload_0
/*      */     //   21: iload #5
/*      */     //   23: invokevirtual charAt : (I)C
/*      */     //   26: istore #6
/*      */     //   28: iload #4
/*      */     //   30: ifne -> 72
/*      */     //   33: iload_3
/*      */     //   34: ifeq -> 72
/*      */     //   37: iload #6
/*      */     //   39: bipush #45
/*      */     //   41: if_icmpeq -> 58
/*      */     //   44: iload #6
/*      */     //   46: bipush #32
/*      */     //   48: if_icmpeq -> 58
/*      */     //   51: iload #6
/*      */     //   53: bipush #95
/*      */     //   55: if_icmpne -> 72
/*      */     //   58: aload_2
/*      */     //   59: iload_3
/*      */     //   60: caload
/*      */     //   61: bipush #95
/*      */     //   63: if_icmpeq -> 72
/*      */     //   66: iconst_1
/*      */     //   67: istore #4
/*      */     //   69: goto -> 172
/*      */     //   72: iconst_0
/*      */     //   73: istore #7
/*      */     //   75: iload #6
/*      */     //   77: bipush #65
/*      */     //   79: if_icmplt -> 89
/*      */     //   82: iload #6
/*      */     //   84: bipush #90
/*      */     //   86: if_icmple -> 128
/*      */     //   89: iload #6
/*      */     //   91: bipush #97
/*      */     //   93: if_icmplt -> 103
/*      */     //   96: iload #6
/*      */     //   98: bipush #122
/*      */     //   100: if_icmple -> 128
/*      */     //   103: iload #6
/*      */     //   105: bipush #48
/*      */     //   107: if_icmplt -> 121
/*      */     //   110: iload #6
/*      */     //   112: bipush #57
/*      */     //   114: if_icmpgt -> 121
/*      */     //   117: iconst_1
/*      */     //   118: goto -> 122
/*      */     //   121: iconst_0
/*      */     //   122: dup
/*      */     //   123: istore #7
/*      */     //   125: ifeq -> 172
/*      */     //   128: iload #4
/*      */     //   130: ifeq -> 144
/*      */     //   133: aload_2
/*      */     //   134: iload_3
/*      */     //   135: iinc #3, 1
/*      */     //   138: bipush #95
/*      */     //   140: castore
/*      */     //   141: iconst_0
/*      */     //   142: istore #4
/*      */     //   144: iload #7
/*      */     //   146: ifeq -> 160
/*      */     //   149: aload_2
/*      */     //   150: iload_3
/*      */     //   151: iinc #3, 1
/*      */     //   154: iload #6
/*      */     //   156: castore
/*      */     //   157: goto -> 172
/*      */     //   160: aload_2
/*      */     //   161: iload_3
/*      */     //   162: iinc #3, 1
/*      */     //   165: iload #6
/*      */     //   167: bipush #95
/*      */     //   169: iand
/*      */     //   170: i2c
/*      */     //   171: castore
/*      */     //   172: iinc #5, 1
/*      */     //   175: iload #5
/*      */     //   177: iload_1
/*      */     //   178: if_icmplt -> 20
/*      */     //   181: new java/lang/String
/*      */     //   184: dup
/*      */     //   185: aload_2
/*      */     //   186: iconst_0
/*      */     //   187: iload_3
/*      */     //   188: invokespecial <init> : ([CII)V
/*      */     //   191: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1838	-> 0
/*      */     //   #1839	-> 5
/*      */     //   #1840	-> 9
/*      */     //   #1841	-> 11
/*      */     //   #1843	-> 14
/*      */     //   #1844	-> 20
/*      */     //   #1846	-> 28
/*      */     //   #1847	-> 66
/*      */     //   #1849	-> 72
/*      */     //   #1851	-> 75
/*      */     //   #1852	-> 128
/*      */     //   #1853	-> 133
/*      */     //   #1854	-> 141
/*      */     //   #1857	-> 144
/*      */     //   #1858	-> 160
/*      */     //   #1843	-> 172
/*      */     //   #1863	-> 181
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	192	0	name	Ljava/lang/String;
/*      */     //   5	187	1	len	I
/*      */     //   9	183	2	chs	[C
/*      */     //   11	181	3	count	I
/*      */     //   14	178	4	appendUnderline	Z
/*      */     //   17	164	5	i	I
/*      */     //   28	144	6	ch	C
/*      */     //   75	97	7	number	Z
/*      */   }
/*      */   
/*      */   public static boolean supports(int version) {
/* 1875 */     return (Data.VERSION >= version);
/*      */   }
/*      */   
/*      */   public String[] getLegacy() {
/* 1879 */     return this.legacy;
/*      */   }
/*      */   
/*      */   private boolean isPlural() {
/* 1900 */     return !(this != CARROTS && this != POTATOES);
/*      */   }
/*      */   
/*      */   public boolean isOneOf(@Nullable Collection<String> materials) {
/* 1942 */     if (materials == null || materials.isEmpty())
/* 1942 */       return false; 
/* 1943 */     String name = name();
/* 1945 */     for (String comp : materials) {
/* 1946 */       String checker = comp.toUpperCase(Locale.ENGLISH);
/* 1947 */       if (checker.startsWith("CONTAINS:")) {
/* 1948 */         comp = format(checker.substring(9));
/* 1949 */         if (name.contains(comp))
/* 1949 */           return true; 
/*      */         continue;
/*      */       } 
/* 1952 */       if (checker.startsWith("REGEX:")) {
/* 1953 */         comp = comp.substring(6);
/* 1954 */         Pattern pattern = (Pattern)CACHED_REGEX.getIfPresent(comp);
/* 1955 */         if (pattern == null)
/*      */           try {
/* 1957 */             pattern = Pattern.compile(comp);
/* 1958 */             CACHED_REGEX.put(comp, pattern);
/* 1959 */           } catch (PatternSyntaxException ex) {
/* 1960 */             ex.printStackTrace();
/*      */           }  
/* 1963 */         if (pattern != null && pattern.matcher(name).matches())
/* 1963 */           return true; 
/*      */         continue;
/*      */       } 
/* 1968 */       Optional<XMaterial> xMat = matchXMaterial(comp);
/* 1969 */       if (xMat.isPresent() && xMat.get() == this)
/* 1969 */         return true; 
/*      */     } 
/* 1971 */     return false;
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   public ItemStack setType(@Nonnull ItemStack item) {
/* 1987 */     Objects.requireNonNull(item, "Cannot set material for null ItemStack");
/* 1988 */     Material material = parseMaterial();
/* 1989 */     Objects.requireNonNull(material, () -> "Unsupported material: " + name());
/* 1991 */     item.setType(material);
/* 1992 */     if (!Data.ISFLAT && material.getMaxDurability() <= 0)
/* 1992 */       item.setDurability(this.data); 
/* 1993 */     return item;
/*      */   }
/*      */   
/*      */   private boolean anyMatchLegacy(@Nonnull String name) {
/* 2006 */     for (int i = this.legacy.length - 1; i >= 0; i--) {
/* 2007 */       if (name.equals(this.legacy[i]))
/* 2007 */         return true; 
/*      */     } 
/* 2009 */     return false;
/*      */   }
/*      */   
/*      */   @Nonnull
/*      */   public String toString() {
/* 2029 */     return Arrays.<String>stream(name().split("_"))
/* 2030 */       .map(t -> String.valueOf(t.charAt(0)) + t.substring(1).toLowerCase())
/* 2031 */       .collect(Collectors.joining(" "));
/*      */   }
/*      */   
/*      */   public int getId() {
/* 2047 */     Material material = parseMaterial();
/* 2048 */     if (material == null)
/* 2048 */       return -1; 
/*      */     try {
/* 2050 */       return material.getId();
/* 2051 */     } catch (IllegalArgumentException ignored) {
/* 2052 */       return -1;
/*      */     } 
/*      */   }
/*      */   
/*      */   public byte getData() {
/* 2066 */     return this.data;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public ItemStack parseItem() {
/* 2079 */     Material material = parseMaterial();
/* 2080 */     if (material == null)
/* 2080 */       return null; 
/* 2081 */     return Data.ISFLAT ? new ItemStack(material) : new ItemStack(material, 1, this.data);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Material parseMaterial() {
/* 2092 */     return this.material;
/*      */   }
/*      */   
/*      */   public boolean isSimilar(@Nonnull ItemStack item) {
/* 2104 */     Objects.requireNonNull(item, "Cannot compare with null ItemStack");
/* 2105 */     if (item.getType() != parseMaterial())
/* 2105 */       return false; 
/* 2106 */     return !(!Data.ISFLAT && item.getDurability() != this.data && item.getType().getMaxDurability() <= 0);
/*      */   }
/*      */   
/*      */   public boolean isSupported() {
/* 2120 */     return (this.material != null);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public XMaterial or(@Nullable XMaterial alternateMaterial) {
/* 2135 */     return isSupported() ? this : alternateMaterial;
/*      */   }
/*      */   
/*      */   private boolean isDuplicated() {
/*      */     String str;
/* 2149 */     switch ((str = name()).hashCode()) {
/*      */       case -1929109465:
/* 2149 */         if (!str.equals("POTATO"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case -1722057187:
/*      */         if (!str.equals("DARK_OAK_DOOR"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case -519277571:
/*      */         if (!str.equals("BIRCH_DOOR"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case -333218805:
/*      */         if (!str.equals("SPRUCE_DOOR"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case -328086150:
/*      */         if (!str.equals("NETHER_BRICK"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 76092:
/*      */         if (!str.equals("MAP"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 63467553:
/*      */         if (!str.equals("BRICK"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 68077974:
/*      */         if (!str.equals("GRASS"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 73242259:
/*      */         if (!str.equals("MELON"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 478520881:
/*      */         if (!str.equals("ACACIA_DOOR"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 868145122:
/*      */         if (!str.equals("CAULDRON"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 1379814896:
/*      */         if (!str.equals("JUNGLE_DOOR"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 1401892433:
/*      */         if (!str.equals("FLOWER_POT"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 1545025079:
/*      */         if (!str.equals("BREWING_STAND"))
/*      */           break; 
/* 2172 */         return true;
/*      */       case 1980706179:
/*      */         if (!str.equals("CARROT"))
/*      */           break; 
/* 2172 */         return true;
/*      */     } 
/* 2174 */     return false;
/*      */   }
/*      */   
/*      */   private static final class Data {
/*      */     private static final int VERSION;
/*      */     
/*      */     static {
/* 2193 */       String version = Bukkit.getVersion();
/* 2194 */       Matcher matcher = Pattern.compile("MC: \\d\\.(\\d+)").matcher(version);
/* 2196 */       if (matcher.find()) {
/* 2196 */         VERSION = Integer.parseInt(matcher.group(1));
/*      */       } else {
/* 2197 */         throw new IllegalArgumentException("Failed to parse server version from: " + version);
/*      */       } 
/*      */     }
/*      */     
/* 2205 */     private static final boolean ISFLAT = XMaterial.supports(13);
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFli\\utilz\XMaterial.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */