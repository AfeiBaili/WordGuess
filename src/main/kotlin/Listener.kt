package online.afeibaili

import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.BotOnlineEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content

object Listener {
    fun load() {
        groupListener()
        onlineListener()
    }

    private fun groupListener() {
        GlobalEventChannel.filter {
            return@filter if (it is GroupMessageEvent) GroupManager.GROUPS.contains(it.group.id) else false
        }.subscribeAlways<GroupMessageEvent> { event ->
            GameManager.parse(event.message.content, event)
        }
    }

    private fun onlineListener() {
        GlobalEventChannel.subscribeOnce<BotOnlineEvent> { GroupManager.bot = it.bot }
    }
}