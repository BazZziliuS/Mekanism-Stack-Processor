package org.cloudea.mekanismstackprocessor.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class EvolvedMekanismMixinPlugin implements IMixinConfigPlugin {

    private static final String EM_CLASS = "fr.iglee42.evolvedmekanism.tiles.factory.TileEntityAlloyingFactory";

    private static Boolean emPresent = null;

    @Override
    public void onLoad(String mixinPackage) {
        emPresent = isClassPresent(EM_CLASS);
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return emPresent != null && emPresent;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static boolean isClassPresent(String className) {
        String resourcePath = className.replace('.', '/') + ".class";
        return EvolvedMekanismMixinPlugin.class.getClassLoader().getResource(resourcePath) != null;
    }
}
