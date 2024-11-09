package com.muoname.vocabcheck.lessons;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepository lessonRepository;

    public LessonController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping
    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Lesson getLessonById(@PathVariable("id") int lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NoSuchElementException("Lesson id " + lessonId + " not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Lesson create(@RequestBody @Valid Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @PutMapping
    public Lesson update(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable("id") int lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
