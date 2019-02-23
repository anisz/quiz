package configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import dataAccessLayer.DAOJdbcTemplate;

@Configuration
@ComponentScan(basePackages={"api", "task", "controller", "model", "view"})
public class SpringJdbcConfig {
	
	@Bean
	public DataSource dataSource() {
	    return new EmbeddedDatabaseBuilder()
	        .setType(EmbeddedDatabaseType.H2)
	        .addScript("classpath:schema.sql")
//	        .addScript("classpath:test-data.sql")
	        .build();
	}
		
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterjdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public DAOJdbcTemplate creatureDataDAOImpl() {
		return new DAOJdbcTemplate();
	}
}
