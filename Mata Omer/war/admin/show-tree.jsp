<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="/static/main.css" />
</head>
<body>
<div id="wrapper">


${q.output}

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
			showAddForm( $(this).attr('key') );
		});
		
	});
	
	
	// take a key and display the form for adding the next question
	function showAddForm (key) {
		
	}
</script>