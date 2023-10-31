package org.example;



//ZHUSHI
//11223
//交易结构体
public class Transaction {
    private String ID;
    private String Vin;
    private String Vout;

    @Override
    public String toString() {
        return "Transaction{" +
                "ID='" + ID + '\'' +
                ", Vin='" + Vin + '\'' +
                ", Vout='" + Vout + '\'' +
                '}';
    }

    public Transaction(){
        this.ID = randomString(4);
        this.Vin = randomString(4);
        this.Vout = randomString(4);
    }

    public String randomString(int e) {
        String t = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
        int a = t.length();
        String n = "";
        for (int i = 0; i < e; i++)
            n += t.charAt((int) Math.floor(Math.random() * a));
        return n;
    }

}
