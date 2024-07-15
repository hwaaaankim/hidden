!(function () {
    "use strict";
    function p() {
        var e = 12 <= new Date().getHours() ? "PM" : "AM",
            t = 12 < new Date().getHours() ? new Date().getHours() % 12 : new Date().getHours(),
            o = new Date().getMinutes() < 10 ? "0" + new Date().getMinutes() : new Date().getMinutes();
        return t < 10 ? "0" + t + ":" + o + " " + e : t + ":" + o + " " + e;
    }
    setInterval(p, 1e3),
        document.querySelector("#product-image-input").addEventListener("change", function () {
            var e = document.querySelector("#product-img"),
                t = document.querySelector("#product-image-input").files[0],
                o = new FileReader();
            o.addEventListener(
                "load",
                function () {
                    e.src = o.result;
                },
                !1
            ),
                t && o.readAsDataURL(t);
        });
        
        
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




var multiSelectBasic = document.getElementById("multiselect-basic"),
    multiSelectHeader = (multiSelectBasic && multi(multiSelectBasic, { enable_search: !1 }), document.getElementById("multiselect-header")),
    multiSelectOptGroup = (multiSelectHeader && multi(multiSelectHeader, { non_selected_header: "등록가능사이즈", selected_header: "등록된 사이즈" }), document.getElementById("multiselect-optiongroup"));
    
var multiSelectBasic = document.getElementById("multiselect-basic"),
    multiSelectHeader = (multiSelectBasic && multi(multiSelectBasic, { enable_search: !1 }), document.getElementById("multiselect-header02")),
    multiSelectOptGroup = (multiSelectHeader && multi(multiSelectHeader, { non_selected_header: "등록가능색상", selected_header: "등록된 색상" }), document.getElementById("multiselect-optiongroup"));
    
    
    
    
    
    
    
    
    
        
