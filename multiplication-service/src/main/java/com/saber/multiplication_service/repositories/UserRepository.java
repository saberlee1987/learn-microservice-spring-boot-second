package com.saber.multiplication_service.repositories;

import com.saber.multiplication_service.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDto, Long> {
    Optional<UserDto> findByAlias(String alias);
}
