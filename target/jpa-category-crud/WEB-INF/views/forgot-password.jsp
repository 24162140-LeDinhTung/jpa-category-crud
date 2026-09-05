<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quen mat khau</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header bg-danger text-white text-center">
                        <h4>Quen mat khau</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${alert != null}">
                            <div class="alert alert-danger">${alert}</div>
                        </c:if>
                        <c:if test="${success != null}">
                            <div class="alert alert-success">${success}</div>
                        </c:if>

                        <p class="text-muted">Nhap email da dang ky, chung toi se gui ma OTP de dat lai mat khau.</p>

                        <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <input type="email" name="email" class="form-control" required>
                            </div>
                            <button type="submit" class="btn btn-danger w-100">Gui OTP</button>
                        </form>

                        <div class="text-center mt-3">
                            <p><a href="${pageContext.request.contextPath}/login">Quay lai dang nhap</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>