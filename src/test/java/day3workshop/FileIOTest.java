package day3workshop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FileIOTest {
    String dirPath = "data2";
    String stringLogin = "write.txt";
    String filePathName = dirPath + File.separator + stringLogin;

    @Test
    public void createDirectory(){
        File newDirectory = new File(dirPath);
        if(newDirectory.exists()){
            System.out.println("Directory exists!");
        } else {newDirectory.mkdir();}
    }

    @Test
    public void appendDataUsingBufferWriter() throws IOException{
        String str = "The fox jumped up";
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePathName,true));
        bw.write("\n");
        bw.write(str);
        bw.close();

    }

    @Test
    public void deleteWriteTextFile() throws IOException{
        File file = new File(filePathName);
        if(file.exists()){
            Boolean isDeleted = file.delete();
        }
    }
    
}
