package in.example.crudDemo.Controller;

import in.example.crudDemo.entity.Student;
import in.example.crudDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student") //Common end point
public class StudentController {
    //create student

    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
     @PostMapping("/create")
     public ResponseEntity<Student> createStudent(@RequestBody Student student){
          Student createdzStudent =studentService.createStudent(student);
         return ResponseEntity
                 .status(HttpStatus.CREATED).body(createdzStudent);
    }
     //return
    @GetMapping("/get/{id}")
     public ResponseEntity<?> getStudent(@PathVariable Long id){
      Student getStudent = studentService.getStudent(id);
      if (getStudent==null){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Somthing went wrong");
      }
       return  ResponseEntity.status(HttpStatus.OK).body(getStudent);

    };
    //Get all student
    @GetMapping("/getall")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> studentResp = studentService.GetAllStudents();
        if (studentResp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentResp);
    };
    //update
    @PutMapping("/update/{id}")
   public ResponseEntity<Student> updataStudent(@PathVariable long id,@RequestBody Student student){
       Student studentResp = studentService.update(id,student);
       if(studentResp==null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.status(HttpStatus.OK).body(studentResp);
   }
    //delete
}
