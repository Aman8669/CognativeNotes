package com.strikes.cognitivenotes.controller;

import com.strikes.cognitivenotes.model.Note;
import com.strikes.cognitivenotes.service.AiService;
import com.strikes.cognitivenotes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;
    private final AiService aiService;

    // Constructor Injection for both NoteService and AiService
    public NoteController(NoteService noteService, AiService aiService) {
        this.noteService = noteService;
        this.aiService = aiService;
    }

    // GET: Logged-in user ke saare notes dekhne ke liye
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotesForCurrentUser();
        return ResponseEntity.ok(notes);
    }

    // POST: Naya note banane ke liye
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note createdNote = noteService.createNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    // DELETE: Note delete karne ke liye
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id) {
        boolean deleted = noteService.deleteNote(id);
        if (deleted) {
            return ResponseEntity.ok("Note deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found or unauthorized.");
        }
    }

    // GET: Kisi ek note ko ID se dekhne ke liye
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        return ResponseEntity.ok(note);
    }

    // PUT: Note ko update (edit) karne ke liye
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        try {
            Note updatedNote = noteService.updateNote(id, noteDetails);
            return ResponseEntity.ok(updatedNote);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // ✨ AI Endpoints: Note ko simplify karne ke liye
    @PostMapping("/{id}/simplify")
    public ResponseEntity<?> simplifyNote(@PathVariable Long id) {
        try {
            Note note = noteService.getNoteById(id);
            if (note == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Note not found"));
            }

            // AI Service call karke content simplify karna
            String simplifiedContent = aiService.simplifyNote(note.getContent());
            return ResponseEntity.ok(Map.of("simplifiedContent", simplifiedContent));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to process with AI: " + e.getMessage()));
        }
    }
}