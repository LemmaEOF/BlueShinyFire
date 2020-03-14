package space.bbkr.blueshinyfire;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.SoulFireBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import space.bbkr.blueshinyfire.item.SoulFlintAndSteelItem;


public class blueshinyfire implements ModInitializer {
    public static final Item SOULDUST = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item SAWDUST = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final SoulFlintAndSteelItem SOULFLINTANDSTEEL = new SoulFlintAndSteelItem(new Item.Settings().group(ItemGroup.TOOLS));
    
    //public static final SoulFireBlock SOULFIREBRIGHT = new SoulFireBlock(FabricBlockSettings.of(Material.FIRE, MaterialColor.LIGHT_BLUE).lightLevel(15).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.WOOL).dropsNothing().build());
    
    @Override
    public void onInitialize()
    {
        Registry.register(Registry.ITEM, new Identifier("blueshinyfire","soul_dust"), SOULDUST);
        Registry.register(Registry.ITEM, new Identifier("blueshinyfire","sawdust"), SAWDUST);
        Registry.register(Registry.ITEM, new Identifier("blueshinyfire","soul_flint_and_steel"), SOULFLINTANDSTEEL);
    
        //Registry.register(Registry.BLOCK, new Identifier("blueshinyfire", "soul_fire"), SOULFIREBRIGHT);
    }
}
