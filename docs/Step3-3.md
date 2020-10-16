# Step 3-3 요구사항 - POST 방식으로 회원가입하기
- http://localhost:8080/user/form.html 파일의 form 태그 method를 get에서 post로 수정한 후 회원강비이 정상적으로 동작하도록 구현한다.
- HTTP Header 와 Body 예) 
    - POST /user/create HTTP/1.1
    - HOST : localhost:8080
    - Connection : keep-alive
    - Content-Length : 59
    - Content-Type : application/x-www.form-urlencoded
    - Accept : */*
    - userId=ohtaeg&password=password&name=tae&email=otk1090%40s.net

### 힌트
- POST로 데이터를 전달할 경우 전달하는 데이터는 HTTP 본문에 담긴다.
- HTTP 본문은 HTTP 헤더 이후 빈 공백을 가지는 한줄 다음부터 시작한다.
- HTTP 본문에 전달되는 데이터는 GET 방식으로 데이터를 전달할 때의 이름=값과 같다.
- BufferedReader에서 본문 데이터는 util.IOUtils 클래스의 readData() 메소드롤 활용한다.
- 본문의 길이는 HTTP 헤더의 Content-Length의 값이다.
- 회원가입시 입력한 모든 데이터를 추출해 User 객체를 생성한다.
                        
