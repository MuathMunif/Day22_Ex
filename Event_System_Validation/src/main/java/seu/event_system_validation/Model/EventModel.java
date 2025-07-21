package seu.event_system_validation.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {


    @NotEmpty(message = "The id must be not empty")
    @Size(min = 3 , message = "The Id must be more than 2 Character")
    private String id;

    @NotEmpty(message = " description must be not empty")
    @Size(min = 16 , message = "description must be more than 15 character")
    private String description;

    @NotNull(message = "the capacity must be not null" )
    @Min(value = 26 , message = "The capacity number must be more than 25 character")
    private Integer capacity;

    @NotNull(message = "Date must not be null")
    @Future(message = "Date must be in the future")
    private LocalDate startDate;

    @NotNull(message = "Date must not be null")
    @Future(message = "Date must be in the future")
    private LocalDate endDate;
}
