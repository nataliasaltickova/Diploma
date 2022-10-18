1.Задача -автоматизировать тестирование веб-сервиса -приложения по покупке тура.

Составлено и проведено 34 тест-кейсов, в том числе
14-успешных, 20 -неуспешных.

![alt text](C:/Users/79271/Desktop/МОЕ/testing/test Summary.png)



Отчет находится в файле index.html 
данного проекта:/Diploma/build/reports/tests/test/index.html


Причина  неуспешных тестов- несоответствие фактического результата ожидаемому.    
По результатам этих тестов составлено  20 баг-репортов.

2.Задача- проверить поддержку двух СУБД.

По результатам запросов в базы данных приложение поддерживается :
СуБД -MySQL.

Поддержка СуБД  PostgreSQL тоже есть, только с немного измененными настройками в файле application.properties:

в задании было указано "spring.datasource.url=jdbc:mysql://localhost:3306/**_app_**"

фактически использовалось spring.datasource.url=jdbc:postgresql://localhost:5432/**_postgres_**"

![alt text](C:/Users/79271/Desktop/МОЕ/testing/docker starting.png)

Для проверки  информации в базе данных о том, успешно ли был совершён платёж и каким способом, использовался инструмент DBeaver

![alt text](C:\Users\79271\Desktop\МОЕ\testing\DBeaver mysql.png)

![alt text](C:\Users\79271\Desktop\МОЕ\testing\DBeaver postgresql.png)


