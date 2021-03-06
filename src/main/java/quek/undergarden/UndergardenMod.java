package quek.undergarden;

import io.netty.buffer.Unpooled;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import quek.undergarden.data.*;
import quek.undergarden.client.render.*;
import quek.undergarden.registry.*;
import quek.undergarden.world.gen.carver.UndergardenCaveWorldCarver;

@Mod(UndergardenMod.MODID)
public class UndergardenMod {
	
	public static final String MODID = "undergarden";

	public static DimensionType undergarden_dimension;

	public UndergardenMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);

		UndergardenEntities.ENTITIES.register(bus);
		UndergardenBlocks.BLOCKS.register(bus);
		UndergardenItems.ITEMS.register(bus);
		UndergardenDimensions.MOD_DIMENSIONS.register(bus);
		UndergardenBiomes.BIOMES.register(bus);
		UndergardenFeatures.FEATURES.register(bus);
		UndergardenWorldCarvers.CARVERS.register(bus);
	}

	public void setup(FMLCommonSetupEvent event) {
		UndergardenBiomes.addBiomeTypes();
		UndergardenBiomes.addBiomeFeatures();
	}

	public void clientSetup(FMLClientSetupEvent event) {
		ClientStuff.registerBlockRenderers();
		RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotwalker, RotwalkerRender::new);
		RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotbeast, RotbeastRender::new);
		RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.dweller, DwellerRender::new);
		RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.gwibling, GwiblingRender::new);
	}


	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		generator.addProvider(new UndergardenLang(generator));
		generator.addProvider(new UndergardenRecipes(generator));
		generator.addProvider(new UndergardenLootTables(generator));
		generator.addProvider(new UndergardenBlockStates(generator, event.getExistingFileHelper()));
		generator.addProvider(new UndergardenItemModels(generator, event.getExistingFileHelper()));
	}

	@Mod.EventBusSubscriber(modid = MODID)
	public static class ForgeEventBus {
		@SubscribeEvent
		public static void registerModDimension(final RegisterDimensionsEvent event) {
			ResourceLocation undergarden = new ResourceLocation(UndergardenMod.MODID, "undergarden");

			if (DimensionType.byName(undergarden) == null) {
				undergarden_dimension = DimensionManager.registerDimension(undergarden, UndergardenDimensions.UNDERGARDEN.get(), new PacketBuffer(Unpooled.buffer()), false);
				DimensionManager.keepLoaded(undergarden_dimension, false);
			} else {
				undergarden_dimension = DimensionType.byName(undergarden);
			}
		}

		@SubscribeEvent
		public static void registerWorldCarver(final RegistryEvent.Register<WorldCarver<?>> event) {
			event.getRegistry().register(new UndergardenCaveWorldCarver(ProbabilityConfig::deserialize));
		}

		@ObjectHolder("undergarden:undergarden_cave")
		public static UndergardenCaveWorldCarver UNDERGARDEN_CAVE;
	}
}
