package ch.lugon.experiment.Camunda8Experiment.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestWorkerConfig {

    @ZeebeWorker(type = "foo")
    public void handleJobFoo(final JobClient client, final ActivatedJob job) {
        // do whatever you need to do
        client.newCompleteCommand(job.getKey())
                .variables("{\"fooResult\": 1}")
                .send()
                .exceptionally( throwable -> { throw new RuntimeException("Could not complete job " + job, throwable); });
    }

}
