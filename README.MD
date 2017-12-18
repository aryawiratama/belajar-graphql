
# Belajar GraphQL
## Belajar GraphQL menggunakan java & Spring Boot
Penjelasan lebih lanjut mengenai GraphQL bisa dibaca pada link berikut (http://graphql.org/learn/)[http://graphql.org/learn/]
### Run Project
* Download project
* update application properties, sesuaikan dengan settingan database di komputer
* buka command prompt lalu ketik perintah `mvn spring-boot:run`
* buka postman atau rest console
#### Category End Point
* pada target ketik `localhost:8080/api/category`
* lalu pada raw body ketik 
```
    {
        allBookCategory{
            id 
            name
        }
    }
```
* kemudian klik post

#### Book End Point
* Book end point merupakan contoh graphql untuk relasi many to one
* pada target ketik `localhost:8080/api/book/query`
* lalu pada raw body buat objek seperti berikut
```
    {
        books{
            id
            title
            isbn
            description
            author
            category{
                id
                name
            }
        }
    }
```
* kemudian klik post