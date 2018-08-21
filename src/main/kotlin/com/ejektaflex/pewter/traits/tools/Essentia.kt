package com.ejektaflex.pewter.traits.tools

import com.ejektaflex.pewter.ext.plusAssign
import com.ejektaflex.pewter.ext.random
import com.ejektaflex.pewter.lib.mixins.AuraExchanger
import com.ejektaflex.pewter.lib.traits.tools.PewterTrait
import net.minecraft.block.Block
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraftforge.event.world.BlockEvent
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.aspects.AspectHelper

class Essentia : PewterTrait("Essentia", 0xD79291), AuraExchanger {

    override fun blockHarvestDrops(tool: ItemStack, event: BlockEvent.HarvestDropsEvent) {
        if (random.nextFloat() <= blockChance) {
            event.harvester.inventory += getBlockCrystal(event.state.block)
            addFluxFor(event, 1f)
        }
        super.blockHarvestDrops(tool, event)
    }

    override fun onHit(tool: ItemStack, player: EntityLivingBase, target: EntityLivingBase, damage: Float, isCritical: Boolean) {
        if (target.health < damage) {
            if (random.nextFloat() <= entityChance) {
                target.entityDropItem(getEntityCrystal(target), 1f)
                addFluxFor(target, 1f)
            }
        }
        super.onHit(tool, player, target, damage, isCritical)
    }

    companion object {
        const val blockChance = 0.035f
        const val entityChance = 0.10f

        private fun getBlockCrystal(b: Block): ItemStack {
            val possibleAspects = AspectHelper.getObjectAspects(ItemStack(b))
            val pickedAspect = possibleAspects.getAspects().toList().random
            return ThaumcraftApiHelper.makeCrystal(pickedAspect)
        }

        private fun getEntityCrystal(e: EntityLivingBase): ItemStack {
            val possibleAspects = AspectHelper.getEntityAspects(e)
            val pickedAspect = possibleAspects.getAspects().toList().random
            return ThaumcraftApiHelper.makeCrystal(pickedAspect)
        }

    }

}