ВАЖНО!!! Когда формируете тело запроса для PostMan - названия полей в кавычках.
Пример:
{
    "userName" : "user 1",
    "userEmail" : "user1@mail.com",
    "password" : "user1"
}

Когда запрос идет из файла JS - названия полей без кавычек.
Пример:
{
    userName : "user 1",
    userEmail : "user1@mail.com",
    password : "user1"
}




Авторизация и регистрация по e-mail:

Для регистрации пользователя по e-mail необходимо отправить POST-запрос
по адресу: /api/auth/register
В тело запроса нужно вложить объект:
{
    "userName" : "user 1",
    "userEmail" : "user1@mail.com",
    "password" : "user1"
}
Если регистрация прошла успешно - в ответе придет статус 200 и ссылка для активации.
Вот пример ссылки:
{
    "activationLink" : "http://lcalhost:8085/api/auth/activate?user_id=1&code=dsijckco-scjsncjsn"
}
Ссылка для активации отправляется на почту.
Для активации аккаунта пользователь должен перейти по ссылке. Если активация прошла успешно
от сервера придет статус 200 и почта пользователя:
{
    "email" : "user1@mail.com"
}
После этого аккаунт активирован и можно входить по почте и паролю

Если пота пользователя указанна некорректно - регистрация невозможна. Так как
в случае ошибки при отправке на почту - удаляется сам аккаунт и ссылка активации.
Если нужна тестовая регистрация без отправки на почту - нужно в файле
\src\main\java\com\example\Diplomna\auth\services\MailService.java закомментировать метод 
sendLink() на 39-й строке. Тогда письмо на почту не будет отправляться.

Для авторизации нужно отправить POST-запрос по адресу:
/api/auth/auth
В тело запроса нужно вложить объект:
{
    "email" : "user1@mail.com",
    "password" : "user1"
}
Если авторизация прошла успешно от сервера придет статус 200 и объект:
{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMUBtYWlsLmNvbSIsImlhdCI6MTcwMjI4MzE3MiwiZXhwIjoxNzAyMzY5NTcyfQ.pmCfiES_I0lijzE2UBIN2fGe_v2ZAKDTBphFpG3YSLk",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMUBtYWlsLmNvbSIsImlhdCI6MTcwMjI4MzE3MiwiZXhwIjoxNzEwOTIzMTcyfQ.a6faYsdPMGzK3HL94lxSwdw20jHbTXAOXt4bjoXpQ9Q",
    "user": {
        "id": 1,
        "email": "user1@mail.com",
        "displayName": "user 1",
        "photoUrl": null
    }
}




Google авторизация:

После того как сработает авторизация от Google придет result. Из result нужно получить user.
Затем отправить запрос по адресу:
/api/google/login
В тело запроса вложить объект:
{
	"uid": user.providerData[0]["uid"],
        "email": user.email,
        "emailVerified": user.emailVerified,
        "displayName": user.displayName,
        "photoUrl": user.photoURL
}
Если авторизация прошла успешно от сервера придет статус 200 и объект:
{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMUBtYWlsLmNvbSIsImlhdCI6MTcwMjI4MzE3MiwiZXhwIjoxNzAyMzY5NTcyfQ.pmCfiES_I0lijzE2UBIN2fGe_v2ZAKDTBphFpG3YSLk",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMUBtYWlsLmNvbSIsImlhdCI6MTcwMjI4MzE3MiwiZXhwIjoxNzEwOTIzMTcyfQ.a6faYsdPMGzK3HL94lxSwdw20jHbTXAOXt4bjoXpQ9Q",
    "user": {
        "id": 1,
        "email": "user1@mail.com",
        "displayName": "user 1",
        "photoUrl": null
    }
}



После любой успешной авторизации (email, Google) accessToken, user - хранить в куках или localStorage.
refreshToken хранить в куках с флагом http-only.
Время жизни куков неограниченно.
accessToken живет сутки.
refreshToken живет 100 дней.


Для доступа к ресурсам при отправке запроса в заголовки нужно добавить элемент:
ключ - "Authorization"
значение - "Bearer {accessToken}"

Если при отправке запроса пришел статус 403 - скорее всего закончился срок жизни accessToken'а.
Нужно отправить POST-запрос на обновления токенов по адресу
/api/auth/refresh-auth
В тело запроса нужно вложить объект:
{
    "refreshToken" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMUBtYWlsLmNvbSIsImlhdCI6MTcwMjIzOTAyMCwiZXhwIjoxNzEwODc5MDIwfQ.tOmep3NIWm2lKYvKbSOc8jHjTGD9OjHb88kFm2pASQQ"
}
Если обновление прошло удачно - от сервера придет статус 200 и объект:
{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMUBtYWlsLmNvbSIsImlhdCI6MTcwMjI4NjM0MSwiZXhwIjoxNzAyMzcyNzQxfQ.g8gBU9jWzPmQVcSQs4VK_t7NJH3ptccP74G_hJQDKWo",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMUBtYWlsLmNvbSIsImlhdCI6MTcwMjI4NjM0MSwiZXhwIjoxNzEwOTI2MzQxfQ.kcUuR2SOqukdGweszAOd4JvzGtur5PudZctr76qCOQc",
    "user": {
        "id": 3,
        "email": "user1@mail.com",
        "displayName": "user 1",
        "photoUrl": null
    }
}
Если же обновление прошло неудачно - скорее всего закончилось время жизни refreshToken'а и нужно заново авторизоваться (email, Google)