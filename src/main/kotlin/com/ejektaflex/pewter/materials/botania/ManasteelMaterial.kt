package com.ejektaflex.pewter.materials.botania

import com.ejektaflex.pewter.dsl.MaterialDSL

class ManasteelMaterial : MaterialDSL("manasteel", "#005EE0", {
    forge { true }
    craft { false }
    blocks("botania:storage")
    ingots("botania:manaresource")
    nuggets("botania:manaresource:17")
    defaultMetalParts()
    shininess(0.8f)
    addTraits(
            "HEAD" to "essentia"
    )
    tool {

        toolTrait("manainfused")

        head {
            durability { 256 }
            attack { 4.2f }
            speed { 6f }
            harvestLevel(1)
        }
        handle {
            durability { 100 }
            modifier { 0.85f }
        }
        extra {
            durability { 75 }
        }
        bow {
            speed(2.5f)
            range { 1.75f }
            string { 0.05f }
            bonusDamage { 6.5f }
        }
        shaft {
            modifier { 1.1f }
            bonusAmmo { 5 }
        }
    }

    armor {
        armorTrait("visbarrier_armor")
        core {
            durability { 12f }
            defense { 14f }
        }
        plates {
            modifier { 0.9f }
            durability { 6f }
            toughness { 0f }
        }
        trim {
            extraDurability { 3.5f }
        }
    }

})