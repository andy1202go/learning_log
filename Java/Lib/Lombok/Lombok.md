# Lombok

[toc]

### Install

IDEA插件 https://projectlombok.org/setup/intellij

maven引入依赖 https://projectlombok.org/setup/maven

### Annotations

#### @RequiredArgsConstructor

先找到api来看：https://projectlombok.org/api/lombok/RequiredArgsConstructor.html

> Generates a constructor with required arguments.

创建一个带参数的构造器；

具体带哪些参数呢？

> Required arguments are final fields and fields with constraints such as `@NonNull`.

final或者@NonNull标记的；

另外，该注解可以有三个参数

| `AccessLevel`                             | `access`        | Sets the access level of the constructor.                    |
| :---------------------------------------- | --------------- | ------------------------------------------------------------ |
| `RequiredArgsConstructor.AnyAnnotation[]` | `onConstructor` | Any annotations listed here are put on the generated constructor. |
| `java.lang.String`                        | `staticName`    | If set, the generated constructor will be private, and an additional static 'constructor' is generated with the same argument list that wraps the real constructor. |

accessLevel是构造器的accessLevel；

第二个没懂，但是是实验特性，暂时可以不关心

staticName是创建一个静态的构造器，通过ClassName.staticname(params...)来构造了；

另外要参考其他构造器注解（@AllArgsConstructor,@NoArgsConstructor）：https://projectlombok.org/features/constructor

```java
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RequiredAccessLevelTest {
    @NonNull
    private String name;
    private Integer age;
}

@RequiredArgsConstructor(staticName = "of")
public class RequiredStaticTest {
    private final String name;
    private Integer age;
}

@Component
@Slf4j
public class ClassInitTest {

    @PostConstruct
    private void init() {
        RequiredStaticTest rt = RequiredStaticTest.of("shit");
        RequiredAccessLevelTest rat = new RequiredAccessLevelTest("shit");
        log.info("[PostConstruct Test] RequiredConstruct ClassInitTest test done!");
    }
}
```

## 参考文献

1. https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok
2. api https://projectlombok.org/api/index.html
3. 





## TODO List

| 时间 | 内容 |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |



## 总结

| 时间 | 内容 |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |



