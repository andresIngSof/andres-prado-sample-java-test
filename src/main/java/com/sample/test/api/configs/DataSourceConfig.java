package com.sample.test.api.configs;

import com.sample.test.api.utils.FileReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : DataSourceConfig.java
 * Clase que crea el Bean fileDataSource para el posterior manejo de nuestro fichero
 */
@Configuration
public class DataSourceConfig {

	@Autowired
	private Environment env;

	@Bean
	@Qualifier("fileDatasource")
	public FileReaderUtil dataSource() {
		FileReaderUtil dataSource = new FileReaderUtil();
		dataSource.setPathFile(env.getProperty("file.path"));
		return dataSource;
	}



}
