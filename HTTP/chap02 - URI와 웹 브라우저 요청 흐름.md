# URI와 웹 브라우저 요청 흐름

---

### URI(Uniform Resource Identifier)

- Uniform: 리소스를 식별하는 통일된 방식
- Resource: 자원, URI로 식별할 수 있는 모든 것
- Identifier: 다른 항목과 구분하는데 필요한 정보
- URL - Locator: 리소스가 있는 위치를 지정
- URN - Name: 리소스에 이름을 부여
- 위치는 변할 수 있지만 이름은 변하지 않는다

---

### URL 문법

scheme://userinfo@host:port/path?query#fragment

- scheme: 주로 프로토콜 사용
- userinfo: URL에 사용자정보를 포함하여 인증, 거의 사용하지 않는다
- host: 호스트명, 도메인명 또는 IP 주소를 직접 사용가능
- port: 접속 포트, 일반적으로 생략(http - 80, https - 443)
- path: 리소스 경로, 계층적 구조
- query: key=value 형태, query parameter, query string 등으로 불린다
- fragment: html 내부 북마크 등에 사용, 서버에 전송하는 정보는 아니다

---

### 웹 브라우저 요청 흐름

- IP, PORT 정보 조회 후 HTTP 요청 메시지 생성
- SOCKET 라이브러리를 통해 전달
- TCP/IP 패킷 생성, HTTP 메시지 포함
- 요청 패킷 전달
- HTTP 응답 메시지 작성
- 응답 패킷 전달

