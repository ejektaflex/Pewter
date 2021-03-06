package com.ejektaflex.pewter.ext

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

val String.toMeta: Int
    get() {
        return when {
            this == "*" -> OreDictionary.WILDCARD_VALUE
            else -> {
                try {
                    Integer.parseInt(this)
                } catch (e: Exception) {
                    e.printStackTrace()
                    0
                }
            }
        }
    }

val String.toItemStack: ItemStack?
    get() {
        val sect = split(":").toMutableList()
        if (sect.size !in 2..3) {
            return null
        } else if (sect.size == 2) {
            sect += "0"
        }
        val item = Item.getByNameOrId("${sect[0]}:${sect[1]}")
        return if (item != null) {
            ItemStack(item, 1, sect[2].toMeta)
        } else {
            null
        }
    }

val String.toItem: Item?
    get() = toItemStack?.item

val String.isInOreDict: Boolean
    get() = startsWith("ore:")