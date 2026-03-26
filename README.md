# 🧪 Automação de Testes - Blog Agibank

## 📋 Descrição

Este projeto contém a automação de testes end-to-end para o blog do Agibank  
https://blog.agibank.com.br

Os testes validam funcionalidades principais do site, com foco em:

- Busca de artigos
- Exibição de resultados
- Comportamento da interface de busca

A automação foi desenvolvida utilizando boas práticas como:

- Page Object Model (POM)
- BDD com Cucumber
- Separação entre testes de UI e testes funcionais

---

## 🚀 Tecnologias utilizadas

- Java 17
- Selenium WebDriver
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

```` bash
mvn clean test
````
---

### ▶️ Executar em modo headless

``` bash
mvn clean verify -Dheadless=true -Dmaven.test.failure.ignore=true
```

---

## 🔄 Integração Contínua (CI)

O projeto utiliza GitHub Actions para execução automática dos testes.

### ✔ O que o CI faz:

- Executa testes a cada push ou pull request
- Roda em ambiente Linux (Ubuntu)
- Executa navegador em modo headless
- Gera screenshots em caso de falha
- Gera relatório Cucumber mesmo com falhas
- Disponibiliza artefatos para análise

Arquivo de configuração:

```
.github/workflows/ci.yml
```
---

## 📸 Evidências de falha (Screenshots)

- Geradas automaticamente em falhas
- Salvas em: target/screenshots/
- No CI, são disponibilizadas como artefatos

---

## 📑 Funcionalidades automatizadas

- Acessar o blog
- Abrir campo de busca
- Validar exibição do campo de busca
- Buscar por termos válidos
- Validar resultados retornados
- Buscar termos inválidos
- Validar mensagens de retorno

---

## ⚠️ Problemas identificados na aplicação

### 🔍 Instabilidade na funcionalidade de busca

Durante a automação, foi identificado comportamento inconsistente na funcionalidade de busca.

### 📌 Comportamento observado

- Ao clicar na lupa:
  - O campo de busca nem sempre é exibido
  - O site pode redirecionar para: https://blog.agibank.com.br/#

- Ao realizar buscas:
  - O termo digitado nem sempre é considerado
  - O sistema pode redirecionar para:
    - https://blog.agibank.com.br/?s=Pix
    - https://blog.agibank.com.br/?s=Pix#

- Em alguns casos:
  - A busca não é executada mesmo com o campo preenchido

---

### 🎯 Impacto

- Testes automatizados se tornam instáveis (flaky)
- Comportamento inconsistente também pode afetar usuários reais
- Dificulta a validação confiável da busca

---

### 🧪 Evidência técnica

- Problema reproduzido com:
  - Selenium WebDriver
  - Cypress (inclusive com `force: true`)

- Persistência do problema mesmo com:
  - Esperas explícitas
  - Interações via JavaScript
  - Simulação de eventos de input

---

### 💡 Possíveis causas

- Manipulação assíncrona do DOM
- Re-renderização do componente de busca
- Eventos de busca não disparados corretamente
- Falha no controle de estado do input

---

## 🚨 Sugestões de melhoria para a aplicação

- Garantir que o campo de busca:
  - esteja sempre disponível no DOM
  - não dependa exclusivamente de eventos JavaScript

- Evitar redirecionamentos inesperados para `/#`

- Garantir que:
  - o valor digitado seja corretamente persistido
  - os eventos de busca sejam disparados de forma consistente

- Adicionar identificadores estáveis (ex: `id`, `data-testid`)

---

## 🧪 Cenários de teste

### ⚠️ Busca sem preenchimento (comportamento inesperado)

````gherkin
Scenario: Buscar sem digitar termo não bloqueia a busca  
Given que o usuário acessa o blog  
When ele clica na lupa e envia a busca sem digitar nada  
Then o sistema retorna resultados sem validar o campo obrigatório
````

### 📌 Observação

- O comportamento esperado seria a exibição de uma mensagem como:  
  "Por favor, digite um termo para buscar"

- Porém, atualmente:
  - O sistema permite busca vazia
  - Retorna resultados genéricos
  - Não valida a obrigatoriedade do campo

- Este cenário indica uma possível melhoria de UX e regra de negócio

---

## 🚧 Melhorias futuras

- Suporte a múltiplos navegadores (Firefox, Edge)
- Implementação de relatórios (Allure / ExtentReports)
- Execução paralela de testes
- Adoção de ferramentas modernas como Playwright

---

## 🧑‍💼 Autor

Joice Martins