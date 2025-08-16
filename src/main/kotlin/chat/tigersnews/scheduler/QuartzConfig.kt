package chat.tigersnews.scheduler

import chat.tigersnews.scheduler.jobs.DailyMailJob
import chat.tigersnews.scheduler.jobs.LogCollectorJob
import org.quartz.JobDetail
import org.quartz.Trigger
import org.quartz.spi.TriggerFiredBundle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.CronTriggerFactoryBean
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SpringBeanJobFactory

@Configuration
class QuartzConfig {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Bean
    fun jobFactory(): SpringBeanJobFactory {
        return AutowiringSpringBeanJobFactory(applicationContext.autowireCapableBeanFactory)
    }

    /** 매일 아침 6시 메일 발송 */
    @Bean
    fun dailyMailJobDetail(): JobDetailFactoryBean =
        JobDetailFactoryBean().apply {
            setJobClass(DailyMailJob::class.java)
            setDescription("매일 아침 6시 메일 발송 Job")
            setDurability(true)
        }

    @Bean
    fun dailyMailTrigger(dailyMailJobDetail: JobDetail): CronTriggerFactoryBean =
        CronTriggerFactoryBean().apply {
            setJobDetail(dailyMailJobDetail)
            setCronExpression("0 0 6 * * ?") // 매일 아침 06:00
        }

    /** 로그 수집 Job (인터페이스 기반) */
    @Bean
    fun logCollectorJobDetail(): JobDetailFactoryBean =
        JobDetailFactoryBean().apply {
            setJobClass(LogCollectorJob::class.java)
            setDescription("로그 수집 Job")
            setDurability(true)
        }

    @Bean
    fun logCollectorTrigger(logCollectorJobDetail: JobDetail): CronTriggerFactoryBean =
        CronTriggerFactoryBean().apply {
            setJobDetail(logCollectorJobDetail)
            setCronExpression("0 0 2 * * ?") // 매일 새벽 2시 (예시)
        }

    /** Scheduler: 여러 Job/Trigger 등록 */
    @Bean
    fun schedulerFactoryBean(
        jobFactory: SpringBeanJobFactory,
        dailyMailTrigger: Trigger,
        dailyMailJobDetail: JobDetail,
        logCollectorTrigger: Trigger,
        logCollectorJobDetail: JobDetail
    ): SchedulerFactoryBean =
        SchedulerFactoryBean().apply {
            setJobFactory(jobFactory)
            setJobDetails(dailyMailJobDetail, logCollectorJobDetail)
            setTriggers(dailyMailTrigger, logCollectorTrigger)
        }
}

class AutowiringSpringBeanJobFactory(
    private val beanFactory: AutowireCapableBeanFactory,
): SpringBeanJobFactory() {

    override fun createJobInstance(bundle: TriggerFiredBundle): Any {
        val job = super.createJobInstance(bundle)

        beanFactory.autowireBean(job)
        return job
    }
}