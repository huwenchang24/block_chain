package org.example;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import java.nio.charset.StandardCharsets;
import java.security.Security;

import java.util.List;
import java.sql.Timestamp;
public class Block {
    private Timestamp Times;

    private List<Transaction> Transactions;
    private String PrevBlockHash;

    private String Hash;

    private int Nonce;

    private int Height;

    @Override
    public String toString() {
        return "Block{" +
                "Times=" + Times +
                ", Transactions=" + Transactions +
                ", PrevBlockHash='" + PrevBlockHash + '\'' +
                ", Hash='" + Hash + '\'' +
                ", Nonce=" + Nonce +
                ", Height=" + Height +
                '}';
    }



    //构造函数
    public Block(){
        this.Times = new Timestamp(System.currentTimeMillis());
        this.Hash = this.set_hash();
        this.Nonce = 1;

    }

    public Block new_block(List<Transaction> Transactions, String PrevBlockHash, int Height){
        this.Transactions = Transactions;
        this.PrevBlockHash = PrevBlockHash;
        this.Height = Height;
        return this;
    }

    //计算当前block的哈希值，计算方法如下：将block的Timestamp和PrevBlockHash链接成字符串，对其使用sha256函数取哈希值
    public String set_hash(){

        // 添加Bouncy Castle提供者
        Security.addProvider(new BouncyCastleProvider());

        // 创建SHA-256哈希计算对象
        Digest sha256 = new SHA256Digest();

        // 输入消息
        String message = this.Times + this.PrevBlockHash;
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // 更新哈希计算
        sha256.update(messageBytes, 0, messageBytes.length);

        // 计算哈希值
        byte[] hash = new byte[sha256.getDigestSize()];
        sha256.doFinal(hash, 0);
        // 将哈希值转换为十六进制字符串
        String hashHex = new String(Hex.encode(hash));
        return hashHex;
    }


}
