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
        entityManagerFactoryRef = "mdEntityManagerFactory",
        transactionManagerRef = "mdTransactionManager",
        basePackages = { "model.masterdata" },
        considerNestedRepositories = true
)
public class MasterDataSourceConfiguration {

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.implicit_naming_strategy", DataReturnsImplicitNamingStrategy.class.getName());
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        return props;
    }

    @Bean(name = "mdDataSource")
    @ConfigurationProperties(prefix="spring.master-data.datasource")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mdDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = builder
                .dataSource(dataSource)
                .packages("model.masterdata")
                .persistenceUnit("md")
                .properties(jpaProperties())
                .build();

        return localContainerEntityManagerFactoryBean;
    }

    @Bean(name = "mdTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("mdEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}

