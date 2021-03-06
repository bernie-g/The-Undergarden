package quek.undergarden.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class UndergardenTags {

    public static class Items {

        public static final Tag<Item> SMOGSTEM_PLANKS = tag("smogstem_planks");
        public static final Tag<Item> SMOGSTEM_STICK = tag("smogstem_stick");
        public static final Tag<Item> WIGGLEWOOD_PLANKS = tag("wigglewood_planks");

        private static Tag<Item> tag(String name)
        {
            return new ItemTags.Wrapper(new ResourceLocation("undergarden", name));
        }

    }

    public static class Blocks {



        private static Tag<Block> tag(String name)
        {
            return new BlockTags.Wrapper(new ResourceLocation("undergarden", name));
        }

    }

}
