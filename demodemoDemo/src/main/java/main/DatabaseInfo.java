package main;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public interface DatabaseInfo {
    HashMap<String,Boolean> states  = new HashMap<>(Map.ofEntries(
            entry( "SQL", (false))
    ));
}
