package chat.tigersnews.scheduler.jobs

import chat.tigersnews.service.MailService
import org.quartz.JobExecutionContext
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component

@Component
class DailyMailJob : QuartzJobBean() {
    private lateinit var mailService: MailService

    override fun executeInternal(context: JobExecutionContext) {
        val applicationContext = context.scheduler.context["applicationContext"] as ApplicationContext
        mailService = applicationContext.getBean(MailService::class.java)

        // Job 실행 로직
        mailService.sendMail()
    }
}
