package checks.nullness;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("unused")
public class NullableReturn {

    @SuppressWarnings("unused")
    private @Nullable Object func1(@NonNull String test) { // Noncompliant
        return new Object();
    }

    @SuppressWarnings("unused")
    @Nullable // Noncompliant
    private Object func2(@NonNull String test) {
        return new Object();
    }

    @SuppressWarnings("unused")
    private @org.checkerframework.checker.nullness.qual.Nullable Object func3(@NonNull String test) { // Noncompliant
        return new Object();
    }

    @SuppressWarnings("unused")
    @org.checkerframework.checker.nullness.qual.Nullable // Noncompliant
    private Object func4(@NonNull String test) {
        return new Object();
    }

}
