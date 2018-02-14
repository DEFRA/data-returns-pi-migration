package migration;

import lombok.extern.slf4j.Slf4j;
import migration.migration.DataMigration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@Slf4j
public class App {
    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        if (context.getEnvironment().acceptsProfiles("migration")) {
            log.info("Running data-returns migration");
            try {
                final DataMigration migration = context.getBean(DataMigration.class);
                migration.migrateArea();
            } catch (final Throwable t) {
                log.error(t.getMessage());
                System.exit(-1);
            }
        }
    }
}