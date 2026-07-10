package io.github.jason13official.stackable_stew_and_soup;

import io.github.jason13official.stackable_stew_and_soup.impl.common.ModConfig;
import io.github.jason13official.stackable_stew_and_soup.platform.Services;
import net.minecraft.resources.ResourceLocation;


public class StackableStewAndSoup {

  public static void init() {

    ModConfig.load(Services.PLATFORM.getConfigDirectory());
  }

  public static ResourceLocation identifier(final String path) {
    return new ResourceLocation(Constants.MOD_ID, path);
  }
}