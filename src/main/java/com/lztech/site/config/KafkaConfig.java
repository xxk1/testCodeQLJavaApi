package com.lztech.site.config;

import com.lztech.site.constants.Constant;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnExpression("${dataVisual.enable}==true")
public class KafkaConfig {

    @Value("${dataVisual.kafka.producer.bootstrap-servers}")
    private String producerBootstrapServers;

    private Map<String, Object> producerProperties(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        props.put(ProducerConfig.CLIENT_ID_CONFIG,"courseApi");
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, Constant.MAX_BLOCK_MS_CONFIG);
        // 重试次数
        props.put(ProducerConfig.RETRIES_CONFIG,Constant.RETRIES_CONFIG);
        // 每个连接的最大并发数，1：只允许一个，保证数据的强顺序性，如果有数据没有应答，product阻塞等待响应
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,Constant.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION);
        //连接超时时间 ms 这里为了方便模拟没有应答场景
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,Constant.REQUEST_TIMEOUT_MS_CONFIG);

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerBootstrapServers);
        return props;
    }

    @Bean("produceFactory")
    public DefaultKafkaProducerFactory produceFactory(){
        return new DefaultKafkaProducerFactory(producerProperties());
    }

    @Bean
    public KafkaTemplate kafkaTemplate(DefaultKafkaProducerFactory produceFactory){
        return new KafkaTemplate(produceFactory);
    }

}
