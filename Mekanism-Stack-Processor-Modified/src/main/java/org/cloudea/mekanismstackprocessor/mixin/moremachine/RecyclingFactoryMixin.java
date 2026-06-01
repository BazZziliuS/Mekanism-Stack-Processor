package org.cloudea.mekanismstackprocessor.mixin.moremachine;

import com.jerry.mekmm.api.recipes.RecyclerRecipe;
import com.jerry.mekmm.common.tile.factory.TileEntityMoreMachineFactory;
import com.jerry.mekmm.common.tile.factory.TileEntityRecyclingFactory;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for MoreMachine Recycling Factory.
 */
@Mixin(value = TileEntityRecyclingFactory.class, remap = false)
public abstract class RecyclingFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull RecyclerRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<RecyclerRecipe>> cir) {
        CachedRecipe<RecyclerRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            TileEntityMoreMachineFactory<?> factory = (TileEntityMoreMachineFactory<?>) (Object) this;
            int multiplier = StackProcessorUtil.getFactoryMultiplier(factory.tier);
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> factory.getOperationsPerTick() * multiplier);
            }
        }
    }
}
