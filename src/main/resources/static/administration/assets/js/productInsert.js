!(function() {
	$('#bigSort').on('change', function() {
		$.ajax({
			cache: false,
			type: 'POST',
			url: '/admin/searchMiddleSort',
			data: {
				bigId: $(this).val()
			}, success: function(result) {
				$('#middleSort').find('option').remove();
				$('#middleSort').append("<option value=''> === 중분류 선택 === </option>");
				for (var i = 0; i < result.length; i++) {
					var option = $("<option value=" + result[i].id + ">" + result[i].name + "</option>");
					$('#middleSort').append(option);
				}
			}

		});
	});
})();



var multiSelectHeader = document.getElementById("multiselect-header");
if (multiSelectHeader) {
	multi(multiSelectHeader, {
		non_selected_header: "등록가능사이즈",
		selected_header: "등록된 사이즈"
	});
}
var multiSelectHeader02 = document.getElementById("multiselect-header02");
if (multiSelectHeader02) {
	multi(multiSelectHeader02, {
		non_selected_header: "등록가능색상",
		selected_header: "등록된 색상"
	});
}
var multiSelectHeader03 = document.getElementById("multiselect-header03");
if (multiSelectHeader03) {
	multi(multiSelectHeader03, {
		non_selected_header: "등록가능옵션",
		selected_header: "등록된 옵션"
	});
}
var multiSelectHeader04 = document.getElementById("multiselect-header04");
if (multiSelectHeader04) {
	multi(multiSelectHeader04, {
		non_selected_header: "등록가능태그",
		selected_header: "등록된 태그"
	});
}

// 대표이미지(단일)
const productImageInput = document.getElementById('product-image-input');
const productImagePreview = document.getElementById('product-image-preview');
productImageInput.addEventListener('change', function() {
	productImagePreview.innerHTML = '';
	if (this.files && this.files[0]) {
		const file = this.files[0];
		const url = URL.createObjectURL(file);
		const wrapper = document.createElement('div');
		wrapper.style.position = 'relative';

		const img = document.createElement('img');
		img.src = url;
		img.style.width = '150px';
		img.style.height = 'auto';
		img.classList.add('rounded', 'border');

		const delBtn = document.createElement('button');
		delBtn.type = 'button';
		delBtn.innerHTML = '&times;';
		delBtn.style.position = 'absolute';
		delBtn.style.top = '0px';
		delBtn.style.right = '0px';
		delBtn.className = 'btn btn-sm btn-danger';

		delBtn.onclick = function() {
			productImageInput.value = '';
			productImagePreview.innerHTML = '';
		};

		wrapper.appendChild(img);
		wrapper.appendChild(delBtn);
		productImagePreview.appendChild(wrapper);
	}
});

// 도면이미지(단일)
const drawingImageInput = document.getElementById('drawing-image-input');
const drawingImagePreview = document.getElementById('drawing-image-preview');
drawingImageInput.addEventListener('change', function() {
	drawingImagePreview.innerHTML = '';
	if (this.files && this.files[0]) {
		const file = this.files[0];
		const url = URL.createObjectURL(file);
		const wrapper = document.createElement('div');
		wrapper.style.position = 'relative';

		const img = document.createElement('img');
		img.src = url;
		img.style.width = '150px';
		img.style.height = 'auto';
		img.classList.add('rounded', 'border');

		const delBtn = document.createElement('button');
		delBtn.type = 'button';
		delBtn.innerHTML = '&times;';
		delBtn.style.position = 'absolute';
		delBtn.style.top = '0px';
		delBtn.style.right = '0px';
		delBtn.className = 'btn btn-sm btn-danger';

		delBtn.onclick = function() {
			drawingImageInput.value = '';
			drawingImagePreview.innerHTML = '';
		};

		wrapper.appendChild(img);
		wrapper.appendChild(delBtn);
		drawingImagePreview.appendChild(wrapper);
	}
});

// 슬라이드이미지(multiple)
function multiFilePreview(inputId, previewId) {
	let input = document.getElementById(inputId);
	const preview = document.getElementById(previewId);

	input.addEventListener('change', function(e) {
		render(Array.from(this.files));
	});

	function render(fileArr) {
		preview.innerHTML = '';
		fileArr.forEach((file, idx) => {
			const url = URL.createObjectURL(file);
			const wrapper = document.createElement('div');
			wrapper.style.position = 'relative';

			const img = document.createElement('img');
			img.src = url;
			img.style.width = '150px';
			img.style.height = 'auto';
			img.classList.add('rounded', 'border');

			const delBtn = document.createElement('button');
			delBtn.type = 'button';
			delBtn.innerHTML = '&times;';
			delBtn.style.position = 'absolute';
			delBtn.style.top = '0px';
			delBtn.style.right = '0px';
			delBtn.className = 'btn btn-sm btn-danger';

			delBtn.onclick = function() {
				// 삭제한 파일 제외한 배열
				fileArr.splice(idx, 1);

				// input 새로 만들기
				const newInput = document.createElement('input');
				newInput.type = 'file';
				newInput.className = input.className;
				newInput.id = input.id;
				newInput.name = input.name;
				newInput.accept = input.accept;
				newInput.setAttribute('multiple', '');

				// 파일 배열을 DataTransfer로 복사
				const dt = new DataTransfer();
				fileArr.forEach(f => dt.items.add(f));
				newInput.files = dt.files;

				// 기존 input 교체
				input.parentNode.replaceChild(newInput, input);
				input = newInput;

				// change 이벤트 재등록
				input.addEventListener('change', function(e) {
					render(Array.from(this.files));
				});

				render(fileArr);
			};

			wrapper.appendChild(img);
			wrapper.appendChild(delBtn);
			preview.appendChild(wrapper);
		});
	}
}
multiFilePreview('slide-image-input', 'slide-image-preview');

let isProductCodeValid = false;
const codeInput = document.getElementById('product-code-input');
const codeMsg = document.getElementById('product-code-check-msg');
const form = document.getElementById('productInsertForm');

codeInput.addEventListener('focusout', function() {
	const code = this.value.trim();
	if (!code) {
		codeMsg.textContent = '';
		isProductCodeValid = false;
		return;
	}
	fetch(`/admin/code-duplicate?code=${encodeURIComponent(code)}`)
		.then(res => res.json())
		.then(isDuplicate => {
			if (isDuplicate) {
				codeMsg.textContent = "이미 사용 중인 코드입니다.";
				codeMsg.style.color = "#e74c3c";
				isProductCodeValid = false;
			} else {
				codeMsg.textContent = "사용 가능한 코드입니다.";
				codeMsg.style.color = "#27ae60";
				isProductCodeValid = true;
			}
		}).catch(e => {
			codeMsg.textContent = "중복체크 실패(네트워크 오류)";
			codeMsg.style.color = "#e74c3c";
			isProductCodeValid = false;
		});
});

// 폼 제출 차단
form.addEventListener('submit', function(e) {
	if (!isProductCodeValid) {
		e.preventDefault();
		codeMsg.textContent = "제품코드 중복체크를 통과해야 합니다.";
		codeMsg.style.color = "#e74c3c";
		codeInput.focus();
		return false;
	}
});



