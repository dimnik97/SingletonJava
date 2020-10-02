package org.example;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class FileChangerTest {
    @Test
    public void testClearOrCreateFile() throws Exception {
        String path = "resources/test.json";
        FileChanger fileChanger = new FileChanger(path);
        fileChanger.clearOrCreateFile();
        File file = new File(path);
        Assert.assertTrue(file.exists());

        File fileToRead = new File(path);
        FileInputStream fis = new FileInputStream(fileToRead);
        byte[] data = new byte[(int) fileToRead.length()];
        fis.read(data);
        fis.close();
        String fileContent = new String(data, "UTF-8");

        Assert.assertEquals(fileContent, "");
        file.delete();
    }

    @Test
    public void testWriteAndRead() {
        String path = "resources/test.json";
        FileChanger fileChanger = new FileChanger(path);

        Map<String, String> map = new HashMap<>();
        map.put("key1", "element 1");

        Gson gson = new Gson();
        byte[] bytes = gson.toJson(map).getBytes();

        fileChanger.writeToFile(bytes);
        Map result = fileChanger.readJsonFromFileAsMap();

        File file = new File(path);
        file.delete();
        Assert.assertEquals(map, result);

    }
}
