**Перечень автоматизируемых сценариев**

_Позитивные_:

-успешная покупка тура с оплатой по карте

-успешная покупка тура в оплатой в кредит

_Негативные_:
- покупка тура с оплатой по карте:
   - невалидные:
      - номер карты
      - данные месяца
      - данные года
      - данные владельца
      - данные кода карты
- покупка тура с оплатой в кредит:
    - невалидные:
        - номер карты
        - данные месяца
        - данные года
        - данные владельца
        - данные кода карты
      
**Перечень используемых инструментов**

          selenide
        - rest-assured
        - docker
        - Gradle
        - Node.js.
        - MySQL
        - PostgreSQL

**Перечень и описание возможных рисков при автоматизации**

        - не откроется приложение на указанном порту
        - не будет поддержки баз данных
        - не запустится симулятор банковского сервиса

**План сдачи работ** 

        - когда будут авто-тесты: 12 августа
        - результаты их прогона: 16 августа
        - отчёт по автоматизации:18 августа