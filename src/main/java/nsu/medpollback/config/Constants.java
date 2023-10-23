package nsu.medpollback.config;

public class Constants {
    public static final String API_VERSION = "1";
    public static final String BASE_API_PATH = "/api/v" + API_VERSION;

    public static final String LOGIN_PATTERN = "^[\\w-]+$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[^0-9\\s]).*$";
    public static final int LOGIN_MIN_SYMBOLS = 6;
    public static final int LOGIN_MAX_SYMBOLS = 32;
    public static final int PASSWORD_MIN_SYMBOLS = 8;
    public static final int PASSWORD_MAX_SYMBOLS = 32;
}
