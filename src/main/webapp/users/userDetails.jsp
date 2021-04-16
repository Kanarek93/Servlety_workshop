<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<link href="<c:url value="/theme/vendor/datatables/dataTables.bootstrap4.min.css"/>" rel="stylesheet">
<link href="<c:url value="/theme/css/sb-admin-2.css"/>" rel="stylesheet">

<jsp:include page="header.jsp"/>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
        <a href="<c:url value="/user/list"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i> Lista użytkowników </a>
    </div>

</div>
<!-- /.container-fluid -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Szczegóły użytkownika</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty user}">
                        <tr>
                            <td>Id</td>
                            <td>${user.getId()}</td>
                        </tr>
                        <tr>
                            <td>Nazwa użytkownika</td>
                            <td> ${user.getUsername()}</td>
                        </tr>
                        <tr>
                            <td>Adres email</td>
                            <td> ${user.getEmail()}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        Nie ma takiego użytkownika, dodaj.
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>

</div>
<!-- End of Main Content -->
<jsp:include page="footer.jsp"/>
