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
		</form>
	</div>
</div>
</body>
</html>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script>
	$(function() {
		
		// add another option on the last focus
		$('.option-text:not(.first)').live('focus', function() {
			if ( $(this).next().length === 0 )
				$('#answers-wrap').append('<input type="text" name="option" class="option-text" />');
		});
		
		// add the next question button
		$('.add-next').click(function() {
			showAddForm( $(this).attr('key'),  // to fill the hidden input
						 $(this).offset().top, // show the form in the same line as the button
						 $(this).parent().offset().left + 400 ); // show the form where the question box ends
		});
		
		$('#add-question-form').submit(function() {
			var toSend = $(this).serialize();
			$.post('/admin/process', toSend, function(data) {
				console.log(data);
			});
		});
		
	});
	
	
	// take a key and display the form for adding the next question
	function showAddForm (key, top, left) {
		$theform = $('#theform').show();
		$theform.css ('left', left + 'px' );
		$theform.css ('top', top + 'px' );
		$('#key').val(key);
		
	}
</script>