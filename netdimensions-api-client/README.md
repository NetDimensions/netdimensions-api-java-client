# Talent Suite API Client Library for Java — Core

The Talent Suite API Client Library for Java is intended to provide straightforward access to the Talent Suite API for Java applications.

## User authentication

Use [`com.netdimensions.client.Client`](https://github.com/rmlowe/netdimensions-api-java-client/blob/master/netdimensions-api-client/src/main/java/com/netdimensions/client/Client.java) to call API functions that expect user authentication, using login credentials or an [OAuth](http://talentsuitedevelopers.com/2014/04/03/oauth/) bearer token.

```java
Client client = new Client("https://www.example.com/ekp/",
                           Credentials.bearer(myToken));
User me = client.send(Request.me());
System.out.println("Hello, " + me.given() + "!");
```

## System authentication

Use [`com.netdimensions.client.SystemClient`](https://github.com/rmlowe/netdimensions-api-java-client/blob/master/netdimensions-api-client/src/main/java/com/netdimensions/client/SystemClient.java) to call API functions that expect system authentication.

### Creating a user

```java
SystemClient sysClient = new SystemClient("https://www.example.com/ekp/",
                                          "ndadmin",
                                          "authKey");
sysClient.send(SystemRequest.createUser("joestudent",
                                        UserField.password("password"),
                                        UserField.familyName("Student"),
                                        UserField.givenName("Joe")));
```

### Updating an existing user's password and job title

```java
sysClient.send(SystemRequest.updateUser("joestudent",
                                        UserField.password("newpassword"),
                                        UserField.jobTitle("new title")));
```

## Checkout API

Construct a redirect to the [Checkout API](http://talentsuitedevelopers.com/2014/09/03/supporting-custom-storefronts-with-the-checkout-api/) as follows.

```java
String baseUrl = "https://www.example.com/ekp/";
Iterable<String> itemIds = ImmutableList.of("course1/EKP000000200", "course2/EKP000000400");
String onSuccess = "https://store.example.com/success";
String onFailure = "https://store.example.com/cancel";
response.sendRedirect(Redirects.checkoutUrl(baseUrl, itemIds, onSuccess, onFailure));
```
