package testting.search.tvec.wkwkw;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.Properties;

/**
 * Created by achat1 on 9/22/15.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
    public Properties connectionProperties() {
      Properties connectionProperties = new Properties();
      connectionProperties.setProperty("Driver", "org.postgresql.Driver");
      connectionProperties.setProperty("user", "<username>");
      connectionProperties.setProperty("password", "<password>");
      return connectionProperties ;
    }

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName("Simple Application")
                .config("spark.master", "local")
                .config("spark.executor.cores", "4")
                .config("spark.ui.enabled", "false")
                .config("spark.ui.killEnabled", "false")
                .config("spark.default.parallelism", "20")
                .config("spark.debug.maxToStringFields", "100")
                .getOrCreate();
    }

}
