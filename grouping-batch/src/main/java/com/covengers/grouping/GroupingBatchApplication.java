package com.covengers.grouping;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableBatchProcessing
@SpringBootApplication
public class GroupingBatchApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext ctx =
				new SpringApplicationBuilder(GroupingBatchApplication.class)
						.web(WebApplicationType.NONE)
						.run(args);

		final int exitCode = SpringApplication.exit(ctx);
		System.exit(exitCode);
	}

}
