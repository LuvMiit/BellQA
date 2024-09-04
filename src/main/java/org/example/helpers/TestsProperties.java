package org.example.helpers;

import org.aeonbits.owner.Config;

@Config.Sources("file:src/main/resources/tests.properties")
public interface TestsProperties extends Config {

    @Config.Key("list.users.api")
    String listUsersAPI();
}
