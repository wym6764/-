# URL Shortener
> URL을 축약해주고, 축약된 URL을 원 URL로 Redirect하는 기능을 제공합니다.

## Rest API

### Shorten API
[post] localhost:8080/shorten


request example
```json
{
  "url" : "http://naver.com"
}
```
response exmaple
```json
{
    "id": 1000034,
    "shortURL": "Kjme",
    "originalURL": "http://naver.com"
}
```

[get] localhost:8080/{url}
해당 url로 redirect
