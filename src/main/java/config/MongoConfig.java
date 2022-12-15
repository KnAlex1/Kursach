package config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration//Клас конфігурації
@EnableMongoRepositories("repository")//Анотація включення репозиторіїв
public class MongoConfig extends AbstractMongoClientConfiguration {//Клас конфігурації монго
    @Override
    public @Bean MongoClient mongoClient() {//Метод створення клієнта монго
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");//Створення з'єднання
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()//Створення налаштувань
                .applyConnectionString(connectionString)//Застосування з'єднання
                .build();//Створення налаштувань
        return MongoClients.create(mongoClientSettings);//Створення клієнта
    }

    @Override
    protected Collection<String> getMappingBasePackages() {//Метод отримання базових пакетів
        return Collections.singleton("");//Повернення пакету
    }

    @Override
    protected String getDatabaseName() {//Метод отримання імені бази даних
        return "music-db";
    }
}
