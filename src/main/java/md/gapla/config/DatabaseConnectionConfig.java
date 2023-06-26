package md.gapla.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
//@Profile("!deploy")
@Slf4j
public class DatabaseConnectionConfig {

    @Value("${dev.datasource.database.host}")
    private String dbHost;

    @Value("${dev.datasource.database.name}")
    private String dbName;

    @Value("${dev.datasource.database.username}")
    private String dbUsername;

    @Value("${dev.datasource.database.password}")
    private String dbPassword;

    @Value("${dev.datasource.database.port}")
    private String port;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Bean
    public DataSource getDataSource() {
        try {


            // Since we're in SSH, connect to localhost and the forwarded port to reach the DB
            DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(driverClass);
            dataSourceBuilder.url("jdbc:postgresql://" + dbHost + ":" + port + "/" + dbName);
            dataSourceBuilder.username(dbUsername);
            dataSourceBuilder.password(dbPassword);
            return dataSourceBuilder.build();

        } catch (Exception e) {
            log.error("Exception occurred while trying to connect to SSH: ", e);
        }

        return null;
    }
}
