package io.codelex.flightplanner.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConditionalOnExpression("'${flight-planner.store-type}'=='database' and '${flight-planner.db-typee}'=='H2'")
    public DataSource getH2DataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:mydb")
                .username("sa")
                .password("")
                .build();
    }

    @Bean
    @ConditionalOnExpression("'${flight-planner.store-type}'=='database' and '${flight-planner.db-typee}'=='postgres'")
    public DataSource getPostgresDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql:<db-info>")
                // change <db-info>, enter info about Database
                .username("codelex")
                .password("codelex")
                .build();
    }

}
