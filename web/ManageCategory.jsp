<%-- 
    Document   : ManageAccount
    Created on : Mar 29, 2023, 10:52:39 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Category</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="./css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("are U sure to delete category")) {
                    window.location = "deletecategory?cid=" + id;
                }
            }
        </script>
        
    </head>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Category</b></h2>
                        </div>
                        
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="fa-solid fa-plus"></i><span>Add New Category</span></a>					
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>category_id</th>
                            <th>name</th>
                            <th>describe</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listC}" var="o">
                        <tr>
                            <td>${o.category_id}</td>
                            <td>${o.name}</td>
                            <td>${o.describe}</td>
                            
                            <td>
                                <a href="loadcategory?cid=${o.category_id}"><i class="fa-regular fa-pen-to-square" title="Edit"></i></a>
                                <a href="#" onclick="doDelete(${o.category_id})"><i class="fa-solid fa-trash" title="Delete"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
<!--            <a href="home"><button type="button" class="btn btn-primary">Back to home</button>-->
                <!-- Edit Modal HTML -->
                <div id="addEmployeeModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="addcategory" method="post">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Add Category</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">

                                    <div class="form-group">
                                        <label>category_name</label>
                                        <input name="name" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>describe</label>
                                        <input name="describe" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="submit" class="btn btn-success" value="Add">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
        </div>
    </body>
</html>
