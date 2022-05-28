
//TODO: student id & name
//M104020011 王培碩
//TODO: This assignment aims to write a function simulate to allocate the course process by student’s preference.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;
import java.util.*;

import javafx.scene.layout.CornerRadii;


public class HW2 {
    
  private HW2() {}
  
  public static class Course{
    int id = 0; // course's id. ITR->1, MIS->2, DataBase->3, ResearchMethod->4
    String name; // course's name
    Student[] candidate; // The course selection result. 
    
    // Course should initial by course id and name.
    private Course(int id, String name, int limit_number) {
        this.id = id;
        this.name = name;
        this.candidate = new Student[limit_number];
    }
    
  }
  
  public static class Student{
    int year; // Student's year. freshman->1, sophomore->2, junior->3, senior ->4
    int id; // Unique student id
        //A set of student's preferences of courses id. e.g. [4,3,2,1]. The first priority of course is 4, which means ResearchMethod
    int[] preference;
    
    // Student should initial by year, id ,and candidate_courses.
    private Student(int year, int id, int[] preference) {
        this.year = year;
        this.preference = preference;
        this.id = id;
    }
    
    //overriding the toString() method
    @Override
    public String toString()
    {
        return "大"+this.year + " 學號" + this.id;
    }
  }
     
  // Test case 1
  private static void test1() {
    Course[] courses = new Course[4];
    courses[0] = new Course(1, "ITR", 3);
    courses[1] = new Course(2, "MIS", 3);
    courses[2] = new Course(3, "DataBase", 3);
    courses[3] = new Course(4, "ResearchMethod", 3);
    
    Student[] students = new Student[12];
    students[0] =  new Student(1, 11, new int[]{1, 2, 3, 4});
    students[1] =  new Student(1, 12, new int[]{1, 2, 3, 4});
    students[2] =  new Student(1, 13, new int[]{1, 2, 3, 4});
    students[3] =  new Student(2, 21, new int[]{1, 2, 3, 4});
    students[4] =  new Student(2, 22, new int[]{1, 2, 3, 4});
    students[5] =  new Student(2, 23, new int[]{1, 2, 3, 4});
    students[6] =  new Student(3, 31, new int[]{1, 2, 3, 4});
    students[7] =  new Student(3, 32, new int[]{1, 2, 3, 4});
    students[8] =  new Student(3, 33, new int[]{1, 2, 3, 4});
    students[9] =  new Student(4, 41, new int[]{1, 2, 3, 4});
    students[10] =  new Student(4, 42, new int[]{1, 2, 3, 4});
    students[11] =  new Student(4, 43, new int[]{1, 2, 3, 4});
    
    System.out.println("Test case1:");
    long startTime = System.nanoTime();
    Course[] result = simulate(students, courses);
    long estimatedTime = System.nanoTime() - startTime;
    print_course(result);
    print_first_priority(result);
    System.out.println("Performance(time): "+estimatedTime);
  }
  
  // Test case 2
  private static void test2() {
    Course[] courses = new Course[3];
    courses[0] = new Course(1, "ITR", 6);
    courses[1] = new Course(2, "MIS", 2);
    courses[2] = new Course(3, "DataBase", 4);
    
    Student[] students = new Student[12];
    students[0] =  new Student(1, 11, new int[]{1, 2, 3});
    students[1] =  new Student(1, 12, new int[]{1, 2, 3});
    students[2] =  new Student(1, 13, new int[]{1, 2, 3});
    students[3] =  new Student(2, 21, new int[]{1, 2, 3});
    students[4] =  new Student(2, 22, new int[]{1, 2});
    students[5] =  new Student(2, 23, new int[]{2, 1, 3});
    students[6] =  new Student(3, 31, new int[]{1, 2, 3});
    students[7] =  new Student(3, 32, new int[]{1, 2, 3});
    students[8] =  new Student(3, 33, new int[]{1, 2, 3});
    students[9] =  new Student(4, 41, new int[]{});
    students[10] =  new Student(4, 42, new int[]{1, 2, 3});
    students[11] =  new Student(4, 43, new int[]{1, 2, 3});
    
    System.out.println("Test case2:");
    long startTime = System.nanoTime();
    Course[] result = simulate(students, courses);
    long estimatedTime = System.nanoTime() - startTime;
    print_course(result);
    print_first_priority(result);
    System.out.println("Performance(time): "+estimatedTime);
  }
  
