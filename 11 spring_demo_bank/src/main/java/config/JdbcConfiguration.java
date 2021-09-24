package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/*
@Qualifier在类中可以单独使用，指定bean id;不指定的话只能寻找dataSource同名的bean id
 */
public class JdbcConfiguration {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.jdbcUrl}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner CreateQueryRunner(@Qualifier("ds") DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    @Bean(name = "ds")
    public DataSource CreateDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(jdbcUrl);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
