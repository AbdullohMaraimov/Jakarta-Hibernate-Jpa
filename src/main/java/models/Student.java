package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name")
    @NotNull(message = "You should enter your name")
    private String fullName;
    @CreationTimestamp
    @Column(name = "created_at", insertable = true)
    private Timestamp createdAt;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "age")
    @Min(value = 5, message = "You should be older than {value}")
    private Integer age;
}
