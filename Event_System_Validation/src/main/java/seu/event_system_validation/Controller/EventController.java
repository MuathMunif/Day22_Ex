package seu.event_system_validation.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.event_system_validation.Model.EventModel;

import java.util.ArrayList;

@RestController
public class EventController {

    ArrayList<EventModel> eventList = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@Valid @RequestBody EventModel eventModel , Errors errors){
        if(errors.hasErrors()){
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        eventList.add(eventModel);
        return ResponseEntity.status(200).body("The event has been added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getEvent(){
        return ResponseEntity.status(200).body(eventList);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable String id, @Valid @RequestBody EventModel eventModel , Errors errors){
        for(int i = 0 ; i < eventList.size() ; i++){
            if(eventList.get(i).getId().equals(id)){
                if(errors.hasErrors()){
                    String errorMessage = errors.getFieldError().getDefaultMessage();
                    return ResponseEntity.status(400).body(errorMessage);
                }
                eventList.set(i, eventModel);
                return ResponseEntity.status(200).body("The event has been updated successfully");
            }
        }
        return ResponseEntity.status(400).body("The event doesn't exist");

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable String id){
        for(int i = 0 ; i < eventList.size() ; i++){
            if(eventList.get(i).getId().equals(id)){
                eventList.remove(i);
                return ResponseEntity.status(200).body("The event has been deleted successfully");
            }
        }
        return ResponseEntity.status(400).body("The event doesn't exist");
    }


    @PutMapping("/change-capacity/{id}/{capacity}")
    public ResponseEntity<?> changeEventCapacity(@PathVariable String id, @PathVariable int capacity){
        for(int i = 0 ; i < eventList.size() ; i++){
            if(eventList.get(i).getId().equals(id)){
                if(capacity > 25) {
                    eventList.get(i).setCapacity(capacity);
                    return ResponseEntity.status(200).body("The event has been changed successfully");
                }
                else {
                    return ResponseEntity.status(400).body("The capacity can't be lower than 25");
                }
            }
        }
        return ResponseEntity.status(400).body("The event doesn't exist");
    }


    @GetMapping("/search-by-id/{id}")
    public ResponseEntity<?> searchEventById(@PathVariable String id){
        ArrayList<EventModel> eventFounded = new ArrayList<>();
        for(int i = 0 ; i < eventList.size() ; i++){
            if(eventList.get(i).getId().equals(id)){
                eventFounded.add(eventList.get(i));
            }
        }
        if(!eventFounded.isEmpty()){
            return ResponseEntity.status(200).body(eventFounded);
        }
        return ResponseEntity.status(400).body("The event doesn't exist");
    }
}
