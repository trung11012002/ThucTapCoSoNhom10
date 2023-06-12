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
        <title>Manage Account</title>
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
                if (confirm("are U sure to delete account")) {
                    window.location = "deleteaccount?uid=" + id;
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
                            <h2>Manage <b>Account</b></h2>
                        </div>
                        
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="fa-solid fa-plus"></i><span>Add New Account</span></a>					
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <caption>
                        <c:if test="${requestScope.ms != null}">
                            <div class="alert alert-danger col-sm-6" role="alert">
                                Account already exists
                            </div>
                        </c:if>
                    </caption>
                    <thead>
                        <tr>
                            <th>User_id</th>
                            <th>User_name</th>
                            <th>User_email</th>
                            <th>User_password</th>
                            <th>Address</th>
                            <th>isAdmin</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listU}" var="o">
                        <tr>
                            <td>${o.user_id}</td>
                            <td>${o.user_name}</td>
                            <td>${o.user_email}</td>
                            <td>${o.user_password}</td>
                            <td>${o.address}</td>
                            <td>${o.isAdmin}</td>
                            <td>
                                <a href="loaduser?uid=${o.user_id}"><i class="fa-regular fa-pen-to-square" title="Edit"></i></a>
                                <a href="#" onclick="doDelete(${o.user_id})"><i class="fa-solid fa-trash" title="Delete"></i></a>
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
                            <form action="addaccount" method="get">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Add Account</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">

                                    <div class="form-group">
                                        <label>user_name</label>
                                        <input name="user_name" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>user_email</label>
                                        <input name="user_email" type="email" class="form-control" required>
                                    </div>

                                    <div class="form-group">
                                        <label>user_password</label>
                                        <input name="user_password" type="password" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>address</label>
                                        <input name="address" type="text" class="form-control" required>
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
