package main.userInformation;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public interface LoginHardCodes {
    HashMap<String,String> logins  = new HashMap<>(Map.ofEntries(
            entry( "Noah", "Mathew"),
            entry("Josh", "Fulton"),
            entry("Kiera", "Shepperd"),
            entry("Emily", "Wokoek"),
            entry("Carter", "Lewis"),
            entry("Lawson", "Hale")
    ));
}
