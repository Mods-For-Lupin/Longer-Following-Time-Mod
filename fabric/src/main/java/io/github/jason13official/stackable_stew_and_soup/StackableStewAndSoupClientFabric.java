package io.github.jason13official.stackable_stew_and_soup;

import net.fabricmc.api.ClientModInitializer;

public class StackableStewAndSoupClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {

    StackableStewAndSoupClient.init();
  }
}
