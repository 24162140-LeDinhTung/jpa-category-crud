<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Admin Dashboard</sitemesh:write>

<div class="row">
    <div class="col-md-12">
        <h2>Admin Dashboard</h2>
        <p>Chào mừng <strong>${sessionScope.account.fullName}</strong>!</p>
        <hr>
        <div class="row">
            <div class="col-md-4">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-list"></i> Quản lý danh mục</h5>
                        <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-light">Vào quản lý</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-user"></i> Thông tin cá nhân</h5>
                        <a href="${pageContext.request.contextPath}/profile" class="btn btn-light">Xem profile</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>