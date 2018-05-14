package com.ejektaflex.pewter.traits

import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import slimeknights.tconstruct.shared.client.ParticleEffect
import slimeknights.tconstruct.tools.TinkerTools
import kotlin.math.min

class Sapping : PewterTrait("Sapping", 0xFFFFFF) {
    override val motto = "Alchemical Trade!"
    override val description = "Damaging enemies usually steals a small portion of their health."

    override fun onHit(tool: ItemStack, player: EntityLivingBase, target: EntityLivingBase, damage: Float, isCritical: Boolean) {

        val healthToSteal = min(target.health, damage) / 8.5f

        if (random.nextFloat() < chance) {
            TinkerTools.proxy.spawnEffectParticle(ParticleEffect.Type.HEART_BLOOD, target, 1)
            player.health += healthToSteal
        }

        super.onHit(tool, player, target, damage, isCritical)
    }

    companion object {
        const val chance = 0.65f
    }

}