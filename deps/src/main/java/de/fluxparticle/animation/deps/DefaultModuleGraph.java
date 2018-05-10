package de.fluxparticle.animation.deps;

/**
 * Created by sreinck on 09.05.18.
 */
public class DefaultModuleGraph implements ModuleGraph {

    @Override
    public Module[] getModules() {
        return new Module[] {
                new Module("A", "B", "C"),
                new Module("B"),
                new Module("C")
        };
    }

}
