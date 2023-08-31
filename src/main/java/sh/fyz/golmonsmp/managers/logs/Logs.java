package sh.fyz.golmonsmp.managers.logs;

import java.util.logging.Logger;

public class Logs {

    static Logger log = Logger.getLogger(Logs.class.getName());

    public static void info(String message) {
        log.info(message);
    }

    public static void warning(String message) {
        log.warning(message);
    }

    public static void severe(String message) {
        log.severe(message);
    }
}
