# Implementing authorization at the method level

First, enable method security by using the `@EnableMethodSecurity` annotation on a configuration class.
```java
@Configuration
@EnableMethodSecurity // this enables @PreAuthorize, @PostAuthorize by default
class SafeTyMethodConfig {
}
```
### Important note:

> *Enable method security will automatically enable a spring aspect.*

## How achieve authorization at the method level?
Authorization at the method level is achieved by using :
- The pre- / post-authorization annotations(`@PreAuthorize`, `@PostAuthorize`) enable by default. These annotations can be used to any method in an application. Controller, service, repository, proxies, etc.
- The JSR-250 annotations(`@RolesAllowed`, `@PermitAll`, `@DenyAll`) are disabled by default.
- The `@Secured` annotation is disabled by default.

### Precision of the annotations
`@PreAuthorize` is called before the method is executed, and `@PostAuthorize` is called after the method is executed.\
`@PostAuthorize`, the annotation receives the SpEL as value, defining an authorization rule.\
**We cas use both annotations in the same method if necessary.**

## Implementing permission for methods
Avoid using long SpEL expressions. Use the concept of permission provided by spring security.