package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "group_name")
    @NotBlank(message = "You should write group name")
    private String name;
    @CreationTimestamp
    @Column(name = "created_at", insertable = true)
    private Timestamp createdAt;
    @OneToMany(mappedBy = "groupId", cascade = CascadeType.ALL)
    private List<Student> studentList;

}