  // Abstract test case 3. Prepare for hidden test case.
  // It is normal that the function is no code.
  private static void test3() {
    /*Course[] courses = new Course[3];
    courses[0] = new Course(1, "ITR", 6);
    courses[1] = new Course(2, "MIS", 2);
    courses[2] = new Course(3, "DataBase", 4);
    
    Student[] students = new Student[12];
    students[0] =  new Student(1, 11, new int[]{1, 2, 3});
    students[1] =  new Student(1, 12, new int[]{1, 2, 3});
    students[2] =  new Student(1, 13, new int[]{1, 2, 3});
    students[3] =  new Student(2, 21, new int[]{1, 2, 3});
    students[4] =  new Student(2, 22, new int[]{1, 2});
    students[5] =  new Student(2, 23, new int[]{2, 1, 3});
    students[6] =  new Student(3, 31, new int[]{2, 1, 3});
    students[7] =  new Student(3, 32, new int[]{2, 1, 3});
    students[8] =  new Student(3, 33, new int[]{2, 1});
    students[9] =  new Student(4, 41, new int[]{});
    students[10] =  new Student(4, 42, new int[]{2, 3, 1});
    students[11] =  new Student(4, 43, new int[]{1, 2, 3});
    
    System.out.println("Test case3:");
    long startTime = System.nanoTime();
    Course[] result = simulate(students, courses);
    long estimatedTime = System.nanoTime() - startTime;
    print_course(result);
    print_first_priority(result);
    System.out.println("Performance(time): "+estimatedTime);*/
  }
  
  // TODO: write your code in this function
  // Simulate courses allocating process
  private static Course[] simulate(Student[] students, Course[] courses) {
    ArrayList<Student> studentp = new ArrayList<>();   //有preferences的學生
    ArrayList<Student> studentn = new ArrayList<>();   //沒preferences的學生

    for (int i=0; i<students.length; i++){  //for迴圈 將所有的學生從陣列提取出來
      if (students[i].preference.length!=0) //如果學生是有preference的寫入到「studentp」ArrayList中
        studentp.add(students[i]);
      else                                  //學生沒有preference的則寫入到「studentp」ArrayList中
        studentn.add(students[i]);
    }

    //System.out.println("studentp："+studentp);    //顯示排序前的陣列資料(有Perference)。
    //System.out.println("studentn："+studentn);    //顯示排序前的陣列資料(沒Perference)。
    
    Collections.sort(studentp, new Comparator<Student>(){   //處理有填Perference的學生並進行排序。
      public int compare(Student a, Student b){
        if (Integer.compare(a.year, b.year) == 0)   //比較年級(year)，年級為降序排序。a比b大為1；b比a大為-1；a=b為0。表示同年級，則需進一步比較學號(id)
          return Integer.compare(a.id, b.id);       //比較學號(id)，學號為升序排序。a比b大為1；b比a大為-1；a=b為0。
        else
          return Integer.compare(b.year, a.year);   //當不為同年級(year)，年級採用降序排序。a比b大為1；b比a大為-1；a=b為0。
      }
    });

    Collections.sort(studentn, new Comparator<Student>(){   //處理沒填Perference的學生並進行排序。
      public int compare(Student a, Student b){
        if (Integer.compare(a.year, b.year) == 0)   //比較年級(year)，年級為降序排序。a比b大為1；b比a大為-1；a=b為0。表示同年級，則需進一步比較學號(id)
          return Integer.compare(a.id, b.id);       //比較學號(id)，學號為升序排序。a比b大為1；b比a大為-1；a=b為0。
        else
          return Integer.compare(b.year, a.year);   //當不為同年級(year)，年級採用降序排序。a比b大為1；b比a大為-1；a=b為0。
      }
    });
    
    //System.out.println("sorted-studentp："+studentp);  //顯示排序之後的陣列資料(有Perference)。
    //System.out.println("sorted-studentn："+studentn);  //顯示排序之後的陣列資料(沒Perference)。

    studentp.addAll(studentn);   //將有填Perference和沒填Perference的ArrayList進行合併。
    //System.out.println("com-studentp："+studentp);  //顯示合併之後的陣列資料。


    for(int i=0; i<courses.length; i++){    //總共有幾堂課
      
      int join = 0;
      int stpn = 0;
      int checkadd = 0;
      Object objn = null;
      //System.out.println("修課人數："+join);
      //System.out.println("限修人數："+courses[i].candidate.length);
      while (join<courses[i].candidate.length){                      //當入選課程的人數未達上限，則繼續執行
        //System.out.println("志願數："+(studentp.get(stpn).preference.length));
        for (int p=0; p<=studentp.get(stpn).preference.length;p++){    //將排序好的人的志願序跑過
          //System.out.println("P："+p);
          //System.out.println("課程編號："+courses[i].id);
          //System.out.println("志願編號："+studentp.get(stpn).preference[p]);
          //System.out.println("STPN："+stpn);
          //System.out.println("P長度："+studentp.get(stpn).preference.length);
          if (studentp.get(stpn).preference.length==0) objn = "1";
          if (objn == "1"){                                            //對於課程排序沒有意見的同學
            courses[i].candidate[join]=studentp.get(stpn);              //如果相符，將此學生加入課程
            //System.out.println("i："+i);
            //System.out.println("學生資料："+studentp.get(stpn));
            studentp.remove(stpn);                                      //學生加入課程後，從ArrayList中移除
            //System.out.println("studentn："+studentp+"\n");
            join++;                                                     //課程人數紀錄+1
            checkadd = 0;
            break;
          }
          if (courses[i].id == studentp.get(stpn).preference[p]){       //確認是否跟課程id有相符
            courses[i].candidate[join]=studentp.get(stpn);              //如果相符，將此學生加入課程
            //System.out.println("i："+i);
            //System.out.println("學生資料："+studentp.get(stpn));
            studentp.remove(stpn);                                      //學生加入課程後，從ArrayList中移除
            //System.out.println("studentp："+studentp+"\n");
            join++;                                                     //課程人數紀錄+1
            checkadd = 0;
            break;                                                      //跳出迴圈
          }
          else if (p==studentp.get(stpn).preference.length) checkadd = 1;  //跑過所有的志願都沒有符合該學生的課程
          else if (courses[i].id < studentp.get(stpn).preference[p]){
            checkadd = 1;
            //System.out.println("跳出迴圈");
            break;
          }else{                                                        //如果填寫志願序的課程都被選滿了則執行
            courses[i].candidate[join]=studentp.get(stpn);              //如果相符，將此學生加入課程
            //System.out.println("i："+i);
            //System.out.println("學生資料："+studentp.get(stpn));
            studentp.remove(stpn);                                      //學生加入課程後，從ArrayList中移除
            //System.out.println("studentp："+studentp+"\n");
            join++;                                                     //課程人數紀錄+1
            checkadd = 0;
            break;                                                      //跳出迴圈
          }
        }
        if (checkadd == 1) stpn++;
      }
      join=0;
    }
    return courses;
  }

  
  // helper function
  // print result of allocating the student to course
  private static void print_course(Course[] courses) {
    for(Course course:courses) {
        System.out.print(course.name+" : ");
        System.out.println(Arrays.toString(course.candidate));
    }
  }
  
