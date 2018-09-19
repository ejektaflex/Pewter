package com.ejektaflex.pewter.traits.tools

import com.ejektaflex.pewter.ext.ifHasCapability
import com.ejektaflex.pewter.api.core.traits.PewterToolTrait
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import thebetweenlands.common.registries.CapabilityRegistry

class Gritty : PewterToolTrait("Gritty", 0xC867D7) {
    override fun damage(tool: ItemStack?, player: EntityLivingBase?, target: EntityLivingBase?, damage: Float, newDamage: Float, isCritical: Boolean): Float {
        var toReturn = damage
        player!!.ifHasCapability(CapabilityRegistry.CAPABILITY_DECAY) {
            toReturn += (this.decayStats.decayLevel / 2)
        }
        return toReturn
    }
}