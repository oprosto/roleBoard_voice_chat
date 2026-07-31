package com.roleplace.settings;

import java.util.HashMap;
import java.util.Map;

public class UserSettings {
    Map<String, String> tags;
    byte volume;

    public UserSettings()
    {
        tags = new HashMap<>();
        volume = 100;
    }
}
