<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link type="text/css" rel="stylesheet" href="/static/main.css" />
</head>
<body>
<div id="wrapper"  class="centered">


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
		<a href="/admin/process?action=edit&key=${ques.id}">Edit</a>
		<a href="/admin/process?action=delete&key=${ques.id}">Delete</a>
	</p>
</c:forEach>

</div>
</body>
</html>
