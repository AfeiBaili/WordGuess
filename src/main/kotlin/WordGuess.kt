package online.afeibaili

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import online.afeibaili.file.EnWordsFill
import online.afeibaili.file.MinecraftWordsFill

object WordGuess : KotlinPlugin(
    JvmPluginDescription(
        id = "online.afeibaili.wordguess",
        name = "WordGuess",
        version = "1.2.1",
    ) {
        author("AfeiBaili")
    }
) {
    override fun onEnable() {
        logger.info { "猜单词插件加载成功" }
        EnWordsFill.loadWordArray()
        MinecraftWordsFill.loadMinecraftWords()
        Listener.load()
    }
}