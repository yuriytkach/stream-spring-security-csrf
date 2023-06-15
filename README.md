# Spring Security with CSRF Demonstration

Demo application for demonstrating Spring Security with CSRF protection for online YouTube
stream that is accessible [here 📹](https://youtube.com/live/yc-OzRwl780) in Ukrainian language.

## How to run
Start the application with Gradle:
```shell
./gradlew bootRun
```

## How to use
Open the application in your browser at [http://localhost:8080](http://localhost:8080).

Use the following credentials to log in:
* Username: `user`
* Password: `password`

Try to perform a CSRF attack by opening the following file [src/test/resources/malicious.html](src/test/resources/malicious.html)
and clicking on the button. You will see that the attack is not successful if CSRF protection is enabled.

If protection is disabled, you will see that the attack is successful and user password will be changed to `777`.

### Reference Documentation
For further reference, please consider the following sections:

* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web.security)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web.servlet.spring-mvc.template-engines)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
