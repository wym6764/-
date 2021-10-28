# URL Shortener

## Rest API

### Shorten API
localhost:8080/shorten

request
```json
{
  "url" : "http://naver.com"
}
```
response
```json
{
    "id": 1000034,
    "shortURL": "Kjme",
    "originalURL": "http://naver.com"
}
```

