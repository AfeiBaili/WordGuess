package online.afeibaili.image

import net.mamoe.mirai.event.events.GroupMessageEvent
import online.afeibaili.file.Word
import online.afeibaili.image.draw.ThemeColor
import online.afeibaili.image.draw.Tip
import online.afeibaili.image.draw.Title
import online.afeibaili.image.draw.WordTable
import online.afeibaili.util.toChinese
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

class GameImage(
    val word: Word,
    val bigTitle: String,
    margin: Int = 40,
    val width: Int = 1200,
    val height: Int = 1800 - (margin * 2),
) {
    val level = word.word.length
    var image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val backgroundColor: Color = ThemeColor.DEFAULT.backgroundColor

    // 300的标题
    val title = Title("${word.word.length.toChinese()}阶$bigTitle", width, 300, margin, 30, ThemeColor.DEFAULT)

    // 1200的内容
    val wordTable = WordTable(level, word, width, 1200, margin, 30, ThemeColor.NO_WARPS_COLOR)

    // 300的词释义
    val tip = Tip(word.translation, width, 300, margin, 30, ThemeColor.DEFAULT)

    init {
        var pen: Graphics2D = image.createGraphics()
        drawBackground(pen)
        drawComponent(pen)
    }

    private fun drawBackground(pen: Graphics2D) {
        pen.color = backgroundColor
        pen.fillRect(0, 0, width, height)

    }

    private fun drawComponent(pen: Graphics2D) {
        pen.drawImage(title.image, 0, 0, title.width, title.height, null)
        pen.drawImage(
            tip.image,
            0,
            title.height + wordTable.height - wordTable.margin - tip.margin,
            tip.width,
            tip.height,
            null
        )
    }

    fun updateWord(newWord: String, event: GroupMessageEvent) {
        wordTable.updateWord(newWord, event)
        image.createGraphics()
            .drawImage(wordTable.image, 0, title.height - wordTable.margin, wordTable.width, wordTable.height, null)
    }

    fun concedeWord(event: GroupMessageEvent) {
        wordTable.drawEndUi("${event.senderName}已认输，答案为${word.word.lowercase()}", Color(70, 70, 70, 90))
        image.createGraphics()
            .drawImage(wordTable.image, 0, title.height - wordTable.margin, wordTable.width, wordTable.height, null)
    }

    fun getImage(): ByteArrayInputStream {
        var outputStream = ByteArrayOutputStream()
        ImageIO.write(image, "JPG", outputStream)

        return ByteArrayInputStream(outputStream.toByteArray())
    }
}