  // helper function
  // Calculate the number of students who meet his first priority.
  private static void print_first_priority(Course[] courses) {
    int count = 0;
    for(Course course:courses) {
        for(Student one:course.candidate) {
            if (one != null &&  one.preference.length>0 && one.preference[0] == course.id) {
                count++;
            }
        }
    }
    System.out.println("Students satisfaction: "+ count);
  }


  public static void main(String[] args) {    
      test1();
//    test1 expected output: 
//    ITR : [大4 學號41, 大4 學號42, 大4 學號43]
//      MIS : [大3 學號31, 大3 學號32, 大3 學號33]
//      DataBase : [大2 學號21, 大2 學號22, 大2 學號23]
//      ResearchMethod : [大1 學號11, 大1 學號12, 大1 學號13]
//      Students satisfaction: 3
//      Performance(time): XXXX
      test2();
//    test2 expected output:
//    ITR : [大4 學號42, 大4 學號43, 大3 學號31, 大3 學號32, 大3 學號33, 大2 學號21]
//      MIS : [大2 學號22, 大2 學號23]
//      DataBase : [大1 學號11, 大1 學號12, 大1 學號13, 大4 學號41]
//      Students satisfaction: 7
//      Performance(time): XXXX
      test3(); // hidden test case 3
  }
}

/*
作業想法討論：M104020014 周紘樟、M104020026 陳亭椰

參考資料：
https://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
https://ithelp.ithome.com.tw/articles/10229625
https://vimsky.com/zh-tw/examples/usage/collections-sort-java-examples.html
https://bbs.csdn.net/topics/330093952
*/
