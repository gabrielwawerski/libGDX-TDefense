package com.tdefense.logging;

import com.tdefense.module.logger.Logger;
import com.tdefense.module.logger.GdxWrapperLogger;

/**
 * Main logging
 */
public class Logging implements Logger {
    private Logger logger;

    private boolean logFps;

    public Logging() {
        logger = new GdxWrapperLogger();
    }
}
