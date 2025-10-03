package hien.project.repository;

import hien.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT u FROM Role u WHERE u.name = :name")
	Role getUserByName(@Param("name") String name);

	Optional<Role> findByName(String name);
}