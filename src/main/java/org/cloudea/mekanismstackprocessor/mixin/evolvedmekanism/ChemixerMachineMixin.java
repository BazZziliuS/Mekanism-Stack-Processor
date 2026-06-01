package org.cloudea.mekanismstackprocessor.mixin.evolvedmekanism;

import fr.iglee42.evolvedmekanism.recipes.ChemixerRecipe;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityChemixer;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityChemixer.class, remap = false)
public abstract class ChemixerMachineMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull ChemixerRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<ChemixerRecipe>> cir) {
        CachedRecipe<ChemixerRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            int multiplier = StackProcessorUtil.getMachineMultiplier();
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> multiplier);
            }
        }
    }
}
