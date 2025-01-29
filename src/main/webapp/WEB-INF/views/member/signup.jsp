<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>제주의 꿈 - 회원가입</title>
        <link rel="stylesheet" href="../resources/css/reset.css">
        <link rel="stylesheet" href="../resources/css/header.css">
        <link rel="stylesheet" href="../resources/css/footer.css">
        <link rel="stylesheet" href="../resources/css/signup.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
			<main>
				<div class="signup-container">
					<div class="signup-title">
						<h2>회원가입</h2>
						<p>제주의 꿈과 함께하는 여행의 시작</p>
					</div>
					
					<!-- form태그 안에 네임값 그리고 action!! method 중요!!
					input 태그에 name값중요!! 어딘가 내가 실습했던 적었던거 있음!!
					빠른실습을 위해 강사님 코드 복붙했음... -->
					
					<form class="signup-form" action="/member/signup" method="post">
						<div class="form-group">
							<label class="required">아이디</label>
							<input type="text" name="memberId" required>
						</div>
						<div class="form-group">
							<label class="required">비밀번호</label>
							<input type="password" name="memberPwd" required>
						</div>
						<div class="form-group">
							<label class="required">이름</label>
							<input type="text" name="memberName" required>
						</div>
						<div class="form-group">
							<label class="required">성별</label>
							<div class="radio-group">
								<label>
								 	<input type="radio" name="gender" value="M" required>
									남
								</label>
								<label>
									<input type="radio" name="gender" value="F" required> 
									여
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="required">나이</label>
							<input type="number" min="14" max="100" name="age" required>
						</div>
						<div class="form-group">
							<label>이메일</label>
							<input type="text" name="email">
						</div>
						<div class="form-group">
							<label>전화번호</label>
							<input type="text" name="phone">
						</div>
						<div class="form-group">
							<label>주소</label>
							<input type="text" name="address">
						</div>
						<div class="form-group">
							<label>취미</label>
							<input type="text" name="hobby">
						</div>
						<button type="submit" class="signup-button">가입하기</button>
					</form>
				</div>
			</main>
			<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		</div>
	</body>
</html>