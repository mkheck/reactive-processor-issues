package com.thehecklers.brpprocessor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.function.Function;

@SpringBootApplication
public class BrpProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrpProcessorApplication.class, args);
	}

}

@EnableBinding(Processor.class)
class MessageProcessor {
	@Bean
	Function<Flux<Subscriber>, Flux<Subscriber>> upperCaseIt() {
		return flux -> flux.map(sub -> new Subscriber(sub.getId(),
				sub.getFirstName().toUpperCase(),
				sub.getLastName().toUpperCase(),
				sub.getSubscribeDate()));
	}
}

@Data
@AllArgsConstructor
class Subscriber {
	private final String id, firstName, lastName;
	private final Instant subscribeDate;
}