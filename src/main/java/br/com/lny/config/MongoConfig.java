package br.com.lny.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

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

}
