

import java.util.Scanner;

public class LibraryManagementSystem {
    private final String[] BookName = new String[199];
     private final byte[] BookQuantity = new byte[199];

    private short bookNum = 0;
    private final String[][] StudentIndexNum_RegistrationDetails = new String[999][6];
    private final String[][] StudentIndexNum_IssuedBooks = new String[999][7];
    private final short[] StudentIndexNum_NoOfIssuedBooks = new short[999];

    static short StudentIndexNum ;
    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        LibraryManagementSystem obj = new LibraryManagementSystem();
        obj.Initialization();
        obj.LoginPage();
    }

    private void LoginPage(){
        System.out.print("Enter your ID : ");
        String tempID = in.nextLine();
        System.out.print("Enter Password : ");
        String tempPassword = in.nextLine();
        String password = "Password501";
        String ID = "ID501";
        if (tempID.equals(ID) && tempPassword.equals(password)){
            System.out.println("Welcome User\n");
            HomePage();
        }
        else System.out.println("Invalid Credentials!!!");
    }
    private void Initialization(){
        for (short i = 0 ; i < 999 ; i++){
            for (byte j = 0 ; j < 7 ; j++){
                StudentIndexNum_IssuedBooks[i][j] = "Not Issued Yet.";
            }
            StudentIndexNum_NoOfIssuedBooks[i] = 0;
        }
    }

    private void HomePage(){
        System.out.println("Enter : \n\t1 to Add Books");
        System.out.println("\t2 to View Available Books");
        System.out.println("\t3 to Search a Book");
        System.out.println("\t4 to Issue a Book for Student");
        System.out.println("\t5 to Register a Student");
        System.out.print("Your choice : ");
        byte choice = in.nextByte();
        in.nextLine();
        switch (choice){
            case 1 -> {
                System.out.println();
                AddBook();
                HomePage();
            }
            case 2 ->{
                System.out.println();
                AvailableBooks();
                HomePage();
            }
            case 3 ->{
                System.out.println();
                SearchBooks();
                HomePage();
            }
            case 4 ->{
                System.out.println();
                IssueBook();
                HomePage();
            }
            case 5 ->{
                System.out.println();
                RegisterStudent();
                HomePage();
            }
            case 127 ->{
                AdvanceMenu();
                System.out.println("\n");
                HomePage();
            }
            default -> {
                System.out.println("Invalid Choice!!!");
                System.out.println("\n");// 2 line break
                HomePage();
            }
        }

    }

    private void AddBook(){
        System.out.print("Enter Book name to add : ");
        BookName[bookNum] = in.nextLine();
        System.out.print("Enter available Quantity of "+BookName[bookNum]+" : ");
        BookQuantity[bookNum] = in.nextByte();
        in.nextLine();
        if(BookQuantity[bookNum]==0){
            System.out.println("As entered Quantity is 0 , the book will automatically discarded in future ");
        } else if (BookQuantity[bookNum]!=0) {
            System.out.println("Book named '"+BookName[bookNum]+"' added Successfully with "+BookQuantity[bookNum]+" available books.");
            bookNum++;

        }
        System.out.println("\n");
    }
    private void AvailableBooks(){
        for(short i = 0 ; i<bookNum ; i++){
            System.out.println("=> "+i+" '"+BookName[i]+"' having available Quantity "+BookQuantity[i]);
        }
        if(bookNum==0){
            System.out.println("No Books available to display");
            System.out.println("Please add some Books.");
            System.out.print("Do you want to add books (Y/N) : ");
            boolean input = in.next().equalsIgnoreCase("Y");
            if(input){
                in.nextLine();
                AddBook();
            }
        }
        System.out.println("\n");
    }
    private void SearchBooks(){
        System.out.print("Enter book name to search : ");
        String bookNameTemp = in.nextLine();
        boolean bookPresent = false;
        for (short i = 0 ; i < bookNum ; i++){
            if(bookNameTemp.equalsIgnoreCase(BookName[i])){
                System.out.println("Book found!");
                System.out.println("Book number is "+i);
                bookPresent = true;
            }
            System.out.println("\n");
        }
        if(!bookPresent){
            System.out.println("Sorry no such book found,\nYou may search again using different keyword or add this book");
            System.out.print("Do you want to Search again (Y/N) : ");
            char searchChoice = in.next().charAt(0);
            in.nextLine();
            if (searchChoice == 'y' || searchChoice == 'Y'){
                SearchBooks();
            }
            else {
                System.out.print("Do you want to add Books (Y/N) : ");
                char addChoice = in.next().charAt(0);
                if (addChoice == 'y' || addChoice == 'Y'){
                    in.nextLine();
                    AddBook();
                }
            }
        }
    }
    private void IssueBook() {
        System.out.print("Enter Enrollment number of Student : ");
        String tempEnroll = in.nextLine();
        short tempSID = 999;
        for (short i = 0; i < StudentIndexNum; i++) {
            if (StudentIndexNum_RegistrationDetails[i][1].equalsIgnoreCase(tempEnroll)) {
                tempSID = i;
            }
        }
        if (tempSID != 999) {
            if (StudentIndexNum_NoOfIssuedBooks[tempSID] >= 0 && StudentIndexNum_NoOfIssuedBooks[tempSID] <= 7) {
                AvailableBooks();
                System.out.print("Enter Book number to Issue : ");
                short tempBookNum = in.nextShort();
                if (tempBookNum < 999){
                    StudentIndexNum_IssuedBooks[tempSID][StudentIndexNum_NoOfIssuedBooks[tempSID]] = BookName[tempBookNum];
                    BookQuantity[tempBookNum] -= 1;
                    StudentIndexNum_NoOfIssuedBooks[tempSID]+=1;
                    UpdateBooks(tempBookNum);
                }
            }
            else {
                System.out.println("This Student is not registered.\n");
            }
            System.out.println("\n");
        }
    }
    private void RegisterStudent(){
        try {
            System.out.print("Enter Name : ");
            StudentIndexNum_RegistrationDetails[StudentIndexNum][0] = in.nextLine();
            System.out.print("Enter Enrollment number : ");//Unique Identity of each and every Student
            StudentIndexNum_RegistrationDetails[StudentIndexNum][1] = in.nextLine();
            System.out.print("Enter Branch : ");
            StudentIndexNum_RegistrationDetails[StudentIndexNum][2] = in.nextLine();
            System.out.print("Enter Father's name : ");
            StudentIndexNum_RegistrationDetails[StudentIndexNum][3] = in.nextLine();
            System.out.print("Enter Contact Number : ");
            StudentIndexNum_RegistrationDetails[StudentIndexNum][4] = in.nextLine();
            System.out.print("Enter Batch : ");
            StudentIndexNum_RegistrationDetails[StudentIndexNum][5] = in.nextLine();

            StudentIndexNum++;
        }
        catch (Exception e){
            System.out.println("It Seems that you have given wrong input!!\nTry Again!");
            RegisterStudent();
        }
        System.out.println("\n");
    }
    private void UpdateBooks(short tempBookNum){
            if (BookQuantity[tempBookNum]==0){
                for (short i = tempBookNum ; i < bookNum ; i++){
                    BookName[tempBookNum] = BookName[tempBookNum+1];
                    BookQuantity[tempBookNum] = BookQuantity[tempBookNum+1];
                }
                bookNum--;
        }
    }
    private void AdvanceMenu() {
        System.out.println("Enter 1 to See Issued Books After Enrollment Number.");
        System.out.println("Enter 2 to see All Students full Details.");
        System.out.print("Your Choice : ");
        byte choice = in.nextByte();
        switch (choice) {
            case 1 -> {
                SeeIssuedBooksAfterEnrollmentNum();
                System.out.println("\n");
                HomePage();
            }
            case 2 -> {
                SeeAllDetailsOfAllStudents();
                System.out.println("\n");
                HomePage();
            }
            case 3 -> {
                SeeAllDetailsOfSpecificStudent();
                System.out.println("\n");
                HomePage();
            }
        }
    }


    private void SeeIssuedBooksAfterEnrollmentNum(){
        in.nextLine();
        for (short i = 0 ; i < 999 ; i++){
            if (StudentIndexNum_NoOfIssuedBooks[i] != 0){
                System.out.println(StudentIndexNum_RegistrationDetails[i][1]+" holds "+StudentIndexNum_NoOfIssuedBooks[i]+" Books");
                for (short j = 0 ; j < StudentIndexNum_NoOfIssuedBooks[0] ; j++){
                    System.out.println("\t"+j+1+" -> "+StudentIndexNum_IssuedBooks[i][j]);
                }
            }
        }
    }
    private void SeeAllDetailsOfAllStudents(){
        for (short i = 0 ; i < StudentIndexNum ; i++) {
            System.out.println("\tName : " + StudentIndexNum_RegistrationDetails[i][0]);
            System.out.println("\tEnrollment number : " + StudentIndexNum_RegistrationDetails[i][1]);
            System.out.print("\tBranch : " + StudentIndexNum_RegistrationDetails[StudentIndexNum][2]);
            System.out.print("\tFather's name : " + StudentIndexNum_RegistrationDetails[StudentIndexNum][3]);
            System.out.print("\tContact Number : " + StudentIndexNum_RegistrationDetails[StudentIndexNum][4]);
            System.out.print("\tBatch : " + StudentIndexNum_RegistrationDetails[StudentIndexNum][5]);
            System.out.println("\n");
        }
    }
    private void SeeAllDetailsOfSpecificStudent(){
        System.out.print("Enter Enrollment number of Student");
        String tempEnrollmentNum = in.nextLine();
        boolean IsStudentRegistered = false;
        for (short i = 0 ; i < StudentIndexNum ; i++){
            if (StudentIndexNum_RegistrationDetails[i][1].equalsIgnoreCase(tempEnrollmentNum)){
                IsStudentRegistered = true;
                System.out.println("\tName : " + StudentIndexNum_RegistrationDetails[i][0]);
                System.out.println("\tEnrollment number : " + StudentIndexNum_RegistrationDetails[i][1]);
                System.out.print("\tBranch : " + StudentIndexNum_RegistrationDetails[StudentIndexNum][2]);
                System.out.print("\tFather's name : " + StudentIndexNum_RegistrationDetails[StudentIndexNum][3]);
                System.out.print("\tContact Number : " + StudentIndexNum_RegistrationDetails[StudentIndexNum][4]);
                System.out.print("\tBatch : " + StudentIndexNum_RegistrationDetails[StudentIndexNum][5]);

            }
            else if (!IsStudentRegistered) {
                System.out.println("This Student is not registered with us.");
            }
        }
    }
}
