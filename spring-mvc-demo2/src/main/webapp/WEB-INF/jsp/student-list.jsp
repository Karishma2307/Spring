<%@ page import="java.util.*" %>
<html>
<head><title>Students List</title></head>
<body style="font-family:Arial;">

<h2>Registered Students</h2>

<table border="1" cellpadding="8">
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Course</th>
        <th>Phone</th>
        <th>Action</th>
    </tr>

    <%
        List<?> students = (List<?>) request.getAttribute("students");
        if(students != null) {
            for(Object obj : students) {
                com.example.demo.model.Student s = (com.example.demo.model.Student) obj;
    %>
    <tr>
        <td><%= s.getName() %></td>
        <td><%= s.getEmail() %></td>
        <td><%= s.getCourse() %></td>
        <td><%= s.getPhone() %></td>
        <td>
            <a href="/students/delete?email=<%= s.getEmail() %>">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<br/>
<a href="/students/new">Add New Student</a>

</body>
</html>