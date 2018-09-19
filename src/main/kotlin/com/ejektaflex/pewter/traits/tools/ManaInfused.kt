package com.ejektaflex.pewter.traits.tools

import com.ejektaflex.pewter.api.core.traits.PewterToolTrait
import com.ejektaflex.pewter.traits.base.IModManaInfused
import com.ejektaflex.pewter.modifiers.base.methods.IToolManaCost
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ManaInfused : PewterToolTrait("Mana Infused", 0x005EE0), IModManaInfused, IToolManaCost {

    override val manaCost = 200

    override fun onUpdate(tool: ItemStack, world: World, entity: Entity?, itemSlot: Int, isSelected: Boolean) {
        super<IModManaInfused>.onUpdate(tool, world, entity, itemSlot, isSelected)
        super<PewterToolTrait>.onUpdate(tool, world, entity, itemSlot, isSelected)
    }

    override fun onToolDamage(tool: ItemStack, damage: Int, newDamage: Int, entity: EntityLivingBase?): Int {
        val newNewDamage = super<IModManaInfused>.onToolDamage(tool, damage, newDamage, entity)
        return super<PewterToolTrait>.onToolDamage(tool, damage, newNewDamage, entity)
    }

}
