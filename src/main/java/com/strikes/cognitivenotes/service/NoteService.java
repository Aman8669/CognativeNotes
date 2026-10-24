package com.strikes.cognitivenotes.service;

import com.strikes.cognitivenotes.model.Note;
import com.strikes.cognitivenotes.model.User;
import com.strikes.cognitivenotes.repository.NoteRepository;
import com.strikes.cognitivenotes.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    // Helper method jo bataye ki abhi kaun sa user logged in hai
    private User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
    }

    // Sirf logged-in user ke notes return karega
    public List<Note> getAllNotesForCurrentUser() {
        User currentUser = getAuthenticatedUser();
        return noteRepository.findByUser(currentUser);
    }

    // Naya note create karte waqt use logged-in user se link karna
    public Note createNote(Note note) {
        try {
            User currentUser = getAuthenticatedUser();
            System.out.println("-> Found Authenticated User: " + currentUser.getUsername());
            
            note.setUser(currentUser);
            return noteRepository.save(note);
        } catch (Exception e) {
            System.out.println("=== ERROR IN createNote ===");
            e.printStackTrace();
            throw new RuntimeException("Failed to create note: " + e.getMessage());
        }
    }

    // Note delete karne se pehle check karna ki kya woh usi user ka hai
    public boolean deleteNote(Long id) {
        User currentUser = getAuthenticatedUser();
        Optional<Note> note = noteRepository.findByIdAndUser(id, currentUser);
        if (note.isPresent()) {
            noteRepository.delete(note.get());
            return true;
        }
        return false;
    }

    // 1. Kisi ek note ko uski ID se lana (Sirf current user ka hona chahiye)
    public Note getNoteById(Long id) {
        User currentUser = getAuthenticatedUser();
        return noteRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Note not found or unauthorized"));
    }

    // 2. Maujuda note ko update karna
    public Note updateNote(Long id, Note noteDetails) {
        User currentUser = getAuthenticatedUser();
        
        // Check karo ki yeh note isi user ka hai ya nahi
        Note existingNote = noteRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Note not found or unauthorized"));

        // Title aur Content update karo
        existingNote.setTitle(noteDetails.getTitle());
        existingNote.setContent(noteDetails.getContent());

        return noteRepository.save(existingNote);
    }
}