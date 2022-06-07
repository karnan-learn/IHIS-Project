package com.admin.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ihis.util.ReadMailBody;
import com.ihis.util.SendEmail;

@Configuration
public class AppConfig {

	@Bean
    public SendEmail sendEmail() {
        return new SendEmail();
    }
	@Bean
    public ReadMailBody readMailBody() {
        return new ReadMailBody();
    }
}
