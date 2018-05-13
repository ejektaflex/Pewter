package com.ejektaflex.pewter.traits

import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.MobEffects
import net.minecraft.potion.PotionEffect
import net.minecraft.util.EntityDamageSource
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


class Brilliance : PewterTrait("Brilliance", 0xFFFFFF) {
    override val description = "Enemies that hit you glow and receive night vision."

    @SubscribeEvent(priority = EventPriority.LOWEST)
    fun defend(event: LivingHurtEvent) {
        if (event.source is EntityDamageSource){
            event.source.trueSource?.let {
                if (it is EntityLivingBase) {
                    it.addPotionEffect(PotionEffect(MobEffects.GLOWING, 100, 2))
                    it.addPotionEffect(PotionEffect(MobEffects.NIGHT_VISION, 100, 5))
                }
            }
        }
    }

}