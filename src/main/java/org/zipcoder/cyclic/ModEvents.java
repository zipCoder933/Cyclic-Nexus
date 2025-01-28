//package org.zipcoder.cyclic;
//
//import net.minecraftforge.eventbus.api.IEventBus;
//
//public class ModEvents {
//    // In the main mod class ExampleMod
//
//    // This event is on the mod bus
//    private void modEventHandler(RegisterEvent event) {
//        // Do things here
//    }
//
//    // This event is on the forge bus
//    private static void forgeEventHandler(AttachCapabilitiesEvent<Entity> event) {
//        // ...
//    }
//
//// In the mod constructor
//    public ModEvents(IEventBus modEventBus, IEventBus forgeEventBus) {
//        modEventBus.addListener(this::modEventHandler);
//        forgeEventBus.addGenericListener(Entity.class, ExampleMod::forgeEventHandler);
//    }
//
//}
