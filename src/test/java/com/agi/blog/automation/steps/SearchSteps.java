package com.agi.blog.automation.steps;

import com.agi.blog.automation.pages.HomePage;
import com.agi.blog.automation.pages.SearchResultsPage;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

public class SearchSteps {

    HomePage homePage = new HomePage();
    SearchResultsPage resultsPage;

    @Given("que o usuário acessa o blog")
    public void acessarBlog() {
        homePage.acessarUrl("https://blogdoagi.com.br/");
    }

    @When("ele busca por {string}")
    public void buscar(String termo) {
        homePage.abrirBusca();
        homePage.buscar(termo);
    }

    @Then("deve ver resultados relacionados")
    public void validarResultados() {
        resultsPage = new SearchResultsPage();
        assertTrue(resultsPage.temResultados());
    }

    @Then("deve exibir mensagem {string}")
    public void validarMensagemNenhumResultado(String mensagemEsperada) {
        resultsPage = new SearchResultsPage(); // inicializa antes de usar
        String mensagem = resultsPage.getMensagemNenhumResultado();
        assertTrue("Mensagem incorreta!", mensagem.contains(mensagemEsperada));
    }

    @When("ele clica na lupa")
    public void ele_clica_na_lupa() {
        homePage.abrirBusca(); // implemente esse método na sua HomePage
    }

    @Then("o campo de busca deve ser exibido para digitar o termo")
    public void o_campo_de_busca_deve_ser_exibido_para_digitar_o_termo() {
        assertTrue(homePage.campoBuscaVisivel());
    }

    @When("ele clica na lupa e envia a busca sem digitar nada")
    public void ele_clica_na_lupa_e_envia_a_busca_sem_digitar_nada() {
        HomePage homePage = new HomePage();

        // Clica na lupa
        homePage.abrirBusca();

        // Envia a busca sem digitar nada
        homePage.enviarBuscaVazia();
    }
}