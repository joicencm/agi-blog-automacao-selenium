package com.agi.blog.automation.pages;

import com.agi.blog.automation.base.BasePage;
import com.agi.blog.automation.elements.HomeElements;
import com.agi.blog.automation.utils.ScreenshotUtils;
import com.agi.blog.automation.utils.Waits;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private final HomeElements elements = new HomeElements();
    private final Waits waits;

    public HomePage() {
        super();
        waits = new Waits(driver);
    }

    /**
     * Abre o campo de busca de forma confiável
     */
    public void abrirBusca() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 1️⃣ Espera a lupa estar presente no DOM
            WebElement lupa = wait.until(ExpectedConditions.presenceOfElementLocated(elements.lupa));

            // 2️⃣ Clica na lupa via JS para evitar problemas de SPA/redirecionamento
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lupa);

            // 3️⃣ Espera o campo de busca aparecer
            WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(elements.campoBusca));

            // 4️⃣ Garante que o campo está clicável e foca nele
            wait.until(ExpectedConditions.elementToBeClickable(campo));
            ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", campo);

        } catch (Exception e) {
            ScreenshotUtils.takeScreenshot(driver, "erro_abrirBusca");
            throw new RuntimeException("Falha ao abrir campo de busca", e);
        }
        click(elements.lupa);
    }

    /**
     * Preenche o campo de busca e envia a pesquisa
     */
    public void buscar(String termo) {
        waits.waitUntilVisible(elements.campoBusca, 30);
        waits.waitUntilClickable(elements.campoBusca, 30);
        write(elements.campoBusca, termo);
        submit(elements.campoBusca);
    }

    /**
     * Verifica se o campo de busca está visível e habilitado
     */
    public boolean campoBuscaVisivel() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(elements.campoBusca));
            return campo.isDisplayed() && campo.isEnabled();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    /**
     * Envia a busca sem digitar nenhum termo
     */
    public void enviarBuscaVazia() {
        waits.waitUntilVisible(elements.campoBusca, 10);
        waits.waitUntilClickable(elements.campoBusca, 10);
        submit(elements.campoBusca);
    }
}