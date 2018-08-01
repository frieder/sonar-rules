package checks.nullness;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;

class CheckerFwAnnotations {
    @NonNull
    private String field1;
    @Nullable
    private String field2;

    public CheckerFwAnnotations(@NonNull String param1, @Nullable String param2) {
        List<@NonNull String> list1 = new ArrayList<>();
        List<@Nullable String> list2 = new ArrayList<>();
    }

    private @NonNull
    Object func1() {
        return new Object();
    }

    private @Nullable
    Object func2() {
        return new Object();
    }
}
