package test.annotation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("unused")
class JavaxAnnotations {
    @Nonnull
    private String field1;
    @Nullable
    private String field2;

    @SuppressWarnings("unused")
    public JavaxAnnotations(@Nonnull String param1, @Nullable String param2) {
        field1 = param1;
        field2 = param2;
    }

    @SuppressWarnings("unused")
    private @Nonnull Object func1() {
        field1 = "Hello " + field1;
        return new Object();
    }

    @SuppressWarnings("unused")
    private @Nullable Object func2(@Nullable Object param) {
        field2 = "Hello " + field2;
        return param;
    }
}
