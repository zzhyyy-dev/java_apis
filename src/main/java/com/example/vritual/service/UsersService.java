package com.example.vritual.service;

import com.example.vritual.dto.SchoolClassDTO;
import com.example.vritual.dto.StudentDTO;
import com.example.vritual.dto.UserDTO;
import com.example.vritual.dto.UserReadDTO;
import com.example.vritual.entities.Administrator;
import com.example.vritual.entities.SchoolClass;
import com.example.vritual.entities.Student;
import com.example.vritual.entities.Teacher;
import com.example.vritual.repository.AdministratorRepository;

import com.example.vritual.repository.SchoolClassRepository;
import com.example.vritual.repository.StudentRepository;
import com.example.vritual.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    public UserReadDTO createUser(String userType, UserDTO userDTO) {
        if (userType.equalsIgnoreCase("student")) {
            Student student = new Student();
            student.setName(userDTO.name());
            student.setEmail(userDTO.email());
            student.setPassword(userDTO.password());
            student.setActive(true);
            student.setCreatedAt(LocalDateTime.now());
            student.setUpdatedAt(LocalDateTime.now());

            if (userDTO.classId() != null) {
                SchoolClass schoolClass = schoolClassRepository.findById(userDTO.classId())
                        .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + userDTO.classId()));
                student.setSchoolClass(schoolClass);
            }

            studentRepository.save(student);
            return new UserReadDTO(student.getId(), student.getName(), student.getEmail(), student.isActive());

        } else if (userType.equalsIgnoreCase("teacher")) {
            Teacher teacher = new Teacher();
            teacher.setName(userDTO.name());
            teacher.setEmail(userDTO.email());
            teacher.setPassword(userDTO.password());
            teacher.setActive(true);
            teacher.setCreatedAt(LocalDateTime.now());
            teacher.setUpdatedAt(LocalDateTime.now());
            teacherRepository.save(teacher);

            return new UserReadDTO(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.isActive());

        } else if (userType.equalsIgnoreCase("administrator")) {
            Administrator administrator = new Administrator();
            administrator.setName(userDTO.name());
            administrator.setEmail(userDTO.email());
            administrator.setPassword(userDTO.password());
            administrator.setActive(true);
            administrator.setCreatedAt(LocalDateTime.now());
            administrator.setUpdatedAt(LocalDateTime.now());
            administratorRepository.save(administrator);

            return new UserReadDTO(administrator.getId(), administrator.getName(), administrator.getEmail(), administrator.isActive());

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }


    public UserReadDTO updateUser(String userType, int id, UserDTO userDTO) {
        if (userType.equalsIgnoreCase("student")) {
            Student student = studentRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));

            if (userDTO.email() != null) {
                student.setEmail(userDTO.email());
            }

            if (userDTO.password() != null) {
                student.setPassword(userDTO.password());
            }

            student.setUpdatedAt(LocalDateTime.now());
            studentRepository.save(student);
            return new UserReadDTO(student.getId(), student.getName(), student.getEmail(), student.isActive());

        } else if (userType.equalsIgnoreCase("teacher")) {
            Teacher teacher = teacherRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + id));

            if (userDTO.email() != null) {
                teacher.setEmail(userDTO.email());
            }

            if (userDTO.password() != null) {
                teacher.setPassword(userDTO.password());
            }

            teacher.setUpdatedAt(LocalDateTime.now());
            teacherRepository.save(teacher);
            return new UserReadDTO(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.isActive());

        } else if (userType.equalsIgnoreCase("administrator")) {
            Administrator administrator = administratorRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Administrator not found with ID: " + id));

            if (userDTO.email() != null) {
                administrator.setEmail(userDTO.email());
            }

            if (userDTO.password() != null) {
                administrator.setPassword(userDTO.password());
            }

            administrator.setUpdatedAt(LocalDateTime.now());
            administratorRepository.save(administrator);
            return new UserReadDTO(administrator.getId(), administrator.getName(), administrator.getEmail(), administrator.isActive());

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }


    public UserReadDTO readUser(String userType, int id) {
        if (userType.equalsIgnoreCase("student")) {
            Student student = studentRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found"));

            return new UserReadDTO(student.getId(), student.getName(), student.getEmail(), student.isActive());

        } else if (userType.equalsIgnoreCase("teacher")) {
            Teacher teacher = teacherRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

            return new UserReadDTO(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.isActive());

        } else if (userType.equalsIgnoreCase("administrator")) {
            Administrator administrator = administratorRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Administrator not found"));

            return new UserReadDTO(administrator.getId(), administrator.getName(), administrator.getEmail(), administrator.isActive());

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }


    public List<UserReadDTO> readAllUsers(String userType) {
        if (userType.equalsIgnoreCase("student")) {
            return studentRepository.findAll().stream()
                    .map(student -> new UserReadDTO(student.getId(), student.getName(), student.getEmail(), student.isActive()))
                    .collect(Collectors.toList());

        } else if (userType.equalsIgnoreCase("teacher")) {
            return teacherRepository.findAll().stream()
                    .map(teacher -> new UserReadDTO(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.isActive()))
                    .collect(Collectors.toList());

        } else if (userType.equalsIgnoreCase("administrator")) {
            return administratorRepository.findAll().stream()
                    .map(admin -> new UserReadDTO(admin.getId(), admin.getName(), admin.getEmail(), admin.isActive()))
                    .collect(Collectors.toList());

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }


    public UserReadDTO deleteUser(String userType, int id) {
        if (userType.equalsIgnoreCase("student")) {
            Student student = studentRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found"));

            student.setActive(false);
            student.setUpdatedAt(LocalDateTime.now());
            studentRepository.save(student);

            return new UserReadDTO(student.getId(), student.getName(), student.getEmail(), student.isActive());

        } else if (userType.equalsIgnoreCase("teacher")) {
            Teacher teacher = teacherRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

            teacher.setActive(false);
            teacher.setUpdatedAt(LocalDateTime.now());
            teacherRepository.save(teacher);

            return new UserReadDTO(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.isActive());

        } else if (userType.equalsIgnoreCase("administrator")) {
            Administrator administrator = administratorRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Administrator not found"));

            administrator.setActive(false);
            administrator.setUpdatedAt(LocalDateTime.now());
            administratorRepository.save(administrator);

            return new UserReadDTO(administrator.getId(), administrator.getName(), administrator.getEmail(), administrator.isActive());

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    public UserReadDTO changeStudentClass(Long studentId, Long newClassId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));

        SchoolClass newClass = schoolClassRepository.findById(newClassId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + newClassId));

        student.setSchoolClass(newClass);
        student.setUpdatedAt(LocalDateTime.now());
        studentRepository.save(student);

        return new UserReadDTO(student.getId(), student.getName(), student.getEmail(), student.isActive());
    }

    public Map<String, Object> changeClassTeacher(Long classId, Long newTeacherId) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + classId));

        Teacher newTeacher = teacherRepository.findById(newTeacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + newTeacherId));

        schoolClass.setTeacher(newTeacher);
        schoolClass.setUpdatedAt(LocalDateTime.now());
        schoolClassRepository.save(schoolClass);

        return Map.of(
                "classId", schoolClass.getId(),
                "newTeacherId", newTeacher.getId(),
                "message", "Teacher changed successfully"
        );
    }

    public List<SchoolClassDTO> getTeacherClasses(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + teacherId));

        List<SchoolClass> classes = schoolClassRepository.findByTeacher(teacher);

        return classes.stream()
                .map(schoolClass -> new SchoolClassDTO(
                        schoolClass.getId(),
                        schoolClass.getName(),
                        schoolClass.getDescription()
                ))
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentsByClass(Long classId) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + classId));

        List<Student> students = studentRepository.findBySchoolClass(schoolClass);

        return students.stream()
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.isActive()
                ))
                .collect(Collectors.toList());
    }

}
