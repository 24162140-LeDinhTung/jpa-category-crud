<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<sitemesh:write property="title">Quan ly san pham</sitemesh:write>

<div class="container mt-4">
    <h2>Quan ly san pham</h2>
    <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-primary mb-3">Them san pham</a>
    
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Hinh anh</th>
                <th>Ten san pham</th>
                <th>Gia</th>
                <th>Ton kho</th>
                <th>Danh muc</th>
                <th>Thao tac</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td>${p.id}</td>
                    <td>
                        <c:if test="${p.image != null}">
                            <img src="${pageContext.request.contextPath}/image?fname=${p.image}" height="50" width="50">
                        </c:if>
                        <c:if test="${p.image == null}">
                            <span class="text-muted">No image</span>
                        </c:if>
                    </td>
                    <td>${p.name}</td>
                    <td>${p.price} VND</td>
                    <td>${p.stock}</td>
                    <td>${p.category.categoryname}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/product/edit?id=${p.id}" class="btn btn-sm btn-warning">Sua</a>
                        <a href="${pageContext.request.contextPath}/admin/product/delete?id=${p.id}" 
                           class="btn btn-sm btn-danger" onclick="return confirm('Xoa?')">Xoa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>