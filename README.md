<p align="center">
  <img width="400" height="250" src="https://github.com/xlimitan/backend_websait_ads/assets/123659683/530ce28b-62a5-4279-a0a0-5bc57cf58ad4)">
</p>

# Backend сайта объявлений.
_____
# Команда №4:
- [Семен Голенко](https://github.com/xlimitan)
- [Андрей Свидинский](https://github.com/Svidinskiy)
- [Анатолий Гракович](https://github.com/Frokolll111)
_____
# Описание:
## Реализованны сдедующий функции для сайта:
- Регистрация и авторизация пользователей;
- Распределение ролей между пользователями: пользователь и администратор;
- CRUD-операции для объявлений и комментариев: администратор может удалять или редактировать все объявления и комментарии, а пользователи — только свои;
- Возможность для пользователей оставлять комментарии под каждым объявлением;
- Показ, сохранение и изменение картинок объявлений, а также аватарок пользователей;
- Показ всех объявлений неавторизованным пользователям сайта;
- Редактирование профиля пользователя: возможность изменить имя, фамилию, номер телфона и пароль и аватар;
- Редактирвоание объявления: возможность изменить название, описание, цену и картинку;
- Редактирование текста комментариев;
_____
### В проекте используются технологии:
- Java 11
- Maven
- Spring Boot
- Spring Web
- Spring Data
- Spring JPA
- Hibernate
- PostgreSQL
- Liquibase
- Swagger Ui
- Lombok
- Docker
- Mapstruct
_____
## Рекомендации по запуску проекта:
- Клонировать проект в среду разработки.
- Скачать, установаить и запустить программу Docker.
- Для запуска frontend, запустите командную строку от имени администратора и прописать команду: "docker run -p 3000:3000 --rm ghcr.io/bizinmitya/front-react-avito:v1.19".
- Настроить БД и прописать значения в файле application.properties.
- Запустить метод main в файле AnimailsShelterApplication
- Перейти по url http://localhost:3000/
- Чтобы использовать REST API, в браузере введите http://localhost:8080/swagger-ui/index.html
