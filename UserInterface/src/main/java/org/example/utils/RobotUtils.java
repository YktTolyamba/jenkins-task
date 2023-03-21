package org.example.utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.logging.LocalizedLoggerUtility;
import aquality.selenium.logging.LogLevel;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotUtils {
    private ISettingsFile environment = new JsonSettingsFile("config.json");
    private String robotDelayTimeValue = "/robot_delay_time";
    private int delayTime = (int) environment.getValue(robotDelayTimeValue);
    private String imageNameValue = "/image_name";
    private String imageName = environment.getValue(imageNameValue).toString();

    public void upload() {
        StringSelection s = new StringSelection(FilePathUtils.getStringPath(imageName));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s,null);

        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            LocalizedLoggerUtility.logByLevel(LogLevel.ERROR, "Error occurred while creating Robot instance!");
            throw new RuntimeException(e);
        }
        r.delay(delayTime);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);

    }
}
