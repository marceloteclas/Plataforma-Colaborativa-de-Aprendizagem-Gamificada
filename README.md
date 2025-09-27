# Trabalho de Padrões de Projeto – Plataforma-Colaborativa-de-Aprendizagem-Gamificada
- **Instituição:** IFBA - Instituto Federal da Bahia
- **Curso:** Análise e Desenvolvimento de Sistemas (ADS)
- **Disciplina:** Padrões de Projeto 
- **Projeto:** Plataforma-Colaborativa-de-Aprendizagem-Gamificada
- **Professor:** Felipe de Souza Silva
- **Semestre:** 5
- **Ano:** 2025.1

# 📌 Projeto:Plataforma-Colaborativa-de-Aprendizagem-Gamificada

## 🎯 Objetivo

Este projeto simula uma plataforma colaborativa de aprendizagem gamificada, onde alunos participam de desafios, acumulam pontos, conquistam medalhas e interagem em um ambiente educativo.
O sistema aplica padrões de projeto (GoF) e princípios SOLID, com foco em modularidade e extensibilidade.

## Integrantes do Projeto

<table>
  <tr>
        <td align="center">
      <img src="https://avatars.githubusercontent.com/u/129338943?v=4" width="100px;" alt="Foto da Integrante Ronaldo"/><br />
      <sub><b><a href="https://github.com/Ronaldo-Correia">Ronaldo Correia</a></b></sub>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/114780494?v=4" width="100px;" alt="Foto da Integrante Marcelo"/><br />
      <sub><b><a href="https://github.com/marceloteclas">Marcelo Jesus</a></b></sub>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/129909472?v=4" width="100px;" alt="Foto da Integrante Franklin"/><br />
      <sub><b><a href="https://github.com/FranklinFelixADS">Franklin Felix</a></b></sub>
    </td>

  </tr>
</table>

## 🏗️ Arquitetura do Sistema
 
```
app/
└── MainConsole.java
    - Ponto de entrada do sistema. Orquestra os controladores, serviços e sessão.

view/
└── MenuPrincipal.java
    - Interface textual com o usuário. Exibe o menu principal e captura entradas.

controller/
├── MainController.java
├── DesafioController.java
├── HistoricoController.java
└── RelatorioController.java
    - Controladores que conectam a interface com os serviços e lógica de negócio.

service/
├── UsuarioService.java
├── DesafioService.java
└── ConquistaService.java
    - Camada de serviços que encapsula regras de negócio.

model/
├── Usuarios.java (abstract)
├── Aluno.java
├── Professor.java
├── Visitante.java
└── UsuarioFactory.java
    - Representação dos tipos de usuários e fábrica para instanciá-los.

repository/
├── IUsuarioRepositorio.java
└── UsuarioRepositorioMemoria.java
    - Persistência em memória dos dados de usuários.

desafios/
├── Desafio.java
├── DesafioRepositorioMemoria.java
├── PontuacaoStrategy.java (interface)
├── PontuacaoPorTempo.java
├── PontuacaoPorDificuldade.java
├── PontuacaoDecorator.java
├── PontuacaoDoubleXP.java
├── PontuacaoStreak.java
└── NotificadorConsole.java
    - Lógica de desafios, estratégias de pontuação e notificações.

conquistas_reestruturadas/
├── Conquista.java (interface)
├── GerenciadorConquistas.java
├── ConquistaObserver.java (interface)
├── AvaliadorConquistas.java
├── ConquistaService.java
├── MedalhaComposite.java
└── ConquistaComposite.java
    - Sistema de conquistas e medalhas, com uso de padrões como Composite e Observer.

historico/
├── Comando.java (interface)
├── HistoricoDeComandos.java
├── HistoricoDeConquistas.java
└── ResponderDesafioCommand.java
    - Registro e desfazer de ações via padrão Command.

relatorios/
├── RelatorioStrategy.java (interface)
├── RelatorioCSV.java
├── RelatorioJSON.java
├── RelatorioPDF.java
├── RelatorioFacade.java
└── RelatorioSistema.java
    - Geração de relatórios em CSV, JSON e PDF via Strategy e Facade.

infra/
└── Sessao.java
    - Gerenciamento da sessão do usuário logado.

adaptador/ e api_externa/
└── RankingGlobalAdapter.java / RankingGlobalAPI.java
    - Integração com fontes externas de dados (ex: ranking global).

```


## ⚙️ Padrões de Projeto Utilizados

### 🔨 Padrões de Criação
- Singleton → Gerenciamento global da sessão do usuário (Sessao)
- Factory Method → Instanciação de perfis de usuário (UsuarioFactory)

### 🧱 Padrões Estruturais
- Decorator → Aplicação de bônus e modificadores de pontuação (PontuacaoDecorator, PontuacaoDoubleXP)
- Composite → Organização hierárquica de conquistas e medalhas (ConquistaComposite, MedalhaComposite)
- Facade → Geração simplificada de relatórios em múltiplos formatos (RelatorioFacade)
- Adapter → Integração com sistemas externos de ranking (RankingGlobalAdapter)

### 🔁 Padrões Comportamentais
- Strategy → Estratégias de cálculo de pontuação (PontuacaoStrategy, PontuacaoPorTempo, PontuacaoPorDificuldade)
- Observer → Notificação automática de conquistas desbloqueadas (ConquistaObserver, GerenciadorConquistas)
- Command → Registro e reversão de ações do usuário (Comando, ResponderDesafioCommand, HistoricoDeComandos)

## 🚀 Como Executar

No main da Aplicação pressione em Run

Ou
Dentro da pasta src/, execute:

 ```
  javac app/MainConsole.java
 ```
 ou
  ```
  .\build.bat

   ```
2. Rodar

 ```
  java -cp out app.MainConsole

 ```
 ou
  ```
  build.bat

  ```
   
## Diagramas UML

- [Diagrama de Classes](uml/Classes.mermaid)
- [Diagrama de Sequência](uml/diagrama_sequencia.mermaid)

   
