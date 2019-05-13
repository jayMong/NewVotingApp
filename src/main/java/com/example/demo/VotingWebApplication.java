package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
public class VotingWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingWebApplication.class, args);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
	    SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
	    factory.setDataSource(ds);
	    factory.setMapperLocations(
	        new PathMatchingResourcePatternResolver()
	            .getResources("classpath:/mappers/*_sql.xml") //Mybatis Query file
	    );
	    return factory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory) {
	    return new SqlSessionTemplate(factory);
	}
	
	/*
	 * @resource public UsersMapper mapper;
	 * 
	 * public void test() { Object result = mapper.findAll(); }
	 */
}
