package de.fluxparticle.animation.servlet.data;

/**
 * Created by sreinck on 09.05.18.
 */
public class Module {

    private String name;

    private String[] deps;

    public Module(String name, String... deps) {
        this.name = name;
        this.deps = deps;
    }

}
