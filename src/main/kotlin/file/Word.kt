package online.afeibaili.file

data class Word(val word: String, val translation: String) {
    companion object {
        fun toWord(word: String, translation: String): Word {
            return Word(word, translation)
        }
    }
}