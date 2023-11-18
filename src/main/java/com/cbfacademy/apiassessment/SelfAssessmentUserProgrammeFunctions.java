package com.cbfacademy.apiassessment;

import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.LinkedList;


import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component

// this class contains methods for functions for the Self Assessment Programme 

public class SelfAssessmentUserProgrammeFunctions {

    private LinkedList<Question> questions; 
    private LinkedList<SelfAssessmentUserDetails> userDetailsInput;

    private String jsonFilePathQuestions;
    private String jsonFilePathUserDetails;
    private final Gson gson;

        public SelfAssessmentUserProgrammeFunctions() {
        try {
        jsonFilePathQuestions = ResourceUtils.getFile("classpath:selfAssessmentQuestions.json").getAbsolutePath();
        jsonFilePathUserDetails = ResourceUtils.getFile("classpath:selfAssessmentData.json").getAbsolutePath();
        }
        catch (FileNotFoundException e) {
            jsonFilePathQuestions = "selfAssessmentQuestions.json";
            jsonFilePathUserDetails = "selfAssessmentData.json";
        }
        
        gson = new GsonBuilder().setPrettyPrinting().create(); 
        questions = readDataFromFile();
        userDetailsInput = readUserDetailsDataFromFile();

        

        }

        public LinkedList<Question> retrieveSelfAssessmentQuestions() {
            return questions; 
        }

        public LinkedList<SelfAssessmentUserDetails> retrieveSelfAssessment() {
            return userDetailsInput;
        }
    
        public void add(SelfAssessmentUserDetails recoUserDetails) {

        if (!find(recoUserDetails.getUserID())) {
            userDetailsInput.add(recoUserDetails);
            writeDataFromSelfAssessmentToFile();
        }
        else {
            System.out.println("Self Assessment Record already exists");
        }   
        }

        public boolean find(String userID) {
            return userDetailsInput.stream().anyMatch(l -> l.getUserID().equals(userID));
        }
    

        // code below is reading data from the JSON file and returning as a list 
            private LinkedList<Question> readDataFromFile() {
                try(Reader reader = new FileReader(jsonFilePathQuestions)) {
                Type listType = new TypeToken<LinkedList<Question>>() {
                }.getType();
                return gson.fromJson(reader, listType); 
// if file does not exist returns an empty list
            } catch (FileNotFoundException e) {
                return new LinkedList<>(); 
            } catch (IOException e) {
                e.printStackTrace();
                return new LinkedList<>(); 
            }
        }

            private LinkedList<SelfAssessmentUserDetails> readUserDetailsDataFromFile() {
                    try(Reader reader2 = new FileReader(jsonFilePathUserDetails)) {
                Type listType2 = new TypeToken<LinkedList<SelfAssessmentUserDetails>>() {
                }.getType();
                return gson.fromJson(reader2, listType2); 
// if file does not exist returns an empty list
            } catch (FileNotFoundException e) {
                return new LinkedList<>(); 
            } catch (IOException e) {
                e.printStackTrace();
                return new LinkedList<>(); 
            }

            }

              private void writeDataFromSelfAssessmentToFile() {
            try(Writer writer = new FileWriter(jsonFilePathUserDetails)){
            gson.toJson(userDetailsInput, writer); 
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    
        // public void delete (UUID recUserID) {

        //     SelfAssessmentUserDetails recordDelete = null; 

        //     for(SelfAssessmentUserDetails l : listOfSelfAssesementInput) {
        //         if (l.getUserID().equals(recUserID)){
        //             recordDelete = l; 
        //         }
        //     }
        //     if (recordDelete == null) {
        //         System.out.println("Invalid userID , please try again");
        //     } else {
        //         listOfSelfAssesementInput.remove(recordDelete); 
        //         System.out.println("Self Assessment User Record Deleted ");
        //         writeDataFromSelfAssessmentToFile();
        //     }
        // }

        // public SelfAssessmentUserDetails findRecord(UUID userID) {
        //     for (SelfAssessmentUserDetails l : listOfSelfAssesementInput) {
        //         if (l.getUserID().equals(userID)) {
        //             return l; 
        //         }
        //     }
        //     return null; 
        // }

        // public void update (UUID id, Scanner input) {
        //     if (find(id)) {
        //         SelfAssessmentUserDetails userRecord = findRecord(id); 

        //         System.out.print("Do you need physical care and support due to difficulties managing with any of the following; personal care, accessing the community, getting in and out of bed, drink and meal prep? "); 
        //         boolean answer1 = input.nextBoolean(); 

        //         System.out.print("Do you have any difficulties ascending and / or descending the stairs? "); 
        //         boolean answer2 = input.nextBoolean();

        //         System.out.print("Do you have any difficulties accessing your bathing facilities? "); 
        //         boolean answer3 = input.nextBoolean();

        //         System.out.print("Do you have difficulties accessing your property?"); 
        //         boolean answer4 = input.nextBoolean();

        //         System.out.print("What is the your new answer to Question 5?"); 
        //         boolean answer5 = input.nextBoolean();
            
        //         userRecord.setAnswer1(answer1);
        //         userRecord.setAnswer2(answer2);
        //         userRecord.setAnswer3(answer3);
        //         userRecord.setAnswer4(answer4);
        //         userRecord.setAnswer5(answer5);
        //         System.out.println("Self Asssessment User Record Updated Succesfully"); 
        //         writeDataFromSelfAssessmentToFile();
        //     } else {
        //         System.out.println("Self assessment user record not found");
        //     }
        //     }

        // public void display() {
        //     if (listOfSelfAssesementInput.isEmpty()) {
        //         System.out.println("No records found");
        //     }
        //     for (SelfAssessmentUserDetails userRecord : listOfSelfAssesementInput) {
        //         System.out.println(userRecord.toString()); 
        //     }
        // }

        // public void displayAsJsonArray(){
        //     if(SelfAssessmentUserDetails userRecord : listOfSelfAssesementInput) {
        //         JsonObject jsonRecord = new JsonObject();
        //         jsonRecord.addProperty(jsonFilePath, jsonFilePath);
        //     }
        // }

            


          
    
}














    

