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
        click(elements.lupa);
    }

    /**
     * Preenche o campo de busca e envia a pesquisa
     */
    public void buscar(String termo) {
        abrirMenuELupa("Serviços");
        aguardarCampoBusca();
        write(elements.campoBusca, termo);
        submit(elements.campoBusca);

    }

    /**
     * Verifica se o campo de busca está visível e habilitado
     */
    public boolean campoBuscaVisivel() {
        abrirMenuELupa("Stories");
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
        abrirMenuELupa("Sua segurança");
        aguardarCampoBusca();
        submit(elements.campoBusca);
    }

    private void aguardarCampoBusca() {
        waits.waitUntilVisible(elements.campoBusca, 30);
        waits.waitUntilClickable(elements.campoBusca, 30);
    }

    private void abrirMenuELupa(String menu) {
        String currentUrl = (String) ((JavascriptExecutor) driver).executeScript("return window.location.href;");
        if (currentUrl.contains("#")) {
            click(elements.menuItem("Seus benefícios"));
        }
        click(elements.menuItem(menu));
        click(elements.lupa);
        waits.waitUntilVisible(elements.campoBusca, 10);
    }
}