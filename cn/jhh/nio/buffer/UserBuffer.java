package cn.jhh.nio.buffer;
import java.nio.IntBuffer;

class UserBuffer{
    static IntBuffer intBuffer = null;
    void init(int cap){
        intBuffer = IntBuffer.allocate(cap);
        show();
        System.out.println();
    }

    public void show(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("position:"+intBuffer.position());
        System.out.println("limit:"+intBuffer.limit());
        System.out.println("cap:"+intBuffer.capacity());
    }

    public void write(int num){
        if(num > intBuffer.capacity()){
            return;
        }
        for(int i = 0; i < num; i++){
            intBuffer.put(i);
            // show();
            // System.out.println();
        }
        show();
        System.out.println();
    }

    public void flip(){
        intBuffer.flip();
        show();
        System.out.println();
    }

    public void read() throws InterruptedException{
        for(int i = 0; i < 2; i++ ){
            System.out.println(intBuffer.get());

        }
        System.out.println("after read 2:");
        show();
        for(int i = 0; i < 3; i++ ){
            System.out.println(intBuffer.get());

        }
        System.out.println("after read 3:");
        show();
    }

    public static void main(String[] args) throws InterruptedException {
        UserBuffer userBuffer = new UserBuffer();
        userBuffer.init(20);

        userBuffer.write(5);

        userBuffer.flip();

        userBuffer.read();
    
    }
}