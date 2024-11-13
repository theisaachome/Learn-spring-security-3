
# UserDetailService Implementation


## Define Resource 

```java
@RestController
@RequestMapping("/api/resources")
public class HomeController {

    @GetMapping
    public  String getUser(){
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(r-> System.out.println("Role : " + r.getAuthority().toString()));
      return "Hello World";
    }
}

```
```java 
@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        return  new JPAUserDetailsService();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
      return NoOpPasswordEncoder.getInstance();
    }
}
```


## User Entity
```java 
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
}
```

## User Repository
```java 
public interface UserRepos extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
```

## SecurityUser 
```java 
public class SecurityUser implements UserDetails {
    private User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->"read");
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}
}
```

## Custom UserDetails Service 
```java 
public class JPAUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserRepos userRepos;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepos.findByEmail(username);
             User u=user.orElseThrow(()-> new UsernameNotFoundException("Not User Found."));
        return new SecurityUser(u);
    }
}
```