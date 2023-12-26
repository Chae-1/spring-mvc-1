<%@ page import="hello.servlet.ch02.domain.member.Member" %>
<%@ page import="hello.servlet.ch02.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response 는 사용 가능하다.
    MemberRepository repository = MemberRepository.getRepository();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    repository.save(member);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUsername()%></li>
    <li>age = <%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
