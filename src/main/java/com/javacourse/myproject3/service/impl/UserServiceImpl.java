package com.javacourse.myproject3.service.impl;

import com.javacourse.myproject3.dto.*;
import com.javacourse.myproject3.exception.ApiException;
import com.javacourse.myproject3.exception.NotFoundException;
import com.javacourse.myproject3.model.UserEntity;
import com.javacourse.myproject3.repository.UserRepository;
import com.javacourse.myproject3.service.UserService;
import com.javacourse.myproject3.util.PersonIdProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PersonIdProvider personIdProvider;

    public UserServiceImpl(UserRepository repository, PersonIdProvider personIdProvider) {
        this.repository = repository;
        this.personIdProvider = personIdProvider;
    }

    @Override
    public UserSummaryResponse create(CreateUserRequest req) throws IOException {
        if (req.getName() == null || req.getName().trim().isEmpty()) throw new ApiException("Name is required");
        if (req.getPersonID() == null || req.getPersonID().trim().isEmpty())
            throw new ApiException("PersonID is required");

        String pid = req.getPersonID().trim();
        if (!personIdProvider.isAllowed(pid)) {
            throw new ApiException("PersonID " + pid + " is not in allowed list");
        }

        if (repository.existsByPersonID(pid)) {
            throw new ApiException("PersonID " + pid + " is already assigned");
        }

        UserEntity e = new UserEntity();
        e.setName(req.getName());
        e.setSurname(req.getSurname());
        //PersonIdService personIdProvider = new PersonIdService(repository);
        //e.setPersonID(personIdProvider.generateUniquePersonId());
        e.setPersonID(pid);
        e.setUuid(UUID.randomUUID().toString());

        repository.save(e);
        return new UserSummaryResponse(e.getId(), e.getName(), e.getSurname());
    }

    @Override
    public UserDetailResponse getById(Long id, boolean detail) {
        UserEntity e = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        if (detail) return new UserDetailResponse(e.getId(), e.getName(), e.getSurname(), e.getPersonID(), e.getUuid());
        return new UserDetailResponse(e.getId(), e.getName(), e.getSurname(), null, null);
    }

    @Override
    public List<UserResponse> getAll(boolean detail) {
        List<UserEntity> all = repository.findAll();
        if (detail) {
            return all.stream().map(e -> new UserDetailResponse(
                    e.getId(), e.getName(), e.getSurname(), e.getPersonID(), e.getUuid())).collect(Collectors.toList());
        }
        return all.stream().map(e -> new UserSummaryResponse(
                e.getId(), e.getName(), e.getSurname())).collect(Collectors.toList());
    }

    @Override
    public UserSummaryResponse update(UpdateUserRequest req) {
        Long id = req.getId();
        String name = req.getName();
        String surname = req.getSurname();
        if (id == null) throw new ApiException("ID is required");
        UserEntity e = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        if (name != null && !name.trim().isEmpty()) e.setName(name);
        if (surname != null) e.setSurname(surname);
        repository.save(e);
        return new UserSummaryResponse(e.getId(), e.getName(), e.getSurname());
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("User not found");
        repository.deleteById(id);
    }


}
