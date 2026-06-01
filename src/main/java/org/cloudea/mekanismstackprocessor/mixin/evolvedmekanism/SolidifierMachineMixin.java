package org.cloudea.mekanismstackprocessor.mixin.evolvedmekanism;

import fr.iglee42.evolvedmekanism.recipes.SolidificationRecipe;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntitySolidifier;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntitySolidifier.class, remap = false)
public abstract class SolidifierMachineMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull SolidificationRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<SolidificationRecipe>> cir) {
        CachedRecipe<SolidificationRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            int multiplier = StackProcessorUtil.getMachineMultiplier();
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> multiplier);
            }
        }
    }
}
