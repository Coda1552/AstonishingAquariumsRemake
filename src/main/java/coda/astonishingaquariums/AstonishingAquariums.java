package coda.astonishingaquariums;

import coda.astonishingaquariums.registry.AABlocks;
import coda.astonishingaquariums.registry.AAItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AstonishingAquariums.MOD_ID)
public class AstonishingAquariums {
    public static final String MOD_ID = "astonishingaquariums";

    public AstonishingAquariums() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        AABlocks.BLOCKS.register(bus);
        AAItems.ITEMS.register(bus);

        bus.addListener(this::clientSetup);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(AABlocks.AQUARIUM_BLOCK.get(), RenderType.cutout());
    }
}
