# Step 3-4 요구사항 - 302 status code 적용
- "회원가입"을 완료하면 /index.html 페이지로 이동하고 싶다.
- 현재는 URL이 /user/create 로 유지되는 상태이기 때문에 응답으로 전달할 파일이 없다.
- 따라서 회원가입을 완료한 후, /index.html 페이지로 이동한다.
- 브라우저의 URL도 /user/create가 아니라 /index.html로 변경해야 한다.

### 힌트
- HTTP 응답 헤더의 status code를 200이 아니라 302 코드를 사용한다.
- http://en.wikipedia.org/wiki/HTTP_302 참고
                        
