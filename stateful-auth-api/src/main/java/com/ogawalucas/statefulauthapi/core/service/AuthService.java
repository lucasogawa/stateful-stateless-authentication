package com.ogawalucas.statefulauthapi.core.service;

import com.ogawalucas.statefulauthapi.core.dto.AuthRequest;
import com.ogawalucas.statefulauthapi.core.dto.AuthUserResponse;
import com.ogawalucas.statefulauthapi.core.dto.TokenDto;
import com.ogawalucas.statefulauthapi.core.repository.UserRepository;
import com.ogawalucas.statefulauthapi.infra.execptions.AuthenticationException;
import com.ogawalucas.statefulauthapi.infra.execptions.ValidationException;
import com.ogawalucas.statefulauthapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public TokenDto login(AuthRequest request) {
        var user = findByUsername(request.username());

        validatePassword(request.password(), user.getPassword());

        var accessToken = tokenService.createToken(user.getUsername());

        return new TokenDto(accessToken);
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new ValidationException("The password is incorrect!");
        }
    }

    public TokenDto validateToken(String token) {
        validateExistingToken(token);

        var valid = tokenService.validateAccessToken(token);

        if (valid) {
            return new TokenDto(token);
        }

        throw new AuthenticationException("Invalid token!");
    }

    private void validateExistingToken(String token) {
        if (isEmpty(token)) {
            throw new ValidationException("The access token must be informed!");
        }
    }

    public AuthUserResponse getAuthenticatedUser(String token) {
        var tokenData = tokenService.getTokenData(token);
        var user = findByUsername(tokenData.username());

        return new AuthUserResponse(user.getId(), user.getUsername());
    }

    public void logout(String token) {
        tokenService.deleteRedisToken(token);
    }

    private User findByUsername(String username) {
        return repository.findByUsername(username)
            .orElseThrow(() -> new ValidationException("User not found!"));
    }
}
