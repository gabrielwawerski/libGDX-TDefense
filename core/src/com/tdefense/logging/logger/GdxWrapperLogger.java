package com.tdefense.logging.logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

/**
 *  Wraps LibGDX's logging tools from {@link com.badlogic.gdx.graphics.FPSLogger} and {@link com.badlogic.gdx.Application}
 */
public class GdxWrapperLogger implements Logger {
    long startTime;

    public GdxWrapperLogger() {
        startTime = TimeUtils.nanoTime();
        setLogLevel(1);
    }

    /**
     * Logging can be limited to specific level:
     *     <li>{@code 1} mutes all logging. </li>
     *     <li>{@code 2} logs all messages. </li>
     *     <li>{@code 3} logs only error messages. </li>
     *     <li>{@code 4} logs error and normal messages. </li></ul>
     * @param level logging level, default is 1
     */
    public void setLogLevel(int level) {
        Gdx.app.setLogLevel(level);
    }

    public void log(String tag, String message) {
        Gdx.app.log(tag, message);
    }

    public void error(String tag, String message) {
        Gdx.app.error(tag, message);
    }

    public void debug(String tag, String message) {
        Gdx.app.debug(tag, message);
    }

    /**
     * Logs the current frames per second to the console. {@link com.badlogic.gdx.graphics.FPSLogger}
     * @author mzechner
     */
    public void logFps () {
        if (TimeUtils.nanoTime() - startTime > 1000000000) {  /* 1,000,000,000ns == one second */
            Gdx.app.log("FPSLogger", "fps: " + Gdx.graphics.getFramesPerSecond());
            startTime = TimeUtils.nanoTime();
        }
    }
}
