package com.roleplace.settings.models;

public class VolumeSetting implements ISetting<Byte>{

    byte volume;

    public VolumeSetting()
    {
        volume = 100;
    }

    public VolumeSetting(Byte volume){
        this.volume = volume;
    }

    public Byte getValue() {
        return 0;
    }

    public String getName() {
        return "";
    }

    @Override
    public void apply(Byte value) {

    }
}
