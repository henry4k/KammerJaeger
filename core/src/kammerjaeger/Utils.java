package kammerjaeger;

public class Utils {

    private static final long startTime = System.currentTimeMillis();

    public static float getTime() {

        final long currentTime = System.currentTimeMillis();
        return (float)(currentTime - startTime) / 1000.0f;
    }
}
