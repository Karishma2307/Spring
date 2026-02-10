<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Student Form</title></head>
<body style="font-family:Arial;">

<h2>Student Registration</h2>

<form:form method="post" action="/students/save" modelAttribute="student">

    Name: <form:input path="name"/>
    <form:errors path="name" style="color:red"/><br/><br/>

    Email: <form:input path="email"/>
    <form:errors path="email" style="color:red"/><br/><br/>

    Course: <form:input path="course"/>
    <form:errors path="course" style="color:red"/><br/><br/>

    Phone: <form:input path="phone"/>
    <form:errors path="phone" style="color:red"/><br/><br/>

    <button type="submit">Register</button>

</form:form>

<br/>
<a href="/students">View Students List</a>

</body>
</html>