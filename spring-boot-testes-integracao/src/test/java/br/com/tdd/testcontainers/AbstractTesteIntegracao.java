package br.com.tdd.testcontainers;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractTesteIntegracao.Initializer.class)
public class AbstractTesteIntegracao {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.28");
        
        private static void startContainers() {
            Startables.deepStart(Stream.of(mysql)).join();
        }
        
        private static Map<String, String> createConnectionConfiguration(){
            return Map.of(
                "spring.datasource.url", mysql.getJdbcUrl(),
                "spring.datasource.username", mysql.getUsername(),
                "spring.datasource.password", mysql.getPassword());
        }
        
        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontaines =
                new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration());
            
            environment.getPropertySources().addFirst(testcontaines);
        }
        
    }
}