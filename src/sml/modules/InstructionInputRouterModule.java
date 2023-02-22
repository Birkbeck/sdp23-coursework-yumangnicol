package sml.modules;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import sml.InstructionFactory;
import sml.InstructionInputRouter;
import sml.utils.Registry;

/**
 * A Guice module that represents the bindings for InstructionInputRouter
 * Each binding is a dependency being injected
 *
 * @author Nicol Luis Yumang
 */
public class InstructionInputRouterModule extends AbstractModule {
    protected void configure(){
        bind(InstructionInputRouter.class);
        Registry.bindRegistrations(MapBinder.newMapBinder(binder(), String.class, InstructionFactory.class));
    }
}
