package spring_boot_google_oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/me")
    public String me(Principal principal) {
        return "Hello " + principal.getName() + ". This is protected resource.";
    }

    @GetMapping("/my-authorities")
    public Collection<GrantedAuthority> getAuthorities() {
        return getAuthentication().getAuthorities();
    }

    @GetMapping("/my-full-info")
    public OAuth2Authentication getMyFullInfo() {
        return getAuthentication();
    }

    private OAuth2Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (OAuth2Authentication) auth;
    }

}
