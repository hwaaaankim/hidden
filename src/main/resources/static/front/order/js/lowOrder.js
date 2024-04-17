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
				console.log(state.stepIndex);
				if ($('input#website').val().length != 0) {
					return false;
				}
				if (!state.isMovingForward)
					return true;
				var inputs = $(this).wizard('state').step.find(':input');
				return !inputs.length || !!inputs.valid();
			},
			afterSelect:function(event, state){
				if(state.stepIndex === 15){
					$('#submitBtn').attr('disabled', false);
				}else{
					$('#submitBtn').attr('disabled', true);
				}
				if(state.stepIndex === 14){
					if($('input[name=model]:checked').attr('id')!=undefined){
						$('#lowModel').val('하부장 모델 : ' + $('input[name=model]').attr('id'));
					}
					
					if($('input[name=color]:checked').attr('id')!=undefined){
						$('#lowColor').val('하부장 색상 : ' + $('input[name=color]').attr('id'));
					}
					
					if($('input[name=form]:checked').attr('id')!=undefined){
						$('#lowForm').val('하부장 형태 : ' + $('input[name=form]').attr('id'));
					}
					
					$('#lowWidth').val('가로길이 : ' + $('input[name=lowWidth]').val() + "[단위 : mm]");
					$('#lowHeight').val('세로길이 : ' + $('input[name=lowHeight]').val() + "[단위 : mm]");
					$('#lowDepth').val('깊이 : ' + $('input[name=lowDepth]').val() + "[단위 : mm]");
					
					
					if($('input[name=marble_color]:checked').attr('id')!=undefined){
						$('#lowMarbleColor').val('대리석 색상 : ' + $('input[name=marble_color]').attr('id'));
					}
					
					if($('input[name=washstand]:checked').attr('id')!=undefined){
						$('#lowWashstand').val('세면대 : ' + $('input[name=color]').attr('id'));
					}
					
					if($('input[name=washstand_topball]:checked').attr('id')!=undefined){
						$('#lowTopWashstand').val('탑볼형 세면대 : ' + $('input[name=washstand_topball]:checked').attr('id'));
					}else{
						$('#lowTopWashstand').val('탑볼형 세면대 : 해당없음');
					}
					
					if($('input[name=washstand_direction]:checked').attr('id')!=undefined){
						$('#lowWashstandPosition').val('LED 색상 : ' + $('input[name=washstand_direction]:checked').attr('id'));
					}
					
					
					if($('input[name=door]:checked').attr('id')!=undefined){
						$('#lowDoor').val('도어 종류 : ' + $('input[name=door]:checked').attr('id'));
					}else{
						$('#lowDoor').val('도어 종류 : 해당없음');
					}
					
					if($('input[name=door_count]:checked').attr('id')!=undefined){
						$('#lowDoorCount').val('도어 개수 : ' + $('input[name=door_count]:checked').attr('id'));
					}else{
						$('#lowDoorCount').val('도어 개수 : 해당없음');
					}
					
					if($('input[name=handle]:checked').attr('id')!=undefined){
						$('#lowHandle').val('손잡이 : ' + $('input[name=handle]:checked').attr('id'));
					}
					
					if($('input[name=handle_color]:checked').attr('id')!=undefined){
						$('#lowHandleColor').val('손잡이 색상 : ' + $('input[name=handle_color]:checked').attr('id'));
					}else{
						$('#lowHandleColor').val('손잡이 색상 : 해당없음');
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
	