package com.agi.blog.automation.pages;

import com.agi.blog.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage extends BasePage {

    private By resultados = By.cssSelector(".ast-article-post");
    private By mensagemNenhumResultado = By.cssSelector(".no-results"); // ajuste conforme seu HTML

    public boolean temResultados() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultados));
            return driver.findElements(resultados).size() > 0;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public String getMensagemNenhumResultado() {
        if (driver.findElements(mensagemNenhumResultado).size() > 0) {
            String mensagem = driver.findElement(mensagemNenhumResultado).getText();

            // remove múltiplos espaços e quebras de linha
            mensagem = mensagem.trim().replaceAll("\\s+", " ");

            // imprime para debug
            System.out.println("Mensagem capturada: '" + mensagem + "'");

            return mensagem;
        }
        return "";
    }
}