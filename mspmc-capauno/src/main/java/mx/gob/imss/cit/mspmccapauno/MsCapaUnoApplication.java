package mx.gob.imss.cit.mspmccapauno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;
import io.opentracing.Tracer;

@SpringBootApplication
public class MsCapaUnoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCapaUnoApplication.class, args);
	}
	
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
	public Tracer getTracer() {
		Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
				.withType(ProbabilisticSampler.TYPE).withParam(1);
		Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
				.withLogSpans(true);
		Configuration config = new Configuration("mspmccapauno Service").withSampler(samplerConfig)
				.withReporter(reporterConfig);
		return config.getTracer();
	}
    
}
