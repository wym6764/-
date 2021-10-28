# URL Shortener
> URL을 축약해주고, 축약된 URL을 원 URL로 Redirect하는 기능을 제공합니다.


# 개발 환경

- Spring Boot
- MySQL
- Mybatis
- IntelliJ

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
[get] localhost:8080/**{shortURL}**

- shortURL의 원 url으로 redirect합니다.

## Exception
- URL을 입력하지 않은 shortURL 생성 요청시
```json
{
    "message": "URL이 입력되지 않았습니다"
}
```
- 등록되어 있지 않은 shortURL으로 redirect요청시
```json
{
    "message": "등록되어있지 않은 URL입니다."
}
```
