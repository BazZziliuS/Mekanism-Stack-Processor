package org.cloudea.mekanismstackprocessor.mixin.moremachine.mekaf;

import com.jerry.mekaf.common.tile.factory.TileEntityAdvancedFactoryBase;
import com.jerry.mekaf.common.tile.factory.TileEntityCrystallizingFactory;
import mekanism.api.recipes.ChemicalCrystallizerRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityCrystallizingFactory.class, remap = false)
public abstract class CrystallizingFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull ChemicalCrystallizerRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<ChemicalCrystallizerRecipe>> cir) {
        CachedRecipe<ChemicalCrystallizerRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            TileEntityAdvancedFactoryBase<?> factory = (TileEntityAdvancedFactoryBase<?>) (Object) this;
            int multiplier = StackProcessorUtil.getChemicalFactoryMultiplier(factory.tier);
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> factory.getOperationsPerTick() * multiplier);
            }
        }
    }
}
