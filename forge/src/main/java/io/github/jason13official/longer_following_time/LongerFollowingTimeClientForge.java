package io.github.jason13official.longer_following_time;

import java.util.function.Consumer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class LongerFollowingTimeClientForge {

  public LongerFollowingTimeClientForge(final IEventBus modEventBus) {

    modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> LongerFollowingTimeClient.init());
  }
}
