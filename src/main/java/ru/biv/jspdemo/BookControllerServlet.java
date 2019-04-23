package ru.biv.jspdemo;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;

@WebServlet("/BookControllerServlet")
public class BookControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookDbUtils bookDbUtil;
    private String nameBook;
    private String startReading;
    private String author;
    private String ideasBook;

    @Resource(name="jdbc/dbtest")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our book db util ... and pass in the conn pool / datasource
        try {
            bookDbUtil = new BookDbUtils(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");

            // if the command is missing, then default to listing students
            if (theCommand == null) {
                theCommand = "LIST";
            }

            // route to the appropriate method
            switch (theCommand) {

                case "LIST":
                    listBooks(request, response);
                    break;

                case "ADD":
                    addBook(request, response);
                    break;

                case "LOAD":
                    loadBook(request, response);
                    break;

                case "UPDATE":
                    updateBook(request, response);
                    break;

                case "DELETE":
                    deleteBook(request, response);
                    break;

                default:
                    listBooks(request, response);
            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read book id from form data
        String bookId = request.getParameter("bookId");

        // delete book from database
        bookDbUtil.deleteBook(bookId);

        // send them back to "list books" page
        listBooks(request, response);
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read book info from form data
        int id = Integer.parseInt(request.getParameter("bookId"));
        nameBook = request.getParameter("name_book");
        startReading = request.getParameter("start_reading");
        author = request.getParameter("author_book");
        ideasBook = request.getParameter("ideas_book");

        // create a new book object
        Book book = new Book(id, nameBook, startReading, author, ideasBook);

        // perform update on database
        bookDbUtil.updateBook(book);

        // send them back to the "list books" page
        listBooks(request, response);

    }

    private void loadBook(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read book id from form data
        String bookId = request.getParameter("bookId");

        // get book from database (db util)
        Book book = bookDbUtil.getBook(bookId);

        // place book in the request attribute
        request.setAttribute("THE_BOOK", book);

        // send to jsp page: update-book-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-book-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read book info from form data
        nameBook = request.getParameter("name_book");
        startReading = request.getParameter("start_reading");
        author = request.getParameter("author_book");
        ideasBook = request.getParameter("ideas_book");

        // create a new book object
        Book book = new Book(nameBook, startReading, author, ideasBook);

        // add the book to the database
        bookDbUtil.addBook(book);

        // send back to main page (the book list)
        listBooks(request, response);
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get books from db util
        List<Book> books = bookDbUtil.getBooks();

        // add books to the request
        request.setAttribute("BOOK_LIST", books);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-books.jsp");
        dispatcher.forward(request, response);
    }
}
