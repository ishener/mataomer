
$(function() {
	
	// add another option on the last focus
	$('.option-text:not(.first)').live('focus', function() {
		if ( $(this).next().length === 0 )
			$('#answers-wrap').append('<input type="text" name="option" class="option-text" />');
	});
	
	// add the next question button
	$('.add-next').click(function() {
//		showAddForm( $(this).attr('key'),  // to fill the hidden input
//					 $(this).offset().top, // show the form in the same line as the button
//					 $(this).parent().offset().left + 400 ); // show the form where the question box ends
		$('#add-question-form')[0].reset();
		$('#answerkey').val( $(this).attr('key') );
		$(this).replaceWith( $('#theform').show() );
		
	});
	
	/*
	$('#add-question-form').submit(function() {
		var toSend = $(this).serialize();
		$.post('/admin/process', toSend, function(data) {
			console.log(data);
		});
	});
	*/
	
	
	$('.qp a').click(function() {
		var key = $(this).attr('key');
		showHideAnswers (key, 'toggle');
	});
	
	$('.edit-q').click(function() {
		// populate the form in order to edit the question
		showHideAnswers($(this).attr('key'), 'hide');
		$form = $('#theform').show();
		$(this).parent().after( $form );
		$('#add-question-form')[0].reset();
		$('#question').val( $(this).siblings('a').html() );
		var i = 0;
		$('.qNode[key="' + $(this).attr('key') + '"] > .answer-div > p').each(function() {
			// loop through the answers 
			$('.option-text:eq(' + i + ')').val ( $(this).html() );
			if (i++)
				$('#answers-wrap').append('<input type="text" name="option" class="option-text" />');
		});
		$('#key').val( $(this).attr('key') );
	});
});


function showHideAnswers (key, action) {
	var $answers = $('.qNode[key="'+key+'"] > .answer-div > p, .qNode[key="'+key+'"] > .answer-div > input');
	if (action == 'toggle') {
		$answers.slideToggle();
	} else if (action == 'hide') {
		console.log('got here');
		$answers.hide();
	} else {
		$answers.show();
	}
}