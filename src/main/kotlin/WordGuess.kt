package online.afeibaili

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object WordGuess : KotlinPlugin(
    JvmPluginDescription(
        id = "online.afeibaili.wordguess",
        name = "WordGuess",
        version = "0.1.0",
    ) {
        author("AfeiBaili")
    }
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }
    }
}