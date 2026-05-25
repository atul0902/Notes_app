package notes_app.notesapp.model;

import jakarta.persistence.Entity; //entity annotation import
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * The Note entity represents the "notes" table in our database.
 * Each instance of this class is a single row in that table.
 */
@Entity  //this java class should be table in my database
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Ensures title cannot be empty
    private String title;

    @Column(length = 1000) // Allows for longer note content
    private String content;
}