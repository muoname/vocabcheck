package com.muoname.vocabcheck.lessons;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface WordsClient {

    @GetExchange("/lessons/{id}")
    Lesson getLessonById(@PathVariable("id") int lessonId);
}
