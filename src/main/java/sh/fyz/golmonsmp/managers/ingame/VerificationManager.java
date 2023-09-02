package sh.fyz.golmonsmp.managers.ingame;

import java.util.ArrayList;
import java.util.UUID;

public class VerificationManager {

    private static final ArrayList<UUID> unverified = new ArrayList<>();

    public static ArrayList<UUID> getUnverified() {
        return unverified;
    }
    public static void addUnverified(UUID uuid) {
        unverified.add(uuid);
    }

    public static void removeUnverified(UUID uuid) {
        unverified.remove(uuid);
    }

    public static boolean isUnverified(UUID uuid) {
        return unverified.contains(uuid);
    }


}
