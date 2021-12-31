package fourbottle;

import java.util.*;

class Bottle implements Cloneable{
    int capacity;
    int volume;

    public Bottle(int capacity, int volume) {
        this.capacity = capacity;
        this.volume = volume;
    }

    public boolean PourTo(Bottle bottle) {
        if (this.volume <= 0) {
            return false;
        }
        if (bottle.volume >= bottle.capacity) {
            return false;
        }
        bottle.volume += this.volume;
        if (bottle.volume >= bottle.capacity) {
            this.volume = bottle.volume - bottle.capacity;
            bottle.volume = bottle.capacity;
        } else {
            this.volume = 0;
        }
        return true;
    }
    @Override
    public Bottle clone() throws CloneNotSupportedException {
        Bottle bottle = new Bottle(this.capacity, this.volume);
        return bottle;
    }
}

public class FourBottle implements Comparable<FourBottle> , Cloneable {
    public FourBottle pre;
    Bottle[] bottles;

    FourBottle(Bottle[] bottles) {
        this.bottles = bottles;
        pre = null;
    }
    void Print()
    {
        int i;
        for(i=0;i<bottles.length;i++)
        {
            System.out.print(bottles[i].volume);
            System.out.print(" ");
        }
        System.out.println(" ");
    }
    @Override
    public int compareTo( FourBottle fourBottle) {
        for (int i = 0; i < bottles.length; i++) {
            if (fourBottle.bottles[i].volume != bottles[i].volume) {
                return fourBottle.bottles[i].volume-bottles[i].volume;
            }
        }
        return 0;
    }
    @Override
    public FourBottle clone() throws CloneNotSupportedException {
        Bottle[] bottles = new Bottle[this.bottles.length];
        int i;
        for(i=0;i<this.bottles.length;i++)
        {
            bottles[i] = this.bottles[i].clone();
        }
        return new FourBottle(bottles);
    }

    public static void main(String[] args) {
        TreeSet<FourBottle> S = new TreeSet<>();
        FourBottle FB = new FourBottle(new Bottle[]{new Bottle(50, 50),
                                                    new Bottle(7, 0),
                                                    new Bottle(3, 0),
                                                    new Bottle(3, 0)});
        Queue<FourBottle> Q = new LinkedList<>();
        S.add(FB);
        Q.offer(FB);
        while(Q.size()!=0)
        {
            FourBottle FBH = Q.poll();
            int i,j;
            for(i=0;i<FBH.bottles.length;i++)
            {
                for(j=0;j<FBH.bottles.length;j++)
                {
                    if(i==j)
                    {
                        continue;
                    }
                    FourBottle T = null;
                    try{ T = FBH.clone();}
                    catch (CloneNotSupportedException e)
                    {
                        T = null;
                        return;
                    }
                    if(T.bottles[i].PourTo(T.bottles[j]))
                    {
                        if(T.bottles == FBH.bottles)
                        {
                            System.out.println("bottles is equal");
                            return;
                        }
                        if(T.bottles[0] == FBH.bottles[0])
                        {
                            System.out.println("bottle is equal");
                            return;
                        }
                        T.pre = FBH;
                        if(S.add(T))
                        {
                            if(Q.size()>1000)
                            {
                                return;
                            }
                            Q.offer(T);
                        }
                        if(T.bottles[0].volume==5 && T.bottles[1].volume ==5)
                        {
                            while(T != null)
                            {
                                T.Print();
                                T = T.pre;
                            }
                            return;
                        }
                    }
                }
            }
        }
    }
}
