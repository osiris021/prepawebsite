package com.prepaWeb.Configurations;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.PlainTextAuthProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(
        basePackages = "com.prepaWeb.Repositories")
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${cassandra.host}")
    private String HOST;

    @Value("${cassandra.port}")
    private int PORT;

    @Value("${cassandra.username}")
    private String USERNAME;

    @Value("${cassandra.password}")
    private String PASSWORD;

    @Value("${cassandra.keyspace}")
    private String KEYSPACE;

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {

        AuthProvider authProvider = new PlainTextAuthProvider(USERNAME, PASSWORD);

        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(HOST);
        cluster.setPort(PORT);
        cluster.setAuthProvider(authProvider);
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
}
