<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>Book Tracker App</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Library</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <!-- put new button: Add Book -->

        <input type="button" value="Add Book"
               onclick="window.location.href='add-book-form.jsp'; return false;"
               class="add-student-button"
        />

        <table>

            <tr>
                <th>Name of the book</th>
                <th>Start reading</th>
                <th>Author</th>
                <th>Ideas of the book</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempBook" items="${BOOK_LIST}">

                <!-- set up a link for each book -->
                <c:url var="tempLink" value="BookControllerServlet">
                    <c:param name="command" value="LOAD" />
                    <c:param name="bookId" value="${tempBook.id}" />
                </c:url>

                <!--  set up a link to delete a book -->
                <c:url var="deleteLink" value="BookControllerServlet">
                    <c:param name="command" value="DELETE" />
                    <c:param name="bookId" value="${tempBook.id}" />
                </c:url>

                <tr>
                    <td> ${tempBook.nameBook} </td>
                    <td> ${tempBook.date} </td>
                    <td> ${tempBook.author} </td>
                    <td> ${tempBook.ideasBook} </td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>
</body>


</html>
