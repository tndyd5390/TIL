# jwt를 사용한 로그인 기능 구현

[Spring security 설명](https://github.com/gyoogle/tech-interview-for-developer/blob/master/Web/Spring/Spring%20Security%20-%20Authentication%20and%20Authorization.md)

[jwt 토큰 및 로그인 참고](https://velog.io/@shinmj1207/Spring-Spring-Security-JWT-%EB%A1%9C%EA%B7%B8%EC%9D%B8)

### Spring security 및 jwt 토큰 의존성 추가
```groovy
implementation 'org.springframework.goot:spring-boot-starter-security'
implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.2'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.2'
```
### 로그인에 사용할 사용자 계정
<details>
<summary>Memeber.java</summary>
<br>
```java
@Entity
@Getter
@Where(clause = "deleted = false")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean deleted;
    
    ...//생성자 및 각종 함수들
    ...
    ...
}
```
</details>


위와 같은 엔티티를 사용하여 기본적인 crud가 있다고 가정


### Spring Security Config 설정

- SecurityConfig.java
```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic().disable()
            .formLogin().disable()
            .cors().disable()
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BcryptPasswordEncoder();}
}
```

### JwtTokenProvider 설정


