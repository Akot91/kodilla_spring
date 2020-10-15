package tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {

    private String receiveEmial;
    private String subject;
    private String message;
    private String toCC;
}
