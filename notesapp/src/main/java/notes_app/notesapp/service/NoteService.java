package notes_app.notesapp.service;

import lombok.RequiredArgsConstructor;
import notes_app.notesapp.model.Note;
import notes_app.notesapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    //@Autowired
    //private NoteRepository noterepository; //field injection ( not recommended)

    private final NoteRepository noterepository; //constructor injection (@RequiredArgsConstructor)


    public List<Note> getnotes()
    {
        return noterepository.findAll();
    }

    public Note postnotes(Note note)
    {
        return noterepository.save(note);

    }
    public Note updatenote(Long id ,Note content) {
        Note existingnote = noterepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        existingnote.setTitle(content.getTitle());
        existingnote.setContent(content.getContent());
        return noterepository.save(existingnote);
    }
    public Note getnotebyid(Long id)
    {
        return noterepository.findById(id).orElseThrow(() -> new RuntimeException("note not found"));
    }

    public String deletenotes(Long id)
    {
        noterepository.deleteById(id);
        return "note with" + id + "deleted succesfully";
    }
    public List<Note> searchNotesByTitle(String titleKeyword) {
        if (titleKeyword == null || titleKeyword.trim().isEmpty()) {
            return noterepository.findAll(); // If search is empty, return everything
        }
        return noterepository.findByTitleContainingIgnoreCase(titleKeyword);
    }
}
