package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries/")
public class TimeEntryController {

    @Autowired
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController() {
    }

    public TimeEntryController( TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);

        return new ResponseEntity(timeEntry,(timeEntry==null)?HttpStatus.NOT_FOUND:HttpStatus.OK);
    }

   @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList= timeEntryRepository.list();
        return new ResponseEntity(timeEntryList, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, expected);
        return new ResponseEntity(timeEntry, (timeEntry==null)?HttpStatus.NOT_FOUND:HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(timeEntry, (timeEntry==null)?HttpStatus.NO_CONTENT:HttpStatus.OK);
    }
}
