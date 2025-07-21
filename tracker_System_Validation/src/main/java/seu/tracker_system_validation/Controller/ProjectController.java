package seu.tracker_system_validation.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.tracker_system_validation.Model.ProjectModel;

import java.util.ArrayList;

@RestController
public class ProjectController {

    ArrayList<ProjectModel> projectList = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<ProjectModel> getProjectList(){
        return projectList;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addProject(@Valid @RequestBody ProjectModel projectModel , Errors errors){
        if(errors.hasErrors()){
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        projectList.add(projectModel);
        return ResponseEntity.status(200).body("Project successfully added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProject(@PathVariable String id ,@Valid @RequestBody ProjectModel projectModel , Errors errors){
        for(int i = 0 ; i < projectList.size() ; i++){
            if(projectList.get(i).getId().equals(id)){
                if(errors.hasErrors()){
                    String errorMessage = errors.getFieldError().getDefaultMessage();
                    return ResponseEntity.status(400).body(errorMessage);
                }
                projectList.set(i, projectModel);
                return ResponseEntity.status(200).body("Project successfully updated");
            }
        }
        return ResponseEntity.status(404).body("Project not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id){
        for(int i = 0 ; i < projectList.size() ; i++){
            if(projectList.get(i).getId().equals(id)){
                projectList.remove(i);
                return ResponseEntity.status(200).body("Project deleted successfully ");
            }
        }
        return ResponseEntity.status(400).body("Project not found");
    }


    @PutMapping("/change-project-status/{id}/{status}")//todo
    public ResponseEntity<?> changeProjectStatus(@Valid @PathVariable String id, @PathVariable String status){
        for(int i = 0 ; i < projectList.size() ; i++){
            if(projectList.get(i).getId().equals(id)){
                if(!status.equals("Not Started") && !status.equals("In Progress") && !status.equals("Completed")){
                    return ResponseEntity.status(400).body("Project status must be In Started or In Progress or Completed ");
                }
                projectList.get(i).setStatus(status);
                return ResponseEntity.status(200).body("Project status successfully changed");
            }
        }
        return ResponseEntity.status(400).body("Project not found");
    }

    @GetMapping("/search-by-title/{title}")
    public ResponseEntity<?> searchByTitle(@PathVariable String title){
        ArrayList<ProjectModel> projectFounded = new ArrayList<>();
        for(int i = 0 ; i < projectList.size() ; i++){
            if(projectList.get(i).getTitle().contains(title)){
                projectFounded.add(projectList.get(i));
            }
        }
        if(!projectFounded.isEmpty()){
            return ResponseEntity.status(200).body(projectFounded);
        }
        return ResponseEntity.status(400).body("No such project found");
    }


    @GetMapping("/search-by-company-name/{companyName}")
    public ResponseEntity<?> searchByCompanyName(@PathVariable String companyName){
        ArrayList<ProjectModel> projectFounded = new ArrayList<>();
        for(int i = 0 ; i < projectList.size() ; i++){
            if(projectList.get(i).getCompanyName().contains(companyName)){
                projectFounded.add(projectList.get(i));
            }
        }
        if(!projectFounded.isEmpty()){
            return ResponseEntity.status(200).body(projectFounded);
        }
        return ResponseEntity.status(400).body("No project found");
    }

}
