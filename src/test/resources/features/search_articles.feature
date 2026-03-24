Feature: Busca de artigos

  Scenario: Deve retornar artigos ao buscar termo válido
    Given que o usuário acessa o blog
    When ele busca por "PIX"
    Then deve ver resultados relacionados

  Scenario: Deve exibir mensagem ao buscar termo inválido
    Given que o usuário acessa o blog
    When ele busca por "abcd1234"
    Then deve exibir mensagem "Lamentamos, mas nada foi encontrado para sua pesquisa, tente novamente com outras palavras"

  Scenario: Exibir campo de busca ao clicar na lupa
    Given que o usuário acessa o blog
    When ele clica na lupa
    Then o campo de busca deve ser exibido para digitar o termo

  #realiza a busca informação aleatória
#  Scenario: Buscar sem digitar termo exibe mensagem de alerta
#    Given que o usuário acessa o blog
#    When ele clica na lupa e envia a busca sem digitar nada
#    Then deve exibir mensagem "Por favor, digite um termo para buscar"