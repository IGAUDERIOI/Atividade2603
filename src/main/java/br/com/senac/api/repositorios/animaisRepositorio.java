package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Animais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface animaisRepositorio extends JpaRepository<Animais, Long> {
}
