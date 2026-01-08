# quarkus-crud
Crud usando Quarkus.


# Por que não usou Beans Validation?

Limitação. Adicionar um código de erro customizado não é possível, ou seja, não é possível adicionar um código de erro customizado para cada validação. 

Retornar um código de erro customizado para cada validação é extremamente útil quando se está escrevendo testes para API, pois permite que se teste cada validação individualmente, garantindo que o erro retornado é exatamente o erro provocado pelo teste.