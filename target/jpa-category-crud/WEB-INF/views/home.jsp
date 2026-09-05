<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Trang chu</sitemesh:write>

<div class="container mt-4">
    <h2 class="text-center mb-4">San pham iPhone moi nhat</h2>
    
    <c:if test="${sessionScope.account != null}">
        <div class="alert alert-info">
            Xin chao <strong>${sessionScope.account.fullName}</strong>!
        </div>
    </c:if>
    
    <div class="row">
        <c:forEach var="product" items="${latestProducts}">
            <div class="col-md-3 col-sm-6 mb-4">
                <div class="card h-100">
                    <c:choose>
                        <c:when test="${product.image != null && product.image != ''}">
                            <img src="${pageContext.request.contextPath}/image?fname=products/${product.image}" 
                                 class="card-img-top" alt="${product.name}" style="height: 200px; object-fit: cover;">
                        </c:when>
                        <c:otherwise>
                            <div class="card-img-top bg-secondary d-flex align-items-center justify-content-center" style="height: 200px;">
                                <span class="text-white">No Image</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text text-danger fw-bold">${product.price} VND</p>
                        <p class="card-text"><small class="text-muted">Con hang: ${product.stock}</small></p>
                        <a href="${pageContext.request.contextPath}/product/detail?id=${product.id}" class="btn btn-primary btn-sm">Xem chi tiet</a>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty latestProducts}">
            <div class="col-12 text-center">
                <p>Chua co san pham nao.</p>
            </div>
        </c:if>
    </div>
    
    <div class="text-center mt-3">
        <a href="${pageContext.request.contextPath}/product" class="btn btn-outline-primary">Xem tat ca san pham</a>
    </div>
</div>