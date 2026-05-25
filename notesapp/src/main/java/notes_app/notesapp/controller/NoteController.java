package notes_app.notesapp.controller;

import lombok.RequiredArgsConstructor;
import notes_app.notesapp.model.Note;
import notes_app.notesapp.repository.NoteRepository;
import notes_app.notesapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/notes")
public class NoteController {

  //  @Autowired
     //private NoteService noteService;

    private final NoteService noteService;

    @GetMapping
    public List<Note> getallnotes()
    {
        return noteService.getnotes();
    }
    @GetMapping("/search")
    public List<Note> searchNotesByTitle(@RequestParam("title") String title) //picks from URl
    {
        return noteService.searchNotesByTitle(title);
    }

    @PostMapping
    public Note postnotes(@RequestBody Note note)
    {
        return noteService.postnotes(note);

    }

    @PutMapping("/{id}")
    public Note updatenote(@PathVariable Long id ,@RequestBody Note content)
    {
        return noteService.updatenote(id,content);
    }


    @GetMapping("/{id}")
    public Note getnotebyid(@PathVariable Long id)
    {
        return noteService.getnotebyid(id);
    }

    @DeleteMapping("/{id}")
    public String deletenotes(@PathVariable Long id)
    {
        return noteService.deletenotes(id);
    }


}
