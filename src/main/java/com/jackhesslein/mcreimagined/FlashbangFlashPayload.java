package com.jackhesslein.mcreimagined;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record FlashbangFlashPayload(boolean witherSkull) implements CustomPacketPayload {
    public static final Type<FlashbangFlashPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MinecraftReimagined.MOD_ID, "flashbang_flash"));
    public static final StreamCodec<ByteBuf, FlashbangFlashPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, FlashbangFlashPayload::witherSkull, FlashbangFlashPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
