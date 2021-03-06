# HTTP 상태코드

### 2xx (Successful): 요청 정상 처리

- 200 OK: 요청 성공
- 201 Created: 요청 성공해서 새로운 리소스가 생성됨
- 202 Accepted: 요청이 접수되었으나 처리가 완료되지 않음
- 204 No Content: 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없음

### 3xx (Redirection): 요청을 완료하려면 추가 행동이 필요

- 영구 리다이렉션: 특정 리소스의 URI가 영구적으로 이동

  - 301 Moved Permanently: 리다이렉트시 요청 메서드가 GET으로 변하고 본문이 제거될 수 있음

  - 308 Permanent Redirect: 리다이렉트시 요청 메서드와 본문 유지(POST로 보내면 리다이렉트도 POST)

- 일시적인 리다이렉션: 리소스의 URI가 일시적으로 변경
  - 302 Found: 리다이렉트시 요청 메서드가 GET으로 변하고 본문이 제거될 수 있음
  - 303 See Other: 302와 기능은 같음, 리다이렉트시 요청 메서드가 GET으로 변경
  - 307 Temporary Redirect: 302와 기능은 같음, 리다이렉트시 요청 메서드와 본문 유지(요청 메서드를 변경하면 안됨)

- 일시적인 리다이렉션 예시 PRG: Post/Redirect/Get
  - POST로 주문후에 새로고침으로 인한 중복 주문 방지
  - POST로 주문후에 주문 결과 화면을 GET 메서드로 리다이렉트
  - 새로고침해도 결과 화면을 GET으로 조회 -> 중복 주문 대신 결과 화면만 GET으로 다시 요청

- 기타 리다이렉션
  - 304 Not Modified: 캐시를 목적으로 사용
    - 클라이언트에게 리소스가 수정되지 않았음을 알려준다, 로컬PC에 저장된 캐시를 재사용
    - 로컬 캐시를 사용해야 하므로 응답에 메시지 바디를 포함하면 안된다
    - 조건부 GET, HEAD 요청시 사용

### 4xx (Client Error): 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없음

- 400 Bad Request: 클라이언트가 잘못된 요청을 해서 서버가 요청을 처리할 수 없음
  - 요청 구문, 메시지 오류 등
  - 클라이언트는 요청 내용을 다시 검토하고 보내야함
  - ex) 요청 파라미터가 잘못되거나 API 스펙이 맞지 않을 때

- 401 Unauthorized: 클라이언트가 해당 리소스에 대한 인증이 필요함
  - 인증(Authentication) 되지 않음
  - 401 오류 발생 시 응답에 WWW-Authenticate 헤더와 함께 인증 방법을 설명
  - 인증(Authentication): 본인이 누구인지 확인 (로그인)
  - 인가(Authorization): 권한부여 (ADMIN 권한처럼 특정 리소스에 접근할 수 있는 권한, 인증이 있어야 인가가 있다)

- 403 Forbidden: 서버가 요청을 이해했지만 승인을 거부함
  - 인증 자격 증명은 있지만 접근 권한이 불충분한 경우

- 404 Not Found: 요청 리소스를 찾을 수 없음
  - 요청 리소스가 서버에 없음 또는 클라이언트가 권한이 부족한 리소스에 접근할 때 해당 리소스를 숨기고 싶을 때

### 5xx (Server Error): 서버 오류, 서버가 정상 요청을 처리하지 못함

- 500 Internal Server Error: 서버 문제로 오류 발생
  - 서버 내부 문제로 오류 발생, 애매하면 500 오류다

- 503 Service Unavailable: 서비스 이용 불가
  - 서비스가 일시적인 과부하 또는 예정된 작업으로 잠시 요청을 처리할 수 없음
  - Retry-After 헤더 필드로 얼마뒤에 복구되는지 보낼 수 있음