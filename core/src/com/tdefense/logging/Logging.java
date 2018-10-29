package com.tdefense.logging;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.Application;
import com.tdefense.Constants;

/**
 * Main logging class for the project. When using in a new class, add {@code public static final String TAG} field
 * and set it to {@code getSimpleName()} of the class.<p>
 * Wraps GDX's {@link FPSLogger#log}, {@link Application#log(String, String)}, {@link Application#debug(String, String)},
 * {@link Application#error(String, String)}<p>
 * Default logging level is {@link #LOG_ERROR}, which is set in the constructor. To change the logging level, use
 * {@link #setLogLevel(int)}. You can also use second constructor to set the logging level, see {@link #Logging(int)}
 * @author mx
 */
public class Logging {
    private long startTime;
    private static final int FPS_LOGGING_INTERVAL = 1000000000;  // 1,000,000,000ns == one second

    /** will mute all log output */
    public static final int LOG_NONE = 0;
    /** will only let error messages through */
    public static final int LOG_ERROR = 1;
    /** will let all non-debug messages through */
    public static final int LOG_INFO = 2;
    /** will let all messages through */
    public static final int LOG_DEBUG = 3;

    /**
     * Sets logging level to the default value, which is {@link #LOG_ERROR}.<p>
     * See also {@link Constants#DEFAULT_LOG_LEVEL} */
    public Logging() {
        startTime = TimeUtils.nanoTime();
        setLogLevel(Constants.DEFAULT_LOG_LEVEL);
    }

    /**
     * Use this constructor if you want to set the desired logging level right away.
     * @param logLevel {@link #LOG_NONE}, {@link #LOG_ERROR}, {@link #LOG_INFO}, {@link #LOG_DEBUG}.
     */
    public Logging(int logLevel) {
        startTime = TimeUtils.nanoTime();
        setLogLevel(logLevel);
    }

    /**
     * Log output can be limited to specific level:
     * <table>
     *     <tr><td>{@link #LOG_NONE}</td> <td>mutes all logging</td></tr>
     *     <tr><td>{@link #LOG_ERROR}</td> <td>will only let error messages through</td></tr>
     *     <tr><td>{@link #LOG_INFO}</td> <td>logs only error messages</td></tr>
     *     <tr><td>{@link #LOG_DEBUG}</td> <td>logs all messages</td></tr>
     * </table>
     * @param logLevel desired logging level, default is {@code LOG_ERROR}. See also {@link Constants#DEFAULT_LOG_LEVEL}.
     */
    public void setLogLevel(int logLevel) {
        Gdx.app.setLogLevel(logLevel);
    }

    /**
     * Logs message to the console or logcat.
     * @param tag calling class {@code TAG} field
     * @param message descriptive log message
     */
    public void log(String tag, String message) {
        Gdx.app.log(tag, message);
    }

    /**
     * Logs a debug message to the console or logcat.
     * @param tag calling class {@code TAG} field
     * @param message descriptive debug message
     */
    public void debug(String tag, String message) {
        Gdx.app.debug(tag, message);
    }

    /**
     * Logs an error message to the console or logcat.
     * @param tag calling class {@code TAG} field.
     * @param message descriptive error message
     */
    public void error(String tag, String message) {
        Gdx.app.error(tag, message);
    }

    /**
     * Logs the current frames per second to the console. See {@link FPSLogger}.
     * @author mzechner
     */
    public void logFps () {
        if (TimeUtils.nanoTime() - startTime > FPS_LOGGING_INTERVAL) {
            Gdx.app.log("FPSLogger", "fps: " + Gdx.graphics.getFramesPerSecond());
            startTime = TimeUtils.nanoTime();
        }
    }
}