package com.portada.portadaapp.auth;

import com.portada.portadaapp.config.JwtService;
import com.portada.portadaapp.user.Role;
import com.portada.portadaapp.user.User;
import com.portada.portadaapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  //private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(final RegisterRequest request) {
    final User user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();

    final User savedUser = repository.save(user);

    final String jwtToken = jwtService.generateToken(user);

    // saveUserToken(savedUser, jwtToken);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(final AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())); // the authentification it ok !

    final User user = repository.findByEmail(request.getEmail()).orElseThrow();

    final String jwtToken = jwtService.generateToken(user);

    //revokeAllUserTokens(user);
    // saveUserToken(user, jwtToken);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  /*private void saveUserToken(final User user, final String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }*/
}