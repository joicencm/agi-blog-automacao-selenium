Projeto desenvolvido com foco em práticas de automação modernas, incluindo execução em CI/CD e tratamento de cenários reais de falha em ambiente headless.

# 🧪 Automação de Testes - Blog Agibank

## 📋 Descrição

Este projeto contém a automação de testes end-to-end para o blog do Agibank  
https://blog.agibank.com.br

Os testes validam funcionalidades principais do site, com foco em:

- Busca de artigos
- Exibição de resultados
- Comportamento da interface de busca

A automação foi desenvolvida utilizando boas práticas como **Page Object Model (POM)** e **BDD com Cucumber**.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Selenium WebDriver 4.14.0
- Maven
- Cucumber (BDD)
- JUnit
- GitHub Actions (CI/CD)

---

## 🧑‍💻 Como executar o projeto

### ✅ Pré-requisitos

- Java 17 instalado
- Maven instalado
- Google Chrome instalado

---

### ▶️ Executar localmente

```bash
mvn clean test
```
### ▶️ Executar em modo headless
```bash
mvn clean verify -Dheadless=true -Dmaven.test.failure.ignore=true
```

---
## 🔄 Integração Contínua (CI)
O projeto utiliza GitHub Actions para execução automática dos testes.

✔ O que o CI faz:
- Executa testes a cada push ou pull request
- Roda em ambiente Linux (Ubuntu)
- Executa navegador em modo headless
- Gera screenshots em caso de falha
- - Gera relatório Cucumber completo mesmo quando alguns testes falham, garantindo visibilidade das falhas
- Disponibiliza artefatos para análise

Arquivo de configuração:
```
.github/workflows/ci.yml
```

###  📸 Evidências de falha (Screenshots)
- Screenshots são gerados automaticamente em falhas
- Salvos em: 
```bash
- target/screenshots/
```
- No CI, são disponibilizados como artefatos

### 📑 Funcionalidades automatizadas
- Acessar o blog
- Abrir campo de busca
- Buscar por termos válidos
- Validar resultados retornados
- Buscar termos inválidos e validar mensagem

---

## ⚠️ Problemas encontrados e soluções

### Lupa de busca e redirecionamento para URL com /#

O blog utiliza SPA (Single Page Application), fazendo com que ao clicar na lupa:

- O campo de busca nem sempre apareça
- O site redireciona intermitentemente para [https://blog.agibank.com.br/#](https://blog.agibank.com.br/#)

**Impactos na automação:**

- Falha ao clicar na lupa via Selenium
- TimeoutException ao aguardar o campo de busca

### 💡 Sugestões de melhorias na implementação e automação

#### 1. Campo de busca e SPA

O clique na lupa nem sempre abre o campo de busca de forma confiável, devido ao comportamento de SPA, que pode redirecionar para `/#` ou atrasar a renderização do input.

**Sugestão para implementação do site:**

- Permitir que o campo de busca seja acessível diretamente pelo DOM, sem depender exclusivamente do clique na lupa
- Adicionar um ID ou atributo estático no input de busca, garantindo que a automação consiga localizar e interagir com ele de forma consistente
- Avaliar se é possível evitar redirecionamentos intermitentes ao abrir a busca, melhorando previsibilidade para testes end-to-end

#### 2. Alternativas de automação

Embora este projeto utilize Selenium + Java, outras ferramentas podem simplificar cenários como este, devido à melhor manipulação de SPA e eventos assíncronos:

- **Cypress**
  - Executa dentro do navegador, garantindo que o DOM esteja sempre pronto antes da interação
  - Permite esperar elementos de forma mais natural (`cy.get().click()`) sem precisar de `JavascriptExecutor`
  - Ideal para testes de interface altamente dinâmicos

- **Playwright (PL)**
  - Suporte robusto a múltiplos navegadores
  - Esperas automáticas de elementos e eventos, reduzindo flakiness
  - Suporte a execução headless e gravação de vídeos/screenshots

---
## 🧪 Cenários de teste (BDD)
🔹 Busca sem preenchimento

```gherkin
Scenario: Buscar sem digitar termo exibe comportamento inesperado
Given que o usuário acessa o blog
When ele clica na lupa e envia a busca sem digitar nada
Then são exibidos resultados sem validação de termo obrigatório
```

### ⚠️ Observação importante
Comportamento identificado:

- Ao realizar uma busca sem digitar nenhum termo, o sistema retorna resultados normalmente
- Não há validação exigindo o preenchimento do campo de busca
- A mensagem esperada "Por favor, digite um termo para buscar" não é exibida

### 📌 Ação necessária

Este cenário deve ser avaliado pelo time de produto/UX, pois pode indicar:

- Falta de validação de entrada
- Comportamento não alinhado com boas práticas de usabilidade
- Possível melhoria na experiência do usuário
---
## 🚧 Melhorias futuras
- Suporte a múltiplos navegadores (Firefox, Edge)
- Implementação de relatórios (Allure / ExtentReports)
- Execução paralela de testes
- Testes mais robustos para navegação do site

---
## 🧑‍💼 Autor

Joice Martins