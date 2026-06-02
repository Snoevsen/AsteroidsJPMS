package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        PluginServices services = loadPluginServices(Paths.get("plugins"));

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getBeanFactory().registerSingleton("gamePluginServices", services.plugins());
        ctx.getBeanFactory().registerSingleton("entityProcessingServiceList", services.processors());
        ctx.getBeanFactory().registerSingleton("postEntityProcessingServices", services.postProcessors());
        ctx.register(ModuleConfig.class);
        ctx.refresh();

        Game game = ctx.getBean(Game.class);
        game.start(window);
        game.render();
    }

    private record PluginServices(
            List<IGamePluginService> plugins,
            List<IEntityProcessingService> processors,
            List<IPostEntityProcessingService> postProcessors
    ) {
    }

    private static PluginServices loadPluginServices(Path pluginsDir) throws IOException {
        if (!Files.isDirectory(pluginsDir)) {
            return new PluginServices(List.of(), List.of(), List.of());
        }

        List<Path> jars;
        try (Stream<Path> paths = Files.list(pluginsDir)) {
            jars = paths
                    .filter(p -> p.toString().endsWith(".jar"))
                    .collect(Collectors.toList());
        }

        if (jars.isEmpty()) {
            return new PluginServices(List.of(), List.of(), List.of());
        }

        ModuleLayer layer = createLayer(jars);

        Set<IGamePluginService> plugins = new LinkedHashSet<>();
        Set<IEntityProcessingService> processors = new LinkedHashSet<>();
        Set<IPostEntityProcessingService> postProcessors = new LinkedHashSet<>();

        for (Module module : layer.modules()) {
            ClassLoader loader = layer.findLoader(module.getName());

            ServiceLoader.load(IGamePluginService.class, loader).forEach(plugins::add);
            ServiceLoader.load(IEntityProcessingService.class, loader).forEach(processors::add);
            ServiceLoader.load(IPostEntityProcessingService.class, loader).forEach(postProcessors::add);
        }

        return new PluginServices(
            List.copyOf(plugins),
            List.copyOf(processors),
            List.copyOf(postProcessors)
        );
    }

    private static ModuleLayer createLayer(List<Path> jarPaths) {
        ModuleFinder finder = ModuleFinder.of(jarPaths.toArray(Path[]::new));
        ModuleLayer parent = ModuleLayer.boot();

        Set<String> moduleNames = finder.findAll().stream()
                .map(ref -> ref.descriptor().name())
                .collect(Collectors.toSet());

        Configuration cf = parent.configuration().resolve(finder, ModuleFinder.of(), moduleNames);

        return parent.defineModulesWithManyLoaders(cf, ClassLoader.getSystemClassLoader());
    }
}