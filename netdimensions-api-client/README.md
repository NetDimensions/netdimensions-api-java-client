# Talent Suite API Client Library for Java—Core

The Talent Suite API Client Library for Java aims to provide straightforward access to the Talent Suite API for Java applications.

Use [`com.netdimensions.client.Client`](https://github.com/rmlowe/netdimensions-api-java-client/blob/master/netdimensions-api-client/src/main/java/com/netdimensions/client/Client.java) to call API functions that expect user authentication, using login credentials or an [OAuth](http://talentsuitedevelopers.com/2014/04/03/oauth/) bearer token.

```java
Client client = new Client("https://www.example.com/ekp/",
                           Credentials.bearer(myToken));
User user = client.send(Request.me());
System.out.println("Hello, " + user.given() + "!");
```

Use [`com.netdimensions.client.SystemClient`](https://github.com/rmlowe/netdimensions-api-java-client/blob/master/netdimensions-api-client/src/main/java/com/netdimensions/client/SystemClient.java) to call API functions that expect system authentication.

```java
SystemClient sc = new SystemClient("https://www.example.com/ekp/",
                                   "ndadmin",
                                   "authKey");
sc.send(SystemRequest.updateUser("joestudent", "newpassword"));
```
