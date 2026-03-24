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
mvn clean test -Dheadless=true
```

---
## 🔄 Integração Contínua (CI)
O projeto utiliza GitHub Actions para execução automática dos testes.

✔ O que o CI faz:
- Executa testes a cada push ou pull request
- Roda em ambiente Linux (Ubuntu)
- Executa navegador em modo headless
- Gera screenshots em caso de falha
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

### 🔹 1. Redirecionamento para URL com /#

O site apresenta comportamento dinâmico, redirecionando para:

https://blog.agibank.com.br/#

Impactos:

- Elementos não disponíveis imediatamente
- Falhas ao clicar na lupa ou menu
- TimeoutException ao aguardar elementos que não estavam visíveis no DOM

✔ Solução/Alternativas:

- Interação prévia com elementos (Ex. menu "Stories") para garantir renderização do DOM
- Uso de esperas explícitas (WebDriverWait)
- Validação de visibilidade e interatividade antes de ações

---
### 🔹 2. Diferença entre execução local e CI

Problemas:

- Testes passavam localmente, mas falhavam no CI
- SessionNotCreatedException
- element not interactable

✔ Solução:

- Instalação do Google Chrome no CI
- Execução headless
- Definição do tamanho da janela:

```Java
options.addArguments("--window-size=1920,1080");
```

Remoção do maximize() em headless

---
## 🔹 3. Screenshots não eram gerados

✔ Solução:

- Criação automática da pasta target/screenshots
- Captura no momento da falha
- Upload no GitHub Actions

## 🚧 Melhorias futuras
- Suporte a múltiplos navegadores (Firefox, Edge)
- Implementação de relatórios (Allure / ExtentReports)
- Execução paralela de testes
- Testes mais robustos para navegação do site

## 🧑‍💼 Autor

Joice Martins