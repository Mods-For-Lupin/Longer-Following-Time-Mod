package io.github.jason13official.stackable_stew_and_soup.mixin;

import io.github.jason13official.stackable_stew_and_soup.impl.common.ModConfig;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Items.class)
public class ItemsMixin {

  // redirects are targeting class initialization, also known as the static initializer

  @Shadow @Final public static Item BOWL;

  @Inject(at = @At("HEAD"), method = "registerItem(Ljava/lang/String;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;")
  private static void register(String name, Properties properties, CallbackInfoReturnable<Item> cir) {

    if (name.contains("stew") || name.contains("soup")) {
      properties.stacksTo(ModConfig.STACKABLE_AMOUNT.getter().get());
    }
  }

//  @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = {"stringValue=rabbit_stew"}, ordinal = 0)), at = @At(value = "NEW", target = "Lnet/minecraft/world/item/Item$Properties;"))
//  private static Properties ssas$RedirectRabbitStew() {
//    return new Item.Properties().stacksTo(ModConfig.STACKABLE_AMOUNT.getter().get()).food(Foods.RABBIT_STEW).usingConvertsTo(BOWL);
//  }
//
//  @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args= {"stringValue=mushroom_stew"}, ordinal = 0)), at = @At(value = "NEW", target = "Lnet/minecraft/world/item/Item$Properties;"))
//  private static Properties ssas$RedirectMushroomStew() {
//    return new Item.Properties().stacksTo(ModConfig.STACKABLE_AMOUNT.getter().get()).food(Foods.MUSHROOM_STEW).usingConvertsTo(BOWL);
//  }
//
//  @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args= {"stringValue=beetroot_soup"}, ordinal = 0)), at = @At(value = "NEW", target = "Lnet/minecraft/world/item/Item$Properties;"))
//  private static Properties ssas$RedirectBeetrootSoup() {
//    return new Item.Properties().stacksTo(ModConfig.STACKABLE_AMOUNT.getter().get()).food(Foods.BEETROOT_SOUP).usingConvertsTo(BOWL);
//  }
//
//  @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args= {"stringValue=suspicious_stew"}, ordinal = 0)), at = @At(value = "NEW", target = "Lnet/minecraft/world/item/Item$Properties;"))
//  private static Properties ssas$RedirectSuspiciousStew() {
//    return new Item.Properties().stacksTo(ModConfig.STACKABLE_AMOUNT.getter().get()).food(Foods.SUSPICIOUS_STEW).component(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffects.EMPTY).usingConvertsTo(BOWL);
//  }
}
