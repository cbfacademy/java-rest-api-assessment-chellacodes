package com.cbfacademy.apiassessment;

import java.util.LinkedList;
// import java.util.UUID;

// import javax.swing.RepaintManager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequestMapping("/api")

public class SelfAssessmentController {

// end points @GetMapping 
    SelfAssessmentServiceForAPI selfAssessmentService;
    public  SelfAssessmentController(SelfAssessmentServiceForAPI selfAssessmentService) {
        this.selfAssessmentService = selfAssessmentService;
    }

// 

// sends a request to retreieve the json file with the self assessment questions 
        @GetMapping("/selfAssessmentQuestions")
         public ResponseEntity <LinkedList<Question>> retrieveSelfAssessmentQuestions(){
         LinkedList<Question> selfAssessmentQuestions = selfAssessmentService.retrieveQuestions();
         return ResponseEntity.ok(selfAssessmentQuestions);
         }

// sends request to retreive the json file with self assessment data 
        @GetMapping("/selfAssessmentData")
         public ResponseEntity <LinkedList<SelfAssessmentUserDetails>> retrieveSelfAssessmentData(){
         LinkedList<SelfAssessmentUserDetails> selfAssessment = selfAssessmentService.retrieveSelfAssessment();
         return ResponseEntity.ok(selfAssessment);
         }


// // post request to add users details 

        //   @PostMapping("/selfAssessmentData/addUser")
        //     public ResponseEntity<String> addUserDetails(@RequestBody SelfAssessmentUserDetails userDetails) {
        //     selfAssessmentService.createUserSelfAssessment(userDetails);
        //     return ResponseEntity.ok("Self Assessment added successfully");
        //     }

        
        @PostMapping("/selfAssessmentData/addUser")
             public ResponseEntity<Void> createUserDetails(@RequestBody SelfAssessmentUserDetails userDetails) {
                selfAssessmentService.createUserSelfAssessment(userDetails);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        

// // sends request to retreive json file with useful information to answers to the questions 
//     @GetMapping("/selfAssessment")




// // allows user to update there self assessment following having access to the useful information
//     @PutMapping("")
//     public ResponseEntity<Void> updateUserSelfAssessment(@PathVariable UUID id, @RequestBody SelfAssessmentUserDetails updateDetails) {
//         selfAssessmentService.updateUserSelfAssessment(id, updateDetails);
//         return ResponseEntity.ok().build();
//     }


// // allows user to delete self assessment with there details 
//     @DeleteMapping("")

    
}
