<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dang nhap</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white text-center">
                        <h4><i class="fas fa-sign-in-alt me-2"></i>Dang nhap</h4>
                    </div>
                    <div class="card-body">
                        <!-- Server-side validation messages -->
                        <c:if test="${alert != null}">
                            <div class="alert alert-danger alert-dismissible fade show">
                                <i class="fas fa-exclamation-circle me-2"></i>${alert}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>
                        <c:if test="${success != null}">
                            <div class="alert alert-success alert-dismissible fade show">
                                <i class="fas fa-check-circle me-2"></i>${success}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/login" method="post" id="loginForm" novalidate>
                            <div class="mb-3">
                                <label class="form-label"><i class="fas fa-user me-2"></i>Ten dang nhap</label>
                                <input type="text" name="username" id="username" class="form-control" 
                                       placeholder="Nhap ten dang nhap" required>
                                <div class="invalid-feedback" id="usernameError">Vui long nhap ten dang nhap</div>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label"><i class="fas fa-lock me-2"></i>Mat khau</label>
                                <input type="password" name="password" id="password" class="form-control" 
                                       placeholder="Nhap mat khau" required>
                                <div class="invalid-feedback" id="passwordError">Vui long nhap mat khau</div>
                            </div>
                            
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="remember" name="remember">
                                <label class="form-check-label" for="remember">Ghi nho dang nhap</label>
                            </div>
                            
                            <button type="submit" class="btn btn-primary w-100">Dang nhap</button>
                        </form>
                        
                        <div class="text-center mt-3">
                            <p><a href="${pageContext.request.contextPath}/forgot-password">Quen mat khau?</a></p>
                            <p>Chua co tai khoan? <a href="${pageContext.request.contextPath}/register">Dang ky</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>
    <script>
        document.getElementById('loginForm').addEventListener('submit', function(e) {
            clearAllErrors();
            var isValid = true;
            
            var username = document.getElementById('username');
            if (username.value.trim() === '') {
                showError('username', 'Vui long nhap ten dang nhap');
                isValid = false;
            }
            
            var password = document.getElementById('password');
            if (password.value.trim() === '') {
                showError('password', 'Vui long nhap mat khau');
                isValid = false;
            }
            
            if (!isValid) {
                e.preventDefault();
            }
        });
    </script>
</body>
</html>