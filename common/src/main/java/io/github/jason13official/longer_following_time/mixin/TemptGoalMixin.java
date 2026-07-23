package io.github.jason13official.longer_following_time.mixin;

import io.github.jason13official.longer_following_time.Constants;
import io.github.jason13official.longer_following_time.impl.common.ModConfig;
import java.util.function.Predicate;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TemptGoal.class)
public class TemptGoalMixin {

  static {

    System.out.println("[" + Constants.MOD_NAME + "] TemptGoalMixin.class static init (<clinit>) executing / class is loading.");
  }

  @Shadow @Final private Ingredient items;

  @Unique
  int longer_following_time$addedTicks = 0;

  @Inject(at = @At("TAIL"), method = "shouldFollow", cancellable = true)
  private void longer_following_time$shouldFollow(LivingEntity player, CallbackInfoReturnable<Boolean> cir) {

    boolean correctlyHeld = items.test(player.getMainHandItem()) || items.test(player.getOffhandItem());

    if (correctlyHeld) {
      float addedSeconds = ModConfig.ADDED_SECONDS.getter().get();
      longer_following_time$addedTicks = (int) (addedSeconds * 20.0f);
    }

    if (longer_following_time$addedTicks > 0) {
      --longer_following_time$addedTicks;
    }

    cir.setReturnValue(correctlyHeld || longer_following_time$addedTicks > 0);
  }

  static {

    System.out.println("[" + Constants.MOD_NAME + "] TemptGoalMixin.class static init (<clinit>) executed / class is loaded.");
  }
}
