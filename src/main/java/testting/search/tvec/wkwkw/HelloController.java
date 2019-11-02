package testting.search.tvec.wkwkw;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Properties;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class HelloController {

    @Autowired
    private SparkSession spark;

    @Autowired
    private Properties connectionProperties;

    private Dataset<Row> telo = null ;

    @RequestMapping("/")
    public String index() {

        String url = "jdbc:postgresql://<host>:<port>/<db>" ;
        String person = "( select * from telo ) as telo";

        this.telo = this.spark.read().jdbc(url, person, this.connectionProperties );
        this.telo = this.telo.persist();

        return "hello world";
    }

    @RequestMapping("/where")
    public String searchNopol(@RequestParam String nopol) {
        if( this.telo == null ){
            index();
        }
        Row data = this.telo.filter(col("telo_goreng").equalTo(nopol)).first();

        return data.toString() ;
    }
}
