package com.tdefense.module.logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

/**
 *  Wraps LibGDX's logging tools from {@link FPSLogger} class and Application methods
 */
public class GdxWrapperLogger implements Logger {

    /**
     * Logging can be limited to a specific level:
     *     <li>{@code 1} mutes all logging. </li>
     *     <li>{@code 2} logs all messages. </li>
     *     <li>{@code 3} logs only error messages. </li>
     *     <li>{@code 4} logs error and normal messages. </li></ul>
     * @param level logging level, default is 1
     */
    public void setLogLevel(int level) {
        Gdx.app.setLogLevel(level);
    }

    /**
     * asd
     * {@inheritDoc}
     */
    public void log(String tag, String message) {
        Gdx.app.log(tag, message);
    }

    /** {@inheritdoc} */
    public void error(String tag, String message) {
        Gdx.app.error(tag, message);
    }

    /** {@inheritDoc} */
    public void debug(String tag, String message) {
        Gdx.app.debug(tag, message);
    }

    /** Logs the current frames per second to the console. */
    public void logFps () {
        long startTime = TimeUtils.nanoTime();
        if (TimeUtils.nanoTime() - startTime > 1000000000) {  /* 1,000,000,000ns == one second */
            Gdx.app.log("FPSLogger", "fps: " + Gdx.graphics.getFramesPerSecond());
            startTime = TimeUtils.nanoTime();
        }
    }
}
