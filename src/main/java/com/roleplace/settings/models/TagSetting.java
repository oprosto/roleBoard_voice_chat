package com.roleplace.settings.models;

import java.util.HashSet;
import java.util.Set;

public class TagSetting implements ISetting<Set<String>>{

    Set<String> tags;

    public TagSetting()
    {
        tags = new HashSet<>();
    }

    public TagSetting(Set<String> tags)
    {
        this.tags = tags;
    }

    @Override
    public Set<String> getValue() {
        return Set.of();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void apply(Set<String> value) {

    }
}
