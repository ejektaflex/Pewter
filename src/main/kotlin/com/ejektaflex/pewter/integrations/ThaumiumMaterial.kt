package com.ejektaflex.pewter.integrations

import com.ejektaflex.pewter.dsl.MaterialDSL

class ThaumiumMaterial : MaterialDSL("thaumium", "#6F43E8", {
    locale("en_US" to "Thaumium")
    forge { true }
    craft { false }
    ingots("thaumcraft:ingot:0")
    defaultMetalParts()
    defaultTrait("aural")
    traitList(
            "HEAD" to "essentia"
    )
    harvestLevel(1)
    head {
        durability { 300 }
        attack { 4 }
        speed { 6.5f }
    }
    handle {
        durability { 95 }
        modifier { 0.9f }
    }
    extra {
        durability { 35 }
    }
    bow {
        speed(2.5f)
        accuracy { 0.85f }
        range { 1.75f }
        string { 0.05f }
        bonusDamage { 6.5f }
    }
    shaft {
        modifier { 1.1f }
        bonusAmmo { 5 }
    }
})