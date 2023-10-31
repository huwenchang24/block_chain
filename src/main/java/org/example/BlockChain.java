package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.FileReader;


import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class BlockChain {



    private List<Block> blocks = new ArrayList<>();
    private String current_hash;

    private int height;



    public List<Block> getBlocks() {
        return blocks;
    }

    public String getCurrent_hash() {
        return current_hash;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "BlockChain{" +
                "blocks=" + blocks +
                ", current_hash='" + current_hash + '\'' +
                ", height=" + height +
                '}';
    }

    public BlockChain(){}
    public void init(){
        this.blocks = new ArrayList<Block>();
        this.height = 0;
    };
    //添加区块，传入一个block对象，加入blocks数组并更新current_hash和height
    public void add_block(Block block){
        this.blocks.add(block);
        this.current_hash = block.set_hash();
        this.height = this.height + 1;
    }

    //采用使用json/xml等结构化格式文件存储和读取区块链（以其他方式存储文件也需要体现一定的结构性）
    public void save_blockchain(){
        // 创建Gson对象
        Gson gson = new Gson();
        // 将Java对象转化为JSON字符串
        String json = gson.toJson(this);


        // 指定要写入的文件路径
        String filePath = "data.json";
        try {
            // 创建文件对象
            File file = new File(filePath);

            // 创建文件写入器
            FileWriter fileWriter = new FileWriter(file);

            // 创建缓冲写入器
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // 写入JSON数据到文件
            bufferedWriter.write(json);

            // 关闭写入器
            bufferedWriter.close();

            System.out.println("JSON数据已成功写入文件: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read_blockchain(){
        // 指定要读取的JSON文件路径
        String filePath = "data.json";

        try {
            // 创建文件读取器
            FileReader fileReader = new FileReader(filePath);

            // 创建缓冲读取器
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // 使用Gson库将JSON数据从文件读取到Java对象
            Gson gson = new Gson();
            BlockChain blockChain = gson.fromJson(bufferedReader, BlockChain.class);

            // 关闭读取器
            bufferedReader.close();

            // 打印读取的对象
            System.out.println("读取的JSON对象");
            System.out.println(blockChain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException | JsonIOException e) {
            e.printStackTrace();
        }
    }



}
