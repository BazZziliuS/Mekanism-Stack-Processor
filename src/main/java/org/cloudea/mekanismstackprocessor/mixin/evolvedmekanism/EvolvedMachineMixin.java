package org.cloudea.mekanismstackprocessor.mixin.evolvedmekanism;

import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityAlloyer;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityChemixer;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityFluidAlloyer;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityFluidCombiner;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityMelter;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntitySolidifier;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {
        TileEntityAlloyer.class,
        TileEntityChemixer.class,
        TileEntityFluidAlloyer.class,
        TileEntityFluidCombiner.class,
        TileEntityMelter.class,
        TileEntitySolidifier.class
}, remap = false)
public abstract class EvolvedMachineMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(CallbackInfoReturnable<CachedRecipe<?>> cir) {
        StackProcessorUtil.applyMachineBatch(cir.getReturnValue());
    }
}
