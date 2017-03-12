package io.pasturesoloutions.greenbox.module;

import io.pasturesoloutions.greenbox.GreenBox;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModuleController {

    private final GreenBox greenBox;

    private List<Object> loadedModules = new ArrayList<>();

    public ModuleController(GreenBox greenBox) {
        this.greenBox = greenBox;
    }

    public Optional<Module> constructModule(Class<? extends Module> module) {
        try {
            Constructor<?> ctor = module.getConstructor(GreenBox.class);
            Module moduleInstance = (Module) ctor.newInstance(this.greenBox);
            loadedModules.add(moduleInstance);
            return Optional.of(moduleInstance);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static class ModuleControllerTask {

        private final ModuleController moduleController;

        public ModuleControllerTask(ModuleController moduleController) {
            this.moduleController = moduleController;
        }

        public void start() {

        }

        public void stop() {

        }

    }

}
