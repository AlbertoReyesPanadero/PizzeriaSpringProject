package com.alberto.users;

import com.alberto.users.persistence.entities.PermissionEntity;
import com.alberto.users.persistence.entities.RoleEntity;
import com.alberto.users.persistence.entities.RoleEnum;
import com.alberto.users.persistence.entities.UserEntity;
import com.alberto.users.persistence.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			RoleEntity adminRole = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissions(Set.of(readPermission, createPermission))
					.build();

			RoleEntity userRole = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissions(Set.of(readPermission))
					.build();

			UserEntity userRoot = UserEntity.builder()
					.username("Root")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.role(adminRole)
					.build();

			UserEntity userPaco = UserEntity.builder()
					.username("Paco")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.role(userRole)
					.build();

			userRepository.saveAll(Set.of(userRoot, userPaco));
		};
	}

}
