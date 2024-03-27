import java.util.Scanner;
import java.util.ArrayList;

class tugas {
    static ArrayList<Book> bookList = new ArrayList<>();
    static ArrayList<Student> userStudent = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    inputNim(scanner);
                    break;
                case "2":
                    Admin admin = new Admin();
                    admin.menuAdmin(scanner);
                    break;
                case "3":
                    System.out.println("Thank you. Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void inputNim(Scanner scanner) {
        System.out.print("Enter your NIM (input 99 for back): ");
        String nim = scanner.nextLine();
        if (nim.equals("99")) {
            return;
        } else if (checkNim(nim)) {
            Student student = new Student(nim);
            student.menuStudent(scanner); // Panggil menuStudent dari objek student yang sudah dibuat
        } else {
            System.out.println("Invalid NIM. Please try again.");
            inputNim(scanner);
        }
    }

    public static boolean checkNim(String nim) {
        for (Student student : userStudent) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }

    public static void initializeData() {
        // Inisialisasi data buku
        bookList.add(new Book("388c-e681-9152", "title1", "author1", "Sejarah", 8));
        bookList.add(new Book("ed90-be30-5cdb", "title2", "author2", "Sejarah", 11));
        bookList.add(new Book("d95e-0c4a-9523", "title3", "author3", "Sejarah", 3));

        // Inisialisasi data user
        userStudent.add(new Student("Taufiq Ramadhan", "202210370311288", "Teknik", "Informatika"));
        userStudent.add(new Student("Who", "202310370311135", "Teknik", "Informatika"));
        userStudent.add(new Student("Sutrisno Adit Pratama", "202210370311203", "Teknik", "Informatika"));
    }

}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int loanDuration;
    private int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getStock() { return stock; }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getLoanDuration() { return loanDuration; }
    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration; }
}

class User {
    protected static ArrayList<Book> bookList = new ArrayList<>();

    public void displayBooks() {
        System.out.println("List of Books:");
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Author || Category || Stock ||");
        int index = 1;
        for (Book book : bookList) {
            System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }
}
class Student extends User{
    private String name;
    private String nim;
    private String faculty;
    private String program;
    private ArrayList<Book> borrowedBooks;
    private int loanDuration;



    // Konstruktor dengan empat argumen
    public Student(String name, String nim, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
        this.borrowedBooks = new ArrayList<>();
        // this.loanDuration = loanDuration;
    }

    // Konstruktor dengan satu argumen
    public Student(String nim) {
        // Temukan detail mahasiswa dari Main.userStudent berdasarkan nim yang diberikan
        for (Student student : tugas.userStudent) {
            if (student.getNim().equals(nim)) {
                this.name = student.getName();
                this.nim = student.getNim();
                this.faculty = student.getFaculty();
                this.program = student.getProgram();
                this.borrowedBooks = student.getBorrowedBooks();
                break;
            }
        }
    }

    // Metode untuk menambah buku yang dipinjam

    // Metode lainnya
    public String getNim() { return nim; }

    public void menuStudent(Scanner scanner) {
        while (true) {
            System.out.println("=== Student Menu ===");
            System.out.println("1. Buku terpinjam");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Pinjam Buku atau Logout");
            System.out.print("Choose option (1-3): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Buku terpinjam:");
                    displayBorrowedBooks();
                    // Implementasi logika untuk menampilkan buku yang sedang dipinjam
                    break;
                case "2":
                    displayBooks();
                    borrowBook(scanner);
                    // Implementasi logika untuk pinjam buku
                    break;
                case "3":
                    System.out.println("System logout...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    // Metode untuk mengembalikan buku yang dipinjam
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books currently borrowed.");
        } else {
            System.out.println("=================================================================================");
            System.out.println("|| No. || Id Buku        || Nama Buku    || Author       || Category   || Durasi ||");
            System.out.println("=================================================================================");
            int index = 1;
            for (Book book : borrowedBooks) {
                System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + " || " + book.getLoanDuration() + " ||" );
                index++;
            }
            System.out.println("=================================================================================");
        }
    }

