package com.niit.config;

import java.util.Arrays;
import java.util.Properties;

import javax.sql.DataSource;

//import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
*/
/*import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;*/
//import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
/*import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;*/
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.ProfilePicture;
import com.niit.model.User;

//import com.niit.domain.User;

@Configuration
@ComponentScan("com.niit.*")
@EnableTransactionManagement

public class ApplicationContextConfig {
	
	//@Override
	/*protected void configure(HttpSecurity http) throws Exception {
		System.out.println("entering new login********");
		http
			.authorizeRequests()
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin();
	}
*/
	
	
	@Bean(name="dataSource")
	public DataSource getMysqlDataSource(){
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		ds.setUsername("niits");
		System.out.println("inside username **************");
		ds.setPassword("niits");
		return ds;
		
	}
	
	/* @Autowired
	 public void init(AuthenticationManagerBuilder auth) throws Exception {
	 auth.ldapAuthentication()
	 .userSearchFilter("(uid={0})")
	 .userSearchBase("ou=users")
	 .groupSearchFilter("(uniqueMember={0})")
	 .groupSearchBase("ou=groups")
	 .groupRoleAttribute("cn")
	 .rolePrefix("ROLE_")
	 .contextSource().ldif("/WEB-INF/conf/users.ldif").root("o=tutorialsdesk");
	 }*/
	 
	// @Override
		/*public void configure(AuthenticationManagerBuilder auth) throws Exception {
			System.out.println("entering the ldap authentication");
			auth
				.ldapAuthentication()
					.userDnPatterns("uid={0},ou=people")
					.groupSearchBase("ou=groups")
					.contextSource(contextSource())
					.passwordCompare()
						.passwordEncoder(new LdapShaPasswordEncoder())
						.passwordAttribute("userPassword");
		}
	 
	 @Bean
		public DefaultSpringSecurityContextSource contextSource() {
		 System.out.println("entering the ldap **********");
			return  new DefaultSpringSecurityContextSource(Arrays.asList("ldap://192.168.0.105:389/"), "dc=example,dc=com");
		}
	*/
	
	@Bean
	public Properties getHibernatePropeties()
	{
		System.out.println("inside PROP**************");
		Properties p=new Properties();
		p.put("hibernate.show_sql", "true");
		System.out.println("showing query**************");
		p.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		p.put("hibernate.hbm2ddl.auto", "update");
		System.out.println("creating table**************");
		return p;
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(dataSource);
	//	sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(ProfilePicture.class);
		System.out.println("ADDINGH USER CLASS**************");
		sessionBuilder.addProperties(getHibernatePropeties());
		
		return sessionBuilder.buildSessionFactory();
	}
	
	
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager tx=new HibernateTransactionManager();
		tx.setSessionFactory(sessionFactory);
		return tx;
	}
	

	
}
