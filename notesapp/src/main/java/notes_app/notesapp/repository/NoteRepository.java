package notes_app.notesapp.repository;

import notes_app.notesapp.model.Note; //connecting notes entity with Repostorty
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Repository interface acts as the link between our Java code and the database.
 * By extending JpaRepository, we get all standard CRUD operations for free.
 *
 * it's helps in writing orm for long  query
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> { //it extends Jpa repository and take input for the entity name and id type
    // No need to write any methods!
    // Spring Boot provides save(), findAll(), deleteById(), etc., automatically.

    List<Note> findByTitleContainingIgnoreCase(String title);

}