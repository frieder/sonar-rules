package checks.vavr;

import io.vavr.control.Option;

import java.util.List;
import java.util.Map;

public abstract class VavrAbstractClass {

    public abstract void func1(io.vavr.collection.List<Object> list); // Noncompliant

    public abstract void func2(io.vavr.collection.Map<String, Object> map); // Noncompliant

    public abstract void func3(List<Object> list);

    public abstract void func4(Map<String, Object> map);

    public abstract Option<String> func5(); // Noncompliant

    protected abstract void func6(io.vavr.collection.List<Object> list); // Noncompliant

    protected abstract void func7(io.vavr.collection.Map<String, Object> map); // Noncompliant

    protected abstract void func8(List<Object> list);

    protected abstract void func9(Map<String, Object> map);

    protected abstract Option<String> func10(); // Noncompliant

    private void func11(io.vavr.collection.List<Object> list) {
    }

    private void func12(io.vavr.collection.Map<String, Object> map) {
    }

    private void func13(List<Object> list) {
    }

    private void func14(Map<String, Object> map) {
    }

    private Option<String> func15() {
        return Option.of("");
    }

}
