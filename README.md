LUNAR

    launch {
      int a;
      int i;
      int f;
      char b;
      real c;
      string d;
      bool e;
      
      i = 0;
      e = true;
      f = a + i;
      
      if(e == true & i = 0) {
        d = "é verdade";
      }
      
      loop(i to 10) {
        a++;
      }
      
      until(a < 0 | e == false) {
        a--;
      }
      
      ..comentario de uma lihna
      
      ...
      comentario
      de
      multiplas
      linhas
      ...
      
    }

**Definições da Linguagem - LUNAR**

- A linguagem diferencia letras maiúsculas de minúsculas (case sensitive). Por exemplo, “Int” é diferente de “int”. 
- A análise sintática começa quando é encontrada a palavra reservada “launch” seguida da abertura de chaves “{“. 
- Um ID deve exclusivamente começar um uma letra. Do segundo caracter em diante é possível utilizar dígitos e o underline “_”.
- A declaração de variáveis deve ser feita separadamente de sua atribuição, e cada atribuição deve ser feita em um comando independente.
- A linguagem suporta números inteiros ou racionais, podendo ser positivos e negativos. Caso o número tenha um sinal associado a ele, não deve haver espaço entre eles. Com o espaço será considerada uma operação aritmética. A parte decimal de um número real começa a partir de um ponto ( . ).
- Um comentário de uma linha deve iniciar com dois pontos finais (“..”). Para comentários de múltiplas linhas devem ser usados três pontos no começo e três no final (“… comentário …”).
- É obrigatório o uso de chaves após as palavras launch, if, else, loop, until.
- Quando é encontrado um erro no código, tenta-se voltar ao ultimo(a) if, loop, until, comentário, declaração ou atribuição.

- Delimitadores de tokens:

| Símbolo         | Descrição               |
| --------------- | ----------------------- |
| ' '             | Espaço vazio            |
| \n              | Quebra de linha         |
| \t              | Tabulação               |
| ,               | Vírgula                 |
| ;               | Ponto e vírgula         |
| ( )             | Parênteses              |
| { }             | Chaves                  |
| + - * / %       | Operadores aritiméticos |
| & |             | Operadores lógicos      |
| > < >= <= == != | Operadores relacionais  |
| -- ++           | Operadores unários      |

- Palavras reservadas:

| Palavra | Utilidade                                        |
| ------- | ------------------------------------------------ |
| launch  | Inicio do programa                               |
| if      | Inicio de um comando de seleção                  |
| else    | Desvio de fluxo no comando de seleção            |
| loop    | Inicio de uma repetição (estilo for)             |
| to      | Parâmetro do loop                                |
| down    | Parâmetro do loop                                |
| until   | Inicio de uma repetição com a condição invertida |
| int     | Declaração de uma variável inteira               |
| real    | Declaração de uma variável real                  |
| char    | Declaração de uma variável de caracter           |
| bool    | Declaração de uma variável boleana               |
| true    | Conteúdo de variáveis booleanas                  |
| false   | Conteúdo de variáveis booleanas                  |

- Operadores aceitos na linguagem:

| Aritméticos |                  |
| ----------- | ---------------- |
| +           | Adição           |
| -           | Subtração        |
| *           | Multiplicação    |
| /           | Divisão          |
| %           | Resto da divisão |
| ++          | Incremento de 1  |
| —           | Decremento de 1  |

| Relacionais |                |
| ----------- | -------------- |
| >           | Maior          |
| >=          | Maior ou igual |
| <           | Menor          |
| <=          | Menor ou igual |
| ==          | Igualdade      |
| !=          | Diferença      |
| !           | Negação        |

| Lógicos |           |
| ------- | --------- |
| &       | e lógico  |
| pipe    | ou lógico |

- Definição dos tokens e seus respectivos lexemas:

| Lexema                        | Token       | Descrição             |
| ----------------------------- | ----------- | --------------------- |
| LAUNCH                        | T_LAUNCH    |                       |
| IF                            | T_IF        |                       |
| ELSE                          | T_ELSE      |                       |
| LOOP                          | T_LOOP      |                       |
| UNTIL                         | T_UNTIL     |                       |
| INT, REAL, CHAR, BOOL, STRING | T_TYPE      |                       |
| letra( letra ou digito + _ )*  | T_ID        |                       |
| (+ou-)digito?ˆ+(.digitoˆ+)?    | T_NUM       |                       |
| TRUE, FALSE                   | T_BOOL      |                       |
| ,                             | T_COMMA     |                       |
| ;                             | T_SEMICOLON |                       |
| {                             | T_BRACESL   |                       |
| }                             | T_BRACESR   |                       |
| (                             | T_PARL      |                       |
| )                             | T_PARR      |                       |
| “="                           | T_ATR       |                       |
| +, -, *, /, %                 | T_OPA       | Operador Aritimético  |
| < , <=, >, >=, == , !=        | T_OPR       | Operador Relacional   |
| &, pipe                        | T_OPL       | Operador Lógico       |
| ++, —                        | T_OPU       | Operador Unário       |
| !                             | T_OPN       | Operador de negação   |
| ..                            | T_SLCOM     | Single-line comment   |
| …                             | T_MLCOM     | Multiple line comment |

