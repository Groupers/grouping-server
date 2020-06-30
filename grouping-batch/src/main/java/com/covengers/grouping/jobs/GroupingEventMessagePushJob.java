package com.covengers.grouping.jobs;

import com.covengers.grouping.FirebaseSdkInit;
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

@Slf4j
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(name = JobConstants.VM_OPTION_JOB_NAMES,
        havingValue = "fcmMessagePushJob")
public class FcmMessagePushJob {

    //private static final int CHUNK_SIZE = 10;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job fcmEventPushJob() {
        return jobBuilderFactory.get("fcmMessagePushJob")
                .incrementer(new RunIdIncrementer())
                .start(fcmPush())
                .build();
    }

    @Bean
    public Step fcmPush() {
        return stepBuilderFactory.get("fcmMessagePushStep")
                .tasklet((contribution, chunkContext) -> {

                    FirebaseSdkInit.initiateFirebaseSdk();

                    List<String> registrationTokens =
                            Arrays.asList("cds1VAHPlZU:APA91bEbRftOvIEZaNyZsqiMrESdk_N-RyJG1F0X-GmT0wFiurAHKBQfTksPwUDTTt8EiBZNke-gwBf4-wuwdZNla3MVbXMwzpTLmDOnVIXB32s3BIAz0bLNo5ZcyW0gnmASwy0jvChx");


                    Notification notification = new Notification("타이틀","내용");

                    MulticastMessage message = MulticastMessage.builder()
                            .putData("score", "850")
                            .addAllTokens(registrationTokens)
                            .setNotification(notification)
                            .build();

                    BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);

                    log.info("send result : " + response);

                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
