# 웹 서버 시작 및 테스트
* webserver.WebServer 는 사용자의 요청을 받아 RequestHandler에 작업을 위임하는 클래스이다.
* 사용자 요청에 대한 모든 처리는 RequestHandler 클래스의 run() 메서드가 담당한다.

# 각 요구사항별 학습 내용 정리
* 구현 단계에서는 각 요구사항을 구현하는데 집중한다. 
* 구현을 완료한 후 구현 과정에서 새롭게 알게된 내용, 궁금한 내용을 기록한다.
* 각 요구사항을 구현하는 것이 중요한 것이 아니라 구현 과정을 통해 학습한 내용을 인식하는 것이 배움에 중요하다. 

### 요구사항 1 - http://localhost:8080/index.html로 접속시 응답
- Http header 에 대해 좀 더 깊이 공부할 필요를 느낌.
    - General Header
    - Request Header
    - Response Header
    - Entity Header
### 요구사항 2 - get 방식으로 회원가입
- url 패턴을 분석
    - [scheme] :// [host:port] / [path] ? [queryString]
- http Header의 uri와 쿼리스트링 파싱
- 서블릿이 이뤄지는 동작 유추해보기

### 요구사항 3 - post 방식으로 회원가입
- http body 파싱
- http RFC 표준 구조로 리팩토링
```
Request {
  RequestLine {
    Method
    RequestUri
    HttpVersion 
  }
  Headers {
    General Header
    Request Header
    Entity Header
  }

  MessageBody
}

Response {
  StatusLine {
    HttpVersion
    HttpMethod Status
    HttpMethod Reason 
  }

  Headers {
    General Header
    Response Header
    Entity Header
  }

  MessageBody
}
```

### 요구사항 4 - redirect 방식으로 이동
- Http Status 설계
- Http Response 표준 설계
- forward 와 sendRedirect 차이

### 요구사항 5 - cookie
- 쿠키 - 세션 공부

### 요구사항 6 - 사용자 목록 출력
- 쿠키 - 세션 공부

### 요구사항 7 - css, js 파일 적용하여 stylesheet 적용
- url 매핑
- 리소스 분리
