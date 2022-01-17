package workshop.springb.testing.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Request {
    private String name;
    private boolean isFormal;
}
