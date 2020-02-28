# Spring Boot Webservice

### Getting Started
After you installed `Java 8+` and `Maven 3+`, the fastest way to run **Spring Boot Webservice** without any configuration:
```
mvn spring-boot:run
```
After successfully launched, you can see the sample response by requesting the url below:
```
http://localhost:8080/services/user/john
```
If you need to change the default port other than **8080**, you can start server with additional `server.port=8000`:
```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8000
```

### Running Options
To run User Webservice Application over on **Mysql**:
- Make sure you have a running Mysql instance on `localhost:3306`
- Create an empty schema `spring_boot_webservice` if you haven't already
- Simply navigate to the project folder and execute command below:
```
mvn spring-boot:run -Dspring-boot.run.profiles=prod,import -Dspring-boot.run.arguments=--spring.datasource.username=root,--spring.datasource.password=password
```
Certainly, you should provide your database username instead of `root` and password instead of `password` in the above command.

If you have already data populated in your database you should skip `import` profile:
```
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Alternatively, you can run the application without any external database. 
An **H2 in-memory** database and some amount of test data automatically configured if you just use the `dev` profile:
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Additionaly, the default fallback profile is also `dev` profile so you can easily skip `-Dspring-boot.run.profiles=dev` part and run directly:
```
mvn spring-boot:run
```
### Run Using Docker
To run with docker you can simply execute `./build.sh` file which contains the command below:
```
mvn clean install
docker build --build-arg USER=webservice -t boilerplates/spring-boot-webservice-0.0.1 .
docker run -p 8080:8000 --name spring-boot-webservice -e JAVA_OPTS=-Dserver.port=8000 boilerplates/spring-boot-webservice-0.0.1
```
If you would like to deploy docker hub, do not forget to change `boilerplates/` to your docker user.
### Automated Tests 
To run automated tests for the application you can simply execute the command below:
```
mvn test
```

### Service Endpoints
User items end point:
```
http://localhost:8080/services/user/{username}
```
Http Method: `GET`
<br>
Produces: `application/json`
<br><br>
Sample response:
```json
{
  "username":"john",
  "item":[{
    "name":"item1",
    "game":"game1",
    "expirationDate":"2012-08-12",
    "quantity":3,
    "property":[{
      "name":"name1","value":"value1"
    }]
   }]
}
```
### Design Notes
1. `getUserItems` query in `UserRepository` implemented by join fetch for brevity. Ideally in production environment with large data we should improve 
performance by using Hibernate `@BatchSize` for `@OneToMany` associations and implementing pagination.

2. Under the package `dev.yavuztas.boilerplate.springbootwebservice.view` we create view models in order to expose our data in a controlled way. Besides, we aim
to keep our domain models clean. Certainly, there are alternatives like Jackson `@JsonView` however, we prefer not to spread our conversion logic over 
on domain models.

3. Since we customized error handling as a sample in `ApiErrorHandler` we have a chance to guide our consumers by more meaningful error messages 
which can be important in API development.

4. Even though more tests can be implemented to increase test coverage, we implement integration tests for only the `UserItemService` 
which was considered the most critical part for this project.

### Notes About Security
This web service designed without security. Ideally in production, accessing end points should be restricted in network level (like: DMZ, firewalls, ip restriction, etc.) 
to only authorized requests.