<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제주의 꿈 - 마이페이지</title>
		<link rel="stylesheet" href="../resources/css/reset.css">
        <link rel="stylesheet" href="../resources/css/header.css">
        <link rel="stylesheet" href="../resources/css/footer.css">
        
</head>
<body>
	
	<div id="container">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<main>
		<div class="sidebar">
		<div>
		 <div class="profile-image"></div>
		 <h3 class="profile-name">일용자님</h3>
		 <p class="profile-info">여행의 발견</p>
		</div>
		<ul class="menu-list">
			<li><a href="#">기본정보</a></li>
			<li><a href="#">예약내역</a></li>
			<li><a href="#">관심상품</a></li>
			<li><a href="#">후기관리</a></li>
			<li><a href="#">설정</a></li>
		</ul>
		</div>
		<div class="content-area">
			<h2 class="content_title">기본정보</h2>
			<form action"" method"">
				<div class="info-group">
					<label>아이디</label>
					<input type="text" readonly>
				</div>
				
				<div class="info-group">
					<label>이름</label>
					<input type="text" readonly>
				</div>
				
				<div class="info-group">
					<label>성별</label>
					<div class="radio-group">
					<label>
					<input type="radio" disabled>
					남
					</label>
					<label>
					<input type="radio" disabled>
					여
					</label>
					</div>
				</div>
					
					<div class="info-group">
					<label>나이</label>
					<input type="number" readonly>
					<!-- readonly역할 수정할수없게하는것? -->
					
				</div>
				
				<div class="info-group">
					<label>이메일</label>
					<input type="email" >
				</div>
				
				<div class="info-group">
					<label>전화번호</label>
					<input type="tel" >
				</div>
				
				<div class="info-group">
					<label>주소</label>
					<input type="text" >
				</div>
				
				<div class="info-group">
					<label>취미</label>
					<input type="text" >
				</div>
				
				<button type="submit" class="save-button">저장하기</button>
				
			</form>
		</div>
		</main>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
	</div>
	
</body>
</html>