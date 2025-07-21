package seu.tracker_system_validation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel {
    @NotEmpty(message = "The id must be not null")
    @Size(min = 3, message = "The id must be more than 2")
    private String id;

    @NotEmpty(message = "title must not empty")
    @Size(min = 9 , message = "title must be more than 8 character")
    private String title;

    @NotEmpty(message = " description must be not empty")
    @Size(min = 16 , message = "description must be more than 15 character")
    private String description;

    @NotEmpty(message = "status must not be empty")
    @Pattern(regexp = "Not Started|In Progress|Completed", message = "status must be either 'Not Started', 'In Progress', or 'Completed'")
    private String status;

    @NotEmpty(message = "companyName must be not empty")
    @Size(min = 7 , message = "companyName must be more than 6 character")
    private String companyName;
}
