package com.agi.blog.automation.pages;

import com.agi.blog.automation.base.BasePage;
import com.agi.blog.automation.elements.HomeElements;
import com.agi.blog.automation.utils.Waits;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private HomeElements elements = new HomeElements();
    private Waits waits;

    public HomePage() {
        super();
        waits = new Waits(driver);
    }

    public void abrirBusca() {
        click(elements.menuItem("Stories")); // ativa o DOM

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement lupa = wait.until(ExpectedConditions.elementToBeClickable(elements.lupa));
        lupa.click();

        // Espera o input realmente aparecer
        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.campoBusca));
        wait.until(ExpectedConditions.elementToBeClickable(elements.campoBusca));
    }

    public void buscar(String termo) {
        waits.waitUntilVisible(elements.campoBusca, 30);  // espera o campo ficar visível
        waits.waitUntilClickable(elements.campoBusca, 30); // espera ficar clicável
        write(elements.campoBusca, termo);
        submit(elements.campoBusca);
    }

    public boolean campoBuscaVisivel() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(elements.campoBusca));

            // adicional: checar se o campo está habilitado
            return campo.isDisplayed() && campo.isEnabled();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void enviarBuscaVazia() {
        waits.waitUntilVisible(elements.campoBusca, 10);
        waits.waitUntilClickable(elements.campoBusca, 10);

        // Apenas envia o form sem escrever nada
        submit(elements.campoBusca);
    }
}