package com.binary.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@Component
public class BrowserLauncher implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(BrowserLauncher.class);

    @Override
    public void run(String... args) throws Exception {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:8080/enter-numbers/"));
                logger.info("Browser launched successfully.");
            } catch (Exception e) {
                logger.error("Failed to launch browser.", e);
            }
        } else {
            logger.warn("Desktop or browser action not supported on this system.");
        }
    }
}
