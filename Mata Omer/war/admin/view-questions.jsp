<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="/static/main.css" />
</head>
<body>
<div id="wrapper">


<c:forEach var="ques" items='${questions}'>
	<p>
		<strong>Question:</strong> 
		<c:out value="${ques.question}"></c:out>
	</p>
	<p>	Answers: </p>
		<c:forEach var="ans" items='${ques.answers}'>
			<p>
				<c:out value="${ans.answer}"></c:out>
			</p>
		</c:forEach>
	<p>
		<a href="/add-question?action=edit&key=${ques.id}">Edit</a>
		<a href="/add-question?action=delete&key=${ques.id}">Delete</a>
	</p>
</c:forEach>

</div>
</body>
</html>
