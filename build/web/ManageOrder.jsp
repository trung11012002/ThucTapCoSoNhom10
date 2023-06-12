<%-- 
    Document   : BuyOrder
    Created on : Apr 24, 2023, 4:54:57 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Buy Order</title>        
        <link href="./css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="./css/iconUser.css">
        <style>
            .order {
                border: 1px solid #ccc;
                padding: 10px;
                margin: 10px;
                width: 60%;
                margin: auto;
            }

            .order-header {
                display: flex;
                justify-content: space-between;
            }

            .order-date,
            .order-total {
                font-weight: bold;
            }

            .order-detail-content {
                display: none;
            }

            .order-detail.active .order-detail-content {
                display: block;
            }

            .order-detail-button {
                background-color: lightgray;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
                margin-top: 10px;
            }

            .order-detail-button:focus {
                outline: none;
            }
            .status button{
                cursor: pointer;
                border-radius: 10px;
                transition: all 0.5s;
            }
            .status button:hover {
                color: white;
                background-color: black;
                border-color: yellow;
            }
            .order{
                background-color: #E9ECEF;
                box-shadow: 0px 0px 10px #E9ECEF;
            }
        </style>
    </head>

    <body>
        <jsp:include page="Header.jsp"></jsp:include>
        <c:forEach items="${listO}" var="o">
            <div class="order">      
                <div class="order-header">
                    <div class="order-date">Order Date: ${o.order_date}</div>
                    <div class="order-date">UserID Buy: ${o.user_id}</div>
                    <div class="order-total">Total Price: ${o.total_price}$</div>
                </div>
                <div class="order-body">
                    <div class="order-address">Address: ${o.address}</div>
                    <div class="order-phone">Phone Number: ${o.phone_number}</div>
                    <c:if test="${o.status eq 'Pending Approval'}">
                        <div style="color: red; font-weight: bold;" class="order-status">Status: ${o.status}</div>
                    </c:if>
                    <c:if test="${o.status != 'Pending Approval'}">
                        <div style="color: green; font-weight: bold;"  class="order-status">Status: ${o.status}</div>
                    </c:if>
                    <hr>
                    <div class="status">
                        <a href="pendingapproval?order_id=${o.order_id}"><button>Pending Approval</button></a>
                        <a href="approved?order_id=${o.order_id}"><button>Approved</button></a>
                    </div>
                    <hr>
                    <div class="order-detail">
                        <button class="order-detail-button" onclick="toggleOrderDetail(this)"><i class="fa-solid fa-bars"></i> Order Detail</button>
                        <div class="order-detail-content">
                            <h3>Order Detail</h3>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Product</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Image</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Price</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Quantity</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Into money</div>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listOD}" var="oo">
                                            <c:if test="${o.order_id==oo.order_id}">
                                                <tr>
                                                    <th scope="row" class="align-middle">
                                                        <div class="p-2">                                                          
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${oo.product.name}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td>
                                                        <img src="${oo.product.image}">
                                                    </td>

                                                    <td class="align-middle text-center"><strong>${oo.price}</strong></td>

                                                    <td class="align-middle text-center"><strong>${oo.quantity}</strong></td>

                                                    <td class="align-middle">                                                    
                                                        <fmt:formatNumber pattern="##.#" value="${oo.price*oo.quantity}"/>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>


                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <script>
            function toggleOrderDetail(button) {
                button.parentElement.classList.toggle("active");
            }

        </script>
    </body>

</html>