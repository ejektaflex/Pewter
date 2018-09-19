package com.ejektaflex.pewter.api.materials

import com.ejektaflex.pewter.Pewter
import com.ejektaflex.pewter.api.materials.stats.IDependency
import com.ejektaflex.pewter.api.materials.stats.MaterialData

interface IMaterialDependency : IDependency {
    val material: MaterialData

    val isBlackListed: Boolean
        get() = material.name in Pewter.blacklistedMaterials

}