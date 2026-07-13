package io.github.jason13official.stackable_stew_and_soup;

import java.util.function.Consumer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class StackableStewAndSoupClientNeoForge {

  public StackableStewAndSoupClientNeoForge(final IEventBus modEventBus) {

    modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> StackableStewAndSoupClient.init());
  }
}
