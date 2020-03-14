package space.bbkr.blueshinyfire;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class blueshinyfire implements ModInitializer {
    // an instance of our new item
    public static final Item SOULDUST = new Item(new Item.Settings().group(ItemGroup.MISC));
 
    @Override
    public void onInitialize()
    {
        Registry.register(Registry.ITEM, new Identifier("blueshinyfire","soul_dust"), SOULDUST);
    }
}
