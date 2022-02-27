package com.devcamp.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.model.CChapter;
import com.devcamp.model.CLesson;
import com.devcamp.repository.IChapterRepository;
import com.devcamp.repository.ILessonRepository;

@RestController
@CrossOrigin
public class LessonController {
	@Autowired
	IChapterRepository chapterRepository;

	@Autowired
	ILessonRepository lessonRepository;

	@GetMapping("/lesson/all")
	public ResponseEntity<Object> getAllLesson() {
		try {
			ArrayList<CLesson> lessons = new ArrayList<>();
			lessonRepository.findAll().forEach(lessons::add);
			return new ResponseEntity<>(lessons, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/lesson/detail/{id}")
	public ResponseEntity<Object> getLessonById(@PathVariable Long id) {
		try {
			if (lessonRepository.findById(id).isPresent()) {
				return new ResponseEntity<>(lessonRepository.findById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/lesson/create/{chapterId}")
	public ResponseEntity<Object> createLessonByChapterId(@Valid @PathVariable Long chapterId,
			@RequestBody CLesson cLesson) {
		String lessonCode = cLesson.getLessonCode();
		Optional<CLesson> lessonCheck = Optional.ofNullable(lessonRepository.findByLessonCode(lessonCode));
		if (lessonCheck.isPresent()) {
			return new ResponseEntity<>("DUPLICATE_lessonCode", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			if (chapterRepository.findById(chapterId).isPresent()) {
				Optional<CChapter> chapterData = chapterRepository.findById(chapterId);
				CLesson newRole = new CLesson();
				newRole.setLessonCode(cLesson.getLessonCode());
				newRole.setLessonName(cLesson.getLessonName());
				newRole.setIntroduction(cLesson.getIntroduction());
				newRole.setPage(cLesson.getPage());
				newRole.setChapter(chapterData.get());
				try {
					return new ResponseEntity<>(lessonRepository.save(newRole), HttpStatus.CREATED);
				} catch (Exception e) {
					System.out.println(e.getCause().getCause().getMessage());
					return ResponseEntity.unprocessableEntity()
							.body("Failed to Create specified Lesson: " + e.getCause().getCause().getMessage());
				}
			} else {
				return new ResponseEntity<>("chapterId not found in DB!", HttpStatus.NOT_FOUND);
			}
		}
	}

	@PutMapping("/lesson/update/{id}")
	public ResponseEntity<Object> updateLessonById(@PathVariable Long id, @RequestBody CLesson cLesson) {
		String lessonCode = cLesson.getLessonCode();
		Optional<CLesson> lessonCheck = Optional.ofNullable(lessonRepository.findByLessonCode(lessonCode));
		Optional<CLesson> lessonData = lessonRepository.findById(id);
		if (lessonCheck.isPresent() && lessonCheck.get().getLessonCode() != lessonData.get().getLessonCode()) {
			return new ResponseEntity<>("DUPLICATE_lessonCode", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			
			if (lessonData.isPresent()) {
				CLesson vLesson = lessonData.get();
				vLesson.setLessonCode(cLesson.getLessonCode());
				vLesson.setLessonName(cLesson.getLessonName());
				vLesson.setIntroduction(cLesson.getIntroduction());
				vLesson.setPage(cLesson.getPage());
				try {
					return new ResponseEntity<>(lessonRepository.save(vLesson), HttpStatus.OK);
				} catch (Exception e) {
					System.out.println(e.getCause().getCause().getMessage());
					return ResponseEntity.unprocessableEntity()
							.body(e.getCause().getCause().getMessage());
				}
			} else {
				return ResponseEntity.badRequest().body("Failed to get specified Voucher: " + id + "  for update.");
			}
		}
	}

	@DeleteMapping("/lesson/delete/{id}")
	public ResponseEntity<Object> deleteLessonById(@PathVariable Long id) {
		try {
			lessonRepository.deleteById(id);
			return new ResponseEntity<>(lessonRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
