package com.ejektaflex.pewter.integrations

import com.ejektaflex.pewter.dsl.MaterialDSL

class HellfireMaterial : MaterialDSL("hellfire", "#FF3D3D", {
    locale("en_US" to "Hellfire")
    forge { true }
    craft { false }
    ingots("betterwithmods:material:17")
    defaultTrait("heatlover")
    traitMap(
            "EXTRA" to "dense"
    )
    harvestLevel(1)
    head {
        durability { 335 }
        attack { 4 }
        speed { 5.5f }
    }
    handle {
        durability { -75 }
        modifier { 0.9f }
    }
    extra {
        durability { 95 }
    }
    bow {
        accuracy { 0.0f }
        range { 0.5f }
        string { 0.85f }
        bonusDamage { -0.5f }
    }
    shaft {
        modifier { 0.85f }
        bonusAmmo { 15 }
    }
})