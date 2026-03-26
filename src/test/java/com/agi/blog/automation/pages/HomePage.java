package com.agi.blog.automation.pages;

import com.agi.blog.automation.base.BasePage;
import com.agi.blog.automation.elements.HomeElements;
import com.agi.blog.automation.utils.Waits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private final HomeElements elements = new HomeElements();
    private final Waits waits;

    public HomePage() {
        super();
        waits = new Waits(driver);
    }

    /**
     * Abre o campo de busca (fluxo normal esperado)
     */
    public void abrirBusca() {
        WebElement lupa = waits.waitUntilClickable(elements.lupa, 10);
        lupa.click();

        waits.waitUntilVisible(elements.campoBusca, 10);
    }

    /**
     * Realiza busca com termo válido
     */
    public void buscar(String termo) {
        WebElement campo = waits.waitUntilVisible(elements.campoBusca, 10);

        campo.click();
        campo.clear();
        campo.sendKeys(termo);
        campo.sendKeys(Keys.ENTER);
    }

    /**
     * Valida se campo de busca está visível
     */
    public boolean campoBuscaVisivel() {
        try {
            WebElement campo = waits.waitUntilVisible(elements.campoBusca, 5);
            return campo.isDisplayed() && campo.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Envia busca vazia (comportamento esperado do sistema)
     */
    public void enviarBuscaVazia() {
        WebElement campo = waits.waitUntilVisible(elements.campoBusca, 10);
        campo.sendKeys(Keys.ENTER);
    }
}