package chat.tigersnews.scheduler

import chat.tigersnews.scheduler.jobs.MondayMorningJob
import org.quartz.JobDetail
import org.quartz.Trigger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.CronTriggerFactoryBean
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean

@Configuration
class QuartzConfig {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Bean
    fun mondayJonDetail() : JobDetailFactoryBean {

        val jobDetail = JobDetailFactoryBean()
        jobDetail.setJobClass(MondayMorningJob::class.java)
        jobDetail.setDescription("월요일 아침 6시 Job")
        jobDetail.setDurability(true)
        return jobDetail

    }

    @Bean
    fun mondayTrigger(mondayJobDetail: JobDetail?): CronTriggerFactoryBean {
        val trigger = CronTriggerFactoryBean()
        trigger.setJobDetail(mondayJobDetail!!)
        // trigger.setCronExpression("0 */1 * * * ?") // 1분마다 실행 test
        trigger.setCronExpression("0 0 6 ? * MON") // 매주 월요일 6시
        return trigger
    }

    // 스케줄러 설정
    @Bean
    fun scheduler(trigger: Trigger?, jobDetail: JobDetail?): SchedulerFactoryBean {
        val schedulerFactory = SchedulerFactoryBean()
        schedulerFactory.setJobDetails(jobDetail)
        schedulerFactory.setTriggers(trigger)
        schedulerFactory.setApplicationContextSchedulerContextKey("applicationContext")
        schedulerFactory.setApplicationContext(applicationContext)
        return schedulerFactory
    }
}