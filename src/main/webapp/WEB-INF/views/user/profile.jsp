<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Thong tin tai khoan</sitemesh:write>

<div class="row justify-content-center">
    <div class="col-md-8 col-lg-6">
        <div class="card shadow">
            <div class="card-header bg-primary text-white text-center">
                <h4><i class="fas fa-user-circle me-2"></i>Thong tin tai khoan</h4>
            </div>
            <div class="card-body p-4">

                <c:if test="${message != null}">
                    <div class="alert alert-${alertType} alert-dismissible fade show">
                        <i class="fas fa-${alertType == 'success' ? 'check-circle' : 'exclamation-circle'} me-2"></i>
                        ${message}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/profile/update" method="post" 
                      enctype="multipart/form-data" id="profileForm" novalidate>

                    <!-- ===== AVATAR ===== -->
                    <div class="text-center mb-3">
                        <c:choose>
                            <c:when test="${user.avatar != null && user.avatar != ''}">
                                <c:url value="/image?fname=${user.avatar}" var="avatarUrl"/>
                                <img src="${avatarUrl}" alt="Avatar" 
                                     style="width:150px;height:150px;border-radius:50%;object-fit:cover;border:4px solid #1877f2;">
                            </c:when>
                            <c:otherwise>
                                <div class="bg-secondary d-inline-flex align-items-center justify-content-center text-white rounded-circle" 
                                     style="width:150px;height:150px;font-size:60px;">
                                    <i class="fas fa-user"></i>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <!-- Upload avatar -->
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-image me-2"></i>Anh dai dien</label>
                        <input type="file" class="form-control" name="avatar" accept="image/*" id="avatar">
                        <div class="invalid-feedback" id="avatarError">Vui long chon file anh hop le (jpg, png, gif)</div>
                        <small class="text-muted">De trong neu khong muon thay doi</small>
                    </div>

                    <!-- ===== THONG TIN CO DINH ===== -->
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-user me-2"></i>Ten dang nhap</label>
                        <input type="text" class="form-control" value="${user.userName}" disabled>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-envelope me-2"></i>Email</label>
                        <input type="text" class="form-control" value="${user.email}" disabled>
                    </div>

                    <!-- ===== THONG TIN CO THE SUA ===== -->
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-user-tag me-2"></i>Ho va ten <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" name="fullname" id="fullname" 
                               value="${user.fullName}" required>
                        <div class="invalid-feedback" id="fullnameError">Vui long nhap ho va ten</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-phone me-2"></i>So dien thoai</label>
                        <input type="tel" class="form-control" name="phone" id="phone" value="${user.phone}">
                        <div class="invalid-feedback" id="phoneError">So dien thoai phai co 10-11 chu so</div>
                    </div>

                    <!-- ===== THONG TIN PHU ===== -->
                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-id-badge me-2"></i>Vai tro</label>
                        <input type="text" class="form-control" value="
                            <c:choose>
                                <c:when test="${user.roleId == 1}">Administrator</c:when>
                                <c:when test="${user.roleId == 2}">Manager</c:when>
                                <c:otherwise>User</c:otherwise>
                            </c:choose>
                        " disabled>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><i class="fas fa-calendar me-2"></i>Ngay tao</label>
                        <input type="text" class="form-control" value="${user.createdDate}" disabled>
                    </div>

                    <!-- ===== NUT BUTTON ===== -->
                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-2"></i>Cap nhat
                        </button>
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">
                            <i class="fas fa-arrow-left me-2"></i>Quay lai
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- ===== JAVASCRIPT VALIDATION ===== -->
<script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>
<script>
    document.getElementById('profileForm').addEventListener('submit', function(e) {
        clearAllErrors();
        var isValid = true;
        
        // Validate fullname
        var fullname = document.getElementById('fullname');
        if (fullname.value.trim() === '') {
            showError('fullname', 'Vui long nhap ho va ten');
            isValid = false;
        }
        
        // Validate phone (optional)
        var phone = document.getElementById('phone');
        if (phone.value.trim() !== '' && !validatePhone(phone.value.trim())) {
            showError('phone', 'So dien thoai phai co 10-11 chu so');
            isValid = false;
        }
        
        // Validate avatar (optional - file size and type)
        var avatar = document.getElementById('avatar');
        if (avatar.files.length > 0) {
            var file = avatar.files[0];
            var validTypes = ['image/jpeg', 'image/png', 'image/gif', 'image/webp'];
            var maxSize = 5 * 1024 * 1024; // 5MB
            
            if (!validTypes.includes(file.type)) {
                showError('avatar', 'Vui long chon file anh (jpg, png, gif, webp)');
                isValid = false;
            } else if (file.size > maxSize) {
                showError('avatar', 'Kich thuoc anh khong duoc vuot qua 5MB');
                isValid = false;
            }
        }
        
        if (!isValid) {
            e.preventDefault();
        }
    });
</script>