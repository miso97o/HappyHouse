<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  회원가입 페이지 -->
<!DOCTYPE html>
<html lang="en">

<head>
<!-- =======================================================
  * Template Name: eStartup - v2.2.1
  * Template URL: https://bootstrapmade.com/estartup-bootstrap-landing-page-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

<style>
.form-control {
	border-color: #eee;
	min-height: 41px;
	box-shadow: none !important;
}

.form-control:focus {
	border-color: #5cd3b4;
}

.form-control, .btn {
	border-radius: 3px;
}

.signup-form {
	width: 500px;
	margin: 0 auto;
	padding: 30px 0;
}

.signup-form h2 {
	color: #333;
	margin: 0 0 30px 0;
	display: inline-block;
	padding: 0 30px 10px 0;
	border-bottom: 3px solid #5cd3b4;
}

.signup-form form {
	color: #999;
	border-radius: 3px;
	margin-bottom: 15px;
	background: #fff;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.signup-form .form-group row {
	margin-bottom: 20px;
}

.signup-form label {
	font-weight: normal;
	font-size: 14px;
	line-height: 2;
}

.signup-form input[type="checkbox"] {
	position: relative;
	top: 1px;
}

.signup-form .btn {
	font-size: 16px;
	font-weight: bold;
	background: #5cd3b4;
	border: none;
	margin-top: 20px;
	min-width: 140px;
}

.signup-form .btn:hover, .signup-form .btn:focus {
	background: #41cba9;
	outline: none !important;
}

.signup-form a {
	color: #5cd3b4;
	text-decoration: underline;
}

.signup-form a:hover {
	text-decoration: none;
}

.signup-form form a {
	color: #5cd3b4;
	text-decoration: none;
}

.signup-form form a:hover {
	text-decoration: underline;
}
</style>
</head>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<script type="text/javascript">
	$(document).on("click", ".delBtn", function() {
		console.log("click")
		if(confirm("정말 삭제?")) {
			location.href= "${root}/user/delete/${userinfo.id}"
		}
	})
</script>
<body>

	<!-- ======= Hero Section ======= -->
	<section id="hero">
		<div class="signup-form">
			<form action="${root}/user/update" method="post" class="form-horizontal">
				<div class="row">
					<div class="col-8 offset-4">
						<h2>회원 정보 확인</h2>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-4">아이디</label>
					<div class="col-8">
						<input type="text" class="form-control" value ="${userinfo.id}" name="id" readonly>					
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-4">비밀번호</label>
					<div class="col-8">
						<input type="password" class="form-control" value ="${userinfo.password}" name="password" required="required">					
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-4">이름</label>
					<div class="col-8">
						<input type="text" class="form-control" value ="${userinfo.name}" name="name" required="required">					
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-4">이메일</label>
					<div class="col-8">
						<input type="text" class="form-control" value ="${userinfo.email}" name="email" required="required">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-4">주소</label>
					<div class="col-8">
						<input type="text" class="form-control" value ="${userinfo.address}" name="address" required="required">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-4">전화번호</label>
					<div class="col-8">
						<input type="phone" class="form-control" value ="${userinfo.phone}" name="phone" required="required">
					</div>
				</div>
				
				<div class="form-group">
					<div class="d-flex justify-content-center">
						<button type="submit" name="button" value="update" class="btn btn-primary btn-lg mr-3">수정
							&nbsp; &#10003;</button>
						<button type="button" value="delete" class="delBtn btn btn-primary btn-lg">탈퇴 
							&nbsp; &#10003;</button>
					</div>
				</div>
			</form>
			
		</div>
	</section>
	<!-- End Hero Section -->

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
<%@ include file="/WEB-INF/views/include/vendorjs.jsp"%>
</html>

