package br.com.fiap.ong;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ONGRepository extends JpaRepository<ONG, Long> {
}