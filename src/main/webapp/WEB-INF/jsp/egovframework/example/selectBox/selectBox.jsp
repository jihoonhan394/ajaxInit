<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
	$(function() {
		$("#parentSelectBox").change(function() {
			var thisParam = $(this).val();
			
			var form 	  = {"param" : thisParam};
			
			$("#childSelectBox > option").remove();
			
			$.ajax({
				type		: "post",
				url			: "/childSelectBox.do",
				data		: JSON.stringify(form),
				contentType : "application/json",
				success : function(data) {
					var jObj = JSON.parse(data);
					
					select.displayChildSelectBox(jObj.childList);
				}
			});
		});
	});
	
	var select = {
	
			displayChildSelectBox : function(childListArr) {
				
				if (childListArr.length > 0) {
					
					$.each(childListArr, function(i, item) {
						var optionStr = "<option value='" + item.prdCd +"'>" + item.prdNm + "</option>"
						
						$("#childSelectBox").append(optionStr);
					});
				} else {
					$("#childSelectBox").append("<option value=''>없음</option>");
				}
				
			}
	}
</script>
<div class="content">
	<div class="container-fluid">
      	<div class="row">
          	<div class="col-md-12">
              	<div class="card ">
	                <div class="header">
	                    <h4 class="title">셀렉트박스</h4>
	                    <p class="category">ajax 잘 모르고 쓰면 어렵지~</p>
	                </div>
	                <div class="content">
	                	<select id="parentSelectBox" name="parentSelectBox">
	                		<c:forEach items="${parentList}" var="parentVar">
	                			<option value='<c:out value="${parentVar.brandCd}"/>'><c:out value="${parentVar.brandNm}"/></option>
	                		</c:forEach>
	                	</select>
	                	<select id="childSelectBox" name="childSelectBox">
	                	</select>
	                </div>
                </div>
            </div>
        </div>
    </div>
</div>