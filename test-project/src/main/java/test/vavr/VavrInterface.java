package test.vavr;

import io.vavr.control.Option;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public interface VavrInterface {

    @SuppressWarnings("unused")
    void func1(io.vavr.collection.List<Object> list);

    @SuppressWarnings("unused")
    void func2(io.vavr.collection.Map<String, Object> map);

    @SuppressWarnings("unused")
    void func3(List<Object> list);

    @SuppressWarnings("unused")
    void func4(Map<String, Object> map);

    @SuppressWarnings("unused")
    Option<String> func5();

}
