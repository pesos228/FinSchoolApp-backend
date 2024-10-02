# FinSchoolApp-backend

## Описание
FinSchoolApp-backend — это серверная часть платформы для финансового образования школьников. Этот backend предоставляет API, необходимый для работы мобильного приложения [FinSchoolApp](https://github.com/karalesus/FinSchoolApp).

## Настройка загрузки файлов
Для загрузки файлов необходимо указать следующие параметры в `application.properties`:

- **storage.location**: путь к директории для хранения загруженных файлов.
- **storage.file-base-url**: базовый URL для доступа к загруженным файлам.

Пример конфигурации:

```properties
storage.location=./uploads
storage.file-base-url=http://localhost:9090/file
