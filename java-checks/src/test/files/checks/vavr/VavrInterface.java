package checks.vavr;

import io.vavr.control.Option;

import java.util.List;
import java.util.Map;

public interface VavrInterface {

    void func1(io.vavr.collection.List<Object> list); // Noncompliant

    void func2(io.vavr.collection.Map<String, Object> map); // Noncompliant

    void func3(List<Object> list);

    void func4(Map<String, Object> map);

    Option<String> func5();

}
