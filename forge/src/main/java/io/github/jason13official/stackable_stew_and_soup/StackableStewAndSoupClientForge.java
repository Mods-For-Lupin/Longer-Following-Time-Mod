package io.github.jason13official.stackable_stew_and_soup;

import java.util.function.Consumer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class StackableStewAndSoupClientForge {

  public StackableStewAndSoupClientForge(final IEventBus modEventBus) {

    modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> StackableStewAndSoupClient.init());
  }
}
