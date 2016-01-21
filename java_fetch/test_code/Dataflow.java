import DataflowPart2.class_static_var1;

public class Dataflow{
    /* static part */
    static int class_static_var1 = 10;

    public static void main(String[] args) {
        System.out.println("Hello!");

        Dataflow flow1 = new Dataflow();
        flow1.func1(20);
    }

    /* class instance definition */
    int class_member1;
    
    public Dataflow(){
        this.class_member1 = 5;
    }

    void func1(int parameter1){
        int local_var1 = 1;

        int result;
        result = class_static_var1;
        System.out.println(result);
        result = this.class_member1;
        System.out.println(result);
        result = local_var1;
        System.out.println(result);
        result = parameter1;
        System.out.println(result);

        /* external class */
        result = DataflowPart2.class_static_var1;
        System.out.println(result);
        DataflowPart2 flow2 = new DataflowPart2();
        result = flow2.class_member1;
        System.out.println(result);

        /* TODO: object reference */
        /* TODO: deep copy */
    }
}
