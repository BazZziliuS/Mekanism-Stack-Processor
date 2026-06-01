package org.cloudea.mekanismstackprocessor.mixin.moremachine.mekaf;

import com.jerry.mekaf.common.tile.factory.TileEntityAdvancedFactoryBase;
import com.jerry.mekaf.common.tile.factory.TileEntityLiquifyingFactory;
import mekanism.api.recipes.basic.BasicItemStackToFluidOptionalItemRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityLiquifyingFactory.class, remap = false)
public abstract class LiquifyingFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull BasicItemStackToFluidOptionalItemRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<BasicItemStackToFluidOptionalItemRecipe>> cir) {
        CachedRecipe<BasicItemStackToFluidOptionalItemRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            TileEntityAdvancedFactoryBase<?> factory = (TileEntityAdvancedFactoryBase<?>) (Object) this;
            int multiplier = StackProcessorUtil.getChemicalFactoryMultiplier(factory.tier);
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> factory.getOperationsPerTick() * multiplier);
            }
        }
    }
}
