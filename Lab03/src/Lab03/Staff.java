package Lab03;

public class Staff{
    private String name;
    private int age;
    private String department;
    private int workDays;
    private int vacationDays;
    public Staff(String name,int age) {
       this.name=name;
       this.age=age;
       department="None";
       workDays=0;
       vacationDays=20;
    }
    public Staff(String name,int age, String department, int workDays, int vacationDays) {
       this.name=name;
       this.age=age;
       this.department=department;
       this.workDays=workDays;
       this.vacationDays=vacationDays;
    }
    public String getName(Staff staff) {
       return staff.name;
    }
    public void setCareer(Staff staff) {
       this.department=staff.department;
       this.workDays=staff.workDays;
    }
    public boolean sameCareer(Staff anotherStaff) {
       if(this.department.equals(anotherStaff.department)&&this.workDays==anotherStaff.workDays) {
          System.out.println(name+" and "+getName(anotherStaff)+", same.");
          System.out.println("---");
          return true;
       }
       else {
          System.out.println(name+" and "+getName(anotherStaff)+", Not exactly same.");
          System.out.println("...A Few years later...");
          return false;
       }
    }
    public String toString() {
       return "Name: "+name+", Age: "+age+", Department: "+department+", workDays: "+workDays+", vacationDays: "+vacationDays;
    }
    public void vacation(int vacationDays) {
       if(this.vacationDays<vacationDays) {
          System.out.println(name+", 남은 휴가 일 수 부족.");
       }
       else {
          this.vacationDays-=vacationDays;
          System.out.println(name+", 휴가 "+vacationDays+" 사용. 남은 휴가 일수:"+this.vacationDays);
       }
    }
   
 }
