package com.example.examplemod

import com.example.examplemod.dsl.NewMaterial
import com.example.examplemod.ext.toItemStack
import com.example.examplemod.proxy.ClientProxy
import com.example.examplemod.proxy.IProxy
import net.minecraft.init.Blocks
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent


@Mod(modid = Pewter.MODID, name = Pewter.NAME, version = Pewter.VERSION, dependencies = Pewter.DEPENDS, modLanguageAdapter = Pewter.ADAPTER)
class Pewter {

    lateinit var logger: Logger

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
        proxy.preInit(event)
        // Register model baking
        MinecraftForge.EVENT_BUS.register(proxy)
    }


    @EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)
    }

    @EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy.postInit(event)
    }

    companion object {
        const val MODID = "examplemod"
        const val NAME = "Example Mod"
        const val VERSION = "1.0"
        const val DEPENDS = "required:tconstruct"
        const val ADAPTER = "net.shadowfacts.forgelin.KotlinAdapter"
        const val CLIENT = "com.example.examplemod.proxy.ClientProxy"
        const val SERVER = "com.example.examplemod.proxy.CommonProxy"

        @SidedProxy(clientSide = CLIENT, serverSide = SERVER)
        @JvmStatic lateinit var proxy: IProxy

        var materials = mutableListOf<NewMaterial>()
    }
}
