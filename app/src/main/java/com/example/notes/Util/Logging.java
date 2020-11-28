package com.example.notes.Util;

import android.util.Log;


public class Logging {

    public  static String LOGCAT_PREFIX = "TODO: ";

    private static final int ERROR = 1;
    private static final int WARN = 2;
    private static final int INFO = 3;
    private static final int DEBUG = 4;

    private static void log(int level, String tag, String... strings) {
        String str;
        if (strings.length == 1) {
            str = strings[0];
        } else {
            StringBuilder sb = new StringBuilder();
            for (String s : strings) {
                sb.append(s);
            }
            str = sb.toString();
        }

        String logTag = LOGCAT_PREFIX;
        if (tag != null) {
            logTag = LOGCAT_PREFIX + tag;
        }
        switch (level) {
            case ERROR:
                Log.i(logTag, str);
                break;
            case WARN:
                Log.w(logTag, str);
                break;
            default:
                Log.i(logTag, str);
        }
    }

    public static void log(int level, String tag, Object... objects) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : objects) {
                sb.append(obj);
            }
            log(level, tag, sb.toString());
    }

    public static void debug(String tag, Object... objects) {
        log(DEBUG, tag, objects);
    }

    public static void debug(String tag, String... strings) {
        log(DEBUG, tag, strings);
    }

    public static void info(String tag, String... strings) {
        log(INFO, tag, strings);
    }

    public static void info(String tag, Object... objects) {
        log(INFO, tag, objects);
    }

    public static void warn(String tag, Object... objects) {
        log(WARN, tag, objects);
    }

    public static void warn(String tag, String... strings) {
        log(WARN, tag, strings);
    }

}
