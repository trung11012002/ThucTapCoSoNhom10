<%-- 
    Document   : Detail
    Created on : Dec 29, 2020, 5:43:04 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
        <link href="./css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="./css/iconUser.css">
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
            .col-sm-9{
                margin : auto;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>

            <div class="col-sm-9">
                <div class="container">
                    <div class="card">
                        <div class="row">
                            <aside class="col-sm-5 border-right">
                                <article class="gallery-wrap"> 
                                    <div class="img-big-wrap">
                                        <div> <a href="#"><img src="${detail.image}"></a></div>
                                </div> <!-- slider-product.// -->
                                <div class="img-small-wrap">
                                </div> <!-- slider-nav.// -->
                            </article> <!-- gallery-wrap .end// -->
                        </aside>
                        <aside class="col-sm-7">
                            <article class="card-body p-5">
                                <h3 class="title mb-3">${detail.name}</h3>

                                <p class="price-detail-wrap"> 
                                    <span class="price h3 text-warning"> 
                                        <span class="currency">$</span><span class="num">${detail.price}</span>
                                    </span> 
                                    <!--<span>/per kg</span>--> 
                                </p> <!-- price-detail-wrap .// -->
                                <dl class="item-property">
                                    <dt>Description</dt>
                                    <dd><p>${detail.describe} </p></dd>
                                </dl>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-5">
                                        <dl class="param param-inline">
                                            <dt>Quantity in stock: ${detail.quantity}</dt>
                                        </dl>  <!-- item-property .// -->
                                        <dl class="param param-inline">
                                            <c:if test="${detail.quantity <= 0}">                                              
                                                <dt>Sold out</dt>
                                                <br> 
                                                <a href="#" class="btn btn-danger btn-block">Add to cart</a>
                                            </c:if>
                                            <c:if test="${detail.quantity > 0}">
                                                <dt>Stocking</dt>
                                                <br> 
                                                <a href="cartcontrol?id=${detail.product_id}&num=1" class="btn btn-success btn-block">Add to cart</a>
                                            </c:if>
                                        </dl>  <!-- item-property .// -->
                                    </div> <!-- col.// -->

                                </div> <!-- row.// -->
                                <hr>
                                
                                
                            </article> <!-- card-body.// -->
                        </aside> <!-- col.// -->
                    </div> <!-- row.// -->
                </div> <!-- card.// -->


            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
