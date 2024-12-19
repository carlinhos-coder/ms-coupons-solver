package co.com.bancolombia.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ErrorDTO {
    private String message;
}
