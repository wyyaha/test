package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/*
* 定义一个配置类，作用和bean.xml一样
* spring中的新注解
* @Configuration
*   指定当前类是一个配置类
* @ComponentScan
*   作用：用于指定spring在创建容器时要扫描的包，等同于xml中
*   <context:component-scan base-package="com.test"></context:component-scan>
*   属性： value和basePackages作用一样
*@Bean
*   作用：把当前对象的返回值作为bean对象存入spring容器
*   属性：
*       name:用于指定bean的id。当不定义时，默认值是当前方法的名称
*@Import
*   作用： 用于导入其他的配置类
*   属性：
*       value: 用于指定其他配置类的字节码
*   有Import注解的就是父配置类
* */
@Configuration
@ComponentScan(basePackages = "com.test")
@Import(JdbcConfiguration.class)
public class SpringConfiguration {

}
