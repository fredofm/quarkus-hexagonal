# About

Maven Multi-module project to illustrate how to structure project based on a hexagonal architecture.

## Technologies

| Technology | Purpose |
| ---------- |----------|
|Hexagonal architecture| The current code is very simple but I tried to follow an hexagonal clean architecture. You can take a look into the project modules to see how the code was divided into application, domain and infrastructure layers. You can also take a look into the interfaces I'm using as "ports" to avoid layer couplings. I think that it could be interesting to understand the difference between the `Prop` entity at domain level (I will try to complete this example with some DDD concepts), and the `PropEntity` at the infrastructure layer. |
| [Hibernate panache](https://quarkus.io/guides/hibernate-orm-panache) | Database access |
| [Lombok](https://projectlombok.org/) | Helper to create builders, setters, getters, etc|
| [MapStruct](https://mapstruct.org/) | Helper to create mappers to pass objects between the different layers |
| [Resteasy](https://quarkus.io/guides/rest-json) | To create the rest API resources |
| [rest-assured](https://rest-assured.io/) | To test the rest API endpoints |
| [smallrye-openapi](https://github.com/smallrye/smallrye-open-api) | To generate our API documentation based on the OpenAPI standard. It's providing a SwaggerUI on dev as well |
| [micrometer-registry-prometheus](https://github.com/micrometer-metrics/micrometer) | To create metrics endpoints that will be consumed by other tools like Prometheus |
| [Flyway](https://flywaydb.org/) | To load database at start |
