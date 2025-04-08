package main;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;
/*
 * this interface serves as an interface for database information
 */
public interface DatabaseInfo {
    HashMap<String,Boolean> states  = new HashMap<>(Map.ofEntries(
            entry( "SQL", (false))
    ));
}
