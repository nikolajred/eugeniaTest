package com.nix.eugenia.security.repository;



import com.nix.eugenia.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
