package com.roleplace.settings.models;

public interface ISetting<T> {
    public T getValue();
    public String getName();
    public void apply(T value);
}
