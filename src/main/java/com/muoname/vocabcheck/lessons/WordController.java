package com.muoname.vocabcheck.lessons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/words")
public class WordController {

    private final WordsClient wordsClient;
    private final WordRepository wordRepository;
    private final LessonRepository lessonRepository;

    public WordController(WordsClient wordsClient, WordRepository wordRepository, LessonRepository lessonRepository) {
        this.wordsClient = wordsClient;
        this.wordRepository = wordRepository;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping
    public List<Word> getWordsByLessonId(@RequestParam("lessonId") int lessonId) {
        return wordRepository.findByLessonId(lessonId);
    }

    @GetMapping(path = "/{id}")
    public Word getWordById(@PathVariable("id") int wordId) {
        return wordRepository.findById(wordId)
                .orElseThrow(() -> new NoSuchElementException("Word not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Word create(@RequestParam("lessonId") int lessonId, @RequestBody Word word) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NoSuchElementException("Lesson not found with ID: " + lessonId));
        word.setLesson(lesson);
        return wordRepository.save(word);
    }

    @PutMapping
    public Word update(@RequestBody Word word) {
        return wordRepository.save(word);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable("id") int id){
        wordRepository.deleteById(id);
    }


}
