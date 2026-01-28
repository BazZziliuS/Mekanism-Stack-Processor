package org.cloudea.mekanismstackprocessor.mixin;

import mekanism.api.recipes.SawmillRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tile.factory.TileEntityFactory;
import mekanism.common.tile.factory.TileEntitySawingFactory;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin для Sawing Factory (Precision Sawmill).
 */
@Mixin(value = TileEntitySawingFactory.class, remap = false)
public abstract class SawingFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull SawmillRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<SawmillRecipe>> cir) {
        CachedRecipe<SawmillRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            TileEntityFactory<?> factory = (TileEntityFactory<?>) (Object) this;
            int multiplier = StackProcessorUtil.getFactoryMultiplier(factory.tier);
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> StackProcessorUtil.getOperationsPerTick(factory) * multiplier);
            }
        }
    }
}
