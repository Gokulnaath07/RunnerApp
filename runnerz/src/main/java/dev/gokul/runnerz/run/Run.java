package dev.gokul.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        @Id
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        @Version Integer version,
        Location location
) {
    //constructor to set the constraints for some variables initiated there
    public Run{
        if(!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("End time must be after start time");
        }
    }
}
