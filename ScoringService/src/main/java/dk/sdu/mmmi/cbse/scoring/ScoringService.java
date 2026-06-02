package dk.sdu.mmmi.cbse.scoring;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ScoringService {

    private Long totalScore = 0L;

    public static void main(String[] args) {
        SpringApplication.run(ScoringService.class, args);
    }

    @GetMapping("/score")
    public Long totalScore() {
        return totalScore ;
    }

    @PostMapping("/score")
    public Long addPoints(@RequestParam(value = "point") Long point) {
        totalScore += point;
        return totalScore ;
    }
}