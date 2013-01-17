<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link type="text/css" rel="stylesheet" href="/static/main.css" />
</head>

<body>
<div id="wrapper" class="centered">
	<form method="post" action="/admin/process">
		<p><label for="question">Question: </label><br/>
		<textarea name="question" id="question"></textarea>
		</p>
		
		<p>
			<input type="checkbox" name="open_answer" value="1" id="open_answer"  />
			<label for="open_answer">Allow open answer</label>
		</p>
		
		<p id="answers-wrap">
		Answers: <Br/>
			<input type="text" name="option" class="option-text first" />
			<input type="text" name="option" class="option-text" />
		</p>
		<p id="tags-wrap">
		Tags: <Br/>
			<input type="text" name="tags" class="" />
		</p>
		
		<input type="submit" />
	</form>
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
	});
</script>