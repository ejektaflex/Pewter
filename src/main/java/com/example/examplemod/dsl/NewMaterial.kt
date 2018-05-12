package com.example.examplemod.dsl

import com.example.examplemod.Pewter
import com.example.examplemod.ext.resource
import com.example.examplemod.ext.toItemStack
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.oredict.OreDictionary
import slimeknights.tconstruct.library.MaterialIntegration
import slimeknights.tconstruct.library.TinkerRegistry
import slimeknights.tconstruct.library.fluid.FluidMolten
import slimeknights.tconstruct.library.materials.Material
import slimeknights.tconstruct.smeltery.block.BlockMolten

class NewMaterial(initName: String, initColor: Int, initFunc: NewMaterial.() -> Unit) : DSL<NewMaterial>() {

    lateinit var material: Material
    var matFluid: Fluid? = null
    private lateinit var matBlock: Block
    private lateinit var fluidItem: ItemBlock
    private var matIngot: ItemStack? = null
    private lateinit var integration: MaterialIntegration
    var nameLocales = mutableMapOf<String, String>()
    private var smeltingMap = mutableMapOf<String, MutableList<Item>>(
            "ingot" to mutableListOf(),
            "nuggets" to mutableListOf()
    )
    val tool = ToolStats()

    init {
        tool.color = initColor
        tool.name = initName
        apply(initFunc)
        addToOreDict()
        createMaterial()
        makeFluid()
        integrateMaterial()
        addMaterialStats()
    }


    // Register all associated items in the Ore Dictionary
    private fun addToOreDict() {
        for (type in smeltingMap.keys ) {
            smeltingMap[type]!!.forEach { item ->
                OreDictionary.registerOre(type + tool.name.capitalize(), item)
            }
        }
    }

    private fun createMaterial() {
        material = TinkerRegistry.getMaterial(tool.name)
        if (material != Material.UNKNOWN) {
            println("Material already registered.")
        } else {
            material = Material(tool.name, tool.color)
        }
    }

    private fun makeFluid() {
        var name = tool.name.toLowerCase()
        matFluid = FluidMolten(name, tool.color).apply {
            unlocalizedName = "${Pewter.MODID}:$name"
        }
        FluidRegistry.registerFluid(matFluid)

        // TODO Set fluid properties here

        name = "molten_$name"

        // Create block
        matBlock = BlockMolten(matFluid).apply {
            unlocalizedName = "${Pewter.MODID}.$name"
            registryName = name.resource
            setCreativeTab(null)
        }
        ForgeRegistries.BLOCKS.register(matBlock)

        // Create item
        fluidItem = ItemBlock(matBlock).apply {
            registryName = name.resource
            creativeTab = null
        }
        ForgeRegistries.ITEMS.register(fluidItem)

        // Bucket
        FluidRegistry.addBucketForFluid(matFluid)

    }

    private fun integrateMaterial() {
        material.isCraftable = false
        material.isCastable = true

        val prefix = "ingot"
        val suffix = tool.name.capitalize()

        // Add ingot for item
        matIngot.let {
            material.addItem(prefix + suffix, 1, Material.VALUE_Ingot)
            println("THIS ITEM IS: $it")
            material.setRepresentativeItem(it)
            println("REPRESENTATIVE ITEM IS:\n\n\n${material.representativeItem}")
        }

        // Integrate
        integration = MaterialIntegration(prefix + suffix, material, matFluid, suffix).apply {
            if (tool.madeInToolForge) { this.toolforge() }
            preInit()
        }
    }

    private fun addMaterialStats() {
        ToolStats.MatPart.values().forEach {
            println("Adding Stats For: $it")
            tool.registerStats(material, it)
        }
    }

    fun integrate() {
        integration.integrate()
    }

    @DslMarker
    annotation class TopLevelToolDSL

    @DslMarker
    annotation class NestedDSL

    @TopLevelToolDSL
    fun locale(vararg pairs: Pair<String, String>) {
        nameLocales = pairs.toMap().toMutableMap()
    }

    fun ingots(vararg ing: String) {
        // Add all ingots to map
        smeltingMap["ingot"]!!.addAll(ing.mapNotNull { it.toItemStack }.mapNotNull { it.item })
        // Set icon to first ingot if no icon exists yet
        if (matIngot == null && smeltingMap["ingot"]!!.isNotEmpty()) {
            matIngot = ItemStack(smeltingMap["ingot"]!!.first())
        }
    }

    @TopLevelToolDSL
    fun forge(func: () -> Boolean) {
        tool.madeInToolForge = func()
    }

    @TopLevelToolDSL
    fun craft(func: () -> Boolean) {
        tool.craftable = func()
    }


    @TopLevelToolDSL
    fun icon(s: String) {
        matIngot = s.toItemStack!!
    }

    @TopLevelToolDSL
    fun head(func: HeadCreator.() -> Unit) {
        HeadCreator().apply(func)
    }

    inner class HeadCreator : DSL<HeadCreator>() {


        @NestedDSL
        fun durability(func: () -> Int) {
            tool.durability = func()
        }

        @NestedDSL
        fun attack(func: () -> Int) {
            tool.attack = func()
        }

        @NestedDSL
        fun speed(func: () -> Float) {
            tool.matSpeed = func()
        }
    }

    @TopLevelToolDSL
    fun handle(func: HandleCreator.() -> Unit) {
        HandleCreator().apply(func)
    }

    inner class HandleCreator : DSL<HandleCreator>() {
        @NestedDSL
        fun durability(func: () -> Int) {
            tool.handleDurability = func()
        }

        @NestedDSL
        fun mult(func: () -> Float) {
            tool.handleMult = func()
        }
    }

    @TopLevelToolDSL
    fun extra(func: ExtraCreator.() -> Unit) {
        ExtraCreator().apply(func)
    }

    inner class ExtraCreator : DSL<ExtraCreator>() {
        @NestedDSL
        fun durability(func: () -> Int) {
            tool.extraDurability = func()
        }
    }

    @TopLevelToolDSL
    fun bow(func: BowCreator.() -> Unit) {
        BowCreator().apply(func)
    }

    inner class BowCreator : DSL<BowCreator>() {
        @NestedDSL
        fun accuracy(func: () -> Float) {
            tool.bowAccuracy = func()
        }

        @NestedDSL
        fun bonusDamage(func: () -> Float) {
            tool.bowBonusDamage = func()
        }

        @NestedDSL
        fun range(func: () -> Float) {
            tool.bowRange = func()
        }

        @NestedDSL
        fun string(func: () -> Float) {
            tool.stringModifier = func()
        }
    }

    @TopLevelToolDSL
    fun shaft(func: ShaftCreator.() -> Unit) {
        ShaftCreator().apply(func)
    }

    inner class ShaftCreator : DSL<ShaftCreator>() {
        @NestedDSL
        fun modifier(func: () -> Float) {
            tool.arrowShaftModifier = func()
        }

        @NestedDSL
        fun bonusAmmo(func: () -> Int) {
            tool.arrowShaftBonusAmmo = func()
        }
    }


}