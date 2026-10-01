package com.jackhesslein.mcreimagined.client;

import com.jackhesslein.mcreimagined.MinecraftReimagined;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = MinecraftReimagined.MOD_ID, value = Dist.CLIENT)
public final class FlashbangOverlay {
    private static final int DURATION_TICKS = 80;
    private static final int FADE_TICKS = 20;
    private static int remainingTicks;
    private static int fadeTicks = FADE_TICKS;

    private FlashbangOverlay() {
    }

    public static void flash(boolean witherSkull) {
        remainingTicks = witherSkull ? DURATION_TICKS / 2 : DURATION_TICKS;
        fadeTicks = witherSkull ? FADE_TICKS / 2 : FADE_TICKS;
        Minecraft.getInstance().getSoundManager().play(
                SimpleSoundInstance.forUI(witherSkull ? MinecraftReimagined.WITHER_FLASH_RINGING.get()
                        : MinecraftReimagined.FLASHBANG_RINGING.get(), 1.0F, 1.6F));
    }

    @SubscribeEvent
    public static void tick(ClientTickEvent.Post event) {
        if (Minecraft.getInstance().level == null) {
            remainingTicks = 0;
        } else if (remainingTicks > 0 && !Minecraft.getInstance().isPaused()) {
            remainingTicks--;
        }
    }

    @SubscribeEvent
    public static void render(RenderGuiEvent.Post event) {
        if (remainingTicks <= 0) {
            return;
        }

        int alpha = remainingTicks > fadeTicks ? 255 : Math.min(255, remainingTicks * 255 / fadeTicks);
        event.getGuiGraphics().fill(0, 0, event.getGuiGraphics().guiWidth(), event.getGuiGraphics().guiHeight(),
                (alpha << 24) | 0xFFFFFF);
    }
}
