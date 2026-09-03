<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Trang chủ</sitemesh:write>

<div class="jumbotron">
    <h1 class="display-4">Chào mừng, ${sessionScope.account.fullName}!</h1>
    <p class="lead">Đây là trang chủ của bạn.</p>
    <hr class="my-4">
    <p>Bạn có thể vào <a href="${pageContext.request.contextPath}/profile">Profile</a> để cập nhật thông tin.</p>
    <c:if test="${sessionScope.account.roleId == 1}">
        <p>Bạn là Admin, vào <a href="${pageContext.request.contextPath}/admin/home">Admin Panel</a> để quản lý.</p>
    </c:if>
</div>