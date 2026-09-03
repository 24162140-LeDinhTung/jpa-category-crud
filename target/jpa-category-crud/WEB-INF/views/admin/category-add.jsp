<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head><title>Thêm category</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Thêm danh mục</h2>
    <form action="<c:url value='/admin/category/insert'/>" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label>Tên danh mục</label>
            <input class="form-control" name="categoryname" required/>
        </div>
        <div class="mb-3">
            <label>Link ảnh (nếu có)</label>
            <input class="form-control" name="images" placeholder="https://..."/>
        </div>
        <div class="mb-3">
            <label>Upload ảnh</label>
            <input type="file" class="form-control" name="images1"/>
        </div>
        <div class="mb-3">
            <label>Trạng thái</label><br>
            <input type="radio" name="status" value="1" checked/> Hoạt động
            <input type="radio" name="status" value="0"/> Khóa
        </div>
        <button type="submit" class="btn btn-primary">Thêm</button>
        <a href="<c:url value='/admin/categories'/>" class="btn btn-secondary">Hủy</a>
    </form>
</div>
</body>
</html>