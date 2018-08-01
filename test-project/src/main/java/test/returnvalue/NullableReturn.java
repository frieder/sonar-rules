package test.returnvalue;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class NullableReturn {

    /*
     * These methods return an object that is marked as potentially null. The Sonar rule should
     * pick this up and print an info hinting to the developer to consider using an Optional
     * return value instead if applicable.
     */

    @SuppressWarnings("unused")
    private @Nullable Object func1(@NonNull String test) {
        return null;
    }

    @SuppressWarnings("unused")
    @Nullable
    private Object func2(@NonNull String test) {
        return null;
    }

}
