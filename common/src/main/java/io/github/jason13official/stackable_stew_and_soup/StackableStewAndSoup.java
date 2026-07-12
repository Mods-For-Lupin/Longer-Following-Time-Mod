package io.github.jason13official.stackable_stew_and_soup;

import net.minecraft.resources.ResourceLocation;

public class StackableStewAndSoup {

  public static void init() {
  }

  public static ResourceLocation identifier(final String path) {
    return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, path);
  }
}