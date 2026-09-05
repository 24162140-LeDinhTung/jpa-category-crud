<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Sua san pham</sitemesh:write>

<div class="container mt-4">
    <h2>Sua san pham</h2>
    <form action="${pageContext.request.contextPath}/admin/product/update" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${product.id}">
        
        <div class="mb-3">
            <label class="form-label">Ten san pham</label>
            <input type="text" name="name" class="form-control" value="${product.name}" required>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Mo ta</label>
            <textarea name="description" class="form-control" rows="3">${product.description}</textarea>
        </div>
        
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">Gia (VND)</label>
                    <input type="number" name="price" class="form-control" value="${product.price}" required step="0.01">
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">Ton kho</label>
                    <input type="number" name="stock" class="form-control" value="${product.stock}" required>
                </div>
            </div>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Danh muc</label>
            <select name="categoryId" class="form-select" required>
                <c:forEach var="c" items="${categories}">
                    <option value="${c.categoryId}" ${c.categoryId == product.category.id ? 'selected' : ''}>${c.categoryname}</option>
                </c:forEach>
            </select>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Hinh anh hien tai</label><br>
            <c:if test="${product.image != null}">
                <img src="${pageContext.request.contextPath}/image?fname=${product.image}" height="100" width="100">
            </c:if>
            <c:if test="${product.image == null}">
                <span class="text-muted">Khong co hinh</span>
            </c:if>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Chon hinh anh moi (de trong neu khong muon thay doi)</label>
            <input type="file" name="image" class="form-control" accept="image/*">
        </div>
        
        <button type="submit" class="btn btn-primary">Cap nhat</button>
        <a href="${pageContext.request.contextPath}/admin/product/list" class="btn btn-secondary">Huy</a>
    </form>
</div>