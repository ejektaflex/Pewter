package com.ejektaflex.pewter.modules.thaumcraft.material

import com.ejektaflex.pewter.api.core.materials.MaterialDSL

class MaterialThaumium(name: String) : MaterialDSL(name, "#55467f", {
    forge { true }
    craft { false }
    allSuffixes("Thaumium")
    defaultMetalParts()
    addTraits(
            "HEAD" to "essentia"
    )
    tool {

        toolTrait("aural")

        head {
            durability { 300 }
            attack { 4f }
            speed { 6.5f }
            harvestLevel(1)
        }
        handle {
            durability { 65 }
            modifier { 0.9f }
        }
        extra {
            durability { 35 }
        }
        bow {
            speed(3.5f)
            range { 1.75f }
            string { 0.05f }
            bonusDamage { 2f }
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