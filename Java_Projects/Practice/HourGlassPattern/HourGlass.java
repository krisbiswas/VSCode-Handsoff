package Practice.HourGlassPattern;

class HourGlass{
    public static void main(String[] args) {
        System.out.println("Lets begin");
        int count = 9;
        for(int i=1;i<=count;i++){
            int j=1;
            for(;j<i;j++){
                System.out.print(" ");
            }
            for(;j<=count;j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        for(int i=count-1;i >= 1;i--){
            int j=1;
            for(;j<i;j++){
                System.out.print(" ");
            }
            for(int k=count;k>=j;k--){
                System.out.print(k+" ");
            }
            System.out.println();
        }
    }
}