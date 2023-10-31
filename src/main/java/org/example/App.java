package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        BlockChain blockChain = new BlockChain();

        for (int i = 0; i<5; i++)
        {
            List<Transaction> transactions = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                transactions.add(new Transaction());
            }
            Block block = new Block();
            block = block.new_block(transactions, blockChain.getCurrent_hash(), blockChain.getHeight());
            blockChain.add_block(block);
        }

        blockChain.save_blockchain();
        System.out.println("test1");
        System.out.println("test2");
        System.out.println("test3");
        blockChain.read_blockchain();

    }
}
