package checks.nullness;

import org.jetbrains.annotations.NotNull; // Noncompliant
import org.jetbrains.annotations.Nullable; // Noncompliant

import java.util.ArrayList;
import java.util.List;

class JetbrainsAnnotations {
    @NotNull // Noncompliant
    private String field1;
    @Nullable // Noncompliant
    private String field2;

    // Noncompliant@+1
    public JetbrainsAnnotations(@NotNull String param1, @Nullable String param2) { // Noncompliant
        List<@NotNull String> list1 = new ArrayList<>();
        List<@Nullable String> list2 = new ArrayList<>();
    }

    private @NotNull Object func1() { // Noncompliant
        return new Object();
    }

    private @Nullable Object func2() { // Noncompliant
        return new Object();
    }
}
