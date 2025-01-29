<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header id="header">
                <div id="logo">
                <a href="/">
                    <h1>Dream Jeju</h1>
                </a>
                </div>
                <!-- ul>li*4>a 만드는방법-->
                <nav id="nav">
                    <ul class="topmenu">
                        <li><a href="#">단체여행</a></li>
                        <li><a href="#">맞춤여행</a></li>
                        <li><a href="#">갤러리</a></li>
                        <li><a href="#">문의하기</a></li>
                        
                        <!-- memberId가 없으면 로그인버튼보이고 -->
                        <!-- memberId가 없으면 로그인이 안보이도록해야함 -->
                        <c:if test="${sessionScope.member.memberId == null }">
                        <li><a href="/member/login">로그인</a></li>
                        </c:if>
                         <!-- memberId가 없으면 로그인이 안보이도록해야함 -->
                        <c:if test="${sessionScope.member.memberId != null }">
                      
                        <li><a href="/member/logout">로그아웃</a></li>
                        </c:if>
                    </ul>
                </nav>
            </header>