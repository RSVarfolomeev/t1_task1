package t1.school.task1.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MethodAverageTimeResponseDto {

    @JsonProperty("Метод")
    private String methodName;

    @JsonProperty("Среднее время выполнения, мс")
    private Double averageTime;
}
