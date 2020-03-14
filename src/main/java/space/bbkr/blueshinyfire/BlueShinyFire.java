package space.bbkr.blueshinyfire;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import space.bbkr.blueshinyfire.item.SoulFlintAndSteelItem;


public class BlueShinyFire implements ModInitializer {
    public static final String MODID = "blueshinyfire";

    public static final Item SOUL_DUST = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item SAWDUST = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item SOUL_FLINT_AND_STEEL = new SoulFlintAndSteelItem(new Item.Settings().group(ItemGroup.TOOLS));

    public static final Tag<Item> SOULIFYING = TagRegistry.item(new Identifier(MODID, "soulifying"));
    public static final Tag<Item> DESOULIFYING = TagRegistry.item(new Identifier(MODID, "desoulifying"));
    public static final Tag<Item> BRIGHTENING = TagRegistry.item(new Identifier(MODID, "brightening"));
    
    public static final Block BRIGHT_SOUL_FIRE = new SoulFireBlock(FabricBlockSettings.copy(Blocks.SOUL_FIRE).lightLevel(15).build());
    
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MODID,"soul_dust"), SOUL_DUST);
        Registry.register(Registry.ITEM, new Identifier(MODID,"sawdust"), SAWDUST);
        Registry.register(Registry.ITEM, new Identifier(MODID,"soul_flint_and_steel"), SOUL_FLINT_AND_STEEL);
    
        Registry.register(Registry.BLOCK, new Identifier("blueshinyfire", "bright_soul_fire"), BRIGHT_SOUL_FIRE);
    }
}
