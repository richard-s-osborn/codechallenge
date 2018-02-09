
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${pageContext.request.locale}"/>
<fmt:setLocale value="${language}"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
  <meta charset="utf-8">
  <title>Contacts</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Le styles -->
  <link href="assets/css/bootstrap.css" rel="stylesheet">
  <link href="assets/css/contacts.css" rel="stylesheet">
  <style>
    body {
      padding-top: 60px;
      /* 60px to make the container go all the way to the bottom of the topbar */
    }
  </style>
  <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">

</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="btn btn-navbar" data-toggle="collapse"
         data-target=".nav-collapse"> <span class="icon-bar"></span> <span
          class="icon-bar"></span> <span class="icon-bar"></span>
      </a> <a class="brand" href="#">Contacts</a>

      <form class="navbar-form pull-right">
        <select name="field">
          <option value="title">Title</option>
          <option value="name">Name</option>
          <option value="address">Address</option>
        </select> <input type="text" name="key" size="20">
        <button type="submit" class="btn">Search</button>
      </form>

      <!--/.nav-collapse -->
    </div>
  </div>
</div>

<div class="container">

  <h1>Contacts</h1>

  <form class="coding-challenge-input-form form-inline" action="codingchallenge"
        method="post">
    <p>Add Contact</p>
    <input type="text" name="title" placeholder="Title" size="29"/>
    <input type="text" name="name" placeholder="Name" size="17"/>
    <input type="text" name="address" placeholder="Address" size="14"/>
    <input type="text" name="unit" placeholder="Unit" size="7"/>
    <input type="text" name="zip" placeholder="ZIp" size="4" style="width: 110px;"/> <input type="submit" name="action" class="btn btn-primary" value="Add"/>
  </form>

  <table class="table table-striped table-bordered">
    <thead>
    <tr>
      <th>Title</th>
      <th>Name</th>
      <th>Address</th>
      <th>Unit</th>
      <th>Zip</th>
      <th>&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${contacts}" var="contact">
      <tr>
        <td><c:out value="${contact.title}"/></td>
        <td><c:out value="${contact.name}"/></td>
        <td><c:out value="${contact.address}"/></td>
        <td><c:out value="${contact.unit}"/></td>
        <td><c:out value="${contact.zip}"/></td>
        <td><a href="?action=Remove&id=${contact.id}"><i
            class="icon-trash"></i></a></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <c:if test="${count > 0}">
    <c:if test="${page > 1}">
      <a href="<c:url value="codingchallenge"><c:param name="page" value="${page - 1}"/><c:param name="field" value="${field}"/>
      <c:param name="key" value="${key}"/></c:url>">&lt; Prev</a>&nbsp;
    </c:if>
    Showing records ${start} to ${end} of ${count}
    <c:if test="${page < pageCount}">
      &nbsp;<a href="<c:url value="codingchallenge"><c:param name="page" value="${page + 1}"/>
      <c:param name="field" value="${field}"/>
      <c:param name="key" value="${key}"/></c:url>">Next &gt;</a>
    </c:if>
  </c:if>
</div>
<!-- /container -->
</body>
</html>
