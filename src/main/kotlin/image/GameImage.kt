package online.afeibaili.image

import online.afeibaili.file.FontFile
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

class WordImage(val width: Int = 1200, val height: Int = 1800) {
    var image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    val title = "猜单词"
    val titleColor: Color = Color.decode("#C0F1B7")
    val titleBackgroundColor: Color = Color.decode("#F1F1F1")

    fun getImage(): ByteArrayInputStream {
        drawTitle()

        var outputStream = ByteArrayOutputStream()
        ImageIO.write(image, "JPG", outputStream)
        return ByteArrayInputStream(outputStream.toByteArray())
    }

    fun drawTitle() = with(image.graphics) {
        color = titleBackgroundColor
        fillRect(0, 0, width, 200)
        color = titleColor
        font = FONT.deriveFont(Font.BOLD, 150f)
        drawString(title, width / 2, height / 2)
    }


    companion object {
        val FONT = FontFile.getFont("font/舟方日明.otf")
    }
}