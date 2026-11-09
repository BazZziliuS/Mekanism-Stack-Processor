package org.cloudea.mekanismstackprocessor.mixin;

import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tile.factory.TileEntityCombiningFactory;
import mekanism.common.tile.factory.TileEntityFactory;
import mekanism.common.tile.factory.TileEntityItemStackToItemStackFactory;
import mekanism.common.tile.factory.TileEntitySawingFactory;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin для предметных фабрик (Crushing, Enriching, Smelting, Combining, Sawing).
 */
@Mixin(value = {
        TileEntityItemStackToItemStackFactory.class,
        TileEntityCombiningFactory.class,
        TileEntitySawingFactory.class
}, remap = false)
public abstract class ItemFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(CallbackInfoReturnable<CachedRecipe<?>> cir) {
        TileEntityFactory<?> factory = (TileEntityFactory<?>) (Object) this;
        StackProcessorUtil.applyBatch(cir.getReturnValue(),
                StackProcessorUtil.getFactoryMultiplier(factory.tier), factory::getOperationsPerTick);
    }
}
