# jwt를 사용한 로그인 기능 구현

[Spring security 설명](https://github.com/gyoogle/tech-interview-for-developer/blob/master/Web/Spring/Spring%20Security%20-%20Authentication%20and%20Authorization.md)

[jwt 토큰 및 로그인 참고](https://velog.io/@shinmj1207/Spring-Spring-Security-JWT-%EB%A1%9C%EA%B7%B8%EC%9D%B8)

### Spring security 및 jwt 토큰 의존성 추가

<details>
<summary>build.gradle</summary>

<p>

```groovy
implementation 'org.springframework.goot:spring-boot-starter-security'
implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.2'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.2'
```

</p>

</details>

### 로그인에 사용할 사용자 계정

<details>
<summary>Member.java</summary>
<p>

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

</p>
</details>



위와 같은 엔티티를 사용하여 기본적인 crud가 있다고 가정


### Spring Security Config 설정
<details>
<summary> SecurityConfig.java</summary>
<br/>
<p>

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

</p>

</details>

### JwtTokenProvider 구현
<details>
<summary>JwtTokenProvider.java</summary>

<p>

```java
@Component
public class JwtTokenProvider{
    private String secretKey = "...key...";
    private long validityInMilliseconds = 360000;

    public String createToken(String payload){
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();
        Date validity = new Date(new.getTime() + validityInMilliseconds);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(getSecretKey())
            .compact();
    }

    public String getPayload(String token){
        return Jwts.parserBuilder)
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch(JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
```


</p>
</details>

### token 요청, 응답 객체 구현
<details>
<summary>TokenReqDto.java</summary>

<p>

```java
@Getter
public class TokenReqDto{
    private String email;
    private String password;

    protected TokenReqDto(){}

    private TokenReqDto(final String email, final String password){
        this.email = email;
        this.password = password
    }

    public static TokenReqDto of(final String email, final String password){
        return new TokenReqDto(email, password);
    }
}
```

</p>

</details>

<details>
<summary>TokenResDto.java</summary>

<p>

```java
@Getter
public class TokenResDto{
    private String accessToken;

    protected TokenResDto(){}

    private TokenResDto(final String accessToken){
        this.accessToken = accessToken;
    }

    public static TokenResDto from(final String accessToken){
        return new TokenResDto(accessToken);
    }
}
```

</p>

</details>

### 로그인 객체 구현
<details>
<summary>LoginMember.java</summary>

<p>

```java
@Getter
public class LoginMember{
    private Long id;
    private String name;
    private String email;

    ...//생성자, 정적 팩토리 메소드

    public boolean isGuest(){return false;}

    public static class Guest extends LoginMember{
        @Override
        public boolean isGuest(){return true;}
    }

    ...//equals, hashcode
}
```

</p>

</details>


### AuthService 구현

<details>
<summary>AuthService.java</summary>

<p>

```java
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService{
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    public TokenResDto login(TokenReqDto tokenReqDto){
        Member member = findByEmail(tokenReqDto.getEmail());
        //비밀번호 확인
        member.validLoginMember(passwordEncoder, tokenReqDto.getPassword());

        String token = jwtTokenProvider.createToken(member.getEmail());
    }

    public LoginMember findMemberByToken(String accessToken){
        if(!jwtTokenProvider.validateToken(accessToken)){
            throw new AuthException();
        }

        String email = jwtTokenProvider.getPayload(accessToken);
        Member member = findByEmail(email);

        return LoginMember.of(member.getId(), member.getName(), member.getEmail());
    }

    private Member findByEmail(String email){
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> throw new AuthException(ErrorCode.NOT_FOUND_MEMBER_ERROR));
    }

}
```

</p>

</details>

### AuthenticationPrincipal 어노테이션 구현(Session에서 데이터를 쉽게 가져오기 위해)

<details>

<summary>AuthorizationExtractor.java</summary>

<p>

```java
public class AuthorizationExtractor {

	public static final String AUTHORIZATION = "Authorization";
	public static String BEARER_TYPE = "Bearer";
	public static final String ACCESS_TOKEN_TYPE =
		AuthorizationExtractor.class.getSimpleName() + ".ACCESS_TOKEN_TYPE";

	public static String extract(HttpServletRequest httpServletRequest) {
		Enumeration<String> headers = httpServletRequest.getHeaders(AUTHORIZATION);
		while (headers.hasMoreElements()) {
			String value = headers.nextElement();
			if ((value.toLowerCase().startsWith(BEARER_TYPE.toLowerCase()))) {
				String authHeaderValue = value.substring(BEARER_TYPE.length()).trim();
				httpServletRequest.setAttribute(ACCESS_TOKEN_TYPE,
					value.substring(0, BEARER_TYPE.length()).trim());
				int commaIndex = authHeaderValue.indexOf(',');
				if (commaIndex > 0) {
					authHeaderValue = authHeaderValue.substring(0, commaIndex);
				}
				return authHeaderValue;
			}
		}

		return null;

	}
}
```

</p>

</details>

<details>
<summary>AuthenticationPrincipal.java</summary>

<p>

```java
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticationPrincipal {

}
```

</p>

</details>


<details>
<summary>AuthenticationPrincipalConfig.java</summary>

<p>

```java
@Configuration
public class AuthenticationPrincipalConfig implements WebMvcConfigurer{
    private final AuthService authService;

    public AuthenticationPrincipalConfig(@Lazy final AuthService authService){
        this.authService = authService;
    }

    @Override
    public void addArgumentResolvers(List resolvers){
        resolvers.add(createAuthenticationPrincipalArgumentResolver());
    }

    @Bean
    public AuthenticationPrincipalArgumentResolver createAuthenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver(autnService);
    }
}
```

</p>

</details>

<details>
<summary>AuthenticationPrincipalArgumentResolver.java</summary>

<p>

```java
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

	private final AuthService authService;

	public AuthenticationPrincipalArgumentResolver(final AuthService authService) {
		this.authService = authService;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
	}

	@Override
	public LoginMember resolveArgument(MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		final String accessToken = AuthorizationExtractor.extract(webRequest.getNativeRequest(
			HttpServletRequest.class));

		if (accessToken == null) {
			return new LoginMember.Guest();
		}

		final LoginMember loginMember = authService.findMemberByToken(accessToken);

		final RoleType permitRole = parameter.getParameterAnnotation(AuthenticationPrincipal.class)
			.role();

		if (!loginMember.hasRole(permitRole)) {
			throw new AuthException(ErrorCode.UNAUTHORIZED_ERROR);
		}

		return loginMember;
	}
}
```

</p>

</details>


### AuthController 구현

<details>
<summary>AuthController.java</summary>

<p>

```java
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticationPrincipal {

}
```

</p>

</details>
