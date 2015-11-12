package io.github.joaomarccos.objectivitycrud.main;

import io.github.joaomarccos.objectivitycrud.dao.DaoStudent;
import io.github.joaomarccos.objectivitycrud.entidades.Student;
import java.util.List;

/**
 *
 * @author joaomarcos
 */
public class Main {

    public static void main(String[] args) {

        DaoStudent dao = new DaoStudent();

        /**
         * Instances of Students
         */
        Student student1 = new Student("João Marcos", "123");
        Student student2 = new Student("Felipe", "124");
        Student student3 = new Student("Manel", "125");
        Student student4 = new Student("Priscila", "126");
        Student student5 = new Student("Rafael", "127");
        Student student6 = new Student("Marcelo", "128");
        Student student7 = new Student("Douglas", "129");

        /**
         * Now is storing the Students
         */
//        dao.save(student1, student1.getMatriculation());
//        dao.save(student2, student2.getMatriculation());
//        dao.save(student3, student3.getMatriculation());
//        dao.save(student4, student4.getMatriculation());
//        dao.save(student5, student5.getMatriculation());
//        dao.save(student6, student6.getMatriculation());
//        dao.save(student7, student7.getMatriculation());
        /**
         * Getting the Student with matriculation equals 125
         */
        System.out.println("Getting the Student with matriculation equals 125");
        System.out.println(dao.find("125"));
        System.out.println("\n");

        Student find = dao.find("125");
        find.setName("Editado");
//        boolean update = dao.update(find);
//        
//        /**
//         * deleting the Student with matriculation equals 123
//         */
        dao.delete("123");
//        /**
//         * List all Students
//         */
        System.out.println("List all Students");
        List<Student> list = dao.list();
        for (Student student : list) {
            System.out.println(student);
        }
//        System.out.println("\n");
    }
}
