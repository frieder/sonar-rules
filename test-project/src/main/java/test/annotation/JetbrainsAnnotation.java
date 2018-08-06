package test.annotation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JetbrainsAnnotation {
    @NotNull
    private String field1;
    @Nullable
    private String field2;

    @SuppressWarnings("unused")
    public JetbrainsAnnotation(@NotNull String param1, @Nullable String param2) {
        field1 = param1;
        field2 = param2;
    }

    @SuppressWarnings("unused")
    private @NotNull Object func1() {
        @SuppressWarnings({"squid:S1854", "squid:S1481"})
        List<@NotNull String> list = new ArrayList<>();
        field1 = "Hello " + field1;
        return new Object();
    }

    @SuppressWarnings("unused")
    private @Nullable Object func2(@Nullable Object param) {
        field2 = "Hello " + field2;
        return param;
    }
}
