package online.afeibaili.image.draw

import online.afeibaili.file.FontFile
import online.afeibaili.image.draw.PenUtil.drawCenterString
import online.afeibaili.image.draw.PenUtil.drawImageBackground
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage

class WordTable(
    val level: Int,
    val word: String,
    width: Int,
    height: Int,
    val margin: Int,
    val round: Int,
    themeColor: ThemeColor,
) : DrawImage(width, height, themeColor) {
    var count = 0
    val wordCharArray: CharArray = word.toCharArray()

    /**
     * 0无状态、1胜利状态、2失败状态
     */
    var state = 0
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).apply {
        createGraphics().apply { drawImageBackground(themeColor, width, height, margin, round) }

    }

    fun updateWord(newWord: String) {
        drawWord(newWord)
    }

    fun drawWord(word1: String) {
        val wrapSize = width - this@WordTable.margin * 2
        var blockSize: Int = wrapSize / level
        val margin = (blockSize * 0.1).toInt()
        var correctCount = 0

        for (i in 0 until level) {
            var char: Char = word1[i]
            val backgroundColor: Color = equalsChar(char, wordCharArray[i]).also { if (it == CORRECT) correctCount++ }
            var themeColor1 = ThemeColor(Color.WHITE, backgroundColor, Color.RED, Color(0, 0, 0, 0))

            var block = Block(char, blockSize, blockSize, margin, themeColor1)

            val xOffset: Int = i * blockSize + this@WordTable.margin
            val yOffset: Int = count * blockSize + this@WordTable.margin

            image.createGraphics().drawImage(block.image, xOffset, yOffset, null)
        }
        if (++count >= word.length) {
            state = 2
            drawEndUi("次数已用尽！", Color(214, 141, 138, 90))
        } else if (correctCount == word.length) {
            state = 1
            drawEndUi("猜对了，真厉害！", Color(167, 214, 139, 90))
        }
    }

    private fun drawEndUi(message: String, backgroundColor: Color) {
        var bufferedImage: BufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).apply {
            val themeColor1 =
                ThemeColor(Color(0, 0, 0, 0), backgroundColor, Color(0, 0, 0, 0), Color(0, 0, 0, 0))
            createGraphics().apply {
                drawImageBackground(themeColor1, width, height, this@WordTable.margin, round)
                drawCenterString(message, Color(0, 0, 0), FontFile.FONT.deriveFont(120f), width, height)
            }
        }
        image.createGraphics().drawImage(bufferedImage, 0, 0, width, height, null)
    }

    private fun equalsChar(char: Char, char2: Char) = when (char) {
        in wordCharArray -> {
            if (char == char2) CORRECT
            else WARNING
        }

        else -> NORMAL
    }

    companion object {
        val NORMAL: Color = Color.decode("#E7E2DE")
        val WARNING: Color = Color.decode("#A7B661")
        val CORRECT: Color = Color.decode("#7CB687")
    }

    class Block(char: Char, width: Int, height: Int, margin: Int, themeColor: ThemeColor) :
        DrawImage(width, height, themeColor) {
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).apply {
            createGraphics().apply {
                color = themeColor.backgroundColor
                fillRect(0, 0, width, height)
                color = themeColor.wrapsColor
                fillRoundRect(margin, margin, width - margin * 2, height - margin * 2, margin, margin)
                color = themeColor.color
                var fontSize: Float = (width * 0.5).toFloat()
                font = FontFile.FONT.deriveFont(Font.BOLD, fontSize)
                var stringWidth: Int = fontMetrics.stringWidth(char.toString())
                var stringHeight: Int = fontMetrics.height
                drawString(char.toString(), width / 2 - stringWidth / 2, height / 2 + stringHeight / 3)
            }
        }
    }
}