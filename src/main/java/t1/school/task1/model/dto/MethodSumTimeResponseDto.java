package t1.school.task1.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MethodSumTimeResponseDto {

    @JsonProperty("Метод")
    private String methodName;

    @JsonProperty("Количество выполнений")
    private Long executionCount;

    @JsonProperty("Суммарное время выполнения, мс")
    private double sumTime;
}
