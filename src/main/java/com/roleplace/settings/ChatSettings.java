package com.roleplace.settings;

import com.roleplace.settings.models.ISetting;

import java.util.HashMap;
import java.util.Map;

public class ChatSettings {
    Map<String, ISetting> settings;

    public ChatSettings()
    {
        settings = new HashMap<>();
    }
    public ChatSettings(Map<String, ISetting> settings)
    {
        this.settings = settings;
    }

    public void add(ISetting setting)
    {
        settings.put(setting.getName(), setting);
    }
    public void remove(ISetting setting)
    {
        settings.remove(setting.getName());
    }
    public void remove(String name)
    {
        settings.remove(name);
    }

}
