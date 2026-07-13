package io.github.jason13official.longer_following_time;

import io.github.jason13official.longer_following_time.impl.common.ModConfig;
import io.github.jason13official.longer_following_time.platform.Services;
import net.minecraft.resources.Identifier;

public class LongerFollowingTime {

  public static void init() {

    ModConfig.load(Services.PLATFORM.getConfigDirectory());
  }

  public static Identifier identifier(final String path) {
    return Identifier.fromNamespaceAndPath(Constants.MOD_ID, path);
  }
}