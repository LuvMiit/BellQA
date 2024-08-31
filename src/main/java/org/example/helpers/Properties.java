package org.example.helpers;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public class Properties {
    public static TestProperties testProperties = ConfigFactory.create(TestProperties.class);
}
