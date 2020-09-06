
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
public String findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
    if (index == -1 ||(index >= input.length() - 3)) {
        break;
    }
    String found = input.substring(index+1, index+4);
    System.out.println(found);
    index = input.indexOf("abc", index+4);
    }
    return " ";
}
public void test(){
    String input="abcdabc";
    String out=findAbc(input);
    System.out.println(out);
}
}
