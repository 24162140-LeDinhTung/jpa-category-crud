<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Them san pham</sitemesh:write>

<div class="container mt-4">
    <h2>Them san pham moi</h2>
    <form action="${pageContext.request.contextPath}/admin/product/insert" method="post" enctype="multipart/form-data">
        
        <div class="mb-3">
            <label class="form-label">Ten san pham</label>
            <input type="text" name="name" class="form-control" required>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Mo ta</label>
            <textarea name="description" class="form-control" rows="3"></textarea>
        </div>
        
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">Gia (VND)</label>
                    <input type="number" name="price" class="form-control" required step="0.01">
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">Ton kho</label>
                    <input type="number" name="stock" class="form-control" required>
                </div>
            </div>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Danh muc</label>
            <select name="categoryId" class="form-select" required>
                <option value="">-- Chon danh muc --</option>
                <c:forEach var="c" items="${categories}">
                    <option value="${c.categoryId}">${c.categoryname}</option>
                </c:forEach>
            </select>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Hinh anh</label>
            <input type="file" name="image" class="form-control" accept="image/*">
        </div>
        
        <button type="submit" class="btn btn-primary">Them</button>
        <a href="${pageContext.request.contextPath}/admin/product/list" class="btn btn-secondary">Huy</a>
    </form>
</div>