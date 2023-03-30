package com.example.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchApplication {

	public static void main(String[] args) throws Exception {
		System.exit(SpringApplication.exit(SpringApplication.run(BatchApplication.class, args)));
	}

	/**
	 * MEMO : 테스트 코드
	 * @SpringJUnitConfig indicates that the class should use Spring’s JUnit facilities
	 * @SpringBatchTest injects Spring Batch test utilities (such as the JobLauncherTestUtils
	 * 	and JobRepositoryTestUtils) in the test context
	 */
}
