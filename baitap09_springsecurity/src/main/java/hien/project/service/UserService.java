package hien.project.service;

import hien.project.entity.Role;
import hien.project.entity.Users;
import hien.project.model.SignUpDto;
import hien.project.repository.RoleRepository;
import hien.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void saveUser(SignUpDto signUpDto) {
		Users user = new Users();
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

		Role role = roleRepository.findByName("ROLE_USER").orElse(null);
		if (role == null) {
			role = createRole("ROLE_USER");
		}
		user.setRoles(new HashSet<>(Arrays.asList(role)));

		userRepository.save(user);
	}

	private Role createRole(String name) {
		Role role = new Role();
		role.setName(name);
		return roleRepository.save(role);
	}

	// Thêm admin thủ công nếu cần (chạy 1 lần)
	public void initRolesAndUser() {
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);

		Users admin = new Users();
		admin.setUsername("admin");
		admin.setEmail("admin@email.com");
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setRoles(new HashSet<>(Arrays.asList(adminRole)));
		userRepository.save(admin);
	}
}