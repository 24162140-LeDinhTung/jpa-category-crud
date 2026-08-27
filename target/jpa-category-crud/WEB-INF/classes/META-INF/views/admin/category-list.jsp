<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head><title>Danh sách category</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Quản lý danh mục</h2>
    <a href="<c:url value='/admin/category/add'/>" class="btn btn-primary mb-3">+ Thêm mới</a>
    <table class="table table-bordered">
        <thead><tr><th>STT</th><th>Hình</th><th>Tên</th><th>Trạng thái</th><th>Thao tác</th></tr></thead>
        <tbody>
        <c:forEach var="c" items="${listcate}" varStatus="stt">
            <tr>
                <td>${stt.index+1}</td>
                <td>
                    <c:choose>
                        <c:when test="${c.images.startsWith('http')}">
                            <img src="${c.images}" height="50"/>
                        </c:when>
                        <c:otherwise>
                            <img src="<c:url value='/image?fname=${c.images}'/>" height="50"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${c.categoryname}</td>
                <td>${c.status == 1 ? 'Hoạt động' : 'Khóa'}</td>
                <td>
                    <a href="<c:url value='/admin/category/edit?id=${c.categoryId}'/>" class="btn btn-sm btn-warning">Sửa</a>
                    <a href="<c:url value='/admin/category/delete?id=${c.categoryId}'/>" class="btn btn-sm btn-danger" onclick="return confirm('Xóa?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>