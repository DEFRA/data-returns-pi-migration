package migration.config;

import migration.persistence.DataReturnsImplicitNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "sdEntityManagerFactory",
        transactionManagerRef = "sdTransactionManager",
        basePackages = { "model.submissions" },
        considerNestedRepositories = true
)
public class PISubmissionsSourceConfiguration {

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.implicit_naming_strategy", DataReturnsImplicitNamingStrategy.class.getName());
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        return props;
    }

    @Bean(name = "sdDataSource")
    @ConfigurationProperties(prefix="spring.submissions.datasource")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("sdDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = builder
                .dataSource(dataSource)
                .packages("model.submissions")
                .persistenceUnit("sd")
                .properties(jpaProperties())
                .build();

        return localContainerEntityManagerFactoryBean;
    }

    @Bean(name = "sdTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("sdEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}

