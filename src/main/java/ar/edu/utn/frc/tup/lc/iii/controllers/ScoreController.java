package ar.edu.utn.frc.tup.lc.iii.controllers;

import ar.edu.utn.frc.tup.lc.iii.dtos.ScoreDto;
import ar.edu.utn.frc.tup.lc.iii.models.Score;
import ar.edu.utn.frc.tup.lc.iii.services.ScoreService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method to get all scores ordered by points.
     *
     * @return all scores.
     */
    @GetMapping("")
    public ResponseEntity<List<ScoreDto>> getScores() {
        List<Score> scores = scoreService.getScores();
        return ResponseEntity.ok(modelMapper.map(scores, new TypeToken<List<ScoreDto>>() {}.getType()));
    }
}
