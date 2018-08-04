package test.vavr;

import io.vavr.control.Option;

import java.util.List;
import java.util.Map;

public abstract class VavrAbstractClass {

    public abstract void func1(io.vavr.collection.List<Object> list);

    public abstract void func2(io.vavr.collection.Map<String, Object> map);

    public abstract void func3(List<Object> list);

    public abstract void func4(Map<String, Object> map);

    public abstract Option<String> func5();

    protected abstract void func6(io.vavr.collection.List<Object> list);

    protected abstract void func7(io.vavr.collection.Map<String, Object> map);

    protected abstract void func8(List<Object> list);

    protected abstract void func9(Map<String, Object> map);

    protected abstract Option<String> func10();

}
