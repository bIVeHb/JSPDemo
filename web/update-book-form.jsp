<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Book</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-book-style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>Library</h2>
    </div>
</div>

<div id="container">
    <h3>Update Book</h3>

    <form action="BookControllerServlet" method="GET">

        <input type="hidden" name="command" value="UPDATE" />

        <input type="hidden" name="bookId" value="${THE_BOOK.id}" />

        <table>
            <tbody>
            <tr>
                <td><label>Name of the book:</label></td>
                <td><input type="text" name="name_book"
                           value="${THE_BOOK.nameBook}" /></td>
            </tr>

            <tr>
                <td><label>Start reading:</label></td>
                <td><input type="text" name="start_reading"
                           value="${THE_BOOK.date}" /></td>
            </tr>

            <tr>
                <td><label>Author:</label></td>
                <td><input type="text" name="author_book"
                           value="${THE_BOOK.author}" /></td>
            </tr>

            <tr>
                <td><label>Ideas of the book:</label></td>
                <td><input type="text" name="ideas_book"
                           value="${THE_BOOK.ideasBook}" /></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="BookControllerServlet">Back to List</a>
    </p>
</div>
</body>

</html>











