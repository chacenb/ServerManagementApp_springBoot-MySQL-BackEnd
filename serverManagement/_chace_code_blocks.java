/**
 * How to install WildFly on Ubuntu 20.04 && Deploy Java Hello World Application on WildFly
 * ***** yt link : https://youtu.be/ULXt-G_-2ws
 * steps document link : https://docs.google.com/document/d/1nDqrJPErhaF6YJEtKuoauEPTv4Mx6ALN/edit
 * steps document also inside materials folder
 */

/**
 * Formatting http responses
 * ***** link : https://www.baeldung.com/spring-response-entity
 * "ResponseEntity<T>" Generic type that represents the whole HTTP response: status code, headers, and body.
 * We then use it to fully configure HTTP responses
 */

/**
 * Simple Logging Facade for Java (abbreviated SLF4J) : @Slf4j
 * ****** link ： https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjKoPqc-qr-AhVni_0HHbytBNIQFnoECBEQAw&url=https%3A%2F%2Fwww.baeldung.com%2Fslf4j-with-log4j2-logback&usg=AOvVaw3BCRBgd7A90j-TaUT5-SMP
 * acts as a facade for different logging frameworks (e.g., java.util.logging, logback, Log4j).
 * It offers a generic API, making the logging independent of the actual implementation.
 * This allows for different logging frameworks to coexist.
 */

/**
 * CORS config on a Spring application
 * ***** link : https://howtodoinjava.com/spring-boot2/spring-cors-configuration/
 * 1- LOCAL LEVEL : class or method level
 * -- By using "@CrossOrigin" annotation on controller class (@Controller) or on method, it allows controlling CORS configuration on it”.
 * 2- GLOBAL / APPLICATION LEVEL: define the CORS configuration at the “global level”
 * --  (recommended) Use WebMvcConfigurer Bean by Overriding "addCorsMappings(CorsRegistry registry)" on WebMvcConfigurer bean.
 */
