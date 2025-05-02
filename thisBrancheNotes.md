# Implementing filtering at the method level

It is classified into two categories:

- *Pre-filtering*: Allows filtering the values of the parameters before calling the method.
- *Post-filtering*: Allows filtering the returned values after the method call.

### Important Note

>This approach can only be applied to collection and array parameters (`@PreFilter`) and to collection and array return values (`@PostFilter`). It cannot be applied to other types of parameters or return values.
>
>Unlike `@PreAuthorize` and `@PostAuthorize`, with `@PreFilter` and `@PostFilter`, the method is called with the data well filtered and returns a value with the data that matches the filters.
>
> Like `@PreAuthorize` and `@PostAuthorize`, for the filtering annotation, we still need to use the `@EnableGlobalMethodSecurity` annotation and enabled.

## Using filtering in Spring Data repositories

We have two approaches for applying filtering in Spring Data repositories.\
Firstly, we can use the `@PreFilter` and `@PostFilter` annotations in the repository interface.\
Secondly, we can embed authorization rules in queries using the `@Query` annotation.

### Important Note

> Using `@PostFilter` on repository methods is not recommended because it can lead to performance issues.