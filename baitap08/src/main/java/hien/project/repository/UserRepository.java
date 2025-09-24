package hien.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hien.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}