package rs.ac.singidunum.itws.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.itws.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
}
