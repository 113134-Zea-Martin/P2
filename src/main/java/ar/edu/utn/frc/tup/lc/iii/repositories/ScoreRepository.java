package ar.edu.utn.frc.tup.lc.iii.repositories;

import ar.edu.utn.frc.tup.lc.iii.entities.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {

    Optional<ScoreEntity> findByUserId(Long userId);

    // TODO 3 - Ranking de usuarios: El sistema deberá mostrar un ranking de usuarios ordenado por la cantidad de puntos
    //   acumulados. En caso de empate, el sistema deberá ordenar los jugadores por orden alfabético del apellido primero y
    //   nombre después.

    // 1. Complete the query to get all scores ordered by points in descending order, user last name and user name.
    @Query("SELECT s FROM ScoreEntity s JOIN s.user usuario ORDER BY s.points DESC, usuario.lastName, usuario.name")
    List<ScoreEntity> findAllOrdered();
}
