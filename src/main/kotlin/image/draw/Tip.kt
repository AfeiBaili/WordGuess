package online.afeibaili.image.draw

import online.afeibaili.file.FontFile
import online.afeibaili.image.draw.PenUtil.drawCenterString
import online.afeibaili.image.draw.PenUtil.drawImageBackground
import java.awt.Color
import java.awt.image.BufferedImage

class Tip(tips: String, width: Int, height: Int, val margin: Int, round: Int, themeColor: ThemeColor) :
    DrawImage(width, height, themeColor) {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).apply {
        createGraphics().apply {
            drawImageBackground(themeColor, width, height, margin, round)
            drawCenterString(tips, Color(0, 0, 0, 95), FontFile.FONT.deriveFont(60f), width, height)
        }
    }
}