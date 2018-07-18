package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Profile("prod")
public class JPAProdConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    DataSource dataSource() throws URISyntaxException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        URI dbUrl = new URI(environment.getProperty("DATABASE_URL"));
        dataSource.setUrl("jdbc:postgre://" + dbUrl.getHost() + ":" + dbUrl.getPort() + dbUrl.getPath());
        dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);
        dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]);
        return dataSource;
    }

}
