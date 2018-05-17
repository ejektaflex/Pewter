package com.ejektaflex.pewter.materials

import com.ejektaflex.pewter.dsl.MaterialDSL

class StarmetalMaterial : MaterialDSL("starmetal", "#222288", {
    locale("en_US" to "Starmetal")
    forge { true }
    craft { false }
    ingots("astralsorcery:itemcraftingcomponent:1")
    defaultMetalParts()
    defaultTrait("brilliance")
    traitList(
            "EXTRA" to "dense"
    )
    harvestLevel(1)
    shininess(0.6f)
    head {
        durability { 275 }
        attack { 4 }
        speed { 5.7f }
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
})