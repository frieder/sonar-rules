package test.annotation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class JavaxAnnotations {
    @Nonnull
    private String field1;
    @Nullable
    private String field2;

    public JavaxAnnotations(@Nonnull String param1, @Nullable String param2) {
    }

    private @Nonnull Object func1() {
        return new Object();
    }

    private @Nullable Object func2() {
        return new Object();
    }
}
