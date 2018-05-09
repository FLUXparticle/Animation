package de.fluxparticle.animation.servlet.graph;

import de.fluxparticle.animation.servlet.data.Module;

/**
 * Created by sreinck on 09.05.18.
 */
public class DefaultGraph implements Graph {

    @Override
    public Module[] getModules() {
        return new Module[] {
                new Module("A", "B", "C"),
                new Module("B"),
                new Module("C")
        };
    }

}
