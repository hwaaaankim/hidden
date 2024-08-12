	/*  Wizard */
	jQuery(function ($) {
		"use strict";
		$('#submitBtn').attr('disabled', true);
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
				console.log(state.branchesActivated[2]);
				console.log(state.stepIndex);
				if(state.stepIndex === 13){
					$('#submitBtn').attr('disabled', false);
				}else{
					$('#submitBtn').attr('disabled', true);
				}
				if(state.stepIndex === 12){
					if($('input[name=model]:checked').attr('id')!=undefined){
						$('#flapModel').val('플랩장 모델 : ' + $('input[name=model]').attr('id'));
					}
					
					if($('input[name=color]:checked').attr('id')!=undefined){
						$('#flapColor').val('플랩장 색상 : ' + $('input[name=color]').attr('id'));
					}
					
					$('#flapWidth').val('가로길이 : ' + $('input[name=flapWidth]').val() + "[단위 : mm]");
					$('#flapHeight').val('세로길이 : ' + $('input[name=flapHeight]').val() + "[단위 : mm]");
					$('#flapDepth').val('깊이 : ' + $('input[name=flapDepth]').val() + "[단위 : mm]");
					
					
					if($('input[name=direction]:checked').attr('id')!=undefined){
						$('#flapDirection').val('도어 방향 : ' + $('input[name=direction]:checked').attr('id'));
					}else{
						$('#flapDirection').val('도어 방향 : 해당없음');
					}
					
					if($('input[name=led]:checked').attr('id')!=undefined){
						$('#flapLed').val('LED 추가여부 : ' + $('input[name=led]:checked').attr('id'));
					}else{
						$('#flapLed').val('LED 추가여부 : 해당없음');
					}
					
					if($('input[name=led_color]:checked').attr('id')!=undefined){
						$('#flapLedColor').val('LED 색상 : ' + $('input[name=led_color]:checked').attr('id'));
					}else{
						$('#flapLedColor').val('LED 색상 : 해당없음');
					}
					
					if($('input[name=option]:checked').attr('id')!=undefined){
						$('#flapOption').val('추가옵션[콘센트] : ' + $('input[name=option]:checked').attr('id'));
					}else{
						$('#flapOption').val('추가옵션[콘센트] : 해당없음');
					}
					
					if($('input[name=option_position]:checked').attr('id')!=undefined){
						$('#flapOptionPosition').val('추가옵션[콘센트] 위치 : ' + $('input[name=option_position]:checked').attr('id'));
					}else{
						$('#flapOptionPosition').val('추가옵션[콘센트] 위치 : 해당없음');
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
			
		
		var flapForm = $('<form></form>');
		//set attribute (form) 
		flapForm.attr("method","post");
		flapForm.attr("action","/order/flapOrderInsert");
	
		// create element & set attribute (input) 
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapModel', value: $('#flapModel').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapColor', value: $('#flapColor').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapWidth', value: $('#flapWidth').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapHeight', value: $('#flapHeight').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapDepth', value: $('#flapDepth').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapDoorDirection', value: $('#flapDirection').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapLed', value: $('#flapLed').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapLedColor', value: $('#flapLedColor').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapConcent', value: $('#flapOption').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'flapConcentPosition', value: $('#flapOptionPosition').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'name', value: $('#name').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'phone', value: $('#phone').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'email', value: $('#email').val() }));
		flapForm.append($('<input/>', {type: 'hidden', name: 'message', value: $('#message').val() }));

	
		flapForm.appendTo('body');
		flapForm.submit();
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
	