<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head><title>Sửa category</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Sửa danh mục</h2>
    <form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="categoryid" value="${cate.categoryId}"/>
        <div class="mb-3">
            <label>Tên danh mục</label>
            <input class="form-control" name="categoryname" value="${cate.categoryname}" required/>
        </div>
        <div class="mb-3">
            <label>Link ảnh (nếu có)</label>
            <input class="form-control" name="images" value="${cate.images}" placeholder="https://..."/>
        </div>
        <div class="mb-3">
            <label>Ảnh hiện tại</label><br>
            <c:choose>
                <c:when test="${cate.images.startsWith('http')}">
                    <img src="${cate.images}" height="100"/>
                </c:when>
                <c:otherwise>
                    <img src="<c:url value='/image?fname=${cate.images}'/>" height="100"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="mb-3">
            <label>Upload ảnh mới</label>
            <input type="file" class="form-control" name="images1"/>
        </div>
        <div class="mb-3">
            <label>Trạng thái</label><br>
            <input type="radio" name="status" value="1" ${cate.status == 1 ? 'checked' : ''}/> Hoạt động
            <input type="radio" name="status" value="0" ${cate.status != 1 ? 'checked' : ''}/> Khóa
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="<c:url value='/admin/categories'/>" class="btn btn-secondary">Hủy</a>
    </form>
</div>
</body>
</html>