package ru.biv.jspdemo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class BookDbUtils {

    private DataSource dataSource;
    private String nameBook;
    private String startReading;
    private String author;
    private String ideasBook;
    private String url = "jdbc:mysql://localhost:3306/dbtest" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";

    BookDbUtils(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    List<Book> getBooks() throws Exception {

        List<Book> Books = new ArrayList<>();

        Connection myConn = DriverManager.getConnection(url,
                "root", "mysql");
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from books order by id";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                nameBook = myRs.getString("name_book");
                startReading = myRs.getString("start_reading");
                author = myRs.getString("author_book");
                ideasBook = myRs.getString("ideas_book");

                // create new Book object
                Book tempBook = new Book(id, nameBook, startReading, author, ideasBook);

                // add it to the list of Books
                Books.add(tempBook);
            }
            return Books;
        } finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    void addBook(Book theBook) throws Exception {

        Connection myConn = DriverManager.getConnection(url,
                "root", "mysql");
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql = "insert into books "
                    + "(name_book, start_reading, author_book, ideas_book) "
                    + "values (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the Book
            setParamValues(theBook, myStmt);

            // execute sql insert
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    private void setParamValues(Book theBook, PreparedStatement myStmt) throws SQLException {
        myStmt.setString(1, theBook.getNameBook());
        myStmt.setString(2, theBook.getDate());
        myStmt.setString(3, theBook.getAuthor());
        myStmt.setString(4, theBook.getIdeasBook());
    }

    Book getBook(String theBookId) throws Exception {

        Book theBook = null;

        Connection myConn = DriverManager.getConnection(url,
                "root", "mysql");
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int bookId;

        try {
            // convert Book id to int
            bookId = Integer.parseInt(theBookId);

            // get connection to database
            myConn = dataSource.getConnection();

            // create sql to get selected Book
            String sql = "select * from books where id=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, bookId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row
            if (myRs.next()) {
                nameBook = myRs.getString("name_book");
                startReading = myRs.getString("start_reading");
                author = myRs.getString("author_book");
                ideasBook = myRs.getString("ideas_book");


                // use the BookId during construction
                theBook = new Book(bookId, nameBook, startReading, author, ideasBook);
            } else {
                throw new Exception("Could not find Book id: " + bookId);
            }

            return theBook;
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    void updateBook(Book theBook) throws Exception {

        Connection myConn = DriverManager.getConnection(url,
                "root", "mysql");
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create SQL update statement
            String sql = "update books "
                    + "set name_book=?, start_reading=?, author_book=?, ideas_book=? "
                    + "where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            setParamValues(theBook, myStmt);
            myStmt.setInt(5, theBook.getId());

            // execute SQL statement
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    void deleteBook(String theBookId) throws Exception {

        Connection myConn = DriverManager.getConnection(url,
                "root", "mysql");
        PreparedStatement myStmt = null;

        try {
            // convert Book id to int
            int bookId = Integer.parseInt(theBookId);

            // get connection to database
            myConn = dataSource.getConnection();

            // create sql to delete Book
            String sql = "delete from books where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, bookId);

            // execute sql statement
            myStmt.execute();
        } finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }
}
