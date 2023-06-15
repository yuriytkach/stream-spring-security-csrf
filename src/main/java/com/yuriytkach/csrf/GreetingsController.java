package com.yuriytkach.csrf;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
class GreetingsController {

  private final UserDetailsManager userDetailsManager;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("/")
  public String publicPage() {
    return "public";
  }

  @GetMapping("/private")
  public String privatePage(final Model model, final Authentication authentication) {
    model.addAttribute("name", authentication.getName());
    return "private";
  }

  @PostMapping("/changePassword")
  public String changePassword(final Model model, final Authentication authentication, final String password) {
    log.info("Changing password for {}", authentication.getName());

    // Correct way to change password for currently logged-in user
    // userDetailsManager.changePassword(oldPassword, newPassword);

    final User user = (User) authentication.getPrincipal();
    final UserDetails updatedUser = User.withUsername(user.getUsername())
      .password(passwordEncoder.encode(password))
      .authorities(user.getAuthorities())
      .build();
    userDetailsManager.updateUser(updatedUser);

    model.addAttribute("passwordChanged", true);
    return "private";
  }

}
