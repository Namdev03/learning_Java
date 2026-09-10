package in.example.crudDemo.service;

import in.example.crudDemo.entity.Student;
import in.example.crudDemo.zrepository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
     public Student createStudent(Student student){
//busisness logic
    //store the data in dab
       Student studentResp = studentRepository.save(student);
      return  studentResp;
    }
    public Student getStudent(Long id){
      Optional<Student> studentResp =  studentRepository.findById(id);

     if(studentResp.isPresent()){
         return studentResp.get();
     }
     else return null;
    }
    public List<Student> GetAllStudents(){
        List<Student> studentResp = studentRepository.findAll();
        return  studentResp;
    }
   public  Student update(Long id,Student student){
        Optional<Student> existingStudent  = studentRepository.findById(id);
       if (existingStudent.isEmpty()) {
           return null;
       }
       Student studentToSave = existingStudent.get();
       studentToSave.setId(id);
       studentToSave.setAge(student.getAge());
       studentToSave.setName(student.getName());
       studentToSave.setSubject(student.getSubject());
    studentRepository.save(studentToSave);
    return studentToSave;
    }
}