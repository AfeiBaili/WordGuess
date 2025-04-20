package online.afeibaili

import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.events.GroupMessageEvent
import online.afeibaili.file.EnWordsFill
import online.afeibaili.file.MinecraftWordsFill
import online.afeibaili.file.Word
import online.afeibaili.image.GameImage
import java.awt.Color
import java.util.*

object GameManager {

    val themeColor = HashMap<String, Color>()
    val games: HashMap<Long, GameImage> = HashMap()
    val random = Random()
    suspend fun parse(message: String, event: GroupMessageEvent) {
        var groupId: Long = event.group.id
        var send: Group = event.subject

        suspend fun wordGuess(level: Int) {
            if (EnWordsFill.wordArray == null) {
                send.sendMessage("词库还在加载中")
                return
            }
            var randomIndex: Int = random.nextInt(EnWordsFill.wordArray!![level - 1].size)
            var word: Word = EnWordsFill.wordArray!![level - 1][randomIndex]
            games.put(groupId, GameImage(word, "猜单词"))
            send.sendImage(games[groupId]!!.getImage())
        }

        suspend fun minecraftWordGuess(isChises: Boolean) {
            if (MinecraftWordsFill.minecraftWords == null) {
                send.sendMessage("Minecraft词库还在加载中")
                return
            }
            var word: Word =
                MinecraftWordsFill.minecraftWords!![random.nextInt(MinecraftWordsFill.minecraftWords!!.size)]

            if (isChises) word = Word(word.translation, word.word)

            games.put(groupId, GameImage(word, "猜MC“词条”"))
            send.sendImage(games[groupId]!!.getImage())
        }

        when {
            message == "猜单词" -> {
                wordGuess(random.nextInt(1, EnWordsFill.wordArray!!.size))
            }

            message == "猜词条" -> {
                minecraftWordGuess(random.nextBoolean())
            }

            message == "换词" -> {
                games[groupId]?.let {
                    if (games[groupId]!!.bigTitle.contains("猜单词")) {
                        wordGuess(games[groupId]!!.word.word.length)
                    }
                    if (games[groupId]!!.bigTitle.contains("猜MC")) {
                        minecraftWordGuess(random.nextBoolean())
                    }
                }
            }

            message == "猜中文词条" -> {
                minecraftWordGuess(true)
            }

            message == "猜英文词条" -> {
                minecraftWordGuess(false)
            }

            message == "我认输" -> {
                games[groupId]?.let {
                    games[groupId]!!.concedeWord(event)
                    send.sendImage(games[groupId]!!.getImage())
                    games.remove(groupId)
                }
            }

            message.contains("阶猜单词") -> {
                var trimString: String = message.trim()
                if (trimString.length >= 5) {
                    var level: String = trimString.split("阶猜单词")[0]
                    when (level) {
                        "1", "一" -> wordGuess(1)
                        "2", "二" -> wordGuess(2)
                        "3", "三" -> wordGuess(3)
                        "4", "四" -> wordGuess(4)
                        "5", "五" -> wordGuess(5)
                        "6", "六" -> wordGuess(6)
                        "7", "七" -> wordGuess(7)
                        "8", "八" -> wordGuess(8)
                        "9", "九" -> wordGuess(9)
                        "10以上", "十以上" -> wordGuess(10)
                    }
                }
            }

            else -> games[groupId]?.run {
                when (wordTable.state) {
                    1 -> {
                        games.remove(groupId)
                    }

                    2 -> {
                        games.remove(groupId)
                    }
                }


                if (games[groupId]!!.bigTitle.contains("猜单词")) {
                    var regex: Regex = "[A-Za-z]{${word.word.length}}".toRegex()
                    if (!regex.matches(message)) return
                } else if (games[groupId]!!.bigTitle.contains("猜MC")) {
                    var regex: Regex = ".{${word.word.length}}".toRegex()
                    if (!regex.matches(message)) return
                }

                updateWord(message.uppercase(), event)
                send.sendImage(getImage())
            }
        }
    }
}