package com.jackhesslein.mcreimagined;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Draws the scuba helmet's 3D item model on the wearer's head, the same way vanilla draws a carved pumpkin.
 * Vanilla skips head-slot armor in its head layer, and the armor material has no texture layers, so this is
 * the only thing that renders the helmet.
 */
@EventBusSubscriber(modid = MinecraftReimagined.MOD_ID, value = Dist.CLIENT)
public final class ScubaDiverHelmetLayer<T extends LivingEntity, M extends EntityModel<T> & HeadedModel>
        extends RenderLayer<T, M> {
    private final ItemInHandRenderer itemInHandRenderer;

    public ScubaDiverHelmetLayer(RenderLayerParent<T, M> renderer, ItemInHandRenderer itemInHandRenderer) {
        super(renderer);
        this.itemInHandRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity,
            float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (!stack.is(MinecraftReimagined.SCUBA_DIVER_HELMET.get())) {
            return;
        }

        poseStack.pushPose();
        if (entity.isBaby()) {
            // Matches CustomHeadLayer's baby (and small armor stand) scaling.
            poseStack.translate(0.0F, 0.03125F, 0.0F);
            poseStack.scale(0.7F, 0.7F, 0.7F);
            poseStack.translate(0.0F, 1.0F, 0.0F);
        }
        getParentModel().getHead().translateAndRotate(poseStack);
        CustomHeadLayer.translateToHead(poseStack, false);
        itemInHandRenderer.renderItem(entity, stack, ItemDisplayContext.HEAD, false, poseStack, buffer, packedLight);
        poseStack.popPose();
    }

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        ItemInHandRenderer itemInHandRenderer = event.getContext().getItemInHandRenderer();
        for (PlayerSkin.Model skin : event.getSkins()) {
            PlayerRenderer renderer = event.getSkin(skin);
            if (renderer != null) {
                renderer.addLayer(new ScubaDiverHelmetLayer<>(renderer, itemInHandRenderer));
            }
        }
        ArmorStandRenderer armorStandRenderer = event.getRenderer(EntityType.ARMOR_STAND);
        if (armorStandRenderer != null) {
            armorStandRenderer.addLayer(new ScubaDiverHelmetLayer<>(armorStandRenderer, itemInHandRenderer));
        }
    }
}
