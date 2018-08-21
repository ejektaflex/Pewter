package com.ejektaflex.pewter.modifiers.base

import c4.conarm.lib.armor.ArmorNBT
import slimeknights.tconstruct.library.tools.ToolNBT

interface IModTopaz : IModGem {

    override val gemMeta: Int
        get() = 3

    override val toolMod: ToolNBT.() -> Unit
        get() = {
            durability += 300
            speed += 0.4f
        }

    override val armorMod: ArmorNBT.() -> Unit
        get() = {
            defense += 2f
            durability += 10
        }

}