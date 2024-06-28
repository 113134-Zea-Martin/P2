package ar.edu.utn.frc.tup.lc.iii.repositories;

import ar.edu.utn.frc.tup.lc.iii.entities.ScoreDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreDetailRepository extends JpaRepository<ScoreDetailEntity, Long> {

    Optional<ScoreDetailEntity> findByScoreIdAndPredictionGameId(Long scoreId, Long predictionGameId);
}
