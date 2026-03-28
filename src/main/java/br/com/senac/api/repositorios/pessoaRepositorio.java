package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface pessoaRepositorio extends JpaRepository<Pessoas, Long> {
}
