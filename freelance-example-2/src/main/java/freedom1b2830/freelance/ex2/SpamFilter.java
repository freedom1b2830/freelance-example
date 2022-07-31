package freedom1b2830.freelance.ex2;

import java.util.concurrent.CopyOnWriteArrayList;

public class SpamFilter {
    private static final CopyOnWriteArrayList<String> badWords = new CopyOnWriteArrayList<>();

    public static boolean filter(String message) {
        if (badWords.isEmpty()) {
            init();
        }
        return badWords.parallelStream().anyMatch(word -> message.toLowerCase().contains(word.toLowerCase()));
    }

    private static void init() {
        badWords.addIfAbsent("fuck");
        badWords.addIfAbsent("bitch");
    }
}