    public void displayBooks() {
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Author || Category || Stock ||");
        int index = 1;
        for (Book book : tugas.bookList) {
            System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }
    public void borrowBook(Scanner scanner) {
        System.out.print("Enter the number of the book you want to borrow: ");
        int bookIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (bookIndex < 0 || bookIndex >= tugas.bookList.size()) {
            System.out.println("Invalid book selection.");
            return;
        }

        Book selectedBook = tugas.bookList.get(bookIndex);
        System.out.print("Enter the duration of loan (in days): ");
        int loanDuration = Integer.parseInt(scanner.nextLine());

        if (selectedBook.getStock() > 0) {
            // Decrease the stock of the selected book
            selectedBook.setStock(selectedBook.getStock() - 1);
            selectedBook.setLoanDuration(loanDuration); // Set loan duration for the book
            borrowBook(selectedBook);
            System.out.println("Book '" + selectedBook.getTitle() + "' borrowed successfully for " + loanDuration + " days.");
        } else {
            System.out.println("Sorry, the selected book is out of stock.");
        }
    }

    // Di kelas Book, tambahkan setter untuk loanDuration
    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    // Di kelas Book, tambahkan getter untuk loanDuration
    public int getLoanDuration() {
        return loanDuration;
    }
    public String getName() { return name; }
    public String getFaculty() { return faculty; }
    public String getProgram() { return program; }
    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }
}
class Admin extends User{
    // Tambahkan informasi username dan password admin
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    public void menuAdmin(Scanner scanner) {
        if (loginAdmin(scanner)) {
            while (true) {
                System.out.println("=== Admin Menu ===");
                System.out.println("1. Tambah Mahasiswa");
                System.out.println("2. Lihat Daftar Mahasiswa");
                System.out.println("3. Tambah Buku");
                System.out.println("4. Lihat Daftar Buku");
                System.out.println("5. Keluar");
                System.out.print("Choose option (1-5): ");
                String option = scanner.nextLine();
                switch (option) {
                    case "1":
                        addStudent(scanner);
                        break;
                    case "2":
                        displayStudent();
                        break;
                    case "3":
                        addBook(scanner);
                        break;
                    case "4":
                        displayBookList();
                        break;
                    case "5":
                        System.out.println("Logging out from admin account.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }else {
            System.out.println("Admin login failed. Exiting.");
        }
    }
    // Metode untuk melakukan autentikasi admin
    private boolean loginAdmin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        // Periksa apakah username dan password sesuai dengan yang diharapkan
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    public void addStudent(Scanner scanner) {
        System.out.println("Enter student details:");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        String nim;
        do {
            System.out.print("Enter student NIM: ");
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("NIM must be 15 digits.");
            }
        } while (nim.length() != 15);
        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter student program: ");
        String program = scanner.nextLine();
        tugas.userStudent.add(new Student(name, nim, faculty, program));
        System.out.println("Student successfully registered.");
    }

    public void displayStudent() {
        System.out.println("List of Registered Students:");
        for (Student student : tugas.userStudent) {
            System.out.println("Nama: " + student.getName());
            System.out.println("Fakultas: " + student.getFaculty());
            System.out.println("NIM: " + student.getNim());
            System.out.println("Prodi: " + student.getProgram());
            System.out.println();
        }
    }
    public void addBook(Scanner scanner) {
        System.out.println("Choose book category:");
        System.out.println("1. Story Book");
        System.out.println("2. History Book");
        System.out.println("3. Text Book");
        System.out.println("4. Keluar");
        System.out.print("Enter choice (1-4): ");
        String categoryChoice = scanner.nextLine();

        String category;
        switch (categoryChoice) {
            case "1":
                category = "Story Book";
                break;
            case "2":
                category = "History Book";
                break;
            case "3":
                category = "Text Book";
                break;
            case "4":
                System.out.println("System logout...");
                return;
            default:
                System.out.println("Invalid choice. Defaulting to Story Book.");
                category = "Story Book";
                break;
        }
        System.out.println("Enter book details:");
        System.out.print("Enter book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        tugas.bookList.add(new Book(id, title, author, category, stock));
        System.out.println("Book successfully added to the library.");
    }
    public void displayBookList() {
        System.out.println("List of Books:");
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Author || Category || Stock ||");
        int index = 1;
        for (Book book : tugas.bookList) {
            System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }
}