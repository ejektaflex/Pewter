package com.ejektaflex.pewter.modules

import com.ejektaflex.pewter.api.core.materials.MaterialDSL
import com.ejektaflex.pewter.api.core.modifiers.IPewterArmorModifier
import com.ejektaflex.pewter.api.core.modifiers.IPewterToolModifier
import com.ejektaflex.pewter.api.core.EffectWrapper
import com.ejektaflex.pewter.api.core.PewterModule
import com.ejektaflex.pewter.api.core.traits.IPewterArmorTrait
import com.ejektaflex.pewter.api.core.traits.IPewterToolTrait
import com.ejektaflex.pewter.mods.botania.armor.*
import com.ejektaflex.pewter.mods.botania.material.*
import com.ejektaflex.pewter.mods.botania.tool.*

class ModuleBotania : PewterModule() {

    override val id = "botania"

    override val armorModifiers: MutableList<EffectWrapper<out IPewterArmorModifier>> = mutableListOf(
            EffectWrapper("dragonstone_armor") { ArmorModDragonstone(this) },
            EffectWrapper("runemana_armor") { ArmorModRuneMana(this) }
    )

    override val armorTraits: MutableList<EffectWrapper<out IPewterArmorTrait>> = mutableListOf(
            EffectWrapper("adapting") { ArmorTraitAdapting(this) },
            EffectWrapper("manainfused") { ArmorTraitManaInfused(this) },
            EffectWrapper("terra") { ArmorTraitTerra(this) }
    )

    override val materials: MutableList<MaterialDSL> = mutableListOf(
            MaterialElementium("elementium"),
            MaterialLivingrock("livingrock"),
            MaterialLivingwood("livingwood"),
            MaterialManasteel("manasteel"),
            MaterialManastring("manastring"),
            MaterialTerrasteel("terrasteel")
    )

    override val toolModifiers: MutableList<EffectWrapper<out IPewterToolModifier>> = mutableListOf(
            EffectWrapper("dragonstone") { ToolModDragonstone(this) },
            EffectWrapper("runeautumn") { ToolModRuneAutumn(this) },
            EffectWrapper("runemana") { ToolModRuneMana(this) }
    )

    override val toolTraits: MutableList<EffectWrapper<out IPewterToolTrait>> = mutableListOf(
            EffectWrapper("manaharvest") { ToolTraitManaHarvest("manaharvest") },
            EffectWrapper("manainfused") { ToolTraitManaInfused(this) }
    )

}

