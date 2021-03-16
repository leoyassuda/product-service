package br.com.lny.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Objects;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "br.com.lny.repository")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        final String mongoDatabaseName = env.getProperty("MONGO_DATABASE_NAME");
        System.out.println("======= mongoDatabaseName: " + mongoDatabaseName);
        return mongoDatabaseName;
    }

    @Bean
    public MongoClient mongoClient() {
        final String mongoUri = env.getProperty("MONGO_URI");
        System.out.println("======= mongoUri: " + mongoUri);
        return MongoClients.create(Objects.requireNonNull(mongoUri));
    }

}
