<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Chi tiet san pham</sitemesh:write>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-6">
            <c:choose>
                <c:when test="${product.image != null && product.image != ''}">
                    <img src="${pageContext.request.contextPath}/image?fname=products/${product.image}" 
                         class="img-fluid rounded" alt="${product.name}">
                </c:when>
                <c:otherwise>
                    <div class="bg-secondary d-flex align-items-center justify-content-center rounded" style="height: 400px;">
                        <span class="text-white">No Image</span>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-6">
            <h2>${product.name}</h2>
            <p class="text-danger h3">${product.price} VND</p>
            <p><strong>Danh muc:</strong> ${product.category.categoryname}</p>
            <p><strong>Ton kho:</strong> ${product.stock}</p>
            <p><strong>Mo ta:</strong></p>
            <p>${product.description}</p>
            <p><small class="text-muted">Ngay tao: ${product.createdDate}</small></p>
            <a href="${pageContext.request.contextPath}/product" class="btn btn-secondary">Quay lai</a>
            
            <c:if test="${sessionScope.account != null && sessionScope.account.roleId == 1}">
                <a href="${pageContext.request.contextPath}/admin/product/edit?id=${product.id}" class="btn btn-warning">Sua</a>
                <a href="${pageContext.request.contextPath}/admin/product/delete?id=${product.id}" 
                   class="btn btn-danger" onclick="return confirm('Ban co chac muon xoa?')">Xoa</a>
            </c:if>
        </div>
    </div>
</div>