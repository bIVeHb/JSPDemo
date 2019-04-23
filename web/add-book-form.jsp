<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>Add Book</title>

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
    <h3>Add Book</h3>

    <form action="BookControllerServlet" method="GET">

        <input type="hidden" name="command" value="ADD"/>

        <table>
            <tbody>
            <tr>
                <td><label>Name of the book:</label></td>
                <td><label>
                    <input type="text" name="name_book"/>
                </label></td>
            </tr>

            <tr>
                <td><label>Start reading:</label></td>
                <td><label>
                    <input type="text" name="start_reading"/>
                </label></td>
            </tr>

            <tr>
                <td><label>Author:</label></td>
                <td><label>
                    <input type="text" name="author_book"/>
                </label></td>
            </tr>

            <tr>
                <td><label>Ideas of the book:</label></td>
                <td><label>
                    <input type="text" name="ideas_book"/>
                </label></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
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
