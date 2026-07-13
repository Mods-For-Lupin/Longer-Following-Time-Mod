package io.github.jason13official.longer_following_time.impl.common;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import io.github.jason13official.longer_following_time.Constants;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModConfig {

  private static final String FILENAME = Constants.MOD_ID + ".toml";

  private static float ADDED_SECONDS_VALUE = 4.0f;

  public static ConfigGetterSetter<Float> ADDED_SECONDS =
      new ConfigGetterSetter<>("added_seconds", () -> ADDED_SECONDS_VALUE, i -> ADDED_SECONDS_VALUE = i);

  public static void load(Path configDir) {

    File configDirectory = new File(configDir.toUri());
    if (!configDirectory.isDirectory() && !configDirectory.mkdirs()) {
      Constants.LOG.info("Failed to get or create config directory {}", configDirectory.getAbsolutePath());
      return;
    }

    Config.setInsertionOrderPreserved(true);
    ModConfig.loadConfig(configDir, FILENAME);
  }

  private static void loadConfig(Path configDir, String filename) {

    Path configFilepath = configDir.resolve(filename);
    File configFile = new File(configFilepath.toUri());

    try (CommentedFileConfig config = CommentedFileConfig.builder(configFile).build()) {

      if (Files.exists(configFilepath)) {
        config.load();
      }

      // getters (getting from config) to load our runtime config values

      ADDED_SECONDS.setter().accept(config.getOrElse(ADDED_SECONDS.key(), ADDED_SECONDS.getter().get()));

      // setters (setting config values/comments for writing the file) to save our runtime config values

      config.setComment(ADDED_SECONDS.key(), " The amount that stew and soup may stack to. Default: 8");
      config.set(ADDED_SECONDS.key(), ADDED_SECONDS.getter().get());

      config.save();
    } catch (Exception e) {

      Constants.LOG.info("Failed to get or create config file {}", configFile.getAbsolutePath());
      e.printStackTrace();
    }
  }

  public record ConfigGetterSetter<T>(String key, Supplier<T> getter, Consumer<T> setter) {}
}
