# Spring Boot Study
Spring Boot の勉強

* redis, spring bootを使用してデータの更新をする感じのものを作る
* 蔵書管理的な

wake up redis in docker
```
docker run --name redis -d -p 6379:6379 redis redis-server --appendonly yes
```

#### API
Book
* Register
```$xslt
book/register
```
* get 
```$xslt
book/{isbnCode}
```

#### DB
```
Book hashmap
key=ISBNcode, 
value={
    title, 
    price, 
    publisher, 
    publishyear, 
    writer,
    translator,
    imgurl
}
```

```
User list
key=userId,
value={
    ISBNcode,
    isRead,
    registrationtime
}
```
