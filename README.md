
![Alt text](https://i.imgur.com/kNp1b8M.png[/img])

*Aplicativo desenvolvido para você conseguir acompanhar sua encomenda sem precisar ir ao site do correio!
Simples e intuitivo*

# Como funciona?

- Na tela inicial do aplicativo, será solicitado o código de rastreio
- Insira e clique em "FIND"

- Em seguida ela te enviará para uma segunda tela onde mostrará todas as etapas percorridas da sua encomenda

- Utilizei o SharedPreferences para salvar o ultimo código rastreado.

- O dados da encomenda foi obtidos utilizando conexão a API e consumidas usando retrofit
- E exibidas no app utilizando o RecyclerView

Foi utilizado o padrão de arquitetura MVVM.

API: https://proxyapp.correios.com.br/v1/sro-rastro/{codigo}

# Biblioteca e Arquitetura

RecyclerView

SharedPreferences

Retrofit

MVVM
