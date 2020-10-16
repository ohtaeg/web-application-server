# Step 3-2 요구사항 - GET 방식으로 회원가입하기
- **회원가입** 메뉴를 클릭하면 http://localhost:8080/user/form.html 으로 이동하면서 회원가입을 할 수 있다.
- 회원가입을 하면 다음과 같은 형태로 사용자가 입력한 값이 서버에 전달한다.
```
/user/create?userId=ohtaeg&password=password&name=taegyeoung&email=otk1090%40s.net
``` 
- HTML과 URL을 비교해 보고 사용자가 입력한 값을 파싱(문자열을 원하는 형태로 분리하거나 조작하는 것을 의미)해 <br>
model.User 클래스에 저장한다.

 - HTTP Header 예) 
    - GET /user/create?userId=ohtaeg&password=password&name=taegyeoung&email=otk1090%40s.net
    - HTTP/1.1 <br>

### 힌트
- HTTP 요청의 첫 번째 라인에서 요청 URL을 추출한다.
- 요청 URL에서 접근 경로와 이름=값 으로 전달되는 데이터를 추출해 User 클래스에 담는다.
- 구현은 가능하면 JUnit을 활용해 단위 테스트를 진행하면서 하면 좀 더 효과적으로 개발 가능하다.
- 이름=값 파싱은 util.HttpRequestUtils 클래스의 parseQueryString() 메서드를 활용한다.
- 요청 URL과 이름=값을 분리해야 한다.
    - String url = "/?data=234";
    - int index = url.indexOf("?");
    - String requestPath = url.subString(0, index);
    - String params = url.subString(index + 1);                          
