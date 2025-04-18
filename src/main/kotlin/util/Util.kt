package online.afeibaili.util

import kotlin.text.iterator

fun Int.toChinese(): String {
    val stringBuilder: StringBuilder = StringBuilder()
    for (c in toString()) {
        stringBuilder.append(
            when (c) {
                '1' -> "一"
                '2' -> "二"
                '3' -> "三"
                '4' -> "四"
                '5' -> "五"
                '6' -> "六"
                '7' -> "七"
                '8' -> "八"
                '9' -> "九"
                '0' -> "零"
                else -> ""
            }
        )
    }
    return stringBuilder.toString()
}