<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- taglib uri=적고 3번째꺼 선택후 prefix=c적어줌! -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제주의 꿈 - 마이페이지</title>
		<link rel="stylesheet" href="../resources/css/reset.css">
        <link rel="stylesheet" href="../resources/css/header.css">
        <link rel="stylesheet" href="../resources/css/footer.css">
        <link rel="stylesheet" href="../resources/css/mypage.css">
</head>
<body>
	
	<div id="container">
		<!-- jsp:include ->jsp: include 띄어쓰기하면 에러남주의!! -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<main>
			<div class="mypage-container">
		<div class="sidebar">
		<div class="profile-section">
		 <div class="profile-image"></div>
		 <h3 class="profile-name">${requestScope.member.memberName }님</h3>
		 <p class="profile-info">여행의 발견</p>
		 <p class="enroll-date">${requestScope.member.enrollDate }</p>
		</div>
		<ul class="menu-list">
			<li><a href="#" class="active">기본정보</a></li>
			<li><a href="#">예약내역</a></li>
			<li><a href="#">관심상품</a></li>
			<li><a href="#">후기관리</a></li>
			<li><a href="#">설정</a></li>
		</ul>
		</div>
		<div class="content-area">
			<h2 class="content_title">기본정보</h2>
			<form action="/member/update" method="post">
				<div class="info-group">
					<label>아이디</label>
					<input type="text" value="${requestScope.member.memberId }" name="memberId" readonly>
				</div>
				
				<div class="info-group">
					<label>이름</label>
					<input type="text" value="${requestScope.member.memberName }" readonly>
				</div>
				
				<div class="info-group">
					<label>성별</label>
				<div class="radio-group">
				<!-- eq 랑 == 이랑 같다?? 주석 에러나서 설명하는 cif 하는거 지움
				테스트 오케이! 남녀가 바꿔가면서 마이페이지에 체크되는거확인!
				test= 여기서 =은 붙여써야함 에러남-->
					<label>
					<input type="radio" disabled <c:if test="${requestScope.member.gender == 'M' }">checked</c:if>> 
					남
					</label>
					<label>
					<input type="radio" disabled <c:if test="${requestScope.member.gender eq 'F' }">checked</c:if>> 
					여
					</label>
					</div>
				</div>
					
					<div class="info-group">
					<label>나이</label>
					<input type="number" value="${requestScope.member.age }" readonly>
					<!-- readonly역할 수정할수없게하는것? -->
					
				</div>
				
				<div class="info-group">
					<label>이메일</label>
					<input type="email" value="${requestScope.member.email }" name="email" >
				</div>
				
				<div class="info-group">
					<label>전화번호</label>
					<input type="tel"value="${requestScope.member.phone }" name="phone" >
				</div>
				
				<div class="info-group">
					<label>주소</label>
					<input type="text"  value="${requestScope.member.address }" name="address" >
				</div>
				
				<div class="info-group">
					<label>취미</label>
					<input type="text" value="${requestScope.member.hobby }" name="hobby" >
				</div>
				
				<button type="submit" class="save-button">저장하기</button>
				
				<!-- div대신span을쓰면 옆으로이동 -->
				<span class="del-button">
				<a href="/member/delete">회원탈퇴</a>
				</span>
			
			</form>
		</div>
		</div>
		</main>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
	</div>
	
</body>
</html>