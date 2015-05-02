package com.lova.spring.trxns.config;

import com.lova.spring.trxns.dao.SampleDAO;
import com.lova.spring.trxns.dao.impl.SampleDaoImpl;
import com.lova.spring.trxns.service.SampleService;
import com.lova.spring.trxns.service.impl.SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Lovababu on 5/2/2015.
 */

@Configuration
@ComponentScan(basePackages = {"com.lova.spring.trxns.service.impl", "com.lova.spring.trxns.dao.impl", "com.lova.spring.trxns.profiler"})
@PropertySource("classpath:dbConfig.properties")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@Slf4j
public class AppConfig {

    @Value("${hibernate.hibernateDialect}")
    private String hibernateDialect;
    @Value("${hibernate.showSQL}")
    private String showSql;
    @Value("${hibernate.generateStatistics}")
    private String generateStatistics;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.lova.spring.trxns.domain");
        sessionFactoryBean.setAnnotatedPackages("com.lova.spring.trxns.domain");
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.afterPropertiesSet();

        return sessionFactoryBean.getObject();
    }

    /**
     * Spring provided H2 Embedded Database. Read the dbscript and initiates the Database with the name H2-Test-DB.
     *
     * @return
     */
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setName("H2-Test-DB");
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:dbscript/my-schema.sql")
                .addScript("classpath:dbscript/my-test-data.sql").build();
        log.info("Initiating the database from dbscript.");
        return db;
    }


    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() throws Exception {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(this.sessionFactory());
        return transactionManager;
    }

    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.cache.use_second_level_cache", false);
        properties.put("hibernate.generate_statistics", generateStatistics);
        return properties;
    }

}
