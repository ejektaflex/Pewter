package com.ejektaflex.pewter.modules.specific

import com.ejektaflex.pewter.api.core.materials.MaterialDSL
import com.ejektaflex.pewter.api.core.modifiers.IPewterArmorModifier
import com.ejektaflex.pewter.api.core.modifiers.IPewterToolModifier
import com.ejektaflex.pewter.api.core.modifiers.ModifierFunc
import com.ejektaflex.pewter.api.core.module.PewterModule
import com.ejektaflex.pewter.api.core.traits.IPewterToolTrait
import com.ejektaflex.pewter.mods.astralsorcery.MaterialStarmetal
import com.ejektaflex.pewter.mods.astralsorcery.ArmorModAquaStarseeker
import com.ejektaflex.pewter.mods.astralsorcery.ToolModAquaCelestial
import com.ejektaflex.pewter.mods.astralsorcery.ToolTraitBrilliance

class ModuleAstralSorcery : PewterModule() {

    override val armorModifiers: MutableList<ModifierFunc<out IPewterArmorModifier>> = mutableListOf(
            ModifierFunc("starseeker_armor") { ArmorModAquaStarseeker(this) }
    )

    override val materials: MutableList<MaterialDSL> = mutableListOf(
            MaterialStarmetal()
    )

    override val toolModifiers: MutableList<ModifierFunc<out IPewterToolModifier>> = mutableListOf(
            ModifierFunc("celestial") { ToolModAquaCelestial(this) }
    )

    override val toolTraits: MutableList<IPewterToolTrait> = mutableListOf(
            ToolTraitBrilliance()
    )

}
