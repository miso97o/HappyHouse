<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title> Happy House </title>
  <meta content="" name="description">
  <meta content="" name="keywords">
  
  <!-- ajax -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
  <!-- Favicons -->
  <link href="" rel="icon">
  <link href="${root}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Roboto:100,300,400,500,700|Philosopher:400,400i,700,700i"
	rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${root}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
  <link href="${root}/assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
  <link href="${root}/assets/vendor/modal-video/css/modal-video.min.css"
	rel="stylesheet">
  <link href="${root}/assets/vendor/owl.carousel/assets/owl.carousel.min.css"
	rel="stylesheet">
  <link href="${root}/assets/vendor/aos/aos.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${root}/assets/css/style.css" rel="stylesheet">
  <link href="${root}/css/style.css" rel="stylesheet">
  <!-- =======================================================
  * Template Name: eStartup - v2.2.1
  * Template URL: https://bootstrapmade.com/estartup-bootstrap-landing-page-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
  
  <style>
.btn {
	font-size: 16px;
	font-weight: bold;
	background: #5cd3b4;
	border: none;
	margin-top: 20px;
	min-width: 140px;
	border-radius: 0;
}
</style>
</head>


<body>
	<!-- ======= Start Navbar ======= -->
  <header id="header" class="header">
    <div class="container">

      <div id="logo" class="pull-left">
        <h1><a href="${root}/"><span>Happy</span> House</a></h1>
      </div>

      <nav id="nav-menu-container">
        <ul class="nav-menu">
          <li><a href="${root}/qnaboard">공지사항</a></li>
          <li><a href="${root}/air">미세먼지</a></li>
          <li><a href="#">오늘의 뉴스</a></li>
          
          <c:if test="${empty userinfo }">
	          <li><a href="${root}/user/signupForm">회원가입</a></li>
	          <li><a data-toggle="modal" data-target="#loginModal">로그인</a></li>
          </c:if>
          
          <c:if test="${!empty userinfo }">
	          <li><a href="${root }/main?act=detail">주택 매물 확인</a></li>
	          <li><a href="${root }/main?act=around">관심 지역 둘러보기</a></li>
	          <li><a href="${root}/user/memberInfo/${userinfo.id}">회원정보</a></li>
	          <li><a href="${root }/user/logout" id="logout">로그아웃</a></li>
          </c:if>
          
          <li><a href="#"><i class="fa fa-search" aria-hidden="true"></i></a></li>
        </ul>
      </nav>
    </div>
  </header>
  <!-- ======= End Navbar ======= -->
  <!-- ======= Start loginModal ======= -->
	<div id="loginModal" class="modal fade">
		<div class="modal-dialog modal-login">
			<div class="modal-content">
				<form action="${root}/user/login" method="post">
					<input type="hidden" name="act" value="login">
					
					<div class="modal-header">
						<h4 class="modal-title">Login</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>아이디</label> 
							<input type="text" name="id" class="form-control" required="required">
						</div>
						<div class="form-group">
							<div class="clearfix">
								<label>비밀번호</label> <a href="#" class="float-right text-muted"><small>비밀번호찾기</small></a>
							</div>
							<input type="password" name="password" class="form-control" required="required">
						</div>
					</div>
					<div class="form-group justify-content-center d-flex">
						<input type="submit" class="btn btn-primary" id="login" value="로그인">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- ======= End loginModal ======= -->
</body>

<script>
	if("${msg}"){
		alert("${msg}");
	}
	if("${success}"){
		alert("${success}");
	}
	
</script>
<c:remove var="msg"/>

</html>