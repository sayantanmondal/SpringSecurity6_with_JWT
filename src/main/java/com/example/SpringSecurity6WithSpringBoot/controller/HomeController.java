package com.example.SpringSecurity6WithSpringBoot.controller;

import com.example.SpringSecurity6WithSpringBoot.entities.Employee;
import com.example.SpringSecurity6WithSpringBoot.entities.User;
import com.example.SpringSecurity6WithSpringBoot.repository.UserRepository;
import com.example.SpringSecurity6WithSpringBoot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping
    public String greet(HttpServletRequest request){
        return "Welcome home" + request.getSession().getId();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity<Employee> home(){
          Employee employee = new Employee(1234, "name");
          HttpHeaders headers = new HttpHeaders();
          headers.add("auth","token");
          return ResponseEntity.ok(employee);
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCSRFToken(HttpServletRequest request){

        //response sample:
//        {
//            "headerName": "X-CSRF-TOKEN",
//                "parameterName": "_csrf",
//                "token": "gA_Bu9sgVvVdZ_EV-oRN5cCdUHElBMvGnq69sj6lXE9V5A1Gt2mj37oYYcJwAZQjw6l5g_f_fUgQZ__rrM_e1gidOiti0D4j"
//        }
//        use the headerName as Key and token as value in header tab of postman to request  data.
          //this token is typically session specific. It lives till user logs out. cannot be used by another user. So if this token gets stolen then cross site
        return (CsrfToken)request.getAttribute("_csrf");
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        User user1 = userRepository.findByUsername(user.getUsername());
        userService.verifyUser(user);
        return user1.getUsername();
    }

}
