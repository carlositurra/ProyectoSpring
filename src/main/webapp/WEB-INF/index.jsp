<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Carlos Iturra González</title>
        </head>

        <body>
            <h1>Hola
                <c:out value="${nombres}"></c:out>
                <c:out value="${apellidos}"></c:out>
            </h1>
            <p>
                <c:out value="${edad}"></c:out>
            </p>
            <br>
            <p>
                <c:out value="${usuario.nombre} ${usuario.apellido} ${usuario.edad}"></c:out>
            </p>
        </body>

        </html>