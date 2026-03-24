package com.agi.blog.automation.elements;

import org.openqa.selenium.By;

public class HomeElements {
    public By lupa = By.cssSelector(".ast-builder-layout-element.ast-flex.site-header-focus-item.ast-header-search");
    public  By campoBusca = By.id("search-field");
    public By menuItem(String texto) {
        return By.xpath("//span[contains(text(),'" + texto + "')]");
    }
}