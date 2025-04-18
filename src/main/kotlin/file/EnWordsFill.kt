package online.afeibaili.file

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import online.afeibaili.WordGuess
import java.io.FileReader

object EnWordsFill {
    val filePath = System.getProperty("user.dir") + "/data/EnWords.csv"

    var wordArray: ArrayList<ArrayList<Word>>? = null
    var wordArray1: ArrayList<Word> = ArrayList()
    var wordArray2: ArrayList<Word> = ArrayList()
    var wordArray3: ArrayList<Word> = ArrayList()
    var wordArray4: ArrayList<Word> = ArrayList()
    var wordArray5: ArrayList<Word> = ArrayList()
    var wordArray6: ArrayList<Word> = ArrayList()
    var wordArray7: ArrayList<Word> = ArrayList()
    var wordArray8: ArrayList<Word> = ArrayList()
    var wordArray9: ArrayList<Word> = ArrayList()
    var wordArray10Above: ArrayList<Word> = ArrayList()

    fun loadWordArray() = CoroutineScope(Dispatchers.IO).launch {
        FileReader(filePath).forEachLine { it ->
            var strings: List<String> = it.split("\",\"")
            if (strings.size == 2) {
                val word = strings[0].substring(1).trim().uppercase()
                val translation = strings[1].substring(0, strings[1].length - 1).trim()

                var level: Int = word.length
                var wordObj: Word = Word.toWord(word, translation)

                when (level) {
                    1 -> wordArray1.add(wordObj)
                    2 -> wordArray2.add(wordObj)
                    3 -> wordArray3.add(wordObj)
                    4 -> wordArray4.add(wordObj)
                    5 -> wordArray5.add(wordObj)
                    6 -> wordArray6.add(wordObj)
                    7 -> wordArray7.add(wordObj)
                    8 -> wordArray8.add(wordObj)
                    9 -> wordArray9.add(wordObj)
                    else -> wordArray10Above.add(wordObj)
                }
            }
        }
        wordArray = ArrayList()
        wordArray!!.add(wordArray1)
        wordArray!!.add(wordArray2)
        wordArray!!.add(wordArray3)
        wordArray!!.add(wordArray4)
        wordArray!!.add(wordArray5)
        wordArray!!.add(wordArray6)
        wordArray!!.add(wordArray7)
        wordArray!!.add(wordArray8)
        wordArray!!.add(wordArray9)
        wordArray!!.add(wordArray10Above)
        WordGuess.logger.info("词库加载成功！")
    }
}