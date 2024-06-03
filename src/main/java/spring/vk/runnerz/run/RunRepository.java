package spring.vk.runnerz.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                    .filter(run -> run.id() == id)
                    .findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> runToUpdate = findById(id);
        if (runToUpdate.isPresent()) {
            runs.set(runs.indexOf(runToUpdate.get()), run);
        }
    }

    void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(1,
            "First Run",
            LocalDateTime.now(), 
            LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
            3,
            Location.OUTDOOR));
        
            runs.add(new Run(2,
            "Secondd Run",
            LocalDateTime.now(), 
            LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
            5,
            Location.INDOOR));
    }
}
