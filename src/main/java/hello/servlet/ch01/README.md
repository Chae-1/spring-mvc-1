서블릿
==
HTTP 통신을 위한 서버를 직접 구축한다면, TCP 연결을 하고 요청 메시지를 파싱하는 등, 굉장히 복잡할 것이다. 이런 과정을 서블릿이 해결해준다.
- URL이 호출되면 서블릿 코드가 실행되고
- HTTP 요청 정보, 응답 정보를 편리하게 사용하고 제공할 수 있는 서블릿 정보가 존재한다.

즉, 서블릿은 간단한 메서드 호출만으로 웹 요청과 응답을 다루게 해주는 기술.

### 서블릿 컨테이너
- 톰캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라고 한다.
- 서블릿 객체의 생명주기를 관리해주는 역할을 수행한다.
- 서블릿 객체는 싱글톤으로 관리한다.
    - request, response 객체는 요청마다 생성되는 것이 맞지만, 서블릿 객체는 싱글톤으로 관리하는 것이 효율적이다.
    - 공유 변수를 사용하는 것을 주의해야한다.
- 동시 요청을 위한 멀티 쓰레드 처리를 지원한다.


#### 컨테이너 동작 방식
1. `@WebServlet` 어노테이션이 있는 클래스를 컨테이너에서 서블릿으로 생성, 관리한다.
2. url로 요청이 오면, HTTP 요청 메시지를 기반으로 request, 응답을 위한 response 객체를 생성하고, 서블릿 코드가 실행된다.
3. 실행된 서블릿 코드안에서 response 객체에 응답할 메시지 내용을 담아 응답 메시지로 만들어서 클라이언트에 반환한다.


## HttpServletRequest
HTTP 요청 메시지를 직접 파싱해서 사용해도 되지만, HTTP 요청 메세지를 편리하게 사용할 수 있도록 HTTP 요청 메시지를 파싱해서 제공해주는 객체
- Start Line
  - HTTP 메서드
  - 쿼리 파라미터
  - URL
  - 프로토콜 정보
- 헤더

- 바디
  - form 파라미터
  - message body 데이터
  
위 정보를 모두 파싱해서 제공해준다.

**임시 저장소 기능**
- HTTP 요청이 시작부터 끝날 때 까지 유지되는 저장소 기능을 수행한다.
  - 저장: request.setAttribute(name, value)
  - 조회: request.getAttribute(name)

**세션 관리 기능**
- request.getSession(boolean create);

## HTTP 요청 데이터
클라이언트에서 HTTP 요청 메시지를 통해 서버로 데이터를 전달하는 방법은 크게 3가지 존재한다.

- **GET - 쿼리 파라미터**
  - /url?username=chae&age=20
  - 메시지 바디 없이, URL의 쿼리 파라미터를 통해 전달한다.
  - ex) 검색, 필터, 페이징들에서 많이 사용한다.
- **Post - HTML Form**
  - content-type: application/x-www-form-urlencoded
  - 메시지 바디에 쿼리 파라미터 형식으로 전달
  - ex) 회원 가입, 상품 주문
- **HTTP message body**
  - HTTP API에서 주로 사용, JSON, XML, TEXT
  - 데이터 형식은 주로 JSON을 사용한다.
  - JSON, PUT, PATCH

### GET-쿼리 파라미터
URL에 다음과 같이 `?`를 시작으로 보낼 수 있고,`&`로 구분된다.
- ex) http://localhost:8080/hello?username=chae&age=20
  - username = chae
  - age = 20
  - 타입은 String

### POST HTML Form
HTML의 Form을 사용해서 클라이언트에서 서버로 데이터를 전송하면, 메시지 바디에 전송된다.
- content-type : application/x-www-form-urlencoded
- 쿼리 파라미터를 전달 받는 방식과 동일한 방법으로 데이터를 전달 받을 수 있다.
- request.getParamter(keyName)

### API 메세지 바디
- **HTTP message body** 에 데이터를 직접 담아서 요청
  - HTTP API에서 주로 사용, JSON, XML, TEXT
  - POST, PUT, PATCH

#### JSON
- HTTP API에서 주로 사용되는 형식이다.
- JavaScript Object Notation
- content-type: application/json
- JSON 결과를 파싱해서 사용하기 위해 `ObjectMapper` 객체를 사용


## HttpServletResponse 
HTTP 응답 메시지를 생성하는 역할을 하는 객체이다.
- HTTP 응답 코드를 지정
- 헤더를 생성
- 바디를 생성

**편의 기능**
- 쿠키
- Redirect
- Content-Type


#### 단순 텍스트, HTML 응답
- 단순 텍스트
  - 단순 텍스트로 응답하는 것은 `Writer` 객체를 받아서 전송하면 된다.
- HTML
  - content-type을 text/html로 설정한 후 reponse의 writer 객체에 html을 직접 전송
- HTTP API
  - object를 json 형태로 파싱한 이후, 이를 전송
  - content-type은 application/json
