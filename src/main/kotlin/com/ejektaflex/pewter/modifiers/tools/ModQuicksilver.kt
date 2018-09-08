package com.ejektaflex.pewter.modifiers.tools


import com.ejektaflex.pewter.ext.getModifierIntNBT
import com.ejektaflex.pewter.ext.toItemStack
import com.ejektaflex.pewter.lib.mixins.TinkerNBTHelper
import com.ejektaflex.pewter.lib.modifiers.PewterToolModifier
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import slimeknights.tconstruct.library.modifiers.IModifierDisplay
import thaumcraft.api.entities.ITaintedMob

class ModQuicksilver(modName: String) : PewterToolModifier(modName, 0xcccccc, 3, 10), TinkerNBTHelper, IModifierDisplay {

    private fun damageMult(item: ItemStack): Float {
        return 1 + (item.getModifierIntNBT<ModQuicksilver>().current.toFloat() / 60) // 30/60 = 50% bonus
    }

    override fun damage(tool: ItemStack?, player: EntityLivingBase?, target: EntityLivingBase?, damage: Float, newDamage: Float, isCritical: Boolean): Float {
        var returnDamage = newDamage
        target?.let {
            if (it is ITaintedMob) {
                returnDamage *= damageMult(tool!!)
            }
        }
        return returnDamage
    }

    override fun getExtraInfo(tool: ItemStack?, modifierTag: NBTTagCompound?): MutableList<String> {
        return mutableListOf("Taint Damage +${"%.1f".format((damageMult(tool!!) - 1) * 100)}%")
    }


    override fun configure() {
        safeAdd("thaumcraft:quicksilver".toItemStack)
    }
}
