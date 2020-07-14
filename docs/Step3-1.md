# Step 3-1 요구사항
- 현재 HTTP 웹 서버에 접속하면 어떤 URL로 접속하더라도 "Hello World" 문자열만 출력하고 있다.
- http://localhost:8080/index.html로 접속했을때, webapp 디렉토리의 index.html파일을 읽어 클라이언트에 응답한다.

## 힌트
- 예) Http Header = GET
    - index.html HTTP/1.1 <br>
    - host: localhost:8080 <br> 
    - Connection : keep-alive <br>
    - Aceept : \*/\*

### 힌트 1단계
- InputStream을 한 줄 단위로 읽기 위해 BufferedReader를 생성한다.
    - 구글에서 "java inputStream bufferedreader"로 검색 후 문제 해결
- BufferedReader.readLine() 메서드를 활용해 라인별로 HTTP 요청 정보를 읽는다.
- HTTP 요청 정보 전체를 출력한다.
    - 헤더 마지막은 while (!" ".equals(line)) {} 으로 확인 가능하다.
    - line이 null 값인 경우에 대한 예외 처리도 해야 한다. <br>
    그렇지 않을 경우 무한 루프에 빠진다. if (line == null) { return ; }

### 힌트 2단계
- HTTP 요청 정보의 첫 번째 라인에서 요청 URL(위 예의 경우 /index.html 이다)을 추출한다.
    - String[] tokens = line.split(" ");를 활용해 문자열을 분리할 수 있다.
    - 구현은 별도의 유틸 클래스를 만들고 단위 테스트를 만들어 진행하면 편하다.

### 힌트 3단계
- 요청 URL에 해당하는 파일을 webapp 디렉토리에서 읽어 전달하면 된다.
- 구글에서 "java files readallbytes"로 검색해 파일 데이터를 byte[]로 읽는다.
- byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());                            
