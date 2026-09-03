<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- Đặt title cho trang -->
<sitemesh:write property="title">Thông tin tài khoản</sitemesh:write>

<div class="row justify-content-center">
    <div class="col-md-8 col-lg-6">
        <div class="card profile-card" style="border-radius:15px;box-shadow:0 4px 12px rgba(0,0,0,0.1);">
            <div class="card-header bg-primary text-white text-center" style="border-radius:15px 15px 0 0;">
                <h4><i class="fas fa-user-circle me-2"></i>Thông tin tài khoản</h4>
            </div>
            <div class="card-body p-4">
                
                <!-- Thông báo -->
                <c:if test="${message != null}">
                    <div class="alert alert-${alertType} alert-dismissible fade show">
                        <i class="fas fa-${alertType == 'success' ? 'check-circle' : 'exclamation-circle'} me-2"></i>
                        ${message}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>
                
                <form action="${pageContext.request.contextPath}/profile/update" method="post" enctype="multipart/form-data">
                    
                    <!-- Avatar -->
                    <div class="text-center mb-3">
                        <c:choose>
                            <c:when test="${user.avatar != null && user.avatar != ''}">
                                <c:url value="/image?fname=${user.avatar}" var="avatarUrl"/>
                                <img src="${avatarUrl}" alt="Avatar" class="rounded-circle" style="width:150px;height:150px;object-fit:cover;border:4px solid #1877f2;"/>
                            </c:when>
                            <c:otherwise>
                                <div class="bg-secondary d-inline-flex align-items-center justify-content-center text-white rounded-circle" style="width:150px;height:150px;font-size:60px;">
                                    <i class="fas fa-user"></i>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    
                    <!-- Upload ảnh -->
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-image me-2"></i>Ảnh đại diện</label>
                        <input type="file" class="form-control" name="avatar" accept="image/*">
                        <small class="text-muted">Chọn ảnh mới để thay đổi (để trống nếu không muốn thay đổi)</small>
                    </div>
                    
                    <!-- Thông tin -->
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-user me-2"></i>Tên đăng nhập</label>
                        <input type="text" class="form-control" value="${user.userName}" disabled>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-envelope me-2"></i>Email</label>
                        <input type="text" class="form-control" value="${user.email}" disabled>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-user-tag me-2"></i>Họ và tên</label>
                        <input type="text" class="form-control" name="fullname" value="${user.fullName}" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-phone me-2"></i>Số điện thoại</label>
                        <input type="tel" class="form-control" name="phone" value="${user.phone}">
                    </div>
                    
                    <!-- Vai trò -->
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-id-badge me-2"></i>Vai trò</label>
                        <input type="text" class="form-control" value="
                            <c:choose>
                                <c:when test="${user.roleId == 1}">Administrator</c:when>
                                <c:when test="${user.roleId == 2}">Manager</c:when>
                                <c:otherwise>User</c:otherwise>
                            </c:choose>
                        " disabled>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-calendar me-2"></i>Ngày tạo</label>
                        <input type="text" class="form-control" value="${user.createdDate}" disabled>
                    </div>
                    
                    <!-- Nút bấm -->
                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-2"></i>Cập nhật
                        </button>
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">
                            <i class="fas fa-arrow-left me-2"></i>Quay lại
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>