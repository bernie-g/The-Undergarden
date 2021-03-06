package quek.undergarden.data.provider;

import net.minecraft.block.Block;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenItems;

import java.util.function.Supplier;

public class UndergardenRecipeProvider extends ForgeRecipeProvider implements IConditionBuilder {

    public UndergardenRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public ShapelessRecipeBuilder makePlanks(Supplier<? extends Block> plankOut, Supplier<? extends Block> logIn) {
        return ShapelessRecipeBuilder.shapelessRecipe(plankOut.get(), 4)
                .addIngredient(logIn.get())
                .addCriterion("has_" + logIn.get().getRegistryName().getPath(), hasItem(logIn.get()));
    }

    public ShapedRecipeBuilder makeBricks(Supplier<? extends Block> bricksOut, Supplier<? extends Block> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(bricksOut.get(), 4)
                .patternLine("MM")
                .patternLine("MM")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeSticks(Supplier<? extends Item> stickOut, Tag<Item> planksIn) {
        return ShapedRecipeBuilder.shapedRecipe(stickOut.get(), 4)
                .patternLine("#")
                .patternLine("#")
                .key('#', planksIn)
                .addCriterion("has_" + planksIn.getId().getPath(), hasItem(planksIn));
    }

    public ShapedRecipeBuilder makeShardToIngot() {
        return ShapedRecipeBuilder.shapedRecipe(UndergardenItems.utherium_ingot.get(), 1)
                .patternLine("SS")
                .patternLine("SS")
                .key('S', UndergardenItems.utheric_shard.get())
                .addCriterion("has_" + UndergardenItems.utheric_shard.get().getRegistryName().getPath(), hasItem(UndergardenItems.utheric_shard.get()));
    }

    public ShapedRecipeBuilder makeNuggetToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Item> nuggetIn) {
        return ShapedRecipeBuilder.shapedRecipe(ingotOut.get(), 1)
                .patternLine("NNN")
                .patternLine("NNN")
                .patternLine("NNN")
                .key('N', nuggetIn.get())
                .addCriterion("has_" + nuggetIn.get().getRegistryName().getPath(), hasItem(nuggetIn.get()));
    }

    public ShapelessRecipeBuilder makeIngotToNugget(Supplier<? extends Item> nuggetOut, Supplier<? extends Item> ingotIn) {
        return ShapelessRecipeBuilder.shapelessRecipe(nuggetOut.get(), 9)
                .addIngredient(ingotIn.get())
                .addCriterion("has_" + ingotIn.get().getRegistryName().getPath(), hasItem(ingotIn.get()));
    }

    public ShapedRecipeBuilder makeSword(Supplier<? extends Item> swordOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(swordOut.get())
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', materialIn.get())
                .key('/', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeTagSword(Supplier<? extends Item> swordOut, Tag<Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(swordOut.get())
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', materialIn)
                .key('/', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_" + materialIn.getId().getPath(), hasItem(materialIn));
    }

    public ShapedRecipeBuilder makePickaxe(Supplier<? extends Item> pickaxeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(pickaxeOut.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', materialIn.get())
                .key('/', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeTagPickaxe(Supplier<? extends Item> pickaxeOut, Tag<Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(pickaxeOut.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', materialIn)
                .key('/', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_" + materialIn.getId().getPath(), hasItem(materialIn));
    }

    public ShapedRecipeBuilder makeAxe(Supplier<? extends Item> axeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(axeOut.get())
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', materialIn.get())
                .key('/', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeTagAxe(Supplier<? extends Item> axeOut, Tag<Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(axeOut.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', materialIn)
                .key('/', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_" + materialIn.getId().getPath(), hasItem(materialIn));
    }

    public ShapedRecipeBuilder makeShovel(Supplier<? extends Item> shovelOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(shovelOut.get())
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', materialIn.get())
                .key('/', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeTagShovel(Supplier<? extends Item> shovelOut, Tag<Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(shovelOut.get())
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', materialIn)
                .key('/', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_" + materialIn.getId().getPath(), hasItem(materialIn));
    }

    public ShapedRecipeBuilder makeHelmet(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(helmetOut.get())
                .patternLine("MMM")
                .patternLine("M M")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeChestplate(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(helmetOut.get())
                .patternLine("M M")
                .patternLine("MMM")
                .patternLine("MMM")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeLeggings(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(helmetOut.get())
                .patternLine("MMM")
                .patternLine("M M")
                .patternLine("M M")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeBoots(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(helmetOut.get())
                .patternLine("M M")
                .patternLine("M M")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.smeltingRecipe(Ingredient.fromStacks(new ItemStack(ingredient, count)), result, exp, 200)
                .addCriterion("has_" + ingredient.asItem().getRegistryName(), hasItem(ingredient));
    }

    public CookingRecipeBuilder blastingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return blastingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder blastingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.blastingRecipe(Ingredient.fromStacks(new ItemStack(ingredient, count)), result, exp, 100)
                .addCriterion("has_" + ingredient.asItem().getRegistryName(), hasItem(ingredient));
    }

    public CookingRecipeBuilder smokingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return smokingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder smokingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.cookingRecipe(Ingredient.fromStacks(new ItemStack(ingredient, count)), result, exp, 100, IRecipeSerializer.SMOKING)
                .addCriterion("has_" + ingredient.asItem().getRegistryName(), hasItem(ingredient));
    }
}
