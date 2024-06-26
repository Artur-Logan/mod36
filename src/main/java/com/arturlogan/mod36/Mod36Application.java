package com.arturlogan.mod36;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.model.process.spi.ManagedResources;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.arturlogan.mod36")
public class Mod36Application {

	public static void main(String[] args) {
		SpringApplication.run(Mod36Application.class, args);
	}

	@Bean
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
		return new EntityManagerFactoryBuilder() {
			@Override
			public ManagedResources getManagedResources() {
				return null;
			}

			@Override
			public MetadataImplementor metadata() {
				return null;
			}

			@Override
			public EntityManagerFactoryBuilder withValidatorFactory(Object o) {
				return null;
			}

			@Override
			public EntityManagerFactoryBuilder withDataSource(DataSource dataSource) {
				return null;
			}

			@Override
			public EntityManagerFactory build() {
				return null;
			}

			@Override
			public void cancel() {

			}

			@Override
			public void generateSchema() {

			}
		};
	}

}
