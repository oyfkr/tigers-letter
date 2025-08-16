package chat.tigersnews.scheduler.jobs

import chat.tigersnews.log.LogCollector
import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component

@Component
class LogCollectorJob(
    private val logCollector: LogCollector
) : QuartzJobBean() {
    override fun executeInternal(context: JobExecutionContext) {
        logCollector.collect() // 구현체가 나중에 생기면 여기서 동작
    }
}