package com.tdefense.logging;

import com.tdefense.logging.logger.Logger;
import com.tdefense.logging.logger.GdxWrapperLogger;

/**
 * Main logging class
 */
public class Logging {
    private Logger logger;

    public Logging() {
        logger = new GdxWrapperLogger();
    }

    public Logger getLogger() {
        return  logger;
    }
}