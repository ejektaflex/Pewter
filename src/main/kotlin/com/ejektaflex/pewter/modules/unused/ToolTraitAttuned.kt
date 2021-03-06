package com.ejektaflex.pewter.modules.unused

import com.ejektaflex.pewter.shared.methods.AuraExchanger
import com.ejektaflex.pewter.api.core.traits.PewterToolTrait
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import kotlin.math.log10
import kotlin.math.min
import kotlin.math.pow


class ToolTraitAttuned : PewterToolTrait("attuned", 0xE18EFF), AuraExchanger {
    override fun onToolHeal(tool: ItemStack?, amount: Int, newAmount: Int, entity: EntityLivingBase): Int {
        val netVis = getVisAt(entity) - getFluxAt(entity)
        val visLog = log10(min(1f, netVis))
        val visMult = min(1f, visLog.pow(0.4f))

        // 20 vis gives visMult of 1.11
        // 60 vis gives visMult of 1.26
        // 100 vis gives visMult of 1.32
        // 200 vis gives visMult of 1.39

        return (super.onToolHeal(tool, amount, newAmount, entity) * visMult).toInt()

    }
}