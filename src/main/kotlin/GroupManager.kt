package online.afeibaili

import net.mamoe.mirai.Bot
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import java.io.InputStream

object GroupManager {

    const val MAIN_GROUP = 975709430L

    val GROUPS = ArrayList<Long>()

    init {
        GROUPS.add(MAIN_GROUP)
        GROUPS.add(962295696L)
    }

    var bot: Bot? = null

    suspend fun sendToGroup(groupId: Long, message: String) {
        bot!!.getGroup(groupId)?.sendMessage(message)
    }

    suspend fun sendImageToGroup(groupId: Long, inputStream: InputStream) {
        bot!!.getGroup(groupId)?.sendImage(inputStream)
    }

    suspend fun sendToMainGroup(message: String) {
        sendToGroup(MAIN_GROUP, message)
    }

    suspend fun sendImageToMainGroup(inputStream: InputStream) {
        sendImageToGroup(MAIN_GROUP, inputStream)
    }
}