package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(value="/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntry);
    }

    @GetMapping(value="/time-entries/{l}")
    public ResponseEntity<TimeEntry> read(@PathVariable long l) {
        TimeEntry  timeEntry = timeEntryRepository.find(l);

        if (timeEntry == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(timeEntry);
        }
    }

    @PutMapping(value="/time-entries/{l}")
    public ResponseEntity<TimeEntry> update(@PathVariable long l, @RequestBody TimeEntry timentry) {
        TimeEntry  timeEntry = timeEntryRepository.update(l,timentry);
        if (timeEntry == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(timeEntry);
        }

    }

    @DeleteMapping(value="/time-entries/{l}")
    public ResponseEntity<Void> delete(@PathVariable long l) {
        timeEntryRepository.delete(l);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value="/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(timeEntryRepository.list());

    }
}
