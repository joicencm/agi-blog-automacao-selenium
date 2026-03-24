package com.agi.blog.automation.pages;

import com.agi.blog.automation.base.BasePage;
import org.openqa.selenium.By;

public class SearchResultsPage extends BasePage {

    private By resultados = By.cssSelector("article");
    private By mensagemNenhumResultado = By.cssSelector(".no-results"); // ajuste conforme seu HTML

    public boolean temResultados() {
        return driver.findElements(resultados).size() > 0;
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