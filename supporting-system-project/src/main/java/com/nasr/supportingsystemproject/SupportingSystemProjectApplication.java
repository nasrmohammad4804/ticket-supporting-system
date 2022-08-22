package com.nasr.supportingsystemproject;

import com.nasr.supportingsystemproject.auditoraware.AuditorAwareImpl;
import com.nasr.supportingsystemproject.domain.Ticket;
import com.nasr.supportingsystemproject.repository.CustomerRepository;
import com.nasr.supportingsystemproject.service.TicketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "getAuditorAware")
public class SupportingSystemProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SupportingSystemProjectApplication.class, args);
    }

    @Bean
    @Primary
    public AuditorAware<String>  getAuditorAware(){
        return new AuditorAwareImpl();
    }

}
