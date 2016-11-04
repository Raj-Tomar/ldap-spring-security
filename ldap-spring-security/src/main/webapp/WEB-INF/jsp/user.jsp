<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>User</title>
	  
  <link href="<c:url value="/resources/css/user-css.css"/>" rel="stylesheet" type="text/css">
	  
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.0.0.js"/>"></script>
  <%-- <script type="text/javascript" src="<c:url value="/resources/js/user-validation.js"/>"></script> --%>
  
 </head>
 <body>
 
 <div class="userForm">
 <c:if test="${empty user.id}">
 	<h2>Add User</h2>
 </c:if>
 <c:if test="${!empty user.id}">
 	<h2>Update User</h2>
 </c:if>
  
  <div class="errorAndSuccessMessage">
	<label class="errorMessage" id="firstNameMissing">Please enter first name.</label>
	<label class="errorMessage" id="firstNameInvalid">Please enter valid first name.</label>
	<label class="errorMessage" id="lastNameInvalid">Please enter valid last name.</label>
	<label class="errorMessage" id="userNameMissing">Please enter user name.</label>
	<label class="errorMessage" id="userNameInvalid">Please enter valid user name.</label>
  </div>
  
  <div>
  	<c:url var="userAction" value ="/saveOrUpdateUser"/>
  <form:form method="POST" action="${userAction}" commandName="user">
      <table align="center">
      <c:if test="${!empty user.id}">
       <tr>
           <td align="right"><form:label path="id">User ID:</form:label></td>
           <td><form:input path="id" readonly="true" id="userId"/></td>
       </tr>
       </c:if>
       <tr>
           <td align="right"><form:label path="firstName"><em>*</em>First Name:</form:label></td>
           <td><form:input path="firstName" id="firstName"/></td>
       </tr>
       <tr>
           <td align="right"><form:label path="lastName">Last Name:</form:label></td>
           <td><form:input path="lastName" id="lastName"/></td>
       </tr>
       <tr>
           <td align="right"><form:label path="userName"><em>*</em>User Name:</form:label></td>
           <td><form:input path="userName" id="userName"/></td>
       </tr>
       <tr>
           <td align="right"><form:label path="password"><em>*</em>Password:</form:label></td>
           <td><form:input path="password" id="password"/></td>
       </tr>
       <tr>
           <td align="right"><form:label path="email"><em>*</em>E-mail:</form:label></td>
           <td><form:input path="email" id="email"/></td>
       </tr>
       <tr>
           <td align="right"><form:label path="mobile"><em>*</em>Contact:</form:label></td>
           <td><form:input path="mobile" id="mobile"/></td>
       </tr>
       <tr>
           <td align="right"><form:label path="dateOfBirth"><em>*</em>Date of Birth:</form:label></td>
           <td><form:input path="dateOfBirth" id="dateOfBirth"/></td>
       </tr>
          <tr>
          	<td colspan="2">	
          	<c:if test="${empty user.id}">
          		<input type="submit" value="Submit" id="addUser"/>
          	</c:if>
          	<c:if test="${!empty user.id}">
          		<input type="submit" value="Update" id="updateUser"/>
          	</c:if>
          	<input type="button" value="Clear" id="clearUser"/>
          </td>
        </tr>
   </table> 
  </form:form>
  
  <c:if test="${!empty userList}">
  <h2>Users List</h2>
 <table border="1" align="center" >
  <tr>
   <th>First Name</th>
   <th>Last Name</th>
   <th>User Name</th>
   <th>Contact</th>
   <th>Actions</th>
  </tr>

  <c:forEach items="${userList}" var="user">
   <tr>
    <td><c:out value="${user.firstName}"/></td>
    <td><c:out value="${user.lastName}"/></td>
    <td><c:out value="${user.userName}"/></td>
    <td><c:out value="${user.mobile}"/></td>
    <td align="center"><a href="updateUser/${user.id}">Edit</a> | <a href="deleteUser/${user.id}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
 </div>
  
</div>

<script type="text/javascript">
$(document).ready(function(){
	User.validateUser();
	$('#clearUser').click(function(){
		User.clearUserForm();
		User.hideAllMessage();
	});
});

</script>

 </body>
</html>