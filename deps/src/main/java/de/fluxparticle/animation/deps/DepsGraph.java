package de.fluxparticle.animation.deps;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.fluxparticle.animation.servlet.data.Module;
import de.fluxparticle.animation.servlet.graph.Graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static pl.touk.throwing.ThrowingFunction.unchecked;

/**
 * Created by sreinck on 09.05.18.
 */
public class DepsGraph implements Graph {

    private static final Pattern PATTERN = Pattern.compile("project\\(\":([^\"]*)\"\\)");

    private final Path root;

    @Inject
    public DepsGraph(@Named("root") Path root) {
        this.root = root;
    }

    @Override
    public Module[] getModules() {
        try {
            System.out.println("root = " + root);
            return Files.walk(root, 2)
                    .map(root::relativize)
                    .filter(path -> path.getNameCount() == 2 && path.endsWith("build.gradle"))
                    .map(unchecked(path -> {
                        String[] deps = Files.readAllLines(root.resolve(path)).stream()
                                .flatMap(line -> {
                                    Matcher m = PATTERN.matcher(line);
                                    if (m.find()) {
                                        return Stream.of(m.group(1));
                                    } else {
                                        return Stream.empty();
                                    }
                                })
                                .toArray(String[]::new);
                        return new Module(path.getName(0).toString(), deps);
                    }))
                    .toArray(Module[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
