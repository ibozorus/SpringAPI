package com.ex.youtubekurs.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.findById(studentId);
        } else {
            throw new IllegalStateException(studentId + "ID not found");
        }
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(studentId + "ID does not exist"));
        String nameToSet = student.getName();
        String emailToSet = student.getEmail();
        if (name != null && name.length() > 0 && !name.equals(student.getName())) {
            nameToSet = name;
        }
        if (email != null && email.length() > 0 && !email.equals(student.getEmail())) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("Email Taken");
            } else {
                emailToSet = email;
            }
        }
        studentRepository.updateStudentById(studentId, nameToSet, emailToSet);
    }
//    @Transactional
//    public void updateStudent(Long studentId, String name, String email) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new IllegalStateException(studentId + "ID does not exist"));
//
//        if (name != null && name.length() > 0 && !name.equals(student.getName())) {
//            student.setName(name);
//        }
//        if (email != null && email.length() > 0 && !email.equals(student.getEmail())) {
//            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
//            if (studentByEmail.isPresent()) {
//                throw new IllegalStateException("Email Taken");
//            } else {
//                student.setEmail(email);}
//        }
//    }
}
