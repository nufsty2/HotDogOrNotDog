package com.cs428.hotdognothotdog.Model;

import com.cs428.hotdognothotdog.Model.Interfaces.IHotDogIdentifier;
import com.cs428.hotdognothotdog.Model.Interfaces.IImage;
import java.util.Random;

public class HotDogIdentifier implements IHotDogIdentifier {
    // Replace this with real functionality
    Random rand;

    public HotDogIdentifier() {
        rand = new Random();
    }

    @Override
    public boolean isHotDog() {
        return rand.nextBoolean();
    }

    @Override
    public boolean isHotDog(IImage potentialHotDog) {
        return rand.nextBoolean();
    }
}
