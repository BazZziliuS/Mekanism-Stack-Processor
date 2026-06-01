package org.cloudea.mekanismstackprocessor.mixin.evolvedmekanism;

import fr.iglee42.evolvedmekanism.recipes.AlloyerRecipe;
import fr.iglee42.evolvedmekanism.tiles.factory.TileEntityAlloyingFactory;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tile.factory.TileEntityFactory;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityAlloyingFactory.class, remap = false)
public abstract class AlloyingFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull AlloyerRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<AlloyerRecipe>> cir) {
        CachedRecipe<AlloyerRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            TileEntityFactory<?> factory = (TileEntityFactory<?>) (Object) this;
            int multiplier = StackProcessorUtil.getFactoryMultiplier(factory.tier);
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> StackProcessorUtil.getOperationsPerTick(factory) * multiplier);
            }
        }
    }
}
