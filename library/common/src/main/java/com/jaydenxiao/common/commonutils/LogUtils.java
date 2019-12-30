package com.jaydenxiao.common.commonutils;


import com.jaydenxiao.common.baseapp.AppConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 如果用于android平台，将信息记录到“LogCat”。如果用于java平台，将信息记录到“Console”
 * 使用logger封装
 */
public class LogUtils {

    public static boolean DEBUG_ENABLE = false;// 是否调试模式

    /**
     * 在application调用初始化
     */
    public static void logInit(boolean debug) {
        DEBUG_ENABLE = debug;

//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
//                .methodCount(0)         // (Optional) How many method line to show. Default 2
//                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
//                .tag("My custom tag")   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
//                .build();

        FormatStrategy formatStrategy = null;
        if (DEBUG_ENABLE) {
//            Logger.init(AppConfig.DEBUG_TAG)                 // default PRETTYLOGGER or use just init()
//                    .methodCount(2)                 // default 2
//                    .logLevel(LogLevel.FULL)        // default LogLevel.FULL
//                    .methodOffset(0);                // default 0

            formatStrategy = PrettyFormatStrategy.newBuilder()
                    .methodCount(2)
                    .methodOffset(0)
                    .tag(AppConfig.DEBUG_TAG)
                    .build();
        } else {
//            Logger.init()                 // default PRETTYLOGGER or use just init()
//                    .methodCount(3)                 // default 2
//                    .hideThreadInfo()               // default shown
//                    .logLevel(LogLevel.NONE)        // default LogLevel.FULL
//                    .methodOffset(2);

            formatStrategy = PrettyFormatStrategy.newBuilder()
                    .methodCount(3)
                    .methodOffset(2)
                    .showThreadInfo(false)
                    .tag(AppConfig.DEBUG_TAG)
                    .build();
        }
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return DEBUG_ENABLE;
            }
        });

    }

    public static void logd(String tag, String message) {
        if (DEBUG_ENABLE) {
            Logger.d(tag, message);
        }
    }

    public static void logd(String message) {
        if (DEBUG_ENABLE) {
            Logger.d(message);
        }
    }

    public static void loge(Throwable throwable, String message, Object... args) {
        if (DEBUG_ENABLE) {
            Logger.e(throwable, message, args);
        }
    }

    public static void loge(String message, Object... args) {
        if (DEBUG_ENABLE) {
            Logger.e(message, args);
        }
    }

    public static void logi(String message, Object... args) {
        if (DEBUG_ENABLE) {
            Logger.i(message, args);
        }
    }

    public static void logv(String message, Object... args) {
        if (DEBUG_ENABLE) {
            Logger.v(message, args);
        }
    }

    public static void logw(String message, Object... args) {
        if (DEBUG_ENABLE) {
            Logger.v(message, args);
        }
    }

    public static void logwtf(String message, Object... args) {
        if (DEBUG_ENABLE) {
            Logger.wtf(message, args);
        }
    }

    public static void logjson(String message) {
        if (DEBUG_ENABLE) {
            Logger.json(message);
        }
    }

    public static void logxml(String message) {
        if (DEBUG_ENABLE) {
            Logger.xml(message);
        }
    }
}
