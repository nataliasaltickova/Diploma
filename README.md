
# Diploma
На компьютере должны быть установлены следующие ПО:
java11, npm , Docker,IntelliJ IDEA
1. Открываем проект в  IDEA  
2. Для запуска приложения необходимо запустить docker контейнер командой
 "docker-compose up" из директории, где расположен файл конфигурации docker-compose.yml

3. В терминале из корневой папки:
запускаем приложение командой:
 'java -jar aqa-shop.jar'    
4. В терминале №2 для перехода в директорию 'gate-simulator'
выполняем команду: 
'cd gate-simulator'    
5. Затем выполняем команду:
 'npm start'    
6. В директории src в пакете  ru.netology открываем файл 'BuyingTourTest'    
7. Запускаем тесты в классе 'BuyingTourTest'