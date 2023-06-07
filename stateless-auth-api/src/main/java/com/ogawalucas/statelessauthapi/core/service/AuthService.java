package com.ogawalucas.statelessauthapi.core.service;

import com.ogawalucas.statelessauthapi.core.dto.AuthRequest;
import com.ogawalucas.statelessauthapi.core.dto.TokenDto;
import com.ogawalucas.statelessauthapi.core.repository.UserRepository;
import com.ogawalucas.statelessauthapi.infra.execptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public TokenDto login(AuthRequest request) {
        var user = repository.findByUsername(request.username())
            .orElseThrow(() -> new ValidationException("User not found!"));

        validatePassword(request.password(), user.getPassword());

        return new TokenDto(jwtService.createToken(user));
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new ValidationException("The password is incorrect!");
        }
    }

    public TokenDto validateToken(String accessToken) {
        validateExistingToken(accessToken);

        jwtService.validateAccessToken(accessToken);

        return new TokenDto(accessToken);
    }

    private void validateExistingToken(String accessToken) {
        if (isEmpty(accessToken)) {
            throw new ValidationException("The access token must be informed!");
        }
    }
}
