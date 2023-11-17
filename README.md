<table>
<tr>
<td>
<a href= "https://www.rockwellautomation.com/pt-br.html"><img src="./docs/img/logo-rockwell.png" alt="Rockwell Automation" border="0" width="70%"></a>
</td>
<td><a href= "https://www.inteli.edu.br/"><img src="./docs/img/logo-inteli.png" alt="Inteli - Instituto de Tecnologia e Liderança" border="0" width="30%"></a>
</td>
</tr>
</table>

# Projeto: *Pegasus*

# Grupo: *Constelação*

# Integrantes:

* [Celine Pereira de Souza](celine.souza@sou.inteli.edu.br)
* [José Vitor Alencar Silva](jose.silva@sou.inteli.edu.br)
* [Lucas Oliveira de Medeiros Galvão](lucas.galvao@sou.inteli.edu.br)
* [Samuel Lucas de Almeida](samuel.almeida@sou.inteli.edu.br)
* [Thomaz Klifson Falcão Barboza](thomaz.barboza@sou.inteli.edu.br)
* [Yago Phellipe Matos Lopes](yago.lopes@souinteli.edu.br)

# Descrição

Junto com a Rockwell Automation, o grupo Constelação está desenvolvendo uma ferramenta para automatizar rotas industriais. Atualmente, há um grande esforço para determinar as melhores rotas para vários fluxos de indústria que buscam a Rockwell. Além disso, as soluções existentes são rígidas, exigindo modificações no código do Controlador Lógico Programável (PLC) sempre que uma nova rota precisa ser criada ou uma existente precisa ser alterada. Nesse contexto, o projeto Pegasus é uma aplicação que resolve esse problema usando uma interface visual poderosa e algoritmos automatizados.

Este projeto simplifica consideravelmente a criação de diagramas complexos com propriedades, tornando a representação e criação de diagramas P&ID mais acessíveis e intuitivas. Além disso, oferece eficiência ao encontrar o caminho mais apropriado entre dois pontos nessas estruturas, graças ao algoritmo de Backtracking, otimizando a navegação e a tomada de decisões.

# Configuração para desenvolvimento

Nesta seção, apresentaremos o passo a passo necessário para executar nossa aplicação:

Passo 1: Baixar o Docker

Para usar nossa aplicação, você precisará fazer o download do Docker. Para isso, siga os passos abaixo:

Acesse a página oficial do Docker. Na página oficial, selecione a opção de download correspondente ao seu sistema operacional. Por exemplo, se você estiver usando o sistema operacional Windows, baixe a opção para sistema windows.
![Tex](https://www.docker.com/wp-content/uploads/2023/06/meta-image-download-docker-desktop-1110x580.png)


Segundo passo:

Abra o terminal em qualquer local, entre nas pastas codigo/backend/docker e execute o seguinte comando para iniciar a imagem no Docker:

```bash
    docker-compose up -d
```
Este comando ativa o Docker e inicia o Neo4j com as configurações especificadas.

Terceiro passo:

Com o projeto aberto no Visual Studio Code (VSCode), siga estas etapas:

Abra o arquivo "PegasusApplication.java".

No próprio arquivo, clique no botão "Run" para executá-lo.


# Documentação

Os arquivos da documentação deste projeto estão na pasta [/docs](/docs).


# Artigo

Os arquivos do artigo estão na pasta [/artigo](/artigo). Um arquivo gerado no formato PDF deverá ser anexado a cada *release* do projeto.

# Tags
- *Sprint 1*
    - Análise SWOT
    - Descrição da solução a ser desenvolvida
    - Value Proposition Canvas
    - Matriz de riscos
    - Contexto da indústria + 5 forças de porter
    - Personas
    - User Stories
    - Modelo matemático para o problema e representação visual, usando Neo4J.
- *Sprint 2*
    - Buscar Icones de bibliotecas
    - Fazer o figma da Aplicação Web
    - Artigo Cientifico
    - Cadastro de Usuário
    - Repositório do Código
    - Cadastro de valvulas/tanques
    - Criação do banco de dados e suas relações
    - Cadastro de projeto
    - Gerenciamento do grafo
    - Entendimento do contexto do problema
    - Criar algoritmo para o grafo
 - *Sprint 3*
    - Front-end: projetos
    - Algoritmo: json
    - Algoritmo: lógica
    - Artigo: motivação, metodologia e bibliografia
    - Front-end: algoritmo
    - Front-end: tela de home
    - Front-end: login/cadastro
    - Front-end: edição, Navbar e rotas

- *Sprint 4*
    - Artigo Científico - Conclusões e Resultados
    - Repositório de Código da Aplicação
    - Integração Aplicada
    - Tratamento de respsotas
    - Complexidade e Corretude

- *Sprint 5*
    - Mudar válvulas para um SVG ou PNG
    - Backend - Arrumar criação de nós e arestas
    - Backend - Terminar autenticação
    - Algoritmo - Revisar e arrumar o backtracking
    - Backend - Criação de nós e arestas em massa
    - Backend - Fazer função para exportar o Excel com o diagrama das válvulas
    - Frontend - Adicionar criação de nó personalizado
    - Backend - Refatorar códigos relacionados a arestas e nós
    - Frontend - Página de edição de usuário
    - Padronização repositório GIT
    - Frontend - Página de loading
    - Ajeitar frontend e backend para criar nós e arestas em massa ao salvar projeto
    - Revisão do artigo e da documentação
    - Frontend - Página de não autorizado
    - Frontend - Adicionar parte de predição à página de edição de grafos


# Licença
Esse projeto faz uso da licença [Application 4.0 International](https://creativecommons.org/licenses/by/4.0/?ref=chooser-v1).


**Licença**

<img src="https://mirrors.creativecommons.org/presskit/icons/by.xlarge.png" alt="Alt text" width="60px" height="60px" />

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Cc.logo.circle.svg/800px-Cc.logo.circle.svg.png" alt="Alt text" width="60px" height="60px" />

[Application 4.0 International](https://creativecommons.org/licenses/by/4.0/?ref=chooser-v1).



