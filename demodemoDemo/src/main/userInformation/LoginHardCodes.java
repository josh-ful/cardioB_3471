package main.userInformation;

import java.util.Map;

import static java.util.Map.entry;

public interface LoginHardCodes {
    Map<String,String> logins  = Map.ofEntries(
            entry( "Noah", "Mathew"),
            entry("Josh", "Fulton"),
            entry("Kiera", "Shepperd"),
            entry("Emily", "Wokoek"),
            entry("Carter", "Lewis"),
            entry("Lawson", "Hale")
    );
}
