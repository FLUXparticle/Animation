package de.fluxparticle.animation.deps;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static pl.touk.throwing.ThrowingFunction.unchecked;

/**
 * Created by sreinck on 09.05.18.
 */
public class DepsGraph implements ModuleGraph {

    private static final Pattern INCLUDE = Pattern.compile("\"([^\"]*)\"");

    private static final Pattern PROJECT = Pattern.compile("project\\((path(:| =) )?\":([^\"]*)\"[^)]*\\)");

    private final Path root;

    @Inject
    public DepsGraph(@Named("root") Path root) {
        this.root = root;
    }

    @Override
    public Module[] getModules() {
        try {
            Optional<String> optIncludes = Files.lines(root.resolve("settings.gradle.kts"))
                    .filter(line -> line.startsWith("include"))
                    .findAny();

            return optIncludes.map(s -> {
                Matcher matcher = INCLUDE.matcher(s);
                List<String> moduleNames = new ArrayList<>();
                while (matcher.find()) {
                    String name = matcher.group(1);
                    moduleNames.add(name);
                }
                return moduleNames.stream().map(unchecked(name -> {
                    Path path = resolveScriptFile(root.resolve(name));
                    String[] deps = Files.readAllLines(root.resolve(path)).stream()
                            .flatMap(line -> {
                                Matcher m = PROJECT.matcher(line);
                                if (m.find()) {
                                    return Stream.of(m.group(3));
                                } else {
                                    return Stream.empty();
                                }
                            })
                            .toArray(String[]::new);
                    return new Module(name, deps);
                })).toArray(Module[]::new);
            }).orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path resolveScriptFile(Path path) {
        Path kotlin = path.resolve("build.gradle.kts");
        if (Files.exists(kotlin)) {
            return kotlin;
        } else {
            return path.resolve("build.gradle");
        }
    }

}
