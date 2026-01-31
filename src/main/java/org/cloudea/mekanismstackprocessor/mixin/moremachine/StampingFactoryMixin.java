package org.cloudea.mekanismstackprocessor.mixin.moremachine;

import com.jerry.mekmm.api.recipes.StamperRecipe;
import com.jerry.mekmm.common.tile.factory.TileEntityMoreMachineFactory;
import com.jerry.mekmm.common.tile.factory.TileEntityStampingFactory;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for MoreMachine Stamping Factory.
 */
@Mixin(value = TileEntityStampingFactory.class, remap = false)
public abstract class StampingFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull StamperRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<StamperRecipe>> cir) {
        CachedRecipe<StamperRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            TileEntityMoreMachineFactory<?> factory = (TileEntityMoreMachineFactory<?>) (Object) this;
            int multiplier = StackProcessorUtil.getFactoryMultiplier(factory.tier);
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> factory.getOperationsPerTick() * multiplier);
            }
        }
    }
}
