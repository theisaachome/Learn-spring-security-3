
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