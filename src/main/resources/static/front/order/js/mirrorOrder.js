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
				if(state.stepIndex === 12){
					$('#submitBtn').attr('disabled', false);
				}else{
					$('#submitBtn').attr('disabled', true);
				}
				if(state.stepIndex === 11){
					if($('input[name=model]:checked').attr('id')!=undefined){
						$('#mirrorShape').val('거울 모델 : ' + $('input[name=model]').attr('id'));
					}
					
					if($('input[name=frame]:checked').attr('id')!=undefined){
						$('#mirrorFrame').val('프레임 여부 : ' + $('input[name=frame]').attr('id'));
					}
					
					if($('input[name=style]:checked').attr('id')!=undefined){
						$('#mirrorStyle').val('프레임 스타일 : ' + $('input[name=style]:checked').attr('id'));
					}else{
						$('#mirrorStyle').val('프레임 스타일 : 해당 없음');
					}
					
					if($('input[name=color]:checked').attr('id')!=undefined){
						$('#mirrorColor').val('프레임 색상 : ' + $('input[name=color]:checked').attr('id'));
					}else{
						$('#mirrorColor').val('프레임 색상 : 해당 없음');
					}
					
					$('#mirrorWidth').val('가로길이 : ' + $('input[id=sizeWidth]').val() + "[단위 : mm]");
					$('#mirrorHeight').val('세로길이 : ' + $('input[id=sizeHeight]').val() + "[단위 : mm]");

					if($('input[name=led]:checked').attr('id')!=undefined){
						$('#mirrorLed').val('LED 추가여부 : ' + $('input[name=led]:checked').attr('id'));
					}
					
					if($('input[name=method]:checked').attr('id')!=undefined){
						$('#mirrorLedMethod').val('LED 동작방식 : ' + $('input[name=method]:checked').attr('id'));
					}else{
						$('#mirrorLedMethod').val('LED 동작방식 : 해당없음');
					}
					
					if($('input[name=form]:checked').attr('id')!=undefined){
						$('#mirrorLedForm').val('LED 형태 : ' + $('input[name=form]:checked').attr('id'));
					}else{
						$('#mirrorLedForm').val('LED 형태 : 해당없음');
					}
/*					
					if($('input[name=sensor]:checked').attr('id')!=undefined){
						$('#mirrorSensor').val('센서 추가여부 : ' + $('input[name=sensor]:checked').attr('id'));
					}else{
						$('#mirrorSensor').val('센서 추가여부 : 해당없음');
					}
					
					if($('input[name=sensor_form]:checked').attr('id')!=undefined){
						$('#mirrorSensorForm').val('센서 형태 : ' + $('input[name=sensor_form]:checked').attr('id'));
					}else{
						$('#mirrorSensorForm').val('센서 형태 : 해당없음');
					}
*/				}
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
/*		mirrorForm.append($('<input/>', {type: 'hidden', name: 'sensor', value:$('#mirrorSensor').val()}));
		mirrorForm.append($('<input/>', {type: 'hidden', name: 'sensorForm', value:$('#mirrorSensorForm').val()}));*/
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
	