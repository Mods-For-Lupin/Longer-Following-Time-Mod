package io.github.jason13official.longer_following_time.platform;

import io.github.jason13official.longer_following_time.Constants;
import io.github.jason13official.longer_following_time.platform.services.IPlatformHelper;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

// Service loaders are a built-in Java feature that allow us to locate implementations of an interface that vary from one
// environment to another. In the context of MultiLoader we use this feature to access a mock API in the common code that
// is swapped out for the platform specific implementation at runtime.
public class Services {

  // In this example we provide a platform helper which provides information about what platform the mod is running on.
  // For example this can be used to check if the code is running on Forge vs Fabric, or to ask the modloader if another
  // mod is loaded.
  public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

  /// abstracted example
  private static <T> T load(Class<T> clazz) {

    Constants.LOG.info("Loading service {}", clazz);

    // A merged multi-loader jar carries every platform's provider entry in
    // META-INF/services, so ServiceLoader can hand back a provider whose
    // backing classes (FabricLoader, ModList, ...) aren't on this platform's
    // classpath. Touch a real platform call per candidate - the wrong one
    // throws NoClassDefFoundError here (construction alone doesn't touch its
    // platform API), and we skip to the next entry instead of crashing.
    for (T helper : ServiceLoader.load(clazz)) {
      try {
        boolean dev = helper instanceof IPlatformHelper platform && platform.isDevelopmentEnvironment();
        if (dev) {
          Constants.LOG.info("Loaded {} for service {}", helper, clazz);
        }
        return helper;
      } catch (NoClassDefFoundError | ServiceConfigurationError e) {
        Constants.LOG.debug("Skipping incompatible platform helper {}", helper.getClass().getName());
      }
    }

    throw new IllegalStateException("Failed to load service for " + clazz.getName());
  }

//  // This code is used to load a service for the current environment. Your implementation of the service must be defined
//  // manually by including a text file in META-INF/services named with the fully qualified class name of the service.
//  // Inside the file you should write the fully qualified class name of the implementation to load for the platform. For
//  // example our file on Forge points to ForgePlatformHelper while Fabric points to FabricPlatformHelper.
//  public static <T> T load(Class<T> clazz) {
//
//    final T loadedService = ServiceLoader.load(clazz)
//        .findFirst()
//        .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
//    Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
//    return loadedService;
//  }
}