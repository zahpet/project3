package com.javacourse.myproject3.service;

import com.javacourse.myproject3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonIdService {
    private final List<String> availableIds;

    private final UserRepository userRepository;

    public PersonIdService(UserRepository userRepository) throws IOException {
        this.userRepository = userRepository;

        this.availableIds = new ArrayList<>(
                Files.readAllLines(Paths.get("src/main/resources/dataPersonId.txt"))
        );
    }

    public synchronized String generateUniquePersonId() {
        for (String id : availableIds) {
            if (userRepository.findByPersonID(id).isEmpty()) {
                return id;
            }
        }
        throw new RuntimeException("No available PersonID values!");
    }
}
