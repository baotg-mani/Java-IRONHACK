package com.devcamp.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.model.CChapter;
import com.devcamp.repository.IChapterRepository;

@RestController
@CrossOrigin
public class ChapterController {
	@Autowired
	IChapterRepository chapterRepository;

	@GetMapping("/chapter/all")
	public ResponseEntity<Object> getAllChapter() {
		try {
			ArrayList<CChapter> chapters = new ArrayList<CChapter>();
			chapterRepository.findAll().forEach(chapters::add);
			return new ResponseEntity<>(chapters, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/chapter/detail/{id}")
	public ResponseEntity<Object> getChapterById(@PathVariable Long id) {
		try {
			if (chapterRepository.findById(id).isPresent()) {
				return new ResponseEntity<>(chapterRepository.findById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/chapter/create")
	public ResponseEntity<Object> createChapter(@Valid @RequestBody CChapter cChapter) {
		String chapterCode = cChapter.getChapterCode();
		Optional<CChapter> chapterCheck = Optional.ofNullable(chapterRepository.findByChapterCode(chapterCode));
		if (chapterCheck.isPresent()) {
			return new ResponseEntity<>("DUPLICATE_chapterCode", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			try {
				CChapter newRole = new CChapter();
				newRole.setChapterCode(cChapter.getChapterCode());
				newRole.setChapterName(cChapter.getChapterName());
				newRole.setIntroduction(cChapter.getIntroduction());
				newRole.setTranslatorName(cChapter.getTranslatorName());
				newRole.setPage(cChapter.getPage());
				newRole.setLessons(cChapter.getLessons());
				CChapter savedRole = chapterRepository.save(newRole);
				return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
			} catch (Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Create specified Chapter: " + e.getCause().getCause().getMessage());
			}
		}
	}

	@PutMapping("/chapter/update/{id}")
	public ResponseEntity<Object> updateChapterById(@PathVariable Long id, @RequestBody CChapter cChapter) {
		String chapterCode = cChapter.getChapterCode();
		Optional<CChapter> chapterCheck = Optional.ofNullable(chapterRepository.findByChapterCode(chapterCode));
		Optional<CChapter> chapterData = chapterRepository.findById(id);
		if (chapterCheck.isPresent() && chapterCheck.get().getChapterCode() != chapterData.get().getChapterCode()) {
			return new ResponseEntity<>("DUPLICATE_chapterCode", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			if (chapterData.isPresent()) {
				CChapter vChapter = chapterData.get();
				vChapter.setChapterCode(cChapter.getChapterCode());
				vChapter.setChapterName(cChapter.getChapterName());
				vChapter.setIntroduction(cChapter.getIntroduction());
				vChapter.setPage(cChapter.getPage());
				vChapter.setTranslatorName(cChapter.getTranslatorName());
				vChapter.setLessons(cChapter.getLessons());
				try {
					return new ResponseEntity<>(chapterRepository.save(vChapter), HttpStatus.OK);
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

	@DeleteMapping("/chapter/delete/{id}")
	public ResponseEntity<Object> deleteChapterById(@PathVariable Long id) {
		try {
			chapterRepository.deleteById(id);
			return new ResponseEntity<>(chapterRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
