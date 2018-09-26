package com.ejektaflex.pewter.logic

import com.ejektaflex.pewter.Pewter
import com.ejektaflex.pewter.content.PewterMaterials
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.IResourceManagerReloadListener
import net.minecraft.util.text.translation.LanguageMap
import java.io.ByteArrayInputStream
import net.minecraft.client.resources.IResourceManager

class ResourceManager : IResourceManagerReloadListener {

    override fun onResourceManagerReload(resourceManager: IResourceManager) {
        var data = ""
        // Write all custom dependencies
        PewterMaterials.content.filter { it.data.isCustomMaterial }.forEach {
            data += writeMaterial(it)
        }

        LanguageMap.inject(ByteArrayInputStream(data.toByteArray()))
    }

    companion object {

        private val mcLocale: String
            get() = Minecraft.getMinecraft().languageManager.currentLanguage.languageCode.toLowerCase()

        fun writeMaterial(mat: MaterialRegistrar): String {
            val locales = mat.data.nameLocales
            val materialName = mat.data.name.toLowerCase()
            var data = ""

            Pewter.LOGGER!!.info("$mcLocale ... ${locales[mcLocale]}")

            // Look up the current locale in the local map. If it exists, then add the entry
            if (locales.keys.isNotEmpty()) {
                locales[mcLocale]?.let {
                    println(it)
                    data += "material.$materialName.identifier=$it\n"
                    data += "fluid.${Pewter.MODID}.$materialName.identifier=Molten $it\n"
                }
            }

            return data
        }
    }

}