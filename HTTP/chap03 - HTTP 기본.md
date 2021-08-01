# HTTP 기본

### HTTP(HyperText Transfer Protocol)

- HTTP 메시지에는 html, text, json, xml, 이미지, 영상, 파일 등 다양한 데이터가 담긴다
- HTTP/1.1 버전이 가장 많이 사용되며 2, 3은 성능 개선 버전이다

---

### Stateful 상태 유지

- 서버가 상태를 유지하는 것으로 항상 같은 서버가 유지되어야 한다
- 중간에 서버 장애가 나게되면 클라이언트는 새 서버에 새로 요청을 해야한다

### Stateless 무상태

- HTTP는 무상태 프로토콜이다
- 요청 메시지에 필요 데이터를 담아서 보냄으로 어떤 서버든 요청할 수 있다
- 갑자기 클라이언트 요청이 증가해도 서버를 대거 투입할 수 있다 (수평 확장에 유리)
- 클라이언트와 서버가 요청과 응답을 주고 받으면 연결이 끊어진다
- 클라이언트가 다시 요청하면 서버는 이전 요청을 기억하지 못한다
- 클라이언트와 서버는 서로 상태를 유지하지 않는다

---

### 비 연결성(Connectionless)

- 요청과 응답이 끝난 후 클라이언트와 서버간 연결을 끊음으로써 자원을 낭비하지 않는다
- HTTP는 기본이 연결을 유지하지 않는 모델이다
- TCP/IP 연결을 새로 맺어야 한다 - 3 way handshake 시간이 추가된다
- 웹 브라우저로 사이트를 요청하면 HTML 뿐만 아니라 js,  css, 이미지 등 수 많은 자원이 함께 다운로드 된다
- 그래서 지금은 HTTP 지속 연결(Persistent Conections)로 문제를 해결한다

---

### HTTP 메시지

HTTP 메시지 구조

- start-line 시작 라인
- header 헤더
- empty line 공백 라인(CRLF)
- message body

시작 라인

- 요청 메시지 - HTTP 메서드, 요청 대상, HTTP 버전
  - ex) GET /search?q=hello HTTP/1.1
- 응답 메시지 - HTTP 버전, HTTP 상태 코드, 이유 문구
  - ex) HTTP/1.1 200 OK

HTTP 헤더

- HTTP 전송에 필요한 모든 부가정보

- filed-name ":" OWS field-value OWS (OWS: 띄어쓰기 허용)
  - ex) 요청 메시지: Host: www.google.com
  - ex) 응답 메시지: Content-Type: text/html;charset=UTF-8

HTTP 메시지 바디

- 실제 전송할 데이터