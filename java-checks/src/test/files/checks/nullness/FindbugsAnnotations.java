package checks.nullness;

import edu.umd.cs.findbugs.annotations.NonNull; // Noncompliant
import edu.umd.cs.findbugs.annotations.Nullable; // Noncompliant

class FindbugsAnnotations {
    @NonNull // Noncompliant
    private String field1;
    @Nullable // Noncompliant
    private String field2;

    // Noncompliant@+1
    public FindbugsAnnotations(@NonNull String param1, @Nullable String param2) { // Noncompliant
    }

    private @NonNull Object func1() { // Noncompliant
        return new Object();
    }

    private @Nullable Object func2() { // Noncompliant
        return new Object();
    }
}
