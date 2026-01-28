package org.cloudea.mekanismstackprocessor.mixin;

import mekanism.api.recipes.CombinerRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tile.factory.TileEntityCombiningFactory;
import mekanism.common.tile.factory.TileEntityFactory;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin для Combining Factory.
 */
@Mixin(value = TileEntityCombiningFactory.class, remap = false)
public abstract class CombiningFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull CombinerRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<CombinerRecipe>> cir) {
        CachedRecipe<CombinerRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            TileEntityFactory<?> factory = (TileEntityFactory<?>) (Object) this;
            int multiplier = StackProcessorUtil.getFactoryMultiplier(factory.tier);
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> multiplier);
            }
        }
    }
}
