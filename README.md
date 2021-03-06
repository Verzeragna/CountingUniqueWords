# CountingUniqueWords
Приложение разработано по тестовому заданию SimbirSoft. 

**Для чего:** вывод статистики по количеству уникальных слов в тексте. Текст извлекается по адресу указанной html-страницы. 

**Инструкция по запуску и использованию:**  
1. Для запуска приложения необходимо наличие JVM. Перейдите на сайт https://www.java.com и установите последнюю версию JRE.
2. Для сохранения статистики используется база данных MySQL. Скачать последнюю версию для вашей системы можно тут https://www.mysql.com/.  
  Затем необходимо создать пользователя user с паролем user, используя следующий SQL код:  
  CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';  
  GRANT ALL PRIVILEGES ON * . * TO 'user'@'localhost';  
  После этого создайте соединение и укажите созданного пользователя.
  Далее необходимо создать базу данных и таблицу (используйте следующий SQL код):  
  CREATE DATABASE  simbirsoft_db;  
  USE simbirsoft_db;  

    CREATE TABLE statistics (  
    id int NOT NULL AUTO_INCREMENT,  
    url varchar(250),  
    word varchar(50),  
    count_word int,  
    PRIMARY KEY (id));  
    
3. Теперь все готово к запуску приложения.  
4. Приложение является консольным, поэтому для взаимодействия используются команды:  
  **search** - осуществить поиск повторяющихся слов;  
  **show data** - получить все записи базы данных;  
  **get data** - получить записи по URL;  
  **clear** - очистить базу данных;  
  **delete** - удалить найденные слова из базы данных с определенным URL;  
  **help** - показать доступные команды;  
  **exit** - выход из приложения.  
  
  Список доступных команд выводится на экран сразу после запуска приложения.
    
