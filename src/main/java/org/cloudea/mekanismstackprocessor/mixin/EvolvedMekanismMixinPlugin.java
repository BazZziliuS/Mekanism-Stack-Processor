package org.cloudea.mekanismstackprocessor.mixin;

public class EvolvedMekanismMixinPlugin extends ConditionalMixinPlugin {

    private static final String EM_CLASS = "fr.iglee42.evolvedmekanism.tiles.factory.TileEntityAlloyingFactory";

    private final boolean emPresent = isClassPresent(EM_CLASS);

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return emPresent;
    }
}
