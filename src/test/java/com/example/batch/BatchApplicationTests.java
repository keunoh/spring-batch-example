package com.example.batch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@SpringJUnitConfig(BatchApplication.class)
//@SpringBootTest
class BatchApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	public void testJob(@Autowired Job job) throws Exception {
		this.jobLauncherTestUtils.setJob(job);
		this.jdbcTemplate.update("delete from people");
		for (int i = 1; i <= 10; i++) {
			this.jdbcTemplate.update("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)", i, "people" + i);
		}

		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertThat("COMPLETED").isEqualTo(jobExecution.getExitStatus().getExitCode());
	}

	@Test
	void test() {
		this.jdbcTemplate.execute("select * from people");
	}
}
