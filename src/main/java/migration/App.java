package migration;

import lombok.extern.slf4j.Slf4j;
import migration.migration.PermitMigration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.PrintWriter;
import java.io.StringWriter;

@SpringBootApplication
@ComponentScan
@Slf4j
public class App {
    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        if (context.getEnvironment().acceptsProfiles("migration")) {
            log.info("Running data-returns migration");
            try {
                final PermitMigration permitMigration = context.getBean(PermitMigration.class);
                permitMigration.migrate();

            } catch (final Throwable t) {
                StringWriter sw = new StringWriter();
                t.printStackTrace(new PrintWriter(sw));
                log.error(sw.toString());
                System.exit(-1);
            }
        }
    }
}