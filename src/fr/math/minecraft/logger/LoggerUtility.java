/**
*  Minecraft Clone Math edition : Cybersecurity - A serious game to learn network and cybersecurity
*  Copyright (C) 2024 MeAndTheHomies (Math)
*
*  This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package fr.math.minecraft.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class LoggerUtility {

    private static final String CLIENT_TEXT_LOG_CONFIG = "log/client/log4j-text.properties";
    private static final String CLIENT_HTML_LOG_CONFIG = "log/client/log4j-html.properties";
    private static final String SERVER_TEXT_LOG_CONFIG = "log/server/log4j-text.properties";
    private static final String SERVER_HTML_LOG_CONFIG = "log/server/log4j-html.properties";
    private static final Date currentDate = new Date();

    public static Logger getClientLogger(Class<?> logClass, LogType logType) {
        Properties properties = new Properties();
        try {
            if (logType == LogType.HTML) {
                properties.load(new FileInputStream(CLIENT_HTML_LOG_CONFIG));
            } else if (logType == LogType.TXT) {
                properties.load(new FileInputStream(CLIENT_TEXT_LOG_CONFIG));
            } else {
                throw new IllegalArgumentException("Type de trace inconnue.");
            }

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(currentDate);
            String logFileName = properties.getProperty("log4j.appender.file.File");
            logFileName = logFileName.replace(".log", "-" + timestamp + ".log");

            properties.setProperty("log4j.appender.file.File", logFileName);

            PropertyConfigurator.configure(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Logger.getLogger(logClass.getName());
    }

    public static Logger getServerLogger(Class<?> logClass, LogType logType) {
        Properties properties = new Properties();
        try {
            if (logType == LogType.HTML) {
                properties.load(new FileInputStream(SERVER_HTML_LOG_CONFIG));
            } else if (logType == LogType.TXT) {
                properties.load(new FileInputStream(SERVER_TEXT_LOG_CONFIG));
            } else {
                throw new IllegalArgumentException("Type de trace inconnue.");
            }

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(currentDate);
            String logFileName = properties.getProperty("log4j.appender.file.File");
            logFileName = logFileName.replace(".log", "-" + timestamp + ".log");

            properties.setProperty("log4j.appender.file.File", logFileName);

            PropertyConfigurator.configure(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Logger.getLogger(logClass.getName());
    }

}