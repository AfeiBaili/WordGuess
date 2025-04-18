package online.afeibaili.image.draw

import java.awt.Color

data class ThemeColor(val color: Color, val wrapsColor: Color, val wrapsLineColor: Color, val backgroundColor: Color) {
    companion object {
        val DEFAULT = ThemeColor(Color.decode("#2E2C2B"), Color.decode("#E7E2DD"), Color.WHITE, Color.decode("#FCF9F4"))
        val NO_WARPS_COLOR = ThemeColor(Color.BLACK, Color(0, 0, 0, 0), Color.WHITE, Color.decode("#FCF9F4"))
    }
}