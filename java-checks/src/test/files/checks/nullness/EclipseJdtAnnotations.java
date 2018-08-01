package checks.nullness;

import org.eclipse.jdt.annotation.NonNull; // Noncompliant
import org.eclipse.jdt.annotation.Nullable; // Noncompliant

import java.util.ArrayList;
import java.util.List;

class EclipseJdtAnnotations {
    @NonNull // Noncompliant
    private String field1;
    @Nullable // Noncompliant
    private String field2;

    // Noncompliant@+1
    public EclipseJdtAnnotations(@NonNull String param1, @Nullable String param2) { // Noncompliant
        List<@NonNull String> list1 = new ArrayList<>();
        List<@Nullable String> list2 = new ArrayList<>();
    }

    private @NonNull Object func1() { // Noncompliant
        return new Object();
    }

    private @Nullable Object func2() { // Noncompliant
        return new Object();
    }
}
