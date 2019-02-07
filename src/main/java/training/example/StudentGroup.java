package training.example;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import training.Student;

@Entity
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany
    private List<Student> students;

    public String getName() {
        return name;
    }

    public StudentGroup setName(String name) {
        this.name = name;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public StudentGroup setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public long getId() {
        return id;
    }

    public StudentGroup setId(long id) {
        this.id = id;
        return this;
    }
}
