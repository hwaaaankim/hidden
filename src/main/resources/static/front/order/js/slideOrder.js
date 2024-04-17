	/*  Wizard */
	jQuery(function ($) {
		"use strict";
		$('#submitBtn').attr('disabled', true);
		// Chose here which method to send the email, available:
		// Simple phpmail text/plain > form_send_multiple_branch.php (default)
		// PHPMailer text/html > phpmailer/multiple_branch_phpmailer.php
		// PHPMailer text/html SMTP > phpmailer/multiple_branch_phpmailer_smtp.php
		// PHPMailer with html template > phpmailer/multiple_branch_phpmailer_template.php
		// PHPMailer with html template SMTP> phpmailer/multiple_branch_phpmailer_template_smtp.php
		$('form#wrapped').attr('POST', '/order/orderInsert');
		$("#wizard_container").wizard({
			stepsWrapper: "#wrapped",
			submit: ".submit",
			beforeSelect: function (event, state) {
				if ($('input#website').val().length != 0) {
					return false;
				}
				if (!state.isMovingForward)
					return true;
				var inputs = $(this).wizard('state').step.find(':input');
				return !inputs.length || !!inputs.valid();
			},
			afterSelect:function(event, state){
				if(state.stepIndex === 9){
					$('#submitBtn').attr('disabled', false);
				}else{
					$('#submitBtn').attr('disabled', true);
				}
				if(state.stepIndex === 8){
					if($('input[name=model]:checked').attr('id')!=undefined){
						$('#slideModel').val('슬라이드장 모델 : ' + $('input[name=model]').attr('id'));
					}
					
					if($('input[name=color]:checked').attr('id')!=undefined){
						$('#slideColor').val('슬라이드장 색상 : ' + $('input[name=color]').attr('id'));
					}
					
					$('#slideWidth').val('가로길이 : ' + $('input[id=flapWidth]').val() + "[단위 : mm]");
					$('#slideHeight').val('세로길이 : ' + $('input[id=flapHeight]').val() + "[단위 : mm]");
					$('#slideDepth').val('깊이 : ' + $('input[id=flapDepth]').val() + "[단위 : mm]");
					
					
					if($('input[name=led]:checked').attr('id')!=undefined){
						$('#slideLed').val('LED 추가여부 : ' + $('input[name=led]:checked').attr('id'));
					}else{
						$('#slideLed').val('LED 추가여부 : 해당없음');
					}
					
					if($('input[name=led_color]:checked').attr('id')!=undefined){
						$('#slideLedColor').val('LED 색상 : ' + $('input[name=led_color]:checked').attr('id'));
					}else{
						$('#slideLedColor').val('LED 색상 : 해당없음');
					}
					
					if($('input[name=option]:checked').attr('id')!=undefined){
						$('#slideOption').val('추가옵션[콘센트] : ' + $('input[name=option]:checked').attr('id'));
					}else{
						$('#slideOption').val('추가옵션[콘센트] : 해당없음');
					}
					
					if($('input[name=option_position]:checked').attr('id')!=undefined){
						$('#slideOptionPosition').val('추가옵션[콘센트] 위치 : ' + $('input[name=option_position]:checked').attr('id'));
					}else{
						$('#slideOptionPosition').val('추가옵션[콘센트] 위치 : 해당없음');
					}
				}
			}
		}).validate({
			errorPlacement: function (error, element) {
				if (element.is(':radio') || element.is(':checkbox')) {
					error.insertBefore(element.next());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
	$('#submitBtn').on('click', function(){
		var result = confirm("제출 하시겠습니까?");
		if(result){
			
		
		var mirrorForm = $('<form></form>');
		//set attribute (form) 
		mirrorForm.attr("method","post");
		mirrorForm.attr("action","/order/mirrorOrderInsert");
	
		// create element & set attribute (input) 
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'model', value:$('#mirrorShape').val() }));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'frame', value:$('#mirrorFrame').val() }));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'frameStyle', value:$('#mirrorStyle').val() }));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'frameColoe', value:$('#mirrorColor').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'width', value:$('#mirrorWidth').val() }));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'height', value:$('#mirrorHeight').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'sizeMessage', value:$('#mirrorMessage').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'led', value:$('#mirrorLed').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'ledMethod', value:$('#mirrorLedMethod').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'ledForm', value:$('#mirrorLedForm').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'sensor', value:$('#mirrorSensor').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'sensorForm', value:$('#mirrorSensorForm').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'name', value:$('#name').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'phone', value:$('#phone').val() }));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'email', value:$('#email').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'message', value:$('#addedMessage').val() }));
	
		mirrorForm.appendTo('body');
	/*	console.log($('#mirrorShape').val());
		console.log($('#mirrorFrame').val());
		console.log($('#mirrorStyle').val());
		console.log($('#mirrorColor').val());
		console.log($('#mirrorWidth').val());
		console.log($('#mirrorHeight').val());
		console.log($('#mirrorMessage').val());
		console.log($('#mirrorLed').val());
		console.log($('#mirrorLedMethod').val());
		console.log($('#mirrorLedForm').val());
		console.log($('#mirrorSensor').val());
		console.log($('#mirrorSensorForm').val());
		console.log($('#name').val());
		console.log($('#phone').val());
		console.log($('#email').val());
		console.log($('#addedMessage').val());*/
		mirrorForm.submit();
		}
	});
	$("#wizard_container").wizard({
		transitions: {
			branchtype: function ($step, action) {
				var branch = $step.find(":checked").val();
				if (!branch) {
					 $("form").valid();
				}
				return branch;
			}
		}
	});
	