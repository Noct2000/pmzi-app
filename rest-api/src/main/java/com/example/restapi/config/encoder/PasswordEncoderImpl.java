package com.example.restapi.config.encoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Primary
@Component
public class PasswordEncoderImpl implements PasswordEncoder {
    private static final char START = 'A';
    private static final char FINISH = 'Z';
    @Value("${SHIFT}")
    private int shift;

    @Override
    public String encode(CharSequence rawPassword) {
        return caesarCipher(rawPassword.toString(), shift);
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

    private String caesarCipher(String rawPassword, int shift) {
        char[] rawPasswordCharArray = rawPassword.toCharArray();
        for (int i = 0; i < rawPasswordCharArray.length; i++) {
            if (Character.isAlphabetic(rawPasswordCharArray[i])) {
                if (Character.isUpperCase(rawPasswordCharArray[i])) {
                    rawPasswordCharArray[i] = caesarEncodeChar(rawPasswordCharArray[i], shift);
                } else {
                    char upperCaseLetter = Character.toUpperCase(rawPasswordCharArray[i]);
                    rawPasswordCharArray[i] = Character.toLowerCase(caesarEncodeChar(upperCaseLetter, shift));
                }
            }
        }
        return new String(rawPasswordCharArray);
    }

    private char caesarEncodeChar(char letter, int shift) {
        return (letter + shift) > FINISH
                ? (char) ((letter + shift) % FINISH + START - 1)
                : (char) (letter + shift);
    }
}
