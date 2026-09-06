<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dang ky</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card shadow">
                    <div class="card-header bg-success text-white text-center">
                        <h4><i class="fas fa-user-plus me-2"></i>Dang ky tai khoan</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${alert != null}">
                            <div class="alert alert-danger">${alert}</div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/register" method="post" id="registerForm" novalidate>
                            
                            <div class="mb-3">
                                <label class="form-label">Ten dang nhap <span class="text-danger">*</span></label>
                                <input type="text" name="username" id="username" class="form-control" 
                                       placeholder="Nhap ten dang nhap (3-30 ky tu)" required>
                                <div class="invalid-feedback" id="usernameError">Ten dang nhap phai tu 3-30 ky tu, chi bao gom chu cai, so, dau gach duoi</div>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Ho va ten <span class="text-danger">*</span></label>
                                <input type="text" name="fullname" id="fullname" class="form-control" 
                                       placeholder="Nhap ho va ten" required>
                                <div class="invalid-feedback" id="fullnameError">Vui long nhap ho va ten</div>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Email <span class="text-danger">*</span></label>
                                <input type="email" name="email" id="email" class="form-control" 
                                       placeholder="Nhap email" required>
                                <div class="invalid-feedback" id="emailError">Vui long nhap email hop le</div>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">So dien thoai</label>
                                <input type="tel" name="phone" id="phone" class="form-control" 
                                       placeholder="Nhap so dien thoai (10-11 so)">
                                <div class="invalid-feedback" id="phoneError">So dien thoai phai co 10-11 chu so</div>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Mat khau <span class="text-danger">*</span></label>
                                <input type="password" name="password" id="password" class="form-control" 
                                       placeholder="Nhap mat khau (toi thieu 6 ky tu)" required>
                                <div class="invalid-feedback" id="passwordError">Mat khau phai co it nhat 6 ky tu</div>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Xac nhan mat khau <span class="text-danger">*</span></label>
                                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" 
                                       placeholder="Nhap lai mat khau" required>
                                <div class="invalid-feedback" id="confirmPasswordError">Mat khau xac nhan khong khop</div>
                            </div>
                            
                            <div class="mb-3 text-muted small">
                                <i class="fas fa-info-circle me-1"></i> 
                                Sau khi dang ky, ban se nhan duoc ma OTP qua email de kich hoat tai khoan.
                            </div>
                            
                            <button type="submit" class="btn btn-success w-100">Dang ky</button>
                        </form>
                        
                        <div class="text-center mt-3">
                            <p>Da co tai khoan? <a href="${pageContext.request.contextPath}/login">Dang nhap</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>
    <script>
        document.getElementById('registerForm').addEventListener('submit', function(e) {
            clearAllErrors();
            var isValid = true;
            
            // Validate username
            var username = document.getElementById('username');
            if (!validateUsername(username.value.trim())) {
                showError('username', 'Ten dang nhap phai tu 3-30 ky tu, chi bao gom chu cai, so, dau gach duoi');
                isValid = false;
            }
            
            // Validate fullname
            var fullname = document.getElementById('fullname');
            if (fullname.value.trim() === '') {
                showError('fullname', 'Vui long nhap ho va ten');
                isValid = false;
            }
            
            // Validate email
            var email = document.getElementById('email');
            if (!validateEmail(email.value.trim())) {
                showError('email', 'Vui long nhap email hop le');
                isValid = false;
            }
            
            // Validate phone (optional)
            var phone = document.getElementById('phone');
            if (phone.value.trim() !== '' && !validatePhone(phone.value.trim())) {
                showError('phone', 'So dien thoai phai co 10-11 chu so');
                isValid = false;
            }
            
            // Validate password
            var password = document.getElementById('password');
            if (!validatePassword(password.value)) {
                showError('password', 'Mat khau phai co it nhat 6 ky tu');
                isValid = false;
            }
            
            // Validate confirm password
            var confirmPassword = document.getElementById('confirmPassword');
            if (confirmPassword.value !== password.value) {
                showError('confirmPassword', 'Mat khau xac nhan khong khop');
                isValid = false;
            }
            
            if (!isValid) {
                e.preventDefault();
            }
        });
    </script>
</body>
</html>