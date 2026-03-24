package com.agi.blog.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver, String name) {
        try {
            // Remove caracteres inválidos
            name = name.replaceAll("[^a-zA-Z0-9\\-_]", "_");

            // Timestamp para não sobrescrever
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Pasta target/screenshots
            File folder = new File("target/screenshots");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(folder, name + "_" + timestamp + ".png");
            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot salva em: " + dest.getAbsolutePath());
        } catch (IOException | WebDriverException e) {
            e.printStackTrace();
        }
    }
}