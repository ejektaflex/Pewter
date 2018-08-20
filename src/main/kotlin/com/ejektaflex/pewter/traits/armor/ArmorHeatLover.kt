package com.ejektaflex.pewter.traits.armor

import c4.conarm.common.armor.utils.ArmorHelper
import com.ejektaflex.pewter.Pewter
import com.ejektaflex.pewter.ext.temperature
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.DamageSource
import net.minecraftforge.event.entity.living.LivingDamageEvent
import slimeknights.tconstruct.library.traits.AbstractTrait
import slimeknights.tconstruct.library.utils.ToolHelper
import kotlin.math.abs
import kotlin.math.ceil

class ArmorHeatLover : PewterArmorTrait("Heat Lover", 0xFF2334) {

    override fun onHit(tool: ItemStack, player: EntityLivingBase, target: EntityLivingBase, damage: Float, isCritical: Boolean) {


        super.onHit(tool, player, target, damage, isCritical)
    }

    override fun onDamaged(armor: ItemStack?, player: EntityPlayer?, source: DamageSource, damage: Float, newDamage: Float, evt: LivingDamageEvent?): Float {

        var healAmount = 0

        if (source.trueSource is EntityLivingBase) {
            val target = (source.trueSource as EntityLivingBase)
            if (target.isBurning) {
                val prob = AbstractTrait.random.nextFloat()
                healAmount += when {
                    prob >= 0.9f -> 2
                    prob >= 0.8f -> 1
                    else -> 0
                }

                if (target.isInLava) {
                    healAmount++
                }
            }
        }

        ArmorHelper.repairArmor(armor, healAmount, player)

        return super.onDamaged(armor, player, source, damage, newDamage, evt)
    }


}