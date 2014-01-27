package net.tajzich.mt.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import java.text.MessageFormat;

public class WebSessionCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static final Logger LOG = LoggerFactory.getLogger(WebSessionCurrentTenantIdentifierResolver.class);

    @Autowired
    private HttpServletRequest request;

    @Override
    public String resolveCurrentTenantIdentifier() {

        String tenantId = request.getHeader("X-TenantId");
        
        LOG.info(MessageFormat.format("Found TenantId=\"{0}\"", tenantId));

        return tenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
    
}

//Enumeration<String> headerNames = request.getHeaderNames();
//while (headerNames.hasMoreElements()) {
//
//	String headerName = headerNames.nextElement();
//
//	Enumeration<String> headers = request.getHeaders(headerName);
//	while (headers.hasMoreElements()) {
//		String headerValue = headers.nextElement();
//        LOG.info(MessageFormat.format("Found TenantId=\"{0}\"", headerValue));
//
//	}
//
//}


//public static void main(String args[]) {    	
//
//    Configuration config = new Configuration();
//
//    Properties properties = new Properties();
//
//    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//    properties.put("hibernate.connection.url", "jdbc:mysql://localhost/mt1"); 
//    properties.put("hibernate.connection.username", "root");
//    properties.put("hibernate.connection.password", "admin");
//    properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//    properties.put("hibernate.show_sql", "true");
//    config.setProperties(properties);
//
//    config.addAnnotatedClass(User.class);
//    config.addAnnotatedClass(TodoItem.class);
//
//    SchemaExport schemaExport = new SchemaExport(config);
//    schemaExport.setDelimiter(";");
//
//    /**Just dump the schema SQLs to the console , but not execute them ***/
//    schemaExport.create(true, false);
//    
// }
