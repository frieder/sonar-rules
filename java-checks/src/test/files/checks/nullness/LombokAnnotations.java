package checks.nullness;

import lombok.NonNull; // Noncompliant

class LombokAnnotations {
    @NonNull // Noncompliant
    private String field;

    public LombokAnnotations(@NonNull String param) { // Noncompliant
    }

    private @NonNull Object func() { // Noncompliant
        return new Object();
    }
}
