import java.util.*;
import java.lang.*;
import java.io.*;


class Codechef
{
    public static int vaccine(int D1,int V1,int D2,int V2,int P){
        int sum=0,temp=0,count=0;
        while(sum<=P){
            if(D1==D2){
                temp=V1+V2;
                sum+=temp;
                count++;
            }
            if(D2<D1){
                for(int i=D2;i<D1;i++){
                    sum+=V2;
                    count++;
                    D2++;
                }
            }
            if(D1<D2){
                for(int j=D1;j<D2;j++){
                    sum+=V1;
                    count++;
                    D1++;
                }
            }
        }
        if(sum>P)
            return count;
        while(sum<P) {
            temp = V1 + V2;
            sum += temp;
            count++;
        }
        return 0;
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        System.out.println(Integer.parseInt("000001000"));
    }
}
