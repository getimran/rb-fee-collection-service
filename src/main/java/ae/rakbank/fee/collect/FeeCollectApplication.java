package ae.rakbank.fee.collect;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@OpenAPIDefinition(info = @Info(
		title = "Fee Collection Service Definition",
		description = "Microservice for collecting student fee and retrieving fee details based on fee referenceId and studentId",
		contact = @Contact(
				name = "Mohammad Imran",
				url= "https://www.linkedin.com/in/md-imran-02524a125/",
				email= "mailmeinfo.imran@gmail.com"
		),
		version = "v1"
))
public class FeeCollectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeeCollectApplication.class, args);
	}

}
