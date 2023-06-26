package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "testtext", schema = "public")
@Data
public class TestTextEntity {
    @Id
    @Column(name = "testtextid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testTextId;

    @Column(name = "value")
    private String value;
}
