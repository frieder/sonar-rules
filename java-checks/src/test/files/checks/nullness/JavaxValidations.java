package checks.nullness;

import javax.validation.constraints.NotNull; // Noncompliant
import java.util.ArrayList;
import java.util.List;

class JavaxValidations {
    @NotNull // Noncompliant
    private String field;

    public JavaxValidations(@NotNull String param) { // Noncompliant
        List<@NotNull String> list = new ArrayList<>();
    }

    private @NotNull Object func() { // Noncompliant
        return new Object();
    }
}
