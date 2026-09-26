// create a new mydate object from the class in the file MyDate.Java
class ICT4705_Assignment3 {
    public static void main(String[] args) {
        MyDate date = new MyDate(26, 9, 2026);
        System.out.println("Date: " + date.getDay() + "/" + date.getMonth() + "/" + date.getYear());
    }
}