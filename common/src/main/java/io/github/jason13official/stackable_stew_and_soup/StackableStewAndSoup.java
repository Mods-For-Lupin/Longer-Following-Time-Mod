package io.github.jason13official.stackable_stew_and_soup;

import net.minecraft.resources.Identifier;

public class StackableStewAndSoup {

  public static void init() {
  }

  public static Identifier identifier(final String path) {
    return Identifier.fromNamespaceAndPath(Constants.MOD_ID, path);
  }
}