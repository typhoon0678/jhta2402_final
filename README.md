# IntArea

## Requirement

### .env.example 수정 
- 파일명 .env.example -> .env 수정
```Bash
VITE_REACT_DEV_PORT=<<React dev port>>
VITE_API_HOST=<<Spring boot URL ex) http://localhost:12345>>
SPRING_BOOT_PORT=<<Spring boot port>>
FRONTEND_DEV_HOST=<<React dev URL ex) http://localhost:11111>>
```
### application.example 수정 
- 파일명 application.example -> application.yml
```Yaml 
server:
  port: ${SPRING_BOOT_PORT:8080} # .env 파일 없으면 8080포트에서 실행

spring:
  config:
    import: optional:file:.env[.properties]
  devtools:
    livereload:
      enabled: true
  application:
    name: home
  datasource:
    driver-class-name: <<db 드라이버>>
    url: <db url>>
    username: <<db 계정>>
    password: <<db 계정 비밀번호>>
  jpa:
    database-platform: <<Dialect>>
    database: postgresql
    hibernate:
      ddl-auto: update
```
### React modules install

```Bash
# frontend/
npm install --save
```
