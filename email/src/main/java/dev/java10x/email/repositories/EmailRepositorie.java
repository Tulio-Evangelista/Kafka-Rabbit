package dev.java10x.email.repositories;

import dev.java10x.email.domain.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepositorie extends JpaRepository<EmailModel, UUID> {



}
