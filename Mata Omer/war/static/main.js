
$(function() {
	
	// add another option on the last focus
	$('.option-text:not(.first)').live('focus', function() {
		if ( $(this).next().length === 0 )
			$('#answers-wrap').append('<input type="text" name="option" class="option-text" />');
	});
	
	// add the next question button
	$('.add-next').click(function() {
		$('#add-question-form')[0].reset();
		resetForm();
		$('#key, #answerkey').val('');
		$('#answerkey').val( $(this).attr('key') );
		$(this).replaceWith( $('#theform').show() );
		
	});
	
	
	$('.qp a').click(function() {
		var key = $(this).attr('key');
		showHideAnswers (key, 'toggle');
	});
	
	$('.edit-q').click(function() {
		// populate the form in order to edit the question
		showHideAnswers($(this).attr('key'), 'hide');
		var $form = $('#theform');
		if ($form.is(":visible")) {
			$form.hide();
			return;
		}
		resetForm();
		$('#key, #answerkey').val('');
		$(this).parent().after( $form.show() );
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
	
	
	$( "#question" ).autocomplete({
        source: availableTags,
        focus: function(event, ui) {
            $("#question").val(ui.item.label);
            return false;
        },
        select: function(event, ui) {
            $("#question").val(ui.item.label);
            prepareForm((ui.item.value));
            return false;
        }
    });
});

function prepareForm (key) {
	// fill the form with the values for question key
	resetForm();
	var i = 0;
	$('.qNode[key="' + key + '"] > .answer-div > p').each(function() {
		// loop through the answers 
		$('.option-text:eq(' + i + ')').val ( $(this).html() );
		if (i++)
			$('#answers-wrap').append('<input type="text" name="option" class="option-text" />');
	});
	$('#key').val(key);
}
function showHideAnswers (key, action) {
	var $answers = $('.qNode[key="'+key+'"] > .answer-div > p, .qNode[key="'+key+'"] > .answer-div > input');
	if (action == 'toggle') {
		$answers.slideToggle();
	} else if (action == 'hide') {
		$answers.hide();
	} else {
		$answers.show();
	}
}
function resetForm() {
	$('.option-text').not('.first').not(':first').remove();
}