<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actFav" value="${ForwardConst.ACT_FAV.getValue()}" />
<c:set var="commFav" value="${ForwardConst.CMD_FAV.getValue()}" />

<c:set var="map" value="${requestScope.favCntMap}"/>
<c:set var="repId" value="${Integer.parseInt(param.repId)}" />
<c:set var="empId" value="${Integer.parseInt(param.empId)}" />

    <c:set var="favCnt" value="${map[repId]}" />
    <c:choose>
        <c:when test="${empId == sessionScope.login_employee.id}">
            <i class="far fa-thumbs-up">いいね(${favCnt})</i>
        </c:when>
        <c:otherwise>
            <a href="<c:url value='?action=${actFav}&command=${commFav}&id=${repId}' />">
                <i class="far fa-thumbs-up">いいね(${favCnt})</i>
           </a>
        </c:otherwise>
    </c:choose>