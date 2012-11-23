<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="/static/main.css" />
</head>
<body class="tree-view">
<div id="wrapper">


${q.output}


	<div id="theform">
		<form method="post" action="/admin/process" id="add-question-form">
			<p><label for="question">Question: </label><br/>
			<input type="text" name="question" id="question" /></p>
			
			<p>
				<input type="checkbox" name="open_answer" value="1" id="open_answer"  />
				<label for="open_answer">Allow open answer</label>
			</p>
			
			<p id="answers-wrap">
			Answers: <Br/>
				<input type="text" name="option" class="option-text first" />
				<input type="text" name="option" class="option-text" />
			</p>
			
			<input type="hidden" name="key" id="key" value="" />
			<input type="submit" />
			<input type="reset" value="Reset">
		</form>
	</div>
</div>
</body>
</html>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="/static/main.js"></script>
