NetDimensions Talent Suite API Client Library for Java - Sample Webapp
======================================================================

The sample webapp demonstrates how a Java web application can access the Talent Suite API using [OAuth](http://talentsuitedevelopers.com/2014/04/03/oauth/).

You should map `AuthorizationFilter` to any resources that requires API access, and also to the path `/cb` which is used to handle authorization callbacks.

Resources can then call `com.netdimensions.client.servlet.Servlets.client(request)` to obtain a `com.netdimensions.client.Client` instance that you can use to make calls to the API.

For an example, see `src/main/webapp/index.jsp`.

You will need to configure the base URL of your Talent Suite site using a context parameter named `com.netdimensions.client.servlet.TALENT_SUITE_BASE_URL`. The value should be something like `https://www.example.com/ekp/`. On Tomcat you can do this **without** modifying `web.xml` as described [here](http://tomcat.apache.org/tomcat-6.0-doc/config/context.html#Context_Parameters).