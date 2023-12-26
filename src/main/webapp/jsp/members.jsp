<%@ page import="hello.servlet.ch02.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.ch02.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cogud
  Date: 2023-12-27
  Time: 오전 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  MemberRepository repository = MemberRepository.getRepository();
  List<Member> members = repository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <table>
    <thead>
      <th>id</th>
      <th>username</th>
      <th>age</th>
    </thead>
    <tbody>
      <%
        for (Member member : members) {
          out.write("   <tr>");
          out.write("   <td>" + member.getId() + "</td>");
          out.write("   <td>" + member.getUsername() + "</td>");
          out.write("   <td>" + member.getAge() + "</td>");
          out.write("   </tr>");
        }
      %>
    </tbody>
  </table>
</body>
</html>
