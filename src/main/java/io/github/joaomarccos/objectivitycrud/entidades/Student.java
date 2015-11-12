package io.github.joaomarccos.objectivitycrud.entidades;

import com.objy.db.app.ooObj;
import java.util.Objects;

/**
 *
 * @author joaomarcos
 */
public class Student extends ooObj{

    private String name;
    private String matriculation;

    public Student() {
    }

    public Student(String name, String matriculation) {
        this();
        this.name = name;
        this.matriculation = matriculation;
    }

    public String getName() {
        fetch();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatriculation() {
        fetch();
        return matriculation;
    }

    public void setMatriculation(String matriculation) {
        this.matriculation = matriculation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.matriculation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        return Objects.equals(this.matriculation, other.matriculation);
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", matriculation=" + matriculation + '}';
    }

}
