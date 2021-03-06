package com.ejektaflex.pewter.proxy

import com.ejektaflex.pewter.content.*
import com.ejektaflex.pewter.ext.resource
import com.ejektaflex.pewter.core.PewterInfo
import com.ejektaflex.pewter.core.PewterLogger
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries
import slimeknights.tconstruct.library.fluid.FluidMolten
import slimeknights.tconstruct.smeltery.block.BlockMolten


open class CommonProxy : IProxy {

    lateinit var fluid: FluidMolten
    lateinit var block: Block
    lateinit var item: Item

    override fun preInit(e: FMLPreInitializationEvent) {
        PewterContent.load()
        PewterLogger.info("Loaded ${PewterMaterials.content.size} materials.")
        PewterLogger.verbose("Doing material preinitialization")
        for (mat in PewterMaterials) {
            mat.preInit(e)
        }
        makePewterFluid()
    }

    override fun init(e: FMLInitializationEvent) {
        PewterLogger.verbose("Doing material initialization..")
        for (mat in PewterMaterials) {
            mat.init(e)
        }
    }

    override fun postInit(e: FMLPostInitializationEvent) {
        // Assign modifiers to items
        for (mod in PewterModifiers) {
            mod.configure()
        }
        PewterLogger.verbose("Doing material postinitialization")
        for (mat in PewterMaterials) {
            mat.postInit(e)
        }
    }

    open fun makePewterFluid() {
        val name = "pewterfluid"
        fluid = FluidMolten(name, 0xffff00ff.toInt()).apply {
            unlocalizedName = "other"
            FluidRegistry.registerFluid(this)
        }

        block = BlockMolten(fluid).apply {
            setCreativeTab(null)
            unlocalizedName = PewterInfo.MODID + "." + name
            registryName = name.resource
            ForgeRegistries.BLOCKS.register(this)
        }

        item = ItemBlock(block).apply {
            registryName = name.resource
            ForgeRegistries.ITEMS.register(this)
        }

    }

}