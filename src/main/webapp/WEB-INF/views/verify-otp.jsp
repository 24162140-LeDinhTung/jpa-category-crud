<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xac nhan OTP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header bg-warning text-dark text-center">
                        <h4>Xac nhan OTP</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${alert != null}">
                            <div class="alert alert-danger">${alert}</div>
                        </c:if>
                        <c:if test="${success != null}">
                            <div class="alert alert-success">${success}</div>
                        </c:if>

                        <p class="text-muted">Ma OTP da duoc gui den email cua ban.</p>

                        <form action="${pageContext.request.contextPath}/verify-otp" method="post">
                            <input type="hidden" name="email" value="${email}">
                            <div class="mb-3">
                                <label class="form-label">Ma OTP</label>
                                <input type="text" name="otp" class="form-control" placeholder="Nhap ma 6 so" required pattern="[0-9]{6}">
                            </div>
                            <button type="submit" class="btn btn-warning w-100">Xac nhan</button>
                        </form>

                        <div class="text-center mt-3">
                            <p><a href="${pageContext.request.contextPath}/resend-otp?email=${email}">Gui lai OTP</a></p>
                            <p><a href="${pageContext.request.contextPath}/login">Quay lai dang nhap</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>