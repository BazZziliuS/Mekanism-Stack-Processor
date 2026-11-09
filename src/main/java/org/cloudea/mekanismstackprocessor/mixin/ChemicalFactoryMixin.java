package org.cloudea.mekanismstackprocessor.mixin;

import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tile.factory.TileEntityFactory;
import mekanism.common.tile.factory.TileEntityItemStackChemicalToItemStackFactory;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin для Chemical Factory (Injecting, Infusing, Purifying).
 */
@Mixin(value = TileEntityItemStackChemicalToItemStackFactory.class, remap = false)
public abstract class ChemicalFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(CallbackInfoReturnable<CachedRecipe<?>> cir) {
        TileEntityFactory<?> factory = (TileEntityFactory<?>) (Object) this;
        StackProcessorUtil.applyBatch(cir.getReturnValue(),
                StackProcessorUtil.getChemicalFactoryMultiplier(factory.tier), factory::getOperationsPerTick);
    }
}
