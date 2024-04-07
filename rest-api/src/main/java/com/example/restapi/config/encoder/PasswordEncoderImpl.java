package com.example.restapi.config.encoder;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Primary
@Component
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return encodeByMathFormula(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

    private String encodeByMathFormula(String rawPassword) {
        char[] rawPasswordCharArray = rawPassword.toCharArray();
        StringBuilder encodedPassword = new StringBuilder();
        for (int i = 0; i < rawPasswordCharArray.length; i++) {
            double encodedChar = i * Math.sin(rawPasswordCharArray[i]);
            encodedPassword.append(encodedChar);
        }
        return encodedPassword.toString();
    }
}
