package com.covengers.grouping.jobs;

import com.covengers.grouping.FirebaseInitializer;
import com.covengers.grouping.NotificationMessage;
import com.covengers.grouping.constants.JobConstants;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(name = JobConstants.VM_OPTION_JOB_NAMES,
        havingValue = "groupingEventMessagePushJob")
public class GroupingEventMessagePushJob {

    //private static final int CHUNK_SIZE = 10;

    private static final NotificationMessage notificationMessage = new NotificationMessage();

    private static final Supplier<Notification> GET_MEESAGE_SUPPLIER = () ->
                    Notification.builder()
            .setTitle(notificationMessage.getNotificationTitle())
            .setBody(notificationMessage.getNotificationBody())
            .build();


    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job GroupingEventMessagePushJob() {
        return jobBuilderFactory.get("groupingEventMessagePushJob")
                .incrementer(new RunIdIncrementer())
                .start(GroupingEventMessagePushStep())
                .build();
    }

    @Bean
    public Step GroupingEventMessagePushStep() {
        return stepBuilderFactory.get("groupingEventMessagePushStep")
                .tasklet((contribution, chunkContext) -> {

                    FirebaseInitializer.initFirebase();

                    final List<String> registrationTokens =
                            Arrays.asList(
                                    "cds1VAHPlZU:APA91bEbRftOvIEZaNyZsqiMrESdk_N-RyJG1F0X-GmT0wFiurAHKBQfTksPwUDTTt8EiBZNke-gwBf4-wuwdZNla3MVbXMwzpTLmDOnVIXB32s3BIAz0bLNo5ZcyW0gnmASwy0jvChx",
                                    "dzoTIdAJTmI:APA91bEyJ-7aGaHlmg88Yy6NcsQknImrZ1wQ169JoCHDNvLmP0kjIa-8jgqn2wgTfZCFYNolp-s3Jz_V4-NBzeVe6Q3cLFy3KI1kN5xaE_g5gIQ6xBblOJDBMRGdWZuaUAqTKfy2aeUA"
                            );

                    MulticastMessage message = MulticastMessage.builder()
                            //.putData(DATA_KEY, DATA_VALUE)
                            .addAllTokens(registrationTokens)
                            .setNotification(GET_MEESAGE_SUPPLIER.get())
                            .build();

                    BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);

                    log.info("send result : " + response);

                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
