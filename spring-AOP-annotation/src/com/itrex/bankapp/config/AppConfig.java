package com.itrex.bankapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Dinesh.Rajput
 *
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.itrex.bankapp"})
public class AppConfig {

}
