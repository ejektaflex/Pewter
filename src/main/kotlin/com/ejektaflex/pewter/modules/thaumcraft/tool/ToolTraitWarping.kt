package com.ejektaflex.pewter.modules.thaumcraft.tool

import com.ejektaflex.pewter.shared.methods.TinkerNBTHelper
import com.ejektaflex.pewter.api.core.traits.PewterToolTrait
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.capabilities.IPlayerWarp

class ToolTraitWarping(name: String) : PewterToolTrait(name, 0x4F1D3C), TinkerNBTHelper {
    override fun onToolDamage(tool: ItemStack?, damage: Int, newDamage: Int, entity: EntityLivingBase): Int {
        if (entity is EntityPlayer) {
            if (random.nextFloat() < calculateChance(tool!!)) {
                ThaumcraftApi.internalMethods.addWarpToPlayer(entity, 2, IPlayerWarp.EnumWarpType.TEMPORARY)
            }
        }
        return super.onToolDamage(tool, damage, newDamage, entity)
    }

    private fun calculateChance(tool: ItemStack): Float {
        return if (hasTag(tool, ToolModSanitizing.SANITIZING_TAG)) {
            baseChance / 2
        } else {
            baseChance
        }
    }

    companion object {
        const val baseChance = 0.05f
    }

}