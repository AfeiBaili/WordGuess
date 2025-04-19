package online.afeibaili.image.draw

import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D

object PenUtil {
    /**
     * 绘制背景（整体块）和包裹色（圆角块）
     */
    fun Graphics2D.drawImageBackground(themeColor: ThemeColor, width: Int, height: Int, margin: Int, round: Int) {
        color = themeColor.backgroundColor
        fillRect(0, 0, width, height)
        color = themeColor.wrapsColor
        fillRoundRect(margin, margin, width - margin * 2, height - margin * 2, round, round)
    }

    fun Graphics2D.drawCenterString(title: String, color1: Color, deriveFont: Font, width: Int, height: Int) {
        color = color1
        font = deriveFont
        var stringWidth: Int = fontMetrics.stringWidth(title)
        var stringHeight: Int = fontMetrics.height
        drawString(title, width / 2 - stringWidth / 2, height / 2 + stringHeight / 3)
    }

    fun Graphics2D.drawCenterCoverString(title: String, color1: Color, deriveFont: Font, width: Int, height: Int) {
        fun getCoverFont(stringWidth: Int): Int {
            if (stringWidth + 100 > width) {
                font = font.deriveFont(font.size - 1.toFloat())
                getCoverFont(fontMetrics.stringWidth(title))
            }
            return fontMetrics.stringWidth(title)
        }

        color = color1
        font = deriveFont
        var stringWidth: Int = getCoverFont(fontMetrics.stringWidth(title))
        var stringHeight: Int = fontMetrics.height

        drawString(title, width / 2 - stringWidth / 2, height / 2 + stringHeight / 3)

    }
}