# ДЗ №1 Открытая школа разработчиков на JAVA 4 поток
Реализация системы учета времени выполнения методов.

# Задание:
Разработать систему учета времени выполнения методов в приложении с использованием Spring AOP. Система должна быть способна асинхронно логировать и анализировать данные о времени выполнения методов.

# Требования:
Создайте аннотации @TrackTime и @TrackAsyncTime, которые можно применять к методам для отслеживания времени их выполнения.
Реализуйте аспекты, используя Spring AOP, для асинхронного и синхронного отслеживания времени выполнения методов, помеченных соответствующими аннотациями.
Создайте сервис, который будет асинхронно сохранять данные о времени выполнения методов в базе данных.
Реализуйте REST API для получения статистики по времени выполнения методов (например, среднее время выполнения, общее время выполнения) для различных методов и их групп.
Настройте приложение с помощью конфигурации Spring для включения использования AOP и асинхронной обработки данных.

# Реализация:
Реализован сервис, который сохраняет в таблицу **locomotives** данные о новых локотивах с помощью 3-х методов:
* public void addLocomotive(Locomotive locomotive) - добавляет новый локомотив в таблицу;
* public void addLocomotiveWithCheck(Locomotive locomotive) - добавляет новый локомотив в таблицу с имитацией проверки данных путем 2 секундной задержки выполнения метода;
* public void addLocomotiveWithCheckTask() - периодическая задача, которая каждые 10 секунд добавляет новый локомотив в таблицу
с имитацией проверки данных путем 13 секундной задержки выполнения метода, т.е. каждый запущенный процесс добавления данных о локомотиве
будет пересекаться с другим ранее запущенным процессом добавления данных.

Аспекты расположены в пакете:
   ```
   src/main/java/t1/school/task1/aspect
   ```

Таблицы БД создаются с помощью скрипта Liquibase:
   ```
   src/main/resources/db/changelog/liquibase.xml
   ```

Unit-тесты расположены в пакете:
   ```
   src/test/java/t1/school/task1
   ```

# REST API
Для взаимодействия с контроллером анализа времени выполнения методов и получения информации о методах контроллера используется Swagger, который доступен по адресу:
   ```
   http://server:port/swagger-ui/index.html#/
   ```
где:
- "server" - ip-адрес сервера, на котором развернуто приложение;
- "port" - порт, на котором доступно приложение.

Например при развертывании приложения и БД с помощью Docker будет следующий адрес:
   ```
   http://127.0.0.1:18080/swagger-ui/index.html#/
   ```

# Запуск приложения для проверки
1. ОС Windows 10: приложение можно запустить вместе с СУБД Postgresql с помощью Docker. Для этого необходимо:
- Скачать архив с master-ветки репозитория проекта по следующему адресу:
   ```
   https://github.com/RSVarfolomeev/t1_task1/archive/refs/heads/master.zip
   ```
- Из корневой папки проекта в командной строке выполнить команду для развертывания проекта:
   ```
   docker-compose up
   ```
- После запуска проекта приложение будет доступно на порту 18080, СУБД Postgresql на порту 15432.

2. Linux (CentOS 7): такой же способ запуска проекта, как было описано выше, но для Linux на примере CentOS 7:
- По нижеуказанной инструкции развертываем Docker:
   ```
   https://docs.docker.com/engine/install/centos/
   ```
- Создаем папку для клонирования проекта, клонируем master-ветку репозитория с проектом:
   ```
   mkdir /opt/app
   git clone https://github.com/RSVarfolomeev/t1_task1.git /opt/app
   ```
- Переходим в корневую папку проекта и развертываем проект с помощью docker compose:
   ```
   cd /opt/app
   docker compose up
   ```
- После запуска проекта приложение будет доступно на порту 18080, СУБД Postgresql на порту 15432.

3. ОС Windows 10: при отсутствии Docker приложение можно запустить с помощью IDE IntelliJ IDEA.
- Также скачиваем архив с master-ветки репозитория проекта по следующему адресу:
   ```
   https://github.com/RSVarfolomeev/t1_task1/archive/refs/heads/master.zip
   ```
- При отсутствии устанавливаем с дефолтными настройками СУБД Postgresql версии 16.x, JDK 17, IntelliJ IDEA.
   ```
   https://postgrespro.ru/windows
   https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
   https://www.jetbrains.com/idea/download/?section=windows
   ```
- В IntelliJ IDEA открываем распакованный проект и запускаем.
- После запуска проекта приложение будет доступно на порту 8080, СУБД Postgresql на дефолтном порту 5432.
---
#### Используемый стек технологий:

---

Java 17, Spring Boot, Spring Web, Spring Data Jpa, Spring AOP, Postgresql, Liquibase, Swagger, Docker, Maven
