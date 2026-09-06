<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Trang chu</sitemesh:write>

<div class="row">
    <div class="col-12">
        <h2 class="mb-4 text-center">San pham iPhone moi nhat</h2>
    </div>
</div>

<div class="row">
    <c:forEach var="product" items="${latestProducts}">
        <div class="col-md-3 col-sm-6 mb-4">
            <div class="product-card">
                <c:choose>
                    <c:when test="${product.image != null}">
                        <img src="${pageContext.request.contextPath}/image?fname=${product.image}" 
                             class="product-image" alt="${product.name}">
                    </c:when>
                    <c:otherwise>
                        <div class="product-image bg-secondary d-flex align-items-center justify-content-center">
                            <span class="text-white">No Image</span>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="product-body">
                    <h5 class="product-title">${product.name}</h5>
                    <p class="product-price">${product.price} VND</p>
                    <p class="small text-muted">Con hang: ${product.stock}</p>
                    <a href="${pageContext.request.contextPath}/product/detail?id=${product.id}" 
                       class="btn btn-primary btn-sm w-100">Xem chi tiet</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<div class="text-center mt-3">
    <a href="${pageContext.request.contextPath}/product" class="btn btn-outline-primary btn-lg">
        Xem tat ca san pham
    </a>
</div>