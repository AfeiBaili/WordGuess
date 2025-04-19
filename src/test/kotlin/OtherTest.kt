import kotlin.test.Test

class OtherTest {
    @Test
    fun test1() {
        val string = "#ffffffff"

        //11111111 11111111 11111111
        println(Integer.decode(string))
    }
}