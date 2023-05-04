package io.github.wesleyosantos91.configuration.health;

import io.github.wesleyosantos91.configuration.properties.ConfigurationProperties;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterOptions;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

@Component("kafka")
public class KafkaHealthIndicator implements HealthIndicator {

    private final KafkaAdmin kafkaAdmin;
    private final ConfigurationProperties properties;
    private final DescribeClusterOptions describeOptions;

    public KafkaHealthIndicator(KafkaAdmin kafkaAdmin, ConfigurationProperties properties) {
        this.kafkaAdmin = kafkaAdmin;
        this.properties = properties;
        this.describeOptions = new DescribeClusterOptions().timeoutMs(properties.getTimeout());
    }

    @Override
    public Health health() {
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            DescribeClusterResult describeClusterResult = adminClient.describeCluster(describeOptions);
            describeClusterResult.nodes().get();
            return Health.up().build();
        } catch (Exception ex) {
            return Health.down().withException(ex).build();
        }
    }
}
