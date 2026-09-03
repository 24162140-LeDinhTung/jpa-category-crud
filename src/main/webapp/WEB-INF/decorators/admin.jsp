<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><sitemesh:write property="title"/> - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <sitemesh:write property="head"/>
</head>
<body>
    <!-- Admin Header -->
    <jsp:include page="/WEB-INF/commons/admin/header.jsp"/>
    
    <!-- Nội dung chính -->
    <div class="container-fluid mt-4 min-vh-100">
        <sitemesh:write property="body"/>
    </div>
    
    <!-- Admin Footer -->
    <jsp:include page="/WEB-INF/commons/admin/footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>