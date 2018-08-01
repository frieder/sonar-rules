package checks.nullness;

import javax.annotation.Nonnull; // Noncompliant
import javax.annotation.Nullable; // Noncompliant

class JavaxAnnotations {
    @Nonnull // Noncompliant
    private String field1;
    @Nullable // Noncompliant
    private String field2;

    // Noncompliant@+1
    public JavaxAnnotations(@Nonnull String param1, @Nullable String param2) { // Noncompliant
    }

    private @Nonnull Object func1() { // Noncompliant
        return new Object();
    }

    private @Nullable Object func2() { // Noncompliant
        return new Object();
    }
}
