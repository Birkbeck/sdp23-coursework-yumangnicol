package sml.utils;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import sml.InstructionFactory;
import sml.InstructionInputRouter;

public class InstructionInputRouterModule extends AbstractModule {
    protected void configure(){
        bind(InstructionInputRouter.class);
        Registry.bindRegistrations(MapBinder.newMapBinder(binder(), String.class, InstructionFactory.class));
    }
}
