package org.cloudea.mekanismstackprocessor.mixin;

/**
 * Mixin plugin that conditionally loads MoreMachine mixins
 * only when the corresponding mod classes are present.
 */
public class MoreMachineMixinPlugin extends ConditionalMixinPlugin {

    private static final String MEKMM_CLASS = "com.jerry.mekmm.common.tile.factory.TileEntityMoreMachineFactory";
    private static final String MEKAF_CLASS = "com.jerry.mekaf.common.tile.factory.base.TileEntityAdvancedFactoryBase";

    private final boolean mekmmPresent = isClassPresent(MEKMM_CLASS);
    private final boolean mekafPresent = isClassPresent(MEKAF_CLASS);

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // mekaf mixins require mekaf classes, the rest require mekmm classes
        return mixinClassName.contains(".mekaf.") ? mekafPresent : mekmmPresent;
    }
}
