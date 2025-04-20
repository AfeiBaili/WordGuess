package online.afeibaili.file

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import online.afeibaili.WordGuess
import java.io.FileReader

object MinecraftWordsFill {
    val filePath = System.getProperty("user.dir") + "/data/MinecraftWords.csv"

    var minecraftWords: ArrayList<Word>? = null
    fun loadMinecraftWords() = CoroutineScope(Dispatchers.IO).launch {
        val word = ArrayList<Word>()

        FileReader(filePath).forEachLine { line ->
            var strings: List<String> = line.split(",")
            if (strings.size == 2) {
                var en: String = strings[0].split("\"")[1].trim()
                var cn: String = strings[1].split("\"")[1].trim()
                word.add(Word(en, cn))
            }
        }
        minecraftWords = word
        WordGuess.logger.info("Minecraft词库加载成功！")
    }
}