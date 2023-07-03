package com.rbc.bbp0.config.activeMq;

import com.rbc.bbp0.exception.LmsActiveMqErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;

@Slf4j
@EnableJms
@Configuration
public class LmsActiveMqConfig implements JmsListenerConfigurer {

   // @Value("${vcap.service.swift-mx-adapter-ups.credentials.active-mq-lms-broker-url:tcp://localhost:61616")
    private String brokerUrl="tcp://localhost:61616";
    //@Value("${vcap-services.swift-mx-adapter-ups.credentials.active-mq-lms-username:admin}")
    private String username="admin";
    //@Value("${vcap-services.swift-mx-adapter-ups.credentials.active-mq-lms-password:admin}")
    private String password="admin";
    
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory(username,password,brokerUrl);
    }
    @Bean
    public JmsListenerContainerFactory<?> activeMqJmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setErrorHandler(new LmsActiveMqErrorHandler());
        return factory;
    }
    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar){
        registrar.setContainerFactory(activeMqJmsListenerContainerFactory());
    }

}
