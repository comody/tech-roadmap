# tech-roadmap-api
tech-roadmap 는 테크 관련 기술 블로그를 공유 저장하는 기능을 제공합니다.

Spec: <strong>Java & Spring Boot</strong>, <strong>JPA</strong>, <strong>JWT</strong>, <strong>Mysql</strong>, <strong>Swagger</strong>, <strong>Docker</strong>
## How To Start
```
    $ ./gradlew build
    $ docker-compose up -d
    
    // initial db setting
    $ docker exec -it ${db_container_id} mysql -uroot -p
    $ create user 'name'@'%' identified by 'pasword';
    $ grant all privileges on *.* to 'username'@'%';
```

## Swagger API
어플리케이션 실행 후 다음 주소로 접속
http://localhost:8080/swagger-ui/index.html

## Todo
- [x] docker로 어플리케이션 구축하기
- [ ] material 좋아요 기능, 순위 기능 구현
- [ ] AWS 서비스(ECR, EC2, RDS) 이용해서 배포하기
- [ ] CI/CD jenkins or code pipeline 배포자동화하기
- [ ] 도커 이미지 레이러 분리