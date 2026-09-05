<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Danh sach san pham</sitemesh:write>

<div class="container mt-4">
    <h2 class="text-center mb-4">Tat ca san pham iPhone</h2>
    
    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-md-4 col-sm-6 mb-4">
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
        <c:if test="${empty products}">
            <div class="col-12 text-center">
                <p>Khong co san pham nao.</p>
            </div>
        </c:if>
    </div>
    
    <!-- Phan trang -->
    <c:if test="${totalPages > 1}">
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 0}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/product?page=${currentPage - 1}">Previous</a>
                    </li>
                </c:if>
                
                <c:forEach var="i" begin="0" end="${totalPages - 1}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}/product?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>
                
                <c:if test="${currentPage < totalPages - 1}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/product?page=${currentPage + 1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>