package test.vavr;

import io.vavr.control.Option;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public abstract class VavrAbstractClass {

    @SuppressWarnings("unused")
    public abstract void func1(io.vavr.collection.List<Object> list);

    @SuppressWarnings("unused")
    public abstract void func2(io.vavr.collection.Map<String, Object> map);

    @SuppressWarnings("unused")
    public abstract void func3(List<Object> list);

    @SuppressWarnings("unused")
    public abstract void func4(Map<String, Object> map);

    @SuppressWarnings("unused")
    public abstract Option<String> func5();

    @SuppressWarnings("unused")
    protected abstract void func6(io.vavr.collection.List<Object> list);

    @SuppressWarnings("unused")
    protected abstract void func7(io.vavr.collection.Map<String, Object> map);

    @SuppressWarnings("unused")
    protected abstract void func8(List<Object> list);

    @SuppressWarnings("unused")
    protected abstract void func9(Map<String, Object> map);

    @SuppressWarnings("unused")
    protected abstract Option<String> func10();

}
