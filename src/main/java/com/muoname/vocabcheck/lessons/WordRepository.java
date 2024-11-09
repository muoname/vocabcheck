package com.muoname.vocabcheck.lessons;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Integer> {
    List<Word> findByLessonId(int lessonId);
}
