<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Catalog</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
   <link rel="stylesheet" type="text/css" href="./css/orders.css">

 
    <script src="./js/orders.js"></script>
</head>
<body>

    <!-- Add this code snippet inside the <body> tag of your JSP page -->
    
<div id="trackOrderModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h3>Order Tracking</h3>
        <div class="shipment-status-line">
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
        </div>
        <div class="shipment-status-text">
            <div class="status">Order Placed</div>
            <div class="status">Order Processed</div>
            <div class="status">dispatched</div>
            <div class="status">Out for Delivery</div>
            <div class="status">Delivered</div>
        </div>
    </div>
</div>
    
    <div id="cancelOrderModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h3>Your order has been cancelled</h3>
        <p>Insert any additional message or details here.</p>
    </div>
</div>
    <div class="container">
        <h2>Product Catalog</h2>
        <c:forEach var="product" items="${orderProducts}">
            <a href="productDetails?id=${product.getId()}">
            <div class="product-card">
            
                <img class="card-img-top" src="${product.getImageUrl()}" alt="${product.getName()}">
                <div class="product-details">
                    <div class="product-title">${product.getName()}</div>
                    <div class="product-description">${product.getDescription()}</div>
                    <div class="product-price">${product.getPrice()}</div></a>
                    <c:if test="${product.getShipStat() != 'delivered' && product.getShipStat() != 'out for delivery' && product.getShipStat() != 'cancelled'}">
                       <button class="btn btn-danger cancel-order-btn" data-orderproid="${product.getId()}">Cancel Order</button>

                    </c:if>
                      <c:if test="${product.getShipStat() == 'cancelled'}">
  <button class="btn btn-secondary" disabled>Cancelled</button>
</c:if>
                    <c:if test="${product.getShipStat() != 'delivered' && product.getShipStat()!='cancelled'}">
                        <button class="btn btn-primary track-order-btn" data-orderproid="${product.getId()}">Track Order</button>
                    </c:if>
                </div>
                
            </div>
        </c:forEach>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
   
</body>
</html>
