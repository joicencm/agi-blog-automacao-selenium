package com.agi.blog.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver, String name) {
        // Remove caracteres inválidos do nome do arquivo
        name = name.replaceAll("[^a-zA-Z0-9\\-_]", "_");

        // Cria a pasta target/screenshots se não existir
        File folder = new File("target/screenshots");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(folder, name + ".png");

        try {
            FileUtils.copyFile(src, dest);
            System.out.println("Screenshot salva em: " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}