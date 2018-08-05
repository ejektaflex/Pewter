package com.ejektaflex.pewter.materials

import com.ejektaflex.pewter.dsl.MaterialDSL

class StarmetalMaterial : MaterialDSL("starmetal", "#222288", {
    forge { true }
    craft { false }
    ingots("astralsorcery:itemcraftingcomponent:1")
    defaultMetalParts()
    addTraits(
            "EXTRA" to "dense"
    )
    shininess(0.6f)
    tool {

        toolTrait("brilliance")
        head {
            durability { 275 }
            attack { 4.2f }
            speed { 5.7f }
            harvestLevel(1)
        }
        handle {
            durability { 95 }
            modifier { 0.8f }
        }
        extra {
            durability { 65 }
        }
        bow {
            speed(2.5f)
            accuracy { 1.0f }
            range { 1.6f }
            string { 0.2f }
            bonusDamage { 6.5f }
        }
        shaft {
            modifier { 1.2f }
            bonusAmmo { 5 }
        }
    }

    // higher attack lower speed
    armor {
        core {
            durability { 14f }
            defense { 10f }
        }
        plates {
            modifier { 0.8f }
            durability { 2.5f }
            toughness { 0f }
        }
        trim {
            extraDurability { 7.5f }
        }
    }


})