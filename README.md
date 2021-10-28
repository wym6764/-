# URL Shortener
> URL을 축약해주고, 축약된 URL을 원 URL로 Redirect하는 기능을 제공합니다.

# 개발 환경

### Backend

- Spring Boot
- MySQL
- Mybatis

## Rest API

### Shorten API
[post] localhost:8080/**shorten**


- request example
```json
{
  "url" : "http://naver.com"
}
```
- response exmaple
```json
{
    "id": 1000034,
    "shortURL": "Kjme",
    "originalURL": "http://naver.com"
}
```
### Redirect API
[get] localhost:8080/**{url}**

- 해당 url로 redirect합니다.
