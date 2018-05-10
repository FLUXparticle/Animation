package de.fluxparticle.animation.deps;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static pl.touk.throwing.ThrowingFunction.unchecked;

/**
 * Created by sreinck on 09.05.18.
 */
public class DepsGraph implements ModuleGraph {

    private static final Pattern INCLUDE = Pattern.compile("'");

    private static final Pattern PROJECT = Pattern.compile("project\\(\":([^\"]*)\"\\)");

    private final Path root;

    @Inject
    public DepsGraph(@Named("root") Path root) {
        this.root = root;
    }

    @Override
    public Module[] getModules() {
        try {
            Optional<String> optIncludes = Files.lines(root.resolve("settings.gradle"))
                    .filter(line -> line.startsWith("include"))
                    .findAny();

            return optIncludes.map(s ->
                    INCLUDE.splitAsStream(s)
                            .filter(part -> !part.contains(" "))
                            .map(part -> Paths.get(part, "build.gradle"))
                            .map(unchecked(path -> {
                                String[] deps = Files.readAllLines(root.resolve(path)).stream()
                                        .flatMap(line -> {
                                            Matcher m = PROJECT.matcher(line);
                                            if (m.find()) {
                                                return Stream.of(m.group(1));
                                            } else {
                                                return Stream.empty();
                                            }
                                        })
                                        .toArray(String[]::new);
                                return new Module(path.getName(0).toString(), deps);
                            }))
                            .toArray(Module[]::new)
            ).orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
