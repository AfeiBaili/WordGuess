package online.afeibaili.file

import java.awt.Font
import java.io.InputStream

object FontFile {
    val FONT = getFont("font/舟方日明.otf")

    fun getFont(name: String): Font {
        var fontStream: InputStream? = FontFile.javaClass.classLoader.getResourceAsStream(name)
        return Font.createFont(Font.TRUETYPE_FONT, fontStream)
    }
}