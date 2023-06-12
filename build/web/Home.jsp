<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="./css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="./css/iconUser.css">
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
            <!--end of menu-->
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="home">Home</a></li> &nbsp &nbsp &nbsp
                                <a href="tel:0705143807" class="mr-4"><i class="fa-solid fa-phone"></i> 0705143807</a>
                                <a href="mailto:" ><i class="fa-regular fa-envelope"></i> vuquangtrung@gmail.com</a>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row"> 
                    <div class="col-sm-3">
                        <div class="card bg-light mb-3">
                            <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                            <ul class="list-group category_block">
                                <li class="list-group-item text-white ${tag == "0" ? "active" : ""}"><a href="category?cid=${0}">All</a></li>

                            <c:forEach items="${listC}" var="o">
                                <li class="list-group-item text-white ${tag == o.category_id ? "active" : ""}"><a href="category?cid=${o.category_id}">${o.name}</a></li>
                                </c:forEach>                              
                        </ul>
                    </div>
                    <hr><!-- comment -->
                    <form action="" method="get">
                        <h3>Filter</h3>
                        <c:forEach items="${listC}" var="o">
                            <label>
                                <input class="filtercategory" type="checkbox" name="filtercategory" value="${o.category_id}"> ${o.name}
                            </label><br>
                        </c:forEach>
                        <hr><!-- comment -->
                        <label>
                            <input class="filterprice" type="checkbox" name="filterprice" value="p.price BETWEEN 0 AND 500"> 0-500$
                        </label><br>
                        <label>
                            <input class="filterprice" type="checkbox" name="filterprice" value="p.price BETWEEN 500 AND 1000"> 500$-1000$
                        </label><br>
                        <label>
                            <input class="filterprice" type="checkbox" name="filterprice" value="p.price > 1000"> > 1000$
                        </label><br>


                    </form>
                    <button onclick="Filter()">Filter</button>
                    <hr>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Last product</div>
                        <div class="card-body">
                            <img class="img-fluid" src="${p.image}" />
                            <h5 class="card-title">${p.name}</h5>
                            <p class="bloc_left_price">${p.price} $</p>
                        </div>
                    </div>
                </div>

                <div class="col-sm-9">
                    <div class="row" id="content">
                        <c:forEach items="${listP}" var="o">
                            <div class="product col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${o.image}" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?product_id=${o.product_id}" title="View Product">${o.name}</a></h4>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${o.price} $</p>
                                            </div>
                                            <c:if test="${o.quantity <= 0}">
                                                <div class="col">
                                                    <p class="btn btn-danger btn-block">Sold out</p>
                                                </div>
                                                <div class="col">
                                                    <a href="#" class="btn btn-danger btn-block">Add to cart</a>
                                                </div>
                                            </c:if>
                                            <c:if test="${o.quantity > 0}">
                                                <div class="col">
                                                    <p class="btn btn-dark btn-block">Stocking</p>
                                                </div>
                                                <div class="col">
                                                    <a href="cartcontrol?id=${o.product_id}&num=1" class="btn btn-success btn-block">Add to cart</a>
                                                </div>
                                            </c:if>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <c:if test="${tag == null && txtS == null && filter == null}">
                        <button onclick="loadMore()" class="btn btn-primary m-lg-3">Load more</button>
                    </c:if>

                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script>
                            loadMore = async() => {
                                var amount = document.getElementsByClassName("product").length;
                                await $.ajax({
                                    url: "/websell/loadmore",
                                    type: "get", //send it through get method
                                    data: {
                                        exits: amount
                                    },
                                    // lay dc data tu servlet
                                    success: function (data) {
                                        var row = document.getElementById("content");
                                        row.innerHTML += data;
                                    },
                                    error: function (xhr) {
                                        //Do Something to handle error
                                    }
                                });
                            }
                            function searchByName(param) {
                                var txtSearch = param.value;
                                $.ajax({
                                    url: "/websell/searchbyajax",
                                    type: "get", //send it through get method
                                    data: {
                                        txt: txtSearch
                                    },
                                    success: function (data) {
                                        var row = document.getElementById("content");
                                        row.innerHTML = data;
                                    },
                                    error: function (xhr) {
                                        //Do Something to handle error
                                    }
                                });
                            }
        </script>
        <script>
            function Filter() {
                var category = document.querySelectorAll(".filtercategory");
                var price = document.querySelectorAll(".filterprice");
                var valueCategory = [];
                var valuePrice = [];
                if (category.length > 0) {
                    for (var item of category) {
                        if (item.checked) {
                            valueCategory.push(item.value);
                        }
                    }
                }
                if (price.length > 0) {
                    for (var item of price) {
                        if (item.checked) {
                            valuePrice.push(item.value);
                        }
                    }
                }
//                alert(valueCategory);
                var queryPrice = "";
                if (valuePrice.length > 0) {
                    if (valuePrice.length === 1) {
                        queryPrice = valuePrice[0];
                    }
                    if (valuePrice.length === 2) {
                        queryPrice = valuePrice[0] + " or " + valuePrice[1];
                    }
                    if (valuePrice.length === 3) {
                        queryPrice = "";
                    }
                }
                var queryCategory = "";
                if (valueCategory.length > 0) {
                    queryCategory = "c.category_id=" + valueCategory[0];
                    for (var i = 1; i < valueCategory.length; i++) {
                        queryCategory = queryCategory + " or " + "c.category_id=" + valueCategory[i];
                    }
                }

                $.ajax({
                    url: "/websell/filter",
                    type: "get", //send it through get method
                    data: {
                        filtercategory: queryCategory,
                        filterprice: queryPrice
                    },
                    // lay dc data tu servlet
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
            function searchByName(param) {
                var txtSearch = param.value;
                $.ajax({
                    url: "/websell/searchbyajax",
                    type: "get", //send it through get method
                    data: {
                        txt: txtSearch
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
        </script>  
    </body>
</html>

