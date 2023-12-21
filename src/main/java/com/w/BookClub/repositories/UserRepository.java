package com.w.BookClub.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.w.BookClub.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User>findByEmail(String email);
	List<User> findAll();

}
