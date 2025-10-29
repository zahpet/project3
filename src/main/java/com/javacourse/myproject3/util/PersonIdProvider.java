package com.javacourse.myproject3.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;


@Component
public class PersonIdProvider {
    private final Set<String> allowed = new HashSet<>();

    @PostConstruct
    public void init() {
        try {
            ClassPathResource res = new ClassPathResource("dataPersonId.txt");
            try (BufferedReader r = new BufferedReader(
                                        new InputStreamReader(res.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = r.readLine()) != null) {
                    if (!line.trim().isEmpty()) allowed.add(line.trim());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to load person ids", e);
        }
    }

    public boolean isAllowed(String personId) {
        return allowed.contains(personId);
    }
}
