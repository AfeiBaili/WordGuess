package online.afeibaili.image.draw

import online.afeibaili.file.FontFile
import online.afeibaili.image.draw.PenUtil.drawCenterString
import online.afeibaili.image.draw.PenUtil.drawImageBackground
import java.awt.Font
import java.awt.image.BufferedImage

class Title(title: String, width: Int, height: Int, margin: Int, round: Int, themeColor: ThemeColor) :
    DrawImage(width, height, themeColor) {

    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).apply {
        createGraphics().apply {
            drawImageBackground(themeColor, width, height, margin, round)
            drawCenterString(title, themeColor.color, FontFile.FONT.deriveFont(Font.BOLD, 90f), width, height)
        }
    }
}