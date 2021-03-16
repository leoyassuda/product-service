package br.com.lny.config;

import com.mongodb.connection.SslSettings;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@ConfigurationProperties("mongo")
@Configuration
@EnableReactiveMongoRepositories(basePackages = "br.com.lny.repository")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("databaseName")
    private String databaseName;

//    @Value("uri")
    private final String uri =
        "mongodb+srv://lny-dft-usr:pass@cluster0.wepxb.mongodb.net/product_db?retryWrites=true&w=majority";

    @Override
    protected String getDatabaseName() {
        return this.databaseName;
    }


    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(uri);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

}
