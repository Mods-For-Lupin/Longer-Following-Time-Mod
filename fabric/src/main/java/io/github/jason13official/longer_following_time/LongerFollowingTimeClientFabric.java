package io.github.jason13official.longer_following_time;

import net.fabricmc.api.ClientModInitializer;

public class LongerFollowingTimeClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {

    LongerFollowingTimeClient.init();
  }
}
