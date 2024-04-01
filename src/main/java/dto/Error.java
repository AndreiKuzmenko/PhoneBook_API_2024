package dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Error {
    int status;
    String error;
    String message;
}
