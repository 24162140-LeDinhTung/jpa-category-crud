<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dat lai mat khau</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card shadow">
                    <div class="card-header bg-info text-white text-center">
                        <h4><i class="fas fa-redo-alt me-2"></i>Dat lai mat khau</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${alert != null}">
                            <div class="alert alert-danger">${alert}</div>
                        </c:if>
                        <c:if test="${success != null}">
                            <div class="alert alert-success">${success}</div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/reset-password" method="post" id="resetForm" novalidate>
                            <input type="hidden" name="email" value="${email}">

                            <div class="mb-3">
                                <label class="form-label">Ma OTP <span class="text-danger">*</span></label>
                                <input type="text" name="otp" id="otp" class="form-control" 
                                       placeholder="Nhap ma OTP 6 so" required pattern="[0-9]{6}">
                                <div class="invalid-feedback" id="otpError">Vui long nhap ma OTP 6 so</div>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Mat khau moi <span class="text-danger">*</span></label>
                                <input type="password" name="newPassword" id="newPassword" class="form-control" 
                                       placeholder="Nhap mat khau moi (toi thieu 6 ky tu)" required>
                                <div class="invalid-feedback" id="newPasswordError">Mat khau phai co it nhat 6 ky tu</div>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Xac nhan mat khau moi <span class="text-danger">*</span></label>
                                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" 
                                       placeholder="Nhap lai mat khau moi" required>
                                <div class="invalid-feedback" id="confirmPasswordError">Mat khau xac nhan khong khop</div>
                            </div>
                            
                            <button type="submit" class="btn btn-info w-100 text-white">Dat lai mat khau</button>
                        </form>

                        <div class="text-center mt-3">
                            <p><a href="${pageContext.request.contextPath}/login">Quay lai dang nhap</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>
    <script>
        document.getElementById('resetForm').addEventListener('submit', function(e) {
            clearAllErrors();
            var isValid = true;
            
            var otp = document.getElementById('otp');
            if (!/^[0-9]{6}$/.test(otp.value.trim())) {
                showError('otp', 'Vui long nhap ma OTP 6 so');
                isValid = false;
            }
            
            var newPassword = document.getElementById('newPassword');
            if (!validatePassword(newPassword.value)) {
                showError('newPassword', 'Mat khau phai co it nhat 6 ky tu');
                isValid = false;
            }
            
            var confirmPassword = document.getElementById('confirmPassword');
            if (confirmPassword.value !== newPassword.value) {
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