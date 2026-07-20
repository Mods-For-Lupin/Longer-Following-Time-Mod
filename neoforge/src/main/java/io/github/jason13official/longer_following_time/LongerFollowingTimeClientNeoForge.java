package io.github.jason13official.longer_following_time;

import java.util.function.Consumer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class LongerFollowingTimeClientNeoForge {

  public LongerFollowingTimeClientNeoForge(final IEventBus modEventBus) {

    modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> LongerFollowingTimeClient.init());
  }
}
