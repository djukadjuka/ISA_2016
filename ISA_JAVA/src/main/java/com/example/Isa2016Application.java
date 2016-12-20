package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import model.UserBean;
import model.UserRowMapper;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"controller","service"})
public class Isa2016Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Isa2016Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Isa2016Application.class, args);
	}
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... strings)throws Exception{
		
		String sql = "SELECT * FROM users";
		
		/*jdbcTemplate.query("SELECT * FROM users;", (rs,rowNum)-> new UserBean()).forEach(user->log.info(user.getFirstName()));;
		*/
		jdbcTemplate.query(sql, new UserRowMapper()).forEach(user->log.info(((UserBean) user).getFirstName()));
	}
	
}
