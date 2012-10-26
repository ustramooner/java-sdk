---

layout: doc
title: Database.com Spring Security Integration

---
# Database.com Spring Security Integration

[Spring Security][ss] provides a comprehensive authentication and authorization solution for J2EE-based applications.

The Database.com Spring Security integration simplifies usage of the OAuth Connector with the Spring Security framework. You can take advantage of this if your application uses Spring Security.

The simplest way to configure a Spring application is to include the fss namespace in your `spring-configuration.xml` file.

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:security="http://www.springframework.org/schema/security"
	       xmlns:fss="http://www.salesforce.com/schema/springsecurity"
	       xsi:schemaLocation="
		   http://www.springframework.org/schema/beans
		   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
		   http://www.springframework.org/schema/security
		   http://www.springframework.org/schema/security/spring-security-3.0.xsd
		   http://www.salesforce.com/schema/springsecurity
		   http://media.developerforce.com/schema/force-springsecurity-1.1.xsd">

	    <!-- Database.com OAuth security config -->
	    <fss:oauth logout-from-sfdc="true" />
 		 <fss:connectionUrl url="URL or a ${Java system property} or ${environment variable}" />
	    </fss:oauth>

	    <!-- Include this bean, if connection URL is in Java system property or environment variable. -->
	    <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer" />

	    <!-- Configure Spring Security -->
	    <security:http>
		<security:anonymous />
	    </security:http>
	   
	</beans>

The main customizations of interest are:

- Include the namespace: xmlns:fss="http://www.salesforce.com/schema/springsecurity"
- Specify the schema location for the force-springsecurity XSD
- Add the \<fss:oauth /> tag
- Add the \<security:http /> tag. For more information about this tag, see [Spring Security documentation][ss].

The \<oauth /> tag requires that you provide OAuth properties using the `connectionUrl` tag. For example:

	<fss:oauth>
        <fss:connectionUrl url="force://login.salesforce.com?oauth_key=sampleKey&amp;oauth_secret=sampleSecret" />
    </fss:oauth>

It is preferable to configure the connection URL as a system property or environment variable rather than setting the URL directly in the `connectionUrl` tag. 
We don't recommend specifying the URL directly in your configuration because you should not check your oauth key and secret into version control. These may vary from one environment to another and should be protected.

Instead, set the connection URL as a system property or environment variable and enable the configuration by including the following tag in your `spring-configuration.xml` file:

    <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer" />

Set the following in your `spring-configuration.xml` file:

    <!-- Uses the connection URL in the CONNECTION_URL environment variable or Java system property -->
    <fss:oauth>
        <fss:connectionUrl url="${CONNECTION_URL}" />
    </fss:oauth>

The `PropertyPlaceholderConfigurer` looks for a `CONNECTION_URL` Java system property. If that's not found, it looks for a `CONNECTION_URL` environment variable.

The following attributes are optional for &lt;fss:oauth> in `spring-configuration.xml`:

<table border="1">
<tr>
    <th>Attribute</th>
    <th>Description</th>
</tr>
<tr>
    <td>default-login-success</td>
    <td>A user is redirected to this URL after a successful OAuth logout. The default value is <codeph>/spring/logoutSuccess</codeph></td>
</tr>    
<tr>
    <td>default-logout-success</td>
    <td>A user is redirected to this URL after a successful OAuth logout. The default value is <codeph>/spring/logoutSuccess</codeph></td>
</tr>    
<tr>
    <td>login-url</td>
    <td>Navigation to this URL initiates a login sequence. The default value is <codeph>/spring/login</codeph></td>
</tr>    
<tr>
    <td>logout-url</td>
    <td>Navigation to this URL initiates a logout sequence. The default is <codeph>/spring/logout</codeph></td>
</tr>    
<tr>
    <td>logout-from-sfdc</td>
    <td>This attribute controls whether a logout from the OAuth application also logs the user out of Database.com. This logout redirects the user to the Database.com logout page so when it is set to <codeph>true</codeph>, the <codeph>default-logout-success</codeph> URL is ignored. The default value is <codeph>false</codeph>.</td>
</tr>    
<tr>
    <td>store-data-in-session</td>
    <td>Flag that sets whether data about the authenticated user is stored in a server side session or an encrypted browser cookie. The default is <codeph>false</codeph> (cookies are used). Sessions should only be used if sticky load balancing is available or if the application runs with a single instance.</td>
</tr>    
<tr>
    <td>store-user-name</td>
    <td>Flag that sets whether or not the username is stored in the user data. This enables you to avoid storing usernames in browser cookies, but it can be used to prevent storing the username in sessions too. The default value is <codeph>true</codeph>.</td>
</tr>    
<tr>
    <td>secure-key-file</td>
    <td>The name of a secure key file, which must be on the classpath. AES encryption is used to encrypt the data about the authenticated user when it's stored in a browser cookie. This is only used if browser cookie storage is on. If cookies are used and no file is specified, a key is automatically generated. However, this should only be done for development purposes because it will be problematic in a multi-instance deployment since each instance will generate a different key. The key is base-64 encoded. This is to be used instead of the secure-key attribute.</td>
</tr>    
<tr>
    <td>secure-key</td>
    <td>The secure key. AES encryption is used to encrypt the data about the authenticated user when it's stored in a browser cookie. This is only used if browser cookie storage is on. If cookies are used and no file is specified, a key is automatically generated. However, this should only be done for development purposes because it will be problematic in a multi-instance deployment since each instance will generate a different key. The key is base-64 encoded. This is to be used instead of the secure-key-file attribute.</td>
</tr> 
</table>

The following is a sample file for the secure-key-file attribute. Replace *yourKeyGoesHere* with a secure key. For more information on AES, see [Using AES with Java Technology](http://java.sun.com/developer/technicalArticles/Security/AES/AES_v1.html).

    # A valid key in base-64 encoding.
    private-key=yourKeyGoesHere


[ss]: http://static.springsource.org/spring-security/site/docs/3.0.x/reference/springsecurity.html
