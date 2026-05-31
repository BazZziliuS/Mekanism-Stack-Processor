package org.cloudea.mekanismstackprocessor.mixin;

import mekanism.api.recipes.ItemStackChemicalToItemStackRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tile.factory.TileEntityFactory;
import mekanism.common.tile.factory.TileEntityItemStackChemicalToItemStackFactory;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin для Chemical Factory (Injecting, Infusing, Purifying).
 */
@Mixin(value = TileEntityItemStackChemicalToItemStackFactory.class, remap = false)
public abstract class ItemStackChemicalToItemStackFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull ItemStackChemicalToItemStackRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<ItemStackChemicalToItemStackRecipe>> cir) {
        CachedRecipe<ItemStackChemicalToItemStackRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            TileEntityFactory<?> factory = (TileEntityFactory<?>) (Object) this;
            int multiplier = StackProcessorUtil.getChemicalFactoryMultiplier(factory.tier);
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> StackProcessorUtil.getOperationsPerTick(factory) * multiplier);
            }
        }
    }
}
