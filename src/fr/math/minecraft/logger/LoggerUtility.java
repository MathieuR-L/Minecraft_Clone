package fr.math.minecraft.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtility {

    private static final String CLIENT_TEXT_LOG_CONFIG = "log/client/log4j-text.properties";
    private static final String CLIENT_HTML_LOG_CONFIG = "log/client/log4j-html.properties";
    private static final String SERVER_TEXT_LOG_CONFIG = "log/server/log4j-text.properties";
    private static final String SERVER_HTML_LOG_CONFIG = "log/server/log4j-html.properties";
    private static final Date currentDate = new Date();

    public static Logger getClientLogger(Class<?> logClass, LogType logType) {
        Configurator.initialize(logClass.getName(), CLIENT_TEXT_LOG_CONFIG);

        return LogManager.getLogger(logClass);
    }

    public static Logger getServerLogger(Class<?> logClass, LogType logType) {
        Configurator.initialize(logClass.getName(), SERVER_TEXT_LOG_CONFIG);

        return LogManager.getLogger(logClass);
    }

